package de.chandanand.neuralnetwork;

import de.chandanand.neuralnetwork.model.Data;
import de.chandanand.neuralnetwork.model.NeuralNetwork;
import de.chandanand.neuralnetwork.util.Utils;

import java.io.BufferedReader;

/**
 * Created by chand on 12/8/16.
 */
public class ACOANNRunner {

    public static void main(String[] args) {
        int numInputs = 7;
        int numOutputs = 1;
        int numTrainCases = 210;
        int numTestCases = 20;

        String trainingFileName = "ant_dataset.dat";
        String testFileName = "ant_dataset_test.dat";

        BufferedReader trainingFile = Utils.getFileContent(trainingFileName);
        BufferedReader testFile = Utils.getFileContent(testFileName);

        Data trainData = new Data(numInputs, numOutputs, numTrainCases);
        Data testData = new Data(numInputs, numOutputs, numTestCases);

        Utils.parse(trainingFile, trainData);
        Utils.parse(testFile, testData);

        trainData.scaleDown();
        testData.scaleDown();

        int[] nodesPerLayer = new int[3];
        nodesPerLayer[0] = numInputs;
        nodesPerLayer[1] = numInputs;
        nodesPerLayer[2] = numOutputs;
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, numInputs, nodesPerLayer);
        
    }
}
