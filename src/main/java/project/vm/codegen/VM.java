package project.vm.codegen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VM {
    private final List<Instruction> instructions;
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<RegisterName, Object> registers = new HashMap<>();
    private int programCounter = 0;
    private final int[] memory;
    private int memoryCounter = 0;
    private final Map<String, Integer> labelAddresses = new HashMap<>();
    private boolean comparisonFlag ;

    public VM(List<Instruction> instructions) {
        this.instructions = instructions;
        this.memory = new int[1024];
        registerStarter();
        resolveLabelAddresses();
    }

    private void registerStarter() {
        for (RegisterName register : RegisterName.values()) {
            registers.put(register, 0);
        }
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public Object getRegister(RegisterName register) {
        return registers.get(register);
    }

    public void setRegister(RegisterName register, Object value) {
        registers.put(register, value);
    }

    public void setVariable(String variableName, Object value) {
        variables.put(variableName, value);
    }

    public Object getVariable(String variableName) {
        return variables.get(variableName);
    }

    public boolean getComparisonFlag() {
        return comparisonFlag;
    }

    public void setComparisonFlag(boolean comparisonFlag) {
        this.comparisonFlag = comparisonFlag;
    }

    public int getLabelAddress(String label) {
        return labelAddresses.getOrDefault(label, -1);
    }

    public void setLabelAddress(String label, int address) {
        labelAddresses.put(label, address);
    }

    public int getMemory(int address) {
        return memory[address];
    }

    public void setMemory(int address, int value) {
        memory[address] = value;
    }

    public int allocateMemory() {
        return memoryCounter++;
    }

    public void execute() {
        while (programCounter < instructions.size()) {
            Instruction instruction = instructions.get(programCounter);
            System.out.println(instruction);
            programCounter = instruction.execute(this);
        }
    }

    private void resolveLabelAddresses() {
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.getLabel() != null && !instruction.getLabel().isEmpty()) {
                System.out.println("Mapping label " + instruction.getLabel() + " to address " + i);
                setLabelAddress(instruction.getLabel(), i);
            }
        }
    }



    public Object getArrayElement(String arrayName, int index) {
        Object array = variables.get(arrayName);
        if (array instanceof List) {
            List<?> arrayList = (List<?>) array;
            if (index >= 0 && index < arrayList.size()) {
                return arrayList.get(index);
            } else {
                throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + arrayList.size());
            }
        } else {
            throw new IllegalArgumentException("Variable " + arrayName + " is not an array");
        }
    }


    public void printState() {
        System.out.println("Registers: ");
        registers.forEach((key, value) -> System.out.println(key + " = " + value));
        System.out.println("Variables: ");
        variables.forEach((key, values) -> System.out.println(key + " = " + values));
        System.out.println("Memory: ");
        for (int i = 0; i < memoryCounter; i++) {
            System.out.println("Memory[" + i + "] = " + memory[i]);
        }
        System.out.println();
    }
}
