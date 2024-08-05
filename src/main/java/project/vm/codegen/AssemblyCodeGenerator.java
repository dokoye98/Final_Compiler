package project.vm.codegen;

import project.compiler.syntaxfunctions.StringChecker;

import java.util.HashMap;
import java.util.Map;

public class AssemblyCodeGenerator {

    private Map<String,Integer> addressMap = new HashMap<>();
    private int addressCounter = 0;
    private int getAddressId(String address){
        return addressMap.computeIfAbsent(address,k->addressCounter++);
    }
    private int getNextAddressId(){
        return addressCounter++;
    }
    public String assemblyGenerator(String tacCode){
        StringBuilder assembly = new StringBuilder();
        String[] tacCodeLines = tacCode.split("\n");
        for(String line: tacCodeLines){
            line = line.trim();
            if(line.startsWith("t")) {

                String[] bits = line.split(" = ");
                String var = bits[0].trim();
                String value = bits[1].trim().replace(";", "");
                assembly.append("Spl t0, ").append(value).append("\n");
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = StringChecker.cleanLiteral(value);
                    String[] literals = value.split(" ");
                    for (String literal : literals) {
                        int registerId = getNextAddressId();
                        assembly.append("Mov R").append(registerId).append(", ").append(literal).append("\n");
                        assembly.append("PRINT R").append(registerId).append("\n");
                    }
                } else {
                    int varId = getAddressId(var);
                    assembly.append("MOV R").append(varId).append(", ").append(value).append("\n");
                }
            }
        }
        return assembly.toString();
    }
}
