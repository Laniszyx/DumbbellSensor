package com.example.gravitysensor;
import java.util.stream.IntStream;
public class NeuralNetwork {
    static final double LEARNING_RATE=0.8;
    final static int NUMB_OF_INPUT_NEURONS =8;
    final static int NUMB_OF_INPUT_NEURONS_UP =4;
    final static int NUMB_OF_OUTPUT_NEURONS = 1;
    private int numbOfHiddenNeurons;
    private Layer[] layers=new Layer[3];//原先:Layer.LayerType.values().length
    private Layer[] layers_up=new Layer[3];//原先:Layer.LayerType.values().length
    public NeuralNetwork(int numbOfHiddenNeurons){
        this.numbOfHiddenNeurons=numbOfHiddenNeurons;
        layers[0]=new Layer(this,Layer.LayerType.I);
        layers[1]=new Layer(this,Layer.LayerType.H);
        layers[2]=new Layer(this,Layer.LayerType.O);

    }


///GZ WEIGHT
    public NeuralNetwork testforwardprop(double input[]){
        double[] weightsH1=new double[]{ -0.62, -1.01, -1.97, -1.30, 2.65, 0.56, -1.07, 0.13};
        double[] weightsH2=new double[]{  -28.28, -3.63, 11.91, -3.96, -6.68, 6.88, 8.28, 0.77};
        double[] weightsH3=new double[]{ 0.20, 0.49, 0.84, 0.17, -0.22, -1.33, 0.11, -0.06 };
        double[] weightsH4=new double[]{ 0.78, 0.95, 1.02, 0.94, -1.49, -0.55, 0.39, -0.05 };
        double[] weightsH5=new double[]{3.68, -1.09, -0.43, -1.69, -3.76, 5.39, 2.07, 1.28 };
        double[] weightsH6=new double[]{ 0.13, 0.47, 0.46, -0.14, -0.60, -0.46, -0.57, 0.25  };
        double[] weightsH7=new double[]{ 1.50, 1.02, -1.83, -3.83, 4.82, 2.58, -3.86, 2.82 };
        double[] weightsH8=new double[]{ -3.81, 0.77, 1.16, 0.94, 4.06, -4.90, -1.03, -2.31 };
        double[] weightsO1=new double[]{ -1.41, -4.75, 0.47, -1.32, -6.54, -0.19, -10.46, 5.67};


        for(int i=0;i<layers.length;i++){
            switch(layers[i].getLayerType()){
                case I:
                    for(int j=0;j<layers[i].getNeuron().length;j++)
                        layers[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
                    }
                    break;
            }
        }
        return this;
    }

//    public NeuralNetwork testforwardpropZup(double input[]){
//
//        double[] weightsH1=new double[]{ 0.56, 0.63, -0.05, -0.52};
//        double[] weightsH2=new double[]{  0.60, 0.51, -0.04, -0.74};
//        double[] weightsH3=new double[]{ 0.57, 0.69, -0.37, -0.17};
//        double[] weightsH4=new double[]{  -0.61, -0.71, -0.21, 0.49 };
//        double[] weightsH5=new double[]{  0.59, 0.60, -0.17, -0.08};
//        double[] weightsH6=new double[]{  -0.77, -0.59, 0.32, -0.07 };
//        double[] weightsH7=new double[]{  -0.76, -0.20, -0.18, 0.05};
//        double[] weightsH8=new double[]{  0.34, 0.84, -0.33, -0.08 };
//        double[] weightsO1=new double[]{  1.95, 1.97, 1.55, -3.23, 1.40, -2.56, -1.54, 1.06 };
//
//
//        for(int i=0;i<3;i++){//i<layers.length=3
//            switch(layers_up[i].getLayerType()){
//                case I2:
//                    for(int j=0;j<layers_up[i].getNeuron().length;j++)
//                        layers_up[i].getNeuron()[j].setOutput(input[j]);
//                    break;
//                case H:
//                    for(int j=0;j<8;j++){
//                        double weightedSum=0;
//                        for(int k=0;k<4;k++){//weight的 數量
//                            switch(j){
//                                case 0:
//                                    weightedSum+=weightsH1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 1:
//                                    weightedSum+=weightsH2[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 2:
//                                    weightedSum+=weightsH3[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 3:
//                                    weightedSum+=weightsH4[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 4:
//                                    weightedSum+=weightsH5[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 5:
//                                    weightedSum+=weightsH6[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 6:
//                                    weightedSum+=weightsH7[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 7:
//                                    weightedSum+=weightsH8[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                    break;
//                            }
//                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
//                    }
//                    break;
//                case O:
//                    double weightedSum=0;
//                    for(int k=0;k<8;k++){
//                        switch(k){
//                            case 0:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 1:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 2:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 3:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 4:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 5:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 6:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 7:
//                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
//                                break;
//                        }
//                        layers_up[i].getNeuron()[0].applyActivationFunction(weightedSum);
//                    }
//                    break;
//            }
//        }
//        return this;
//    }


///GY WEIGHT
    public NeuralNetwork testforwardpropY(double input[]){
        double[] weightsH1=new double[]{ -0.44, -0.25, -0.58, -0.38, 0.24, -0.19, 0.28, -0.06};
        double[] weightsH2=new double[]{0.24, -0.70, 0.14, -0.55, 0.43, -0.22, 0.72, -0.00 };
        double[] weightsH3=new double[]{  0.18, -0.50, 0.01, -0.14, 0.03, 0.09, 0.79, -0.13 };
        double[] weightsH4=new double[]{  0.14, -0.22, 0.21, -0.17, -0.09, 0.12, 0.30, 0.31 };
        double[] weightsH5=new double[]{ 0.19, 0.37, 0.56, -0.20, -0.40, 0.29, -0.54, 0.20 };
        double[] weightsH6=new double[]{  0.07, -0.60, -0.30, -0.33, 0.42, 0.26, -0.21, 0.54 };
        double[] weightsH7=new double[]{  -0.60, 0.15, -0.49, -0.51, -0.26, 0.11, 0.38, -0.07};
        double[] weightsH8=new double[]{-0.28, 0.40, -0.37, 0.15, -0.12, -0.30, -0.50, -0.22 };
        double[] weightsO1=new double[]{ 1.28, 1.87, 1.90, 0.74, -0.26, 1.35, 1.11, -0.37 };


        for(int i=0;i<layers.length;i++){
            switch(layers[i].getLayerType()){
                case I:
                    for(int j=0;j<layers[i].getNeuron().length;j++)
                        layers[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
                    }
                    break;
            }
        }
        return this;
    }




