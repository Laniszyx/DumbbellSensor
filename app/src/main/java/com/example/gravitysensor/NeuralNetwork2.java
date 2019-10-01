package com.example.gravitysensor;

public class NeuralNetwork2 {
    static final double LEARNING_RATE=0.8;
    final static int NUMB_OF_INPUT_NEURONS =8;
    final static int NUMB_OF_INPUT_NEURONS_UP =4;
    final static int NUMB_OF_OUTPUT_NEURONS = 1;
    private int numbOfHiddenNeurons;
   // private Layer[] layers=new Layer[3];//原先:Layer.LayerType.values().length
    private Layer[] layers_up=new Layer[4];//原先:Layer.LayerType.values().length
    public NeuralNetwork2(int numbOfHiddenNeurons){
        this.numbOfHiddenNeurons=numbOfHiddenNeurons;
        layers_up[0]=new Layer(NeuralNetwork2.this ,Layer.LayerType.I2);
        layers_up[1]=new Layer(NeuralNetwork2.this,Layer.LayerType.H);
        layers_up[2]=new Layer(NeuralNetwork2.this,Layer.LayerType.H2);
        layers_up[3]=new Layer(NeuralNetwork2.this,Layer.LayerType.O);
    }


///GZ WEIGHT
//    public NeuralNetwork2 testforwardprop(double input[]){
//        double[] weightsH1=new double[]{ -0.62, -1.01, -1.97, -1.30, 2.65, 0.56, -1.07, 0.13};
//        double[] weightsH2=new double[]{  -28.28, -3.63, 11.91, -3.96, -6.68, 6.88, 8.28, 0.77};
//        double[] weightsH3=new double[]{ 0.20, 0.49, 0.84, 0.17, -0.22, -1.33, 0.11, -0.06 };
//        double[] weightsH4=new double[]{ 0.78, 0.95, 1.02, 0.94, -1.49, -0.55, 0.39, -0.05 };
//        double[] weightsH5=new double[]{3.68, -1.09, -0.43, -1.69, -3.76, 5.39, 2.07, 1.28 };
//        double[] weightsH6=new double[]{ 0.13, 0.47, 0.46, -0.14, -0.60, -0.46, -0.57, 0.25  };
//        double[] weightsH7=new double[]{ 1.50, 1.02, -1.83, -3.83, 4.82, 2.58, -3.86, 2.82 };
//        double[] weightsH8=new double[]{ -3.81, 0.77, 1.16, 0.94, 4.06, -4.90, -1.03, -2.31 };
//        double[] weightsO1=new double[]{ -1.41, -4.75, 0.47, -1.32, -6.54, -0.19, -10.46, 5.67};
//
//
//        for(int i=0;i<layers.length;i++){
//            switch(layers[i].getLayerType()){
//                case I:
//                    for(int j=0;j<layers[i].getNeuron().length;j++)
//                        layers[i].getNeuron()[j].setOutput(input[j]);
//                    break;
//                case H:
//                    for(int j=0;j<8;j++){
//                        double weightedSum=0;
//                        for(int k=0;k<8;k++){
//                            switch(j){
//                                case 0:
//                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 1:
//                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 2:
//                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 3:
//                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 4:
//                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 5:
//                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 6:
//                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 7:
//                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                            }
//                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
//                    }
//                    break;
//                case O:
//                    double weightedSum=0;
//                    for(int k=0;k<8;k++){
//                        switch(k){
//                            case 0:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 1:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 2:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 3:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 4:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 5:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 6:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 7:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                        }
//                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
//                    }
//                    break;
//            }
//        }
//        return this;
//    }

