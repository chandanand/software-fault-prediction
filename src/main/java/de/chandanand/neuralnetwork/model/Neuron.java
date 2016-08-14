package de.chandanand.neuralnetwork.model;

/**
 * Created by chand on 14/8/16.
 */
public class Neuron {

    public final int numWeights;
    public final double[] weights;
    public double output = 0;

    public Neuron(int numWeights) {
        this.numWeights = numWeights;
        this.weights = new double[this.numWeights];
    }

    public void calculateOutput(double[] input) {
        for (int i = 0; i < numWeights; i++) {
            output += weights[i] * input[i];
        }
    }
}
