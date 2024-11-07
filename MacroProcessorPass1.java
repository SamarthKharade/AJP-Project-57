import java.util.*;

class MacroProcessorPass1 {
    // Data Structures
    private Map<String, Integer> MNT = new HashMap<>(); // Macro Name Table
    private List<String> MDT = new ArrayList<>(); // Macro Definition Table
    private List<String> ALA = new ArrayList<>(); // Argument List Array
    private int MDT_Index = 0; // Index for MDT

    // Function to process the macro definition
    public void processMacroDefinition(List<String> sourceCode) {
        boolean isMacroDefinition = false;
        String macroName = "";

        for (String line : sourceCode) {
            String[] tokens = line.split("\\s+");
            if (tokens[0].equals("MACRO")) {
                isMacroDefinition = true;
                continue;
            }

            if (isMacroDefinition) {
                // Handle the macro name
                if (macroName.isEmpty()) {
                    macroName = tokens[0];
                    MNT.put(macroName, MDT_Index); // Add to MNT
                    // Collect arguments in ALA
                    if (tokens.length > 1) {
                        ALA.addAll(Arrays.asList(tokens).subList(1, tokens.length));
                    }
                } else {
                    // Store macro definition in MDT
                    MDT.add(line);
                    MDT_Index++;
                    // Check if macro definition ends
                    if (line.equals("MEND")) {
                        isMacroDefinition = false;
                        macroName = "";
                    }
                }
            } else {
                // Non-macro lines can be handled in Pass 2
                System.out.println("Non-macro line: " + line);
            }
        }
    }

    // Display contents of MNT, MDT, and ALA
    public void displayTables() {
        System.out.println("MNT (Macro Name Table):");
        for (Map.Entry<String, Integer> entry : MNT.entrySet()) {
            System.out.println("Macro Name: " + entry.getKey() + ", MDT Index: " + entry.getValue());
        }

        System.out.println("\nMDT (Macro Definition Table):");
        for (int i = 0; i < MDT.size(); i++) {
            System.out.println(i + ": " + MDT.get(i));
        }

        System.out.println("\nALA (Argument List Array):");
        for (int i = 0; i < ALA.size(); i++) {
            System.out.println(i + ": " + ALA.get(i));
        }
    }

    public static void main(String[] args) {
        MacroProcessorPass1 pass1 = new MacroProcessorPass1();

        // Sample input: Source code containing macros and non-macro lines
        List<String> sourceCode = Arrays.asList(
            "MACRO",
                "INCR &ARG1, &ARG2",
                "LCL &TEMP",
                "ADD &TEMP, &ARG1",
                "ADD &TEMP, &ARG2",
                "MEND",
                "MACRO",
                "DECR &ARG1, &ARG2",
                "LCL &TEMP",
                "ADD &TEMP, &ARG1",
                "ADD &TEMP, &ARG2",
                "MEND",
                "START",
                "INCR A, B",
                "END"
        );

        // Process macro definitions (Pass 1)
        pass1.processMacroDefinition(sourceCode);

        // Display the contents of MNT, MDT, and ALA
        pass1.displayTables();
    }
}