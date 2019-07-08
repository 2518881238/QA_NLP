package com.word2vec.domain;

public class HiddenNeuron extends Neuron{
    
    public double[] syn1 ; //hidden->out存放神经元的维度数组大小
    
    //构造隐藏神经元的层数
    public HiddenNeuron(int layerSize){
        syn1 = new double[layerSize] ;
    }
    
}
