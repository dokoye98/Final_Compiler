package project.vm.codegen;

public class JumpInstruction extends Instruction {
    private final Instruction targetLabel;

    public JumpInstruction(String label, Instruction targetLabel) {
        super(label, "JUMP");
        this.targetLabel = targetLabel;
    }

    @Override
    public int execute(VM vm) {

        return vm.getProgramCounter() +1;
    }

    @Override
    public String toString() {
        return "JUMP " + targetLabel;
    }
}