    public NeuralNetwork2 testforwardpropZup(double input[]){

        double[] weightsH1=new double[]{ 0.40, 0.47, 0.20, -0.27  };
        double[] weightsH2=new double[]{  -0.15, 0.05, -0.14, 0.11  };
        double[] weightsH3=new double[]{ 0.31, 0.22, -0.36, 0.02 };
        double[] weightsH4=new double[]{  -0.45, -0.35, -0.06, 0.16  };
        double[] weightsH5=new double[]{ -0.45, -0.53, 0.01, 0.10  };
        double[] weightsH6=new double[]{  -0.27, -0.76, 0.29, 0.49 };
        double[] weightsH7=new double[]{ -0.36, -0.44, 0.02, 0.19 };
        double[] weightsH8=new double[]{  0.33, 0.12, 0.29, -0.44  };
        double[] weightsH2_1=new double[]{  -0.96, 0.13, -0.58, 0.84, 0.82, 1.15, 0.65, -1.13 };
        double[] weightsH2_2=new double[]{  0.10, -0.38, -0.19, 0.21, 0.01, 0.48, 0.51, 0.34  };
        double[] weightsH2_3=new double[]{ 0.29, 0.31, 0.57, -0.19, -0.05, 0.06, 0.22, 0.64  };
        double[] weightsH2_4=new double[]{  1.09, -0.16, 1.11, -0.88, -1.02, -1.12, -0.40, 0.66  };
        double[] weightsH2_5=new double[]{  1.01, -0.29, 0.98, -0.78, -0.71, -1.17, -0.25, 0.45  };
        double[] weightsH2_6=new double[]{   -1.64, 1.14, -1.43, 0.34, 1.10, 1.52, 0.43, -0.63  };
        double[] weightsH2_7=new double[]{  1.37, -0.58, 0.58, -0.36, -1.04, -1.25, -0.76, 1.26  };
        double[] weightsH2_8=new double[]{  0.32, -0.82, 0.29, -0.51, -0.71, -0.14, 0.06, 0.70 };
        double[] weightsO1=new double[]{  -3.40, -0.49, 0.14, 2.96, 2.38, -4.95, 3.55, 1.08 };


        for(int i=0;i<4;i++){//i<layers.length=4
            switch(layers_up[i].getLayerType()){
                case I2:
                    for(int j=0;j<layers_up[i].getNeuron().length;j++)
                        layers_up[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<4;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case H2:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH2_1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2_2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH2_3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH2_4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH2_5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH2_6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH2_7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH2_8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers_up[i].getNeuron()[0].applyActivationFunction(weightedSum);
                    }
                    break;
            }
        }
        return this;
    }


///GY WEIGHT
//    public NeuralNetwork2 testforwardpropY(double input[]){
//        double[] weightsH1=new double[]{ -0.44, -0.25, -0.58, -0.38, 0.24, -0.19, 0.28, -0.06};
//        double[] weightsH2=new double[]{0.24, -0.70, 0.14, -0.55, 0.43, -0.22, 0.72, -0.00 };
//        double[] weightsH3=new double[]{  0.18, -0.50, 0.01, -0.14, 0.03, 0.09, 0.79, -0.13 };
//        double[] weightsH4=new double[]{  0.14, -0.22, 0.21, -0.17, -0.09, 0.12, 0.30, 0.31 };
//        double[] weightsH5=new double[]{ 0.19, 0.37, 0.56, -0.20, -0.40, 0.29, -0.54, 0.20 };
//        double[] weightsH6=new double[]{  0.07, -0.60, -0.30, -0.33, 0.42, 0.26, -0.21, 0.54 };
//        double[] weightsH7=new double[]{  -0.60, 0.15, -0.49, -0.51, -0.26, 0.11, 0.38, -0.07};
//        double[] weightsH8=new double[]{-0.28, 0.40, -0.37, 0.15, -0.12, -0.30, -0.50, -0.22 };
//        double[] weightsO1=new double[]{ 1.28, 1.87, 1.90, 0.74, -0.26, 1.35, 1.11, -0.37 };
//
//
//        for(int i=0;i<layers.length;i++){
//            switch(layers[i].getLayerType()){
//                case I:
//                    for(int j=0;j<layers[i].getNeuron().length;j++)
//                        layers[i].getNeuron()[j].setOutput(input[j]);
//                    break;
//                case H:
//                    for(int j=0;j<8;j++){
//                        double weightedSum=0;
//                        for(int k=0;k<8;k++){
//                            switch(j){
//                                case 0:
//                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 1:
//                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 2:
//                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 3:
//                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 4:
//                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 5:
//                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 6:
//                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 7:
//                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                            }
//                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
//                    }
//                    break;
//                case O:
//                    double weightedSum=0;
//                    for(int k=0;k<8;k++){
//                        switch(k){
//                            case 0:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 1:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 2:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 3:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 4:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 5:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 6:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 7:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                        }
//                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
//                    }
//                    break;
//            }
//        }
//        return this;
//    }

