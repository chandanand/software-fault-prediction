package de.chandanand.neuralnetwork.model;

/**
 * Created by chand on 14/8/16.
 */
public class NeuralNetwork {

    public final int columns;
    public final int[] nodesPerLayer;
    public int numWeights = 0;
    private Neuron[][] neuralNet;
    private int numInputs;
    private int numOutputs;
    public Data dataSet;

    public NeuralNetwork(int numLayers, int maxRows, int[] nodesPerLayer) {
        this.columns = numLayers;
        this.nodesPerLayer = nodesPerLayer;
        this.neuralNet = new Neuron[columns][maxRows];

        numInputs = this.nodesPerLayer[0];
        numOutputs = this.nodesPerLayer[numLayers - 1];

        for (int i = 1; i < columns; i++)
            numWeights = numWeights + (this.nodesPerLayer[i] * this.nodesPerLayer[i - 1]);
        initNeurons();
    }

    private void initNeurons() {
        for (int i = 0; i < numInputs; i++)
            neuralNet[0][i] = new Neuron(0);

        for (int i = 1; i < columns; i++)
            for (int j = 0; j < nodesPerLayer[i]; j++)
                neuralNet[i][j] = new Neuron(nodesPerLayer[i - 1]);
    }

    public void setWeights(double[] weights) {
        int x = 0;

        if (weights.length != numWeights) {
            System.out.println("mismatch in number of weights");
            System.exit(1);
        }

        for (int i = 1; i < columns; i++)
            for (int j = 0; j < nodesPerLayer[i]; j++)
                for (int k = 0; k < neuralNet[i][j].numWeights; k++)
                    neuralNet[i][j].weights[k] = weights[x++];
    }

    public double computeError(boolean training) {
        double[] input = new double[nodesPerLayer[0]];
        double[] output;
        double desiredOutput[] = new double[nodesPerLayer[columns - 1]];
        double totalError = 0.0;
        double temp;

        for (int i = 0; i < dataSet.data.length; i++) {

            System.arraycopy(dataSet.data[i], 0, input, 0, numInputs);

            System.arraycopy(dataSet.data[i], numInputs, desiredOutput, 0, numOutputs);

            output = computeOutput(input);

            if (!training) {
                temp = (((output[0] - 0.05) / 0.9) * 2) + 1;
                System.out.println(Math.round(temp));
            }

            for (int j = 0; j < numOutputs; j++) {
                double error = output[j] - desiredOutput[j];
                totalError += error * error;
            }
        }
        return totalError;
    }

    protected double[] computeOutput(double[] inputs) {
        double sum;
        double[] output = new double[nodesPerLayer[columns - 1]];

        /*Input Layer*/
        for (int i = 0; i < nodesPerLayer[0]; i++)
            neuralNet[0][i].output = inputs[i];

        /*Hidden Layers*/
        for (int i = 1; i < columns - 1; i++)
            for (int j = 0; j < nodesPerLayer[i]; j++) {
                sum = 0.0;
                for (int k = 0; k < nodesPerLayer[i - 1]; k++)
                    sum += neuralNet[i][j].weights[k] * neuralNet[i - 1][k].output;
                neuralNet[i][j].output = 1.0 / (1.0 + Math.exp(-sum));
            }

        /*output Layer*/
        for (int i = 0; i < nodesPerLayer[columns - 1]; i++) {
            sum = 0.0;
            for (int j = 0; j < nodesPerLayer[columns - 2]; j++)
                sum += neuralNet[columns - 1][i].weights[j]
                        * neuralNet[columns - 2][j].output;
            output[i] =
                    neuralNet[columns - 1][i].output = 1.0 / (1.0 + Math.exp(-sum));
        }

        return output;
    }
}
