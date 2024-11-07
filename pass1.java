import java.util.*;
import java.io.*;

public class pass1 {
    // Location Counter
    private static int LC = 0;
    
    // Tables to store symbols, literals, and intermediate code
    private static Map<String, Integer> symbolTable = new LinkedHashMap<>();
    private static List<String> literalTable = new ArrayList<>();
    private static List<String> intermediateCode = new ArrayList<>();
    
    public static void main(String[] args) {
        // Sample input file (assembly program)
        String[] program = {
            "START 100",
            "AGAIN MOVER AREG, BETA",
            "ADD AREG, ALPHA",
            "LTORG",
            "BETA DC '5'",
            "ALPHA DS 1",
            "END"
        };
        
        // Process each line in the program
        for (String line : program) {
            processLine(line);
        }
        
        // Display the output tables and intermediate code
        System.out.println("Symbol Table:");
        symbolTable.forEach((symbol, address) -> System.out.println(symbol + "\t" + address));
        
        System.out.println("\nLiteral Table:");
        for (int i = 0; i < literalTable.size(); i++) {
            System.out.println(literalTable.get(i) + "\t" + (LC + i));
        }
        
        System.out.println("\nIntermediate Code:");
        intermediateCode.forEach(System.out::println);
    }

    // Method to process each line of the assembly program
    private static void processLine(String line) {
        String[] parts = line.split(" ");
        String command = parts[0];
        
        switch (command) {
            case "START":
                LC = Integer.parseInt(parts[1]);
                intermediateCode.add("(AD, 01) " + parts[1]); // Start directive with address
                break;
                
            case "MOVER":
            case "ADD":
                String opcode = command.equals("MOVER") ? "(IS, 04)" : "(IS, 01)";
                String operand1 = parts[1].split(",")[0];
                String operand2 = parts[1].split(",")[1];
                
                if (!symbolTable.containsKey(operand2)) {
                    symbolTable.put(operand2, -1); // Placeholder for undefined symbols
                }
                
                intermediateCode.add(opcode + " " + operand1 + " " + operand2);
                LC++;
                break;
                
            case "DC":
            case "DS":
                String symbol = parts[0];
                int size = command.equals("DC") ? 1 : Integer.parseInt(parts[1]);
                
                if (!symbolTable.containsKey(symbol)) {
                    symbolTable.put(symbol, LC);
                }
                
                LC += size;
                intermediateCode.add("(DL, " + (command.equals("DC") ? "02" : "01") + ") " + parts[1]);
                break;
                
            case "LTORG":
                for (String literal : literalTable) {
                    symbolTable.put(literal, LC);
                    LC++;
                }
                literalTable.clear();
                intermediateCode.add("(AD, 05)"); // LTORG directive
                break;
                
            case "END":
                intermediateCode.add("(AD, 02)");
                break;
                
            default:
                // If it's a label or symbol
                if (!symbolTable.containsKey(command)) {
                    symbolTable.put(command, LC);
                }
                break;
        }
    }
}
