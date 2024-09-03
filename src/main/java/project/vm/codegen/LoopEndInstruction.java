package project.vm.codegen;

public class LoopEndInstruction extends Instruction {
    private final RegisterName counterReg;
    private final int counter;
    public LoopEndInstruction(String label, RegisterName counterReg, int counter) {
        super(label, "Loop_Start");
        this.counterReg = counterReg;
        this.counter = counter;
    }

    @Override
    public int execute(VM vm) {
          vm.getRegister(counterReg);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "L"+counter;
    }
}

