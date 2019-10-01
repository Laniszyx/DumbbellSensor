package com.example.gravitysensor;

public class Layer {
    static enum LayerType {//input hidden output
        I, H, O,I2,H2};
    private Neuron[] neurons=null;
    private LayerType layerType;
    public Layer(NeuralNetwork neuralNetwork, LayerType layerType){
        this.layerType=layerType;
        switch(layerType){
//            case I2:
//                neurons=new Neuron[NeuralNetwork.NUMB_OF_INPUT_NEURONS_UP];
//                for(int x=0;x<NeuralNetwork.NUMB_OF_INPUT_NEURONS_UP;x++){neurons[x]=new Neuron(layerType,0);}
//                break;
            case I:
                neurons=new Neuron[NeuralNetwork.NUMB_OF_INPUT_NEURONS];
                for(int x=0;x<NeuralNetwork.NUMB_OF_INPUT_NEURONS;x++){neurons[x]=new Neuron(layerType,0);}
                break;
            case H:
                neurons=new Neuron[neuralNetwork.getNumbOfHiddenNeurons()];
                for(int x=0;x<neuralNetwork.getNumbOfHiddenNeurons();x++){
                    neurons[x]=new Neuron(layerType,NeuralNetwork.NUMB_OF_INPUT_NEURONS);
                }
                break;
            case O:
                neurons=new Neuron[NeuralNetwork.NUMB_OF_OUTPUT_NEURONS];
                neurons[0]=new Neuron(layerType,neuralNetwork.getNumbOfHiddenNeurons());
                break;
        }
    }

    public Layer(NeuralNetwork2 neuralNetwork, LayerType layerType){
        this.layerType=layerType;
        switch(layerType){
            case I2:
                neurons=new Neuron[4];
                for(int x=0;x<4;x++){neurons[x]=new Neuron(layerType,0);}
                break;
            case H:
                neurons=new Neuron[neuralNetwork.getNumbOfHiddenNeurons()];
                for(int x=0;x<neuralNetwork.getNumbOfHiddenNeurons();x++){
                    neurons[x]=new Neuron(layerType,8);
                }
                break;
            case H2:
                neurons=new Neuron[neuralNetwork.getNumbOfHiddenNeurons()];
                for(int x=0;x<neuralNetwork.getNumbOfHiddenNeurons();x++){
                    neurons[x]=new Neuron(layerType,8);
                }
                break;
            case O:
                neurons=new Neuron[1];
                neurons[0]=new Neuron(layerType,neuralNetwork.getNumbOfHiddenNeurons());
                break;
        }
    }


    public Neuron[] getNeuron(){ return neurons;}
    public LayerType getLayerType(){return layerType;}
    public String toString(){
        StringBuffer returnValue=new StringBuffer();
        for(int x=0;x<neurons.length;x++){returnValue.append(neurons[x]+" ");}
        return returnValue.toString();
    }
}

