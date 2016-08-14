package de.chandanand.neuralnetwork.model;

/**
 * Created by chand on 14/8/16.
 */
public class Neuron {

    private final int numWeights;
    private final double[] weights;

    Neuron(int numWeights) {
        this.numWeights = numWeights;
        this.weights = new double[this.numWeights];
    }
}