    public NeuralNetwork2 testforwardpropYup(double input[]){
        double[] weightsH1=new double[]{-0.30, 0.23, 0.58, -0.32};
        double[] weightsH2=new double[]{ 0.16, 0.17, 0.79, 0.16 };
        double[] weightsH3=new double[]{  -0.25, 0.38, 0.42, -0.15  };
        double[] weightsH4=new double[]{  0.01, -0.16, -0.09, 0.60  };
        double[] weightsH5=new double[]{ -0.16, -0.83, -0.17, -0.57};
        double[] weightsH6=new double[]{  0.32, 0.73, 0.43, -0.78 };
        double[] weightsH7=new double[]{  -0.14, 0.15, 0.06, -0.46 };
        double[] weightsH8=new double[]{  -1.06, -0.59, -0.57, 0.97   };
        double[] weightsH2_1=new double[]{ 0.39, 0.19, 1.08, -0.59, -1.68, 0.58, 0.11, -1.62 };
        double[] weightsH2_2=new double[]{  -0.09, -0.45, -1.05, 0.31, 1.81, -0.35, -0.44, 1.92   };
        double[] weightsH2_3=new double[]{   -0.15, 0.54, 0.64, -0.38, -0.74, 0.04, 0.56, -0.11 };
        double[] weightsH2_4=new double[]{ -0.58, -0.59, -0.19, 0.42, 0.65, -0.48, -0.25, 1.23   };
        double[] weightsH2_5=new double[]{  0.00, -0.59, -0.57, 0.53, 0.98, -0.76, -0.09, 1.31   };
        double[] weightsH2_6=new double[]{   0.38, 0.59, 1.27, -0.25, -3.71, 1.05, -0.00, -2.90   };
        double[] weightsH2_7=new double[]{ -0.35, 0.13, -0.01, -0.03, 0.33, -0.60, 0.05, 0.38   };
        double[] weightsH2_8=new double[]{  -0.08, -0.34, -1.94, 0.13, 3.69, -0.45, -0.61, 3.12  };
        double[] weightsO1=new double[]{  -3.27, 3.19, -1.15, 1.66, 1.91, -6.09, 0.51, 6.02  };


        for(int i=0;i<4;i++){//i<layers.length=4
            switch(layers_up[i].getLayerType()){
                case I2:
                    for(int j=0;j<layers_up[i].getNeuron().length;j++)
                        layers_up[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<4;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case H2:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH2_1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2_2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH2_3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH2_4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH2_5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH2_6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH2_7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH2_8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers_up[i].getNeuron()[0].applyActivationFunction(weightedSum);
                    }
                    break;
            }
        }
        return this;
    }



    ///GX WEIGHT
//    public NeuralNetwork2 testforwardpropX(double input[]){
//        double[] weightsH1=new double[]{0.11, -0.16, 0.01, -0.11, -0.21, 0.21, -0.64, -0.55};
//        double[] weightsH2=new double[]{  0.63, 0.32, 0.26, 0.08, -0.16, 0.11, -0.35, -0.02 };
//        double[] weightsH3=new double[]{ -0.25, 0.40, -0.02, -0.29, 0.12, -0.30, -0.20, 0.08  };
//        double[] weightsH4=new double[]{ 0.07, 0.73, 0.59, 0.31, -0.54, 0.28, -0.41, 0.17  };
//        double[] weightsH5=new double[]{0.17, 0.17, -0.06, 0.26, -0.02, -0.37, -0.48, -0.40};
//        double[] weightsH6=new double[]{ -0.12, 0.51, -0.05, 0.23, -0.25, -0.68, -0.17, 0.13  };
//        double[] weightsH7=new double[]{0.26, 0.50, 0.00, -0.14, -0.49, 0.21, -0.17, -0.26   };
//        double[] weightsH8=new double[]{  0.47, 0.25, -0.06, -0.06, 0.25, -0.02, 0.25, -0.18 };
//        double[] weightsO1=new double[]{ 1.45, 1.79, 1.44, 1.60, 1.78, 1.81, 1.20, 1.09  };
//
//
//        for(int i=0;i<layers.length;i++){
//            switch(layers[i].getLayerType()){
//                case I:
//                    for(int j=0;j<layers[i].getNeuron().length;j++)
//                        layers[i].getNeuron()[j].setOutput(input[j]);
//                    break;
//                case H:
//                    for(int j=0;j<8;j++){
//                        double weightedSum=0;
//                        for(int k=0;k<8;k++){
//                            switch(j){
//                                case 0:
//                                    weightedSum+=weightsH1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 1:
//                                    weightedSum+=weightsH2[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 2:
//                                    weightedSum+=weightsH3[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 3:
//                                    weightedSum+=weightsH4[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 4:
//                                    weightedSum+=weightsH5[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 5:
//                                    weightedSum+=weightsH6[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 6:
//                                    weightedSum+=weightsH7[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                                case 7:
//                                    weightedSum+=weightsH8[k]*layers[i-1].getNeuron()[k].getOutput();
//                                    break;
//                            }
//                            layers[i].getNeuron()[j].applyActivationFunction(weightedSum);}
//                    }
//                    break;
//                case O:
//                    double weightedSum=0;
//                    for(int k=0;k<8;k++){
//                        switch(k){
//                            case 0:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 1:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 2:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 3:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 4:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 5:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 6:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                            case 7:
//                                weightedSum+=weightsO1[k]*layers[i-1].getNeuron()[k].getOutput();
//                                break;
//                        }
//                        layers[i].getNeuron()[0].applyActivationFunction(weightedSum);
//                    }
//                    break;
//            }
//        }
//        return this;
//    }

