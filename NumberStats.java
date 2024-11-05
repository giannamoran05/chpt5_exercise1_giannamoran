import java.io.*;
import java.util.*;

public class NumberStats {

    public static void main(String[] args) {
        // Define the path to the input CSV file
        String inputFile = "Book1.csv";
        // Define the path for the output text file
        String outputFile = "stats.txt";

        try {
            // Read numbers from the CSV file
            List<Double> numbers = readNumbersFromFile(inputFile);

            // If no numbers are read, exit
            if (numbers.isEmpty()) {
                System.out.println("No numbers found in the file.");
                return;
            }

            // Calculate sum, lowest, highest, and average
            double sum = calculateSum(numbers);
            double lowest = Collections.min(numbers);
            double highest = Collections.max(numbers);
            double average = sum / numbers.size();

            // Write the results to the output file
            writeStatsToFile(outputFile, sum, lowest, highest, average);

            // Optionally, print the results to the console
            System.out.println("The sum of the numbers is: " + sum);
            System.out.println("The lowest number is: " + lowest);
            System.out.println("The highest number is: " + highest);
            System.out.println("The average of the numbers is: " + average);

        } catch (IOException e) {
            System.out.println("An error occurred while processing the file: " + e.getMessage());
        }
    }

    // Method to read numbers from the CSV file
    private static List<Double> readNumbersFromFile(String fileName) throws IOException {
        List<Double> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas and convert each value to a number
                String[] values = line.split(",");
                for (String value : values) {
                    try {
                        numbers.add(Double.parseDouble(value.trim()));
                    } catch (NumberFormatException e) {
                        // Handle case where a value is not a valid number
                        System.out.println("Skipping invalid number: " + value);
                    }
                }
            }
        }
        return numbers;
    }

    // Method to calculate the sum of the numbers
    private static double calculateSum(List<Double> numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Method to write statistics to the output text file
    private static void writeStatsToFile(String fileName, double sum, double lowest, double highest, double average) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("The sum of the numbers is: " + sum);
            writer.newLine();
            writer.write("The lowest number is: " + lowest);
            writer.newLine();
            writer.write("The highest number is: " + highest);
            writer.newLine();
            writer.write("The average of the numbers is: " + average);
            writer.newLine();
        }
    }
}