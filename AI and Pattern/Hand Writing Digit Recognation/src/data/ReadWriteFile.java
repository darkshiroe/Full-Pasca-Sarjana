package data;

import neural.TrainingSet;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {

    public static ArrayList<TrainingSet> readTrainingSets() {
        ArrayList<TrainingSet> trainingSets = new ArrayList<>();

        //NUMBER OF NEURON
        for (int i = 0; i < 10; i++) {
            char letterValue = (char) (i + 48);
            String letter = String.valueOf(letterValue);


            //GENERATE TRAIN SET
            for (ArrayList<Integer> list : readFromFile("/" + letter + ".txt")) {
                trainingSets.add(new TrainingSet(list, GoodOutputs.getInstance().getGoodOutput(letter)));
            }
        }

        return trainingSets;
    }

    //GENERATE DATA SET
    private static ArrayList<ArrayList<Integer>> readFromFile(String filename) {
        ArrayList<ArrayList<Integer>> inputs = new ArrayList<>();

        try {
            InputStream in = ReadWriteFile.class.getClass().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> input = new ArrayList<>();

                //FX
                for (int i = 0; i < line.length(); i++) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(line.charAt(i)));
                    } catch (Exception e) {
                    }
                    input.add(value);
                }
                inputs.add(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputs;
    }

    //SAVE TRAIN DATA
    public static void saveToFile(ArrayList<Integer> input, String filename) {
        try {
            File file = new File("resources/" + filename + ".txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            for (Integer i : input) {
                pw.write(Integer.toString(i));
            }
            pw.write("\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
