package com.example.gravitysensor;

import android.app.Dialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Dialog dialog;//popup視窗


    public static double NEW_DATAXUP[][][] = new double[][][]{{{0, 0, 0, 0}, {0}}};
    public static double NEW_DATAYUP[][][] = new double[][][]{{{0, 0, 0, 0}, {0}}};
    public static double NEW_DATAZUP[][][] = new double[][][]{{{0, 0, 0, 0}, {0}}};


    //private static final String TAG = "MainActivity";

    private SensorManager sensorManagerG;
    private Sensor GyosensorManager;


    Button Start;
    Button Stop;
    Button Clean;

    List listC;
    List listT;
    List listGX;
    List listGY;
    List listGZ;
    List listGC;
    List listGT;



    List GZupindex;
    List GXupindex;
    List GYupindex;

    NeuralNetwork neuralNetwork;
    NeuralNetwork2 neuralNetwork2;

    private int savecount = 0;

    public SharedPreferences.Editor editorGX;
    public SharedPreferences.Editor editorGY;
    public SharedPreferences.Editor editorGZ;
    public SharedPreferences.Editor editorGC;
    public SharedPreferences sharedPreGX;
    public SharedPreferences sharedPreGY;
    public SharedPreferences sharedPreGZ;
    public SharedPreferences sharedPreGCount;
    public SharedPreferences sharedPreT;
    public SharedPreferences.Editor editorT;


    public Calendar calendar;
    public SimpleDateFormat sdf;
    public Date date;

    TextView txtlifttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.liftpopout);

        txtlifttime=(TextView)dialog.findViewById(R.id.lifttext);
        neuralNetwork = new NeuralNetwork(8);//HiddenNeuronAmount
        neuralNetwork2 = new NeuralNetwork2(8);//HiddenNeuronAmount


        Start = (Button) findViewById(R.id.startb);
        Stop = (Button) findViewById(R.id.stopb);

        Clean = (Button) findViewById(R.id.saveb);

        listC = new ArrayList<Float>();
        listT = new ArrayList<>();
        listGX = new ArrayList<Float>();
        listGY = new ArrayList<Float>();
        listGZ = new ArrayList<Float>();
        listGC = new ArrayList<Float>();
        listGT = new ArrayList<>();
        GZupindex = new ArrayList<>();
        GYupindex = new ArrayList<>();
        GXupindex = new ArrayList<>();




        sharedPreT = this.getSharedPreferences("SensorDataT", MODE_PRIVATE);
        editorT = sharedPreT.edit();
        sharedPreGX = this.getSharedPreferences("SensorDataGX", MODE_PRIVATE);
        editorGX = sharedPreGX.edit();
        sharedPreGY = this.getSharedPreferences("SensorDataGY", MODE_PRIVATE);
        editorGY = sharedPreGY.edit();
        sharedPreGZ = this.getSharedPreferences("SensorDataGZ", MODE_PRIVATE);
        editorGZ = sharedPreGZ.edit();
        sharedPreGCount = this.getSharedPreferences("SensorDataGC", MODE_PRIVATE);
        editorGC = sharedPreGCount.edit();



        sensorManagerG = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        GyosensorManager = sensorManagerG.getDefaultSensor(TYPE_GYROSCOPE);

        Clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                Restart();
            }
        });


        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                sensorManagerG.unregisterListener(MainActivity.this, GyosensorManager);
                saveSensorData();
                LoagDataG();
                initialize();
            }
        });

    }





    public void initialize(){
        editorGX.clear();
        editorGX.commit();
        editorGY.clear();
        editorGY.commit();
        editorGZ.clear();
        editorGZ.commit();
        editorGC.clear();
        editorGC.commit();
    }

    public void LoagDataG() {
        Map<String, ?> countmap = sharedPreGCount.getAll();//以下取出count原本沒有用Gcount
        Set countset = countmap.keySet();
        List countarray = Arrays.asList(countset.toArray());
        Collections.sort(countarray, new Comparator() {//將count排序
            @Override
            public int compare(Object o1, Object o2) {
                return -(Integer.valueOf(o1.toString()).compareTo(Integer.valueOf(o2.toString())));
            }
        });
        int Gcount = 0;
        for (int i = 0; i < countarray.size(); i++) {
            Gcount = sharedPreGCount.getInt(String.valueOf(countarray.get(i)), 99);
            Float GXvalue = sharedPreGX.getFloat(String.valueOf(countarray.get(i)), 99);
            Float GYvalue = sharedPreGY.getFloat(String.valueOf(countarray.get(i)), 99);
            Float GZvalue = sharedPreGZ.getFloat(String.valueOf(countarray.get(i)), 99);

            listGC.add(i, Gcount);//index=原先資料count-1
            listGX.add(i, GXvalue);
            listGY.add(i, GYvalue);
            listGZ.add(i, GZvalue);
        }

        checkGZupbyweighted();
        checkGYupbyweighted();
        checkGXupbyweighted();
        FinalconsortUP();

    }


    private void checkGXupbyweighted() {
        for (int i = 0; i < listGX.size(); i++) {
            double[] tresult = new double[NEW_DATAXUP.length];
            if (i + 4 > listGX.size()) {
                break;
            } else {
                for (int x = 0; x < 4; x++) {
                    NEW_DATAXUP[0][0][x] = Double.valueOf(listGX.get(i + x).toString());
                }
                double tTA = 0.98;
                for (int y = 0; y < NEW_DATAXUP.length; y++) {
                    tresult[y] = neuralNetwork2.testforwardpropXup(NEW_DATAXUP[y][0])
                            .getLayers()[3].getNeuron()[0].getOutput();
                    if (tresult[y] > tTA) {
                        GXupindex.add(i);
                    }
                }
            }
        }
    }


    private void checkGYupbyweighted() {
        for (int i = 0; i < listGY.size(); i++) {
            double[] tresult = new double[NEW_DATAYUP.length];
            if (i + 4 > listGY.size()) {
                break;
            } else {
                for (int x = 0; x < 4; x++) {
                    NEW_DATAYUP[0][0][x] = Double.valueOf(listGY.get(i + x).toString());
                }
                double tTA = 0.98;
                for (int y = 0; y < NEW_DATAYUP.length; y++) {
                    tresult[y] = neuralNetwork2.testforwardpropYup(NEW_DATAYUP[y][0])
                            .getLayers()[3].getNeuron()[0].getOutput();
                    if (tresult[y] > tTA) {
                        GYupindex.add(i);
                    }
                }
            }
        }
    }


    private void checkGZupbyweighted() {
        for (int i = 0; i < listGZ.size(); i++) {
            double[] tresult = new double[NEW_DATAZUP.length];
            if (i + 4 > listGZ.size()) {
                break;
            } else {
                for (int x = 0; x < 4; x++) {
                    NEW_DATAZUP[0][0][x] = Double.valueOf(listGZ.get(i + x).toString());
                }
                double tTA = 0.98;
                for (int y = 0; y < NEW_DATAZUP.length; y++) {
                    tresult[y] = neuralNetwork2.testforwardpropZup(NEW_DATAZUP[y][0])
                            .getLayers()[3].getNeuron()[0].getOutput();
                    if (tresult[y] > tTA) {
                        GZupindex.add(i);
                    }
                }
            }
        }
    }

    public void FinalconsortUP() {
        List list2 = new ArrayList();
        for (int i = 0; i < GZupindex.size(); i++) {
            for (int j = 0; j < GYupindex.size(); j++) {
                if (GYupindex.get(j).equals(GZupindex.get(i))) {
                    for (int x = 0; x < GXupindex.size(); x++) {
                        if (GXupindex.get(x).equals(GZupindex.get(i))) {
                            list2.add(GZupindex.get(i));
                        }
                    }
                }
            }
        }
        if(list2.isEmpty()==false){
            Boolean b1=true;
            for (int n = 0; n <list2.size(); n++) {
                if (n + 2 > list2.size()) {
                    break;
                } else {
                    if (Integer.valueOf(list2.get(n + 1).toString()) - Integer.valueOf(list2.get(n).toString()) < 3){
                        list2.set(n,"?");
                    }
                }
            }
            removeDuplicate(list2);
            list2.remove("?");
        }
        txtlifttime.setText((list2.size()-1)+"~"+list2.size() + " times");
        setViewon();
    }


    public void setViewon() {
        dialog.show();
    }
    public static void removeDuplicate(List list) {
        for ( int i = 0 ; i < list.size()-1 ; i ++ )
        { for ( int j = list.size()-1 ; j > i; j--) {
            if (list.get(j).equals(list.get(i))) {
                list.remove(j); }
        }
        }
    }

    public void saveSensorData(){
        for (int i = 1; i < listGX.size(); i++) {
            editorGC.putInt(String.valueOf(i), Integer.valueOf(listGC.get(i-1).toString()));
            editorGC.commit();
            editorGX.putFloat(String.valueOf(i), Float.valueOf(listGX.get(i - 1).toString()));
            editorGX.commit();
            editorGY.putFloat(String.valueOf(i), Float.valueOf(listGY.get(i - 1).toString()));
            editorGY.commit();
            editorGZ.putFloat(String.valueOf(i), Float.valueOf(listGZ.get(i - 1).toString()));
            editorGZ.commit();
        }

        listGX.clear();
        listGY.clear();
        listGZ.clear();
        listGC.clear();
        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

    }


    public void StartSensor(View view) {
       //SensorManager.SENSOR_DELAY_UI
        sensorManagerG.registerListener(MainActivity.this, GyosensorManager, 50000000|SENSOR_STATUS_ACCURACY_MEDIUM);
        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("ss.SSSS");
        date=new Date();
        String time=sdf.format(date);
        Sensor sensor = event.sensor;
        if (sensor.getType() == TYPE_GYROSCOPE) {

            savecount++;

            listGX.add(event.values[0]);
            listGY.add(event.values[1]);
            listGZ.add(event.values[2]);
            listGC.add(savecount);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void Restart(){
        Toast.makeText(this.getBaseContext(),"已清除記錄",Toast.LENGTH_LONG).show();
        Intent intent=this.getBaseContext().getPackageManager().getLaunchIntentForPackage(this.getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }



}