package project.vm.codegen;

public class CompareInstruction  extends Instruction {
    private final RegisterName reg1;
    private final RegisterName reg2;

    public CompareInstruction(String label, RegisterName reg1, RegisterName reg2) {
        super(label, "CMP");
        this.reg1 = reg1;
        this.reg2 = reg2;
    }

    @Override
    public int execute(VM vm) {
        int value1 = (int) vm.getRegister(reg1);
        int value2 = (int) vm.getRegister(reg2);
        vm.setComparisonFlag(value1 == value2);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "CMP "+ reg1 + " "+ reg2;
    }
}
