import java.io.*;
public class FileReadWriteDemo {

    public static void main(String[] args) {
        String inputFile = "input.txt";  
        String outputFile = "output.txt";

        try {
            FileWriter fw = new FileWriter(inputFile);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Hello, this is a test file.\n");
            bw.write("We are learning FileReader and FileWriter in Java.\n");
            bw.write("This can be used for data storage and retrieval.\n");

            bw.close();

            System.out.println("Data successfully written to " + inputFile);

            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            br.close();

            System.out.println("\nData read from " + inputFile + ":");
            System.out.println(fileContent);

            FileWriter fw2 = new FileWriter(outputFile);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            bw2.write(fileContent.toString());

            bw2.close();

            System.out.println("Data successfully copied to " + outputFile);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