    ///GX WEIGHT
    public NeuralNetwork testforwardpropX(double input[]){
        double[] weightsH1=new double[]{0.11, -0.16, 0.01, -0.11, -0.21, 0.21, -0.64, -0.55};
        double[] weightsH2=new double[]{  0.63, 0.32, 0.26, 0.08, -0.16, 0.11, -0.35, -0.02 };
        double[] weightsH3=new double[]{ -0.25, 0.40, -0.02, -0.29, 0.12, -0.30, -0.20, 0.08  };
        double[] weightsH4=new double[]{ 0.07, 0.73, 0.59, 0.31, -0.54, 0.28, -0.41, 0.17  };
        double[] weightsH5=new double[]{0.17, 0.17, -0.06, 0.26, -0.02, -0.37, -0.48, -0.40};
        double[] weightsH6=new double[]{ -0.12, 0.51, -0.05, 0.23, -0.25, -0.68, -0.17, 0.13  };
        double[] weightsH7=new double[]{0.26, 0.50, 0.00, -0.14, -0.49, 0.21, -0.17, -0.26   };
        double[] weightsH8=new double[]{  0.47, 0.25, -0.06, -0.06, 0.25, -0.02, 0.25, -0.18 };
        double[] weightsO1=new double[]{ 1.45, 1.79, 1.44, 1.60, 1.78, 1.81, 1.20, 1.09  };


        for(int i=0;i<layers.length;i++){
            switch(layers[i].getLayerType()){
                case I:
                    for(int j=0;j<layers[i].getNeuron().length;j++)
                        layers[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
                    }
                    break;
            }
        }
        return this;
    }



    public int getNumbOfHiddenNeurons() {
        return numbOfHiddenNeurons;
    }

    public Layer[] getLayers() {
        return layers;
    }

    public String toString(){
        StringBuffer returnValue=new StringBuffer();
        for(int x=0;x<layers.length;x++){returnValue.append(layers[x]+"   ");}
        return returnValue.toString();
    }



}

