package project.vm.codegen;

public class LoadInstruction extends Instruction {

    private final RegisterName register;
    private final String value;
    public LoadInstruction(String label, RegisterName register, String value) {
        super(label, "LOAD");
        this.register = register;
        this.value = value;
    }

    @Override
    public int execute(VM vm) {

        if (value.matches("-?\\d+")) {
            vm.setRegister(register, Integer.parseInt(value));
        } else {
            Object varValue = vm.getVariable(value);
            if (varValue == null) {
                throw new IllegalArgumentException("Unknown variable: " + value);
            }
            vm.setRegister(register, (Integer) varValue);
        }
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return  getOpcode() + " " + register + ", " + value;
    }
}
