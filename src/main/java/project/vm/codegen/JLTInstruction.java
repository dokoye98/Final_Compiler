package project.vm.codegen;

public class JLTInstruction extends Instruction {
    private final RegisterName reg1;
    private final RegisterName reg2;

    private final Instruction instruction;
    public JLTInstruction(String label, RegisterName reg1, RegisterName reg2, Instruction instruction) {
        super(label, "JGE");
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.instruction = instruction;
    }

    @Override
    public int execute(VM vm) {

        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "JLT " + reg1 + ", " + reg2 + ", " + instruction;
    }
}
