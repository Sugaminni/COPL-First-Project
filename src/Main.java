import com.google.gson.*;
import java.io.*;
import java.util.*;

/**
 * Main class for the SCL Scanner program.
 * Reads an SCL source file, tokenizes it, prints the tokens,
 * then outputs the token list to a JSON file.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // Ensures the program is run with a valid file path argument
        if (args.length < 1) {
            System.out.println("Usage: java Main <file.scl>");
            return;
        }

        // Extracts the file path from command-line argument
        String filePath = args[0];

        // Creates and runs the scanner on the scl file
        Scanner scanner = new Scanner();
        scanner.scanFile(filePath);

        // Prints scanned tokens to the console
        System.out.println("Scanned Tokens:");
        for (Token token : scanner.getTokens()) {
            System.out.println(token);
        }

        // Converts token data for JSON output
        List<Map<String, String>> jsonList = new ArrayList<>();
        for (Token token : scanner.getTokens()) {
            Map<String, String> entry = new HashMap<>();
            entry.put("type", token.getType().toString());
            entry.put("value", token.getValue());
            jsonList.add(entry);
        }

        // Write tokens to a JSON file using Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("tokens.json");
        gson.toJson(jsonList, writer);
        writer.close();

        // Confirms output was saved into a JSON file
        System.out.println("\nOutput saved to tokens.json");
    }
}

