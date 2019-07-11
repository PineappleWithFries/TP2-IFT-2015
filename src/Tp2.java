import pharmacy.TreePharmacy;
import primitives.PharmacyDate;
import primitives.PharmacyItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Tp2 {
    private static int currentPrescription;
    private static BufferedReader inputReader;
    private static BufferedWriter outputWriter;
    private static TreePharmacy treePharmacy;

    private static LinkedList<String> inputs = new LinkedList<>();
    private static LinkedList<PharmacyItem> orderList = new LinkedList<>();

    public static void main(String args[]) {

        String inputFile;
        String outputFile;
        if(args != null && args.length >= 2) {
            inputFile = args[0];
            outputFile = args[1];
        } else {
            inputFile = "src/tests/exemple7.txt";
            outputFile = "src/tests/exemple7-.txt";
        }

        treePharmacy = new TreePharmacy();

        PharmacyDate testJour = new PharmacyDate(1999, PharmacyDate.Month.August, 2);

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

            inputs.addAll(new ArrayList<>(Arrays.asList(splitInput)));
        }

        while(!inputs.isEmpty()) {
            String command = inputs.removeFirst();

            switch(command) {
                case "DATE":
                    processDate();
                    break;
                case "PRESCRIPTION":
                    processPrescription();
                    break;
                case "APPROV":
                    processApprov();
                    break;
                case "STOCK":
                    processStock();
                    break;
                default:
                    throw new IOException("Error in file structure");
            }
        }

        outputWriter.flush();
    }

    private static void processDate() throws IOException {
        boolean invalid;

        try {
            PharmacyDate currentDate = new PharmacyDate(inputs.removeFirst());
            treePharmacy.setDate(currentDate);

            if(!inputs.removeFirst().equals(";"))
                throw new IOException("Error in file structure");

            if(orderList.size() == 0) {
                outputWriter.append(currentDate + " OK\n\n");
            } else {
                outputWriter.append(currentDate + " COMMANDES :\n");

                while(!orderList.isEmpty()) {
                    PharmacyItem item = orderList.removeFirst();

                    outputWriter.append(item.getMedication() + " " + item.getQuantity() + "\n");
                }

                outputWriter.append("\n");
            }

        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            //throw new IOException("Error in file structure");
        }
    }

    private static void processPrescription() throws IOException {
        if(!inputs.removeFirst().equals(":"))
            throw new IOException("Error in file structure");

        currentPrescription++;

        outputWriter.write("PRESCRIPTION " + currentPrescription + "\n");

        String currentString = inputs.removeFirst();
        while(!currentString.equals(";")) {
            String name = currentString;
            int quantityPerCycle = Integer.parseInt(inputs.removeFirst());
            int cycles = Integer.parseInt(inputs.removeFirst());
            int quantity = quantityPerCycle * cycles;

            PharmacyDate endDate = treePharmacy.getDate().addDays(quantity);

            boolean retrieved = treePharmacy.retrieve(quantity, name, endDate);

            if(!retrieved) {
                outputWriter.write(name + " " + quantityPerCycle + " " + cycles + "  COMMANDE\n");

                boolean found = false;
                for(PharmacyItem pharmacyItem : orderList) {
                    if(pharmacyItem.getMedication().equals(name)) {
                        pharmacyItem.addQuantity(quantity);
                        found = true;
                    }
                }

                if(!found)
                    orderList.add(new PharmacyItem(PharmacyDate.BEGIN_DATE, name, quantity));
            } else {
                outputWriter.write(name + " " + quantityPerCycle + " " + cycles + "  OK\n");
            }

            currentString = inputs.removeFirst();
        }

        outputWriter.write("\n");
    }

    private static void processApprov() throws IOException {
        if(!inputs.removeFirst().equals(":"))
            throw new IOException("Error in file structure");

        String currentString = inputs.removeFirst();
        while(!currentString.equals(";")) {
            String name = currentString;
            int quantity = Integer.parseInt(inputs.removeFirst());
            PharmacyDate pharmacyDate = new PharmacyDate(inputs.removeFirst());

            treePharmacy.insert(new PharmacyItem(pharmacyDate, name, quantity));

            currentString = inputs.removeFirst();
        }

        outputWriter.append("APPROV OK\n");
    }

    private static void processStock() throws IOException {
        if(!inputs.removeFirst().equals(";"))
            throw new IOException("Error in file structure");

        outputWriter.append("STOCK " + treePharmacy.getDate() + "\n");
        outputWriter.append("" + treePharmacy + "\n");
    }
}
