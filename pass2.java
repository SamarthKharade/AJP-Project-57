import java.util.*;
import java.io.*;

public class pass2 {
    // Location Counter
    private static int LC = 0;

    // Tables from Pass 1
    private static Map<String, Integer> symbolTable = new LinkedHashMap<>();
    private static List<String> literalTable = new ArrayList<>();
    private static List<String> intermediateCode = new ArrayList<>();

    public static void main(String[] args) {
        // Sample input from Pass 1 (for simulation purposes)
        symbolTable.put("AGAIN", 100);
        symbolTable.put("BETA", 103);
        symbolTable.put("ALPHA", 104);
        
        literalTable.add("'5'");

        intermediateCode.add("(AD, 01) 100");
        intermediateCode.add("(IS, 04) AREG BETA");
        intermediateCode.add("(IS, 01) AREG ALPHA");
        intermediateCode.add("(AD, 05)");
        intermediateCode.add("(DL, 02) '5'");
        intermediateCode.add("(DL, 01) 1");
        intermediateCode.add("(AD, 02)");

        // Process the intermediate code for Pass 2
        processPassTwo();

        // Output the final machine code
        System.out.println("Final Machine Code:");
        for (String line : intermediateCode) {
            System.out.println(line);
        }
    }

    // Process intermediate code and replace symbols with their addresses
    private static void processPassTwo() {
        for (int i = 0; i < intermediateCode.size(); i++) {
            String line = intermediateCode.get(i);
            String[] parts = line.split(" ");
            String instruction = parts[0];

            // Replace symbols and literals in the instruction
            if (instruction.equals("(IS, 04)") || instruction.equals("(IS, 01)")) {
                String operand1 = parts[1];
                String operand2 = parts[2];

                // Replace symbol with actual address from the symbol table
                if (symbolTable.containsKey(operand2)) {
                    operand2 = String.valueOf(symbolTable.get(operand2));
                }
                
                // Update the intermediate code with the resolved address
                intermediateCode.set(i, instruction + " " + operand1 + " " + operand2);
            } else if (instruction.equals("(DL, 02)") || instruction.equals("(DL, 01)")) {
                // For literals, replace with actual address from the literal table
                String literal = parts[1];
                if (literalTable.contains(literal)) {
                    int literalAddress = LC;
                    intermediateCode.set(i, instruction + " " + literal + " " + literalAddress);
                    LC++; // Increment LC for the literal address
                }
            } else if (instruction.equals("(AD, 01)")) {
                // For START directive, just keep the address
                String address = parts[1];
                intermediateCode.set(i, instruction + " " + address);
            } else if (instruction.equals("(AD, 05)")) {
                // For LTORG directive, handle literals
                intermediateCode.set(i, "(AD, 05)");
            } else if (instruction.equals("(AD, 02)")) {
                // For END directive, just add it as is
                intermediateCode.set(i, "(AD, 02)");
            }
        }
    }
}
