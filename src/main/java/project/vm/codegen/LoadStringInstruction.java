package project.vm.codegen;

public class LoadStringInstruction extends Instruction{
    private final RegisterName register;
    private final String value;

    public LoadStringInstruction(String label, RegisterName register, String value) {
        super(label, "LOAD_STRING");
        this.register = register;
        this.value = value;
    }

    @Override
    public int execute(VM vm) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            vm.setRegister(register, value.substring(1, value.length() - 1));
            //System.out.println("check string");
        } else {
            Object variableValue = vm.getVariable(value);
            //System.out.println("check var");
            if (variableValue != null) {
                vm.setRegister(register, variableValue);
            } else {
                throw new IllegalArgumentException("Unknown variable: " + value);
            }
        }
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "LOAD "   + register + ", " + value ;
    }
}
