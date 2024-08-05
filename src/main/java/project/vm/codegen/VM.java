package project.vm.codegen;

import project.vm.codegen.Instruction;
import project.vm.codegen.RegisterName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VM {
    private final List<Instruction> instructions;
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<RegisterName, Object> registers = new HashMap<>();
    private int programCounter = 0;
    private int[] memory;
    private int memoryCounter = 0;

    public VM(List<Instruction> instructions) {
        this.instructions = instructions;
        this.memory = new int[1024]; // Initialize memory with a fixed size
        registerStarter();
    }

    private void registerStarter() {
        for (RegisterName register : RegisterName.values()) {
            registers.put(register, 0);
        }
    }

    public int getProgramCounter() {
        return programCounter;
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

    public void execute() {
        while (programCounter < instructions.size()) {
            Instruction instruction = instructions.get(programCounter);
            System.out.println(instruction);
            programCounter = instruction.execute(this);
            //printState(); annoyingly clunky
        }
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
