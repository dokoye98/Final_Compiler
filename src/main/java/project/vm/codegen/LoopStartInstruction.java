package project.vm.codegen;

public class LoopStartInstruction extends Instruction {
    private final RegisterName counterReg;

    public LoopStartInstruction(String label, RegisterName counterReg) {
        super(label, "Loop_Start");
        this.counterReg = counterReg;
    }

    @Override
    public int execute(VM vm) {
          vm.setRegister(counterReg,0);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "L";
    }
}
