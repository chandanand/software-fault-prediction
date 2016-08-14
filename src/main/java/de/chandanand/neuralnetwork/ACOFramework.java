package de.chandanand.neuralnetwork;

import de.chandanand.neuralnetwork.model.NeuralNetwork;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Random;

/**
 * Created by chand on 14/8/16.
 */
public class ACOFramework {

    private final NeuralNetwork neuralNetwork;
    private final int archiveSize;
    private final int numWeights;
    private final double q = .08;
    private final double constantSd = 0.1;
    private final double epsilon = .75; /*affects pheromone evaporation rate*/
    private final int maxIterations = 100000;
    private final double errorCriteria = 0.4;
    private double[][] archive;
    private double[] fitness;
    private double[] solWeights;
    private double[] solution;
    private double testError = -1;
    private double sumSolWeights;
    private RandomDataGenerator grand = new RandomDataGenerator();

    public ACOFramework(NeuralNetwork neuralNetwork, int archiveSize) {
        this.neuralNetwork = neuralNetwork;
        this.archiveSize = archiveSize;
        this.numWeights = neuralNetwork.numWeights;
        initialize();
    }

    protected void initialize() {
        archive = new double[archiveSize * 2][numWeights];
        fitness = new double[archiveSize * 2];
        solWeights = new double[archiveSize];
        Random rand = new Random();

        for (int i = 0; i < (archiveSize * 2); i++)
            for (int j = 0; j < (numWeights); j++)
                archive[i][j] = rand.nextDouble() * 2 - 1;
    }

    public boolean trainNeuralNet() {
        computeFitness(0);
        sortArchive();
        computeSolutionWeights();
        generateBiasedWeights();
        sortArchive();

        for (int j = 0; j < maxIterations; j++) {
            computeFitness(0);
            sortArchive();
            if (j % 1000 == 0)
                System.out.println(fitness[0]);
            if (fitness[0] < errorCriteria) {
                System.out.println("Solution found in iteration" + (j + 1));
                solution = archive[0];
                for (int i = 0; i < numWeights; i++)
                    System.out.println(archive[0][i]);
                return true;
            }
            computeSolutionWeights();
            generateBiasedWeights();
        }
        System.out.println("Network did not converge!");
        return false;
    }

    private void computeFitness(int type) {
        if (type == 0) {
            for (int i = 0; i < (archiveSize * 2); i++) {
                neuralNetwork.setWeights(archive[i]);
                fitness[i] = neuralNetwork.computeError(true);
            }
        }

        if (type == 1) {
            neuralNetwork.setWeights(solution);
            testError = neuralNetwork.computeError(false);
            System.out.println("Test error: " + testError);
        }
    }

    private void sortArchive() {
        int n = archive.length;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - 1; j++) {
                try {
                    if (fitness[j] > fitness[j + 1]) {

                        double temp = fitness[j];
                        fitness[j] = fitness[j + 1];
                        fitness[j + 1] = temp;

                        double[] tempTrail = archive[j];
                        archive[j] = archive[j + 1];
                        archive[j + 1] = tempTrail;
                    }
                } catch (Exception e) {
                    System.out.println("error: " + j + " n " + n);
                    System.exit(0);
                }
            }
    }

    private void computeSolutionWeights() {
        sumSolWeights = 0;
        for (int i = 0; i < archiveSize; i++) {
            double exponent = (i * i) / (2 * q * q * archiveSize * archiveSize);
            solWeights[i] =
                    (1 / (0.1 * Math.sqrt(2 * Math.PI))) * Math.pow(Math.E, -exponent);
            sumSolWeights += solWeights[i];
        }
    }

    private void generateBiasedWeights() {
        double sigma; /*standard deviation*/
        double mu; /*mean*/

        for (int i = archiveSize; i < archiveSize * 2; i++) {
            int pdf = selectPDF();
            for (int j = 0; j < numWeights; j++) {
                sigma = computeSD(j, pdf);
                mu = archive[pdf][j];
                archive[i][j] = grand.nextGaussian(mu, sigma);
            }
        }
    }

    private int selectPDF() {
        int l = 0;
        double temp = 0;
        Random random = new Random();
        double r = random.nextDouble();

        for (int i = 0; i < archiveSize; i++) {
            temp += solWeights[i] / sumSolWeights;
            if (r < temp) {
                l = i;
                break;
            }
        }
        return l;
    }

    private double computeSD(int x, int l) {
        double sum = 0.0;
        for (int i = 0; i < archiveSize; i++) {
            sum += Math.abs(archive[i][x] - archive[l][x]) / (archiveSize - 1);
        }
        if (sum == 0) {
            System.out.println("sum = 0 " + l + "archive size = " + archiveSize);
            return constantSd;
        }
        return (epsilon * sum);
    }

    public void testNeuralNet() {
        computeFitness(1);
    }
}
