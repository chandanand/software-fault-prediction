package de.chandanand.neuralnetwork.util;

import de.chandanand.neuralnetwork.model.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by chand on 14/8/16.
 */
public class Utils {

    public static BufferedReader getFileContent(String fileName) {
        String filePath = Utils.class.getClassLoader()
                                    .getResource(fileName)
                                    .getPath();

        BufferedReader fileContent = null;
        try {
            fileContent = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.exit(1);
        }

        return fileContent;
    }

    public static void parse(BufferedReader content, Data data) {
        try {
            for (int i = 0; i < data.numCases; i++) {
                String[] line = (content.readLine().split("\\s+"));

                for (int j = 0; j < (data.numInputs + data.numOutputs); j++) {
                    data.data[i][j] = Double.parseDouble(line[j]);

                    if (data.data[i][j] < data.extrema[j][0])
                        data.extrema[j][0] = data.data[i][j];
                    if (data.data[i][j] > data.extrema[j][1])
                        data.extrema[j][1] = data.data[i][j];
                }
            }

            for (int i = 0; i < (data.numInputs + data.numOutputs); i++)
                if (data.extrema[i][0] == data.extrema[i][1])
                    data.extrema[i][1] = data.extrema[i][0] + 1;

        } catch (Exception ex) {
            System.out.println("Error while parsing file!");
        }
    }
}
