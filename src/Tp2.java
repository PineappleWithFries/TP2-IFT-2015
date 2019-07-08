import pharmacy.TreePharmacy;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tp2 {
    private static BufferedReader inputReader;
    private static BufferedWriter outputWriter;
    private static TreePharmacy treePharmacy;

    private static LinkedList<String> inputs = new LinkedList<>();

    public static void main(String args[]) {
        String inputFile = "src/tests/exemple1.txt";
        String outputFile = "src/tests/exemple1-.txt";
        TreePharmacy tp = new TreePharmacy();

        try {
            inputReader = new BufferedReader(new FileReader(inputFile));
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
            process();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void process() throws IOException {
        String nextInput;

        while((nextInput = inputReader.readLine()) != null) {
            String[] splitInput = nextInput.split("\\s+");

            inputs.addAll(new ArrayList<>(inputs));
        }

        while(!inputs.isEmpty()) {
            String command = inputs.removeFirst();

            switch(command) {
                case "DATE":
                    processDate();
                    break;
                case "PRESCRIPTION":

                    break;
                case "APPROV":

                    break;
                case "STOCK":

                    break;
                default:
                    throw new IOException("Error in file structure");
            }
        }
    }

    private static void processDate() {
        treePharmacy.setDate(inputs.removeFirst());


    }

    private static void
}
