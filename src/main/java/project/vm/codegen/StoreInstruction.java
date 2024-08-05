package project.vm.codegen;

public class StoreInstruction extends Instruction{

    private final RegisterName register;
    private final String variableName;

    public StoreInstruction(String label,  RegisterName register, String variableName) {
        super(label, "STORE");
        this.register = register;
        this.variableName = variableName;
    }

    @Override
    public int execute(VM vm) {
        Object value =  vm.getRegister(register);
        vm.setVariable(variableName, value);
        return vm.getProgramCounter() + 1;

    }

    @Override
    public String toString() {
        return getOpcode() + " "  + variableName + " , " + register;
    }


}