    public NeuralNetwork2 testforwardpropXup(double input[]){
        double[] weightsH1=new double[]{ -6.65, 1.69, 0.22, 3.92};
        double[] weightsH2=new double[]{-0.30, -0.06, 0.06, -0.26 };
        double[] weightsH3=new double[]{ 3.07, 0.62, -0.92, -1.09 };
        double[] weightsH4=new double[]{  -1.30, -2.77, -0.36, -0.11 };
        double[] weightsH5=new double[]{   -0.24, -0.47, -0.93, -0.28 };
        double[] weightsH6=new double[]{ -6.68, -2.30, 1.55, 3.38   };
        double[] weightsH7=new double[]{ 0.33, 1.19, 1.10, -7.49   };
        double[] weightsH8=new double[]{   1.97, -3.27, 4.46, -0.85 };
        double[] weightsH2_1=new double[]{  -11.42, 0.07, 5.89, 2.83, 2.79, -8.32, -4.22, -5.20  };
        double[] weightsH2_2=new double[]{  1.18, 0.21, 2.13, 0.51, -0.58, -1.60, 3.27, 0.40  };
        double[] weightsH2_3=new double[]{  0.04, -0.67, 4.21, -0.21, -0.01, -6.03, 3.17, 1.42  };
        double[] weightsH2_4=new double[]{ -2.33, 0.32, -1.73, 3.82, 1.68, 0.83, -1.68, -1.94   };
        double[] weightsH2_5=new double[]{  -0.87, 0.84, -0.26, 1.89, 0.68, 0.20, -0.61, -0.43  };
        double[] weightsH2_6=new double[]{  0.36, -0.88, 5.67, -0.36, 0.13, -8.82, 4.07, 1.67   };
        double[] weightsH2_7=new double[]{   0.72, 0.26, 8.30, -1.17, -2.92, -4.35, 12.58, 1.85  };
        double[] weightsH2_8=new double[]{  -9.24, -0.54, -5.32, 6.25, 0.00, 4.64, 2.46, -15.12  };
        double[] weightsO1=new double[]{ -13.36, 0.89, 5.01, -5.68, -3.95, 9.64, 14.88, -18.18   };


        for(int i=0;i<4;i++){//i<layers.length=4
            switch(layers_up[i].getLayerType()){
                case I2:
                    for(int j=0;j<layers_up[i].getNeuron().length;j++)
                        layers_up[i].getNeuron()[j].setOutput(input[j]);
                    break;
                case H:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<4;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case H2:
                    for(int j=0;j<8;j++){
                        double weightedSum=0;
                        for(int k=0;k<8;k++){//weight的 數量
                            switch(j){
                                case 0:
                                    weightedSum+=weightsH2_1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 1:
                                    weightedSum+=weightsH2_2[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 2:
                                    weightedSum+=weightsH2_3[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 3:
                                    weightedSum+=weightsH2_4[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 4:
                                    weightedSum+=weightsH2_5[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 5:
                                    weightedSum+=weightsH2_6[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 6:
                                    weightedSum+=weightsH2_7[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                                case 7:
                                    weightedSum+=weightsH2_8[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                    break;
                            }
                            layers_up[i].getNeuron()[j].applyActivationFunction(weightedSum);}
                    }
                    break;
                case O:
                    double weightedSum=0;
                    for(int k=0;k<8;k++){
                        switch(k){
                            case 0:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 1:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 2:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 3:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 4:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 5:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 6:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                            case 7:
                                weightedSum+=weightsO1[k]*layers_up[i-1].getNeuron()[k].getOutput();
                                break;
                        }
                        layers_up[i].getNeuron()[0].applyActivationFunction(weightedSum);
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
        return layers_up;
    }

    public String toString(){
        StringBuffer returnValue=new StringBuffer();
        for(int x=0;x<layers_up.length;x++){returnValue.append(layers_up[x]+"   ");}
        return returnValue.toString();
    }


//    public NeuralNetwork backpropError(double targetResult){
//        Neuron[] iNeuron=layers[0].getNeuron();
//        Neuron[] hNeuron=layers[1].getNeuron();
//        Neuron oNeuron=layers[layers.length-1].getNeuron()[0];
//        oNeuron.setError((targetResult-oNeuron.getOutput())*oNeuron.derivation());
//        for(int j=0;j<oNeuron.getWeights().length;j++)
//            oNeuron.getWeights()[j]=oNeuron.getWeights()[j]+LEARNING_RATE*oNeuron.getError()*hNeuron[j].getOutput();
//        for(int i=0;i<hNeuron.length;i++){
//            hNeuron[i].setError((oNeuron.getWeights()[i]*oNeuron.getError())*hNeuron[i].derivation());
//            for(int j=0;j<hNeuron[0].getWeights().length;j++)
//                hNeuron[i].getWeights()[j]=hNeuron[i].getWeights()[j]+LEARNING_RATE*hNeuron[i].getError()*iNeuron[j].getOutput();
//        }
//        return this;
//    }





}

