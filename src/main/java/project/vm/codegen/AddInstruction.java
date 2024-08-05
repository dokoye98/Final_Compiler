package project.vm.codegen;

public class AddInstruction extends Instruction{

    private final RegisterName operand1;
    private final RegisterName result;

    private final RegisterName operand2;

    public AddInstruction(String label, RegisterName result, RegisterName operand1, RegisterName operand2) {
        super(label, "ADD");
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int execute(VM vm) {
        String value1 = (String) vm.getRegister(operand1);
        String value2 = (String) vm.getRegister(operand2);
        int intValue1 = Integer.parseInt(value1, 2);
        int intValue2 = Integer.parseInt(value2, 2);
        int total= intValue1+intValue2;
        String bin = Integer.toBinaryString(total);
        vm.setRegister(result,bin);
        return vm.getProgramCounter() + 1;

    }

    @Override
    public String toString() {
        return getOpcode() + " " + result + ", " + operand1 + " , " + operand2;
    }
}
