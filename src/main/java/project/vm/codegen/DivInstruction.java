package project.vm.codegen;

public class DivInstruction extends Instruction{

    private final Object operand1;
    private final Object result;

    private final Object operand2;

    public DivInstruction(String label, Object result, Object operand1, Object operand2) {
        super(label, "DIV");
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int execute(VM vm) {
        if(operand1 instanceof RegisterName && operand2 instanceof RegisterName) {
            String value1 = (String) vm.getRegister((RegisterName) operand1);
            String value2 = (String) vm.getRegister((RegisterName) operand2);
            int intValue1 = Integer.parseInt(value1, 2);
            int intValue2 = Integer.parseInt(value2, 2);
            int total = intValue1 / intValue2;
            String bin = Integer.toBinaryString(total);
            vm.setRegister((RegisterName) result, bin);
        }else if(operand1 instanceof String && operand2 instanceof String && result instanceof String){
            int value1 = (int) vm.getVariable((String) operand1);
            int value2 = (int) vm.getVariable((String) operand2);
            int total = value1 / value2;
            String bin = Integer.toBinaryString(total);
            vm.setVariable((String) result, bin);
        }
        return vm.getProgramCounter() + 1;

    }

    @Override
    public String toString() {
        return getOpcode() + " " + result + ", " + operand1 + " , " + operand2;
    }
}
