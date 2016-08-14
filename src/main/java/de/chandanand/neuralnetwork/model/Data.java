package de.chandanand.neuralnetwork.model;

/**
 * Created by chand on 14/8/16.
 */
public class Data {

    public final int numInputs;
    public final int numOutputs;
    public final int numCases;

    public double[][] data;
    public double[][] extrema;

    public Data(int numInputs, int numOutputs, int numCases) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numCases = numCases;

        data = new double[numCases][numInputs + numOutputs];
        extrema = new double[numInputs + numOutputs][2];

        for (int i = 0; i < (numInputs + numOutputs); i++) {
            extrema[i][0] = 10000.0;
            extrema[i][1] = -10000.0;
        }


    }
}
