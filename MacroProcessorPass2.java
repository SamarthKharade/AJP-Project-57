import java.util.*;
import java.io.*;

public class MacroProcessorPass2 {

    // Macro Name Table (MNT) maps macro names to their corresponding index in MDT
    private static Map<String, Integer> MNT = new HashMap<>();
    
    // Macro Definition Table (MDT) contains the actual macro bodies
    private static List<List<String>> MDT = new ArrayList<>();
    
    // Intermediate Code generated in Pass-I
    private static List<String> intermediateCode = new ArrayList<>();
    
    // Final machine code after macro expansion
    private static List<String> finalCode = new ArrayList<>();

    public static void main(String[] args) {
        // Sample MNT and MDT (these would be read from the output of Pass-I in a real scenario)
        // MNT: Macro name -> Index of macro in MDT
        MNT.put("ADD_TWO", 0);

        // MDT: Macro definitions at indices
        MDT.add(Arrays.asList("MOV R1, &1", "ADD R1, &2", "MOV RESULT, R1"));
        
        // Sample intermediate code (this would be the output of Pass-I)
        intermediateCode.add("MOV R1, 5");
        intermediateCode.add("ADD_TWO R1, 10"); // Macro call
        intermediateCode.add("END");

        // Process Pass-II: Expand macros
        processPassTwo();

        // Output the final machine code after macro expansion
        System.out.println("Final Expanded Code:");
        for (String line : finalCode) {
            System.out.println(line);
        }
    }

    // Process each line of the intermediate code
    private static void processPassTwo() {
        for (String line : intermediateCode) {
            String[] parts = line.split(" ");
            
            // Check if the line is a macro call
            if (MNT.containsKey(parts[0])) {
                // It's a macro call, so expand it
                expandMacro(parts);
            } else {
                // It's not a macro, so add it directly to the final code
                finalCode.add(line);
            }
        }
    }

    // Expand the macro by replacing parameters with actual arguments
    private static void expandMacro(String[] macroCallParts) {
        String macroName = macroCallParts[0];
        int macroIndex = MNT.get(macroName);  // Get the index of the macro in the MDT
        
        // Get the macro body from the MDT
        List<String> macroBody = MDT.get(macroIndex);

        // Replace parameters in the macro body with actual arguments
        for (String macroLine : macroBody) {
            String expandedLine = macroLine;
            for (int i = 1; i < macroCallParts.length; i++) {
                expandedLine = expandedLine.replace("&" + i, macroCallParts[i]);
            }
            // Add the expanded line to the final code
            finalCode.add(expandedLine);
        }
    }
}
