package com.example.gravitysensor;
import java.util.stream.IntStream;
public class Neuron {
    private double[] weights=null;
    private double output=0;
    private double error=0;
    private Layer.LayerType layerType=null;

    public Neuron(Layer.LayerType layerType,int numbOfWeights) {
        this.layerType = layerType;
        if(layerType!=Layer.LayerType.I){
            weights=new double[numbOfWeights];
            for(int x=0;x<numbOfWeights;x++){weights[x]=0.5-Math.random();}

        }
    }





    public double applyActivationFunction(double weightedSum){//Sigmoid function
        output=1.0/(1+Math.exp(-1.0*weightedSum));
        return output;
    }
    public double derivation(){return output*(1.0-output);}


    //setter and getter
    public double[] getWeights(){return weights;}
    public void setWeights(double[] weights){this.weights=weights;}
    public double getOutput(){return output;}
    public void setOutput(double output){this.output=output;}
    public double getError() {return error;}
    public void setError(double error) { this.error = error; }

    public String toString(){
        StringBuffer returnValue =new StringBuffer("("+layerType+", ");
        if (layerType==Layer.LayerType.I)returnValue.append(String.format("%.2f",output)+")");
        else{
            for(int x=0;x<weights.length;x++){returnValue.append(String.format("%.2f",weights[x])+", ");}

            if(layerType==Layer.LayerType.H)returnValue.append(String.format("%.2f", output)+")");
            else returnValue.append(String.format("%.5f", output)+")");
        }
        return returnValue.toString();
    }


}
