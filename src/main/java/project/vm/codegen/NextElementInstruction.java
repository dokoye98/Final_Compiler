package project.vm.codegen;

public class NextElementInstruction extends Instruction {
    private final RegisterName counterReg;



    public NextElementInstruction(String label, RegisterName counterReg) {
        super(label, "NEXT_ELEMENT");
        this.counterReg = counterReg;

    }

    @Override
    public int execute(VM vm) {
        int currentValue = (int) vm.getRegister(counterReg);
        vm.setRegister(counterReg, currentValue + 1);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "NEXT_ELEMENT " + counterReg;
    }
}
