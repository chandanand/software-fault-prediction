package de.chandanand.neuralnetwork.model;

/**
 * Created by chand on 14/8/16.
 */
public class NeuralNetwork {

    private Neuron[][] neuralNet;
    private final int columns;
    private final int[] nodesPerLayer;
    private int numInputs;
    private int numOutputs;
    private int numWeights = 0;

    public NeuralNetwork(int numLayers, int maxRows, int[] nodesPerLayer) {
        this.columns = numLayers;
        this.nodesPerLayer = nodesPerLayer;
        this.neuralNet = new Neuron[columns][maxRows];

        numInputs = this.nodesPerLayer[0];
        numOutputs = this.nodesPerLayer[numLayers - 1];

        for (int i = 1; i < columns; i++)
            numWeights = numWeights + (this.nodesPerLayer[i]* this.nodesPerLayer[i-1]);
        initNeurons();
    }

    private void initNeurons() {
        for(int i = 0; i < numInputs; i++)
            neuralNet[0][i] = new Neuron(0);

        for (int i = 1; i < columns; i++)
            for (int j = 0; j < nodesPerLayer[i]; j++)
                neuralNet[i][j] = new Neuron(nodesPerLayer[i-1]);
    }
}
