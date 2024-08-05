package project.vm.codegen;

public class LoadHeapInstruction extends Instruction{
    private final RegisterName destination;
    private final RegisterName base;
    private final int offset;

    public LoadHeapInstruction(String label, RegisterName destination, RegisterName base, int offset) {
        super(label, "LOAD_HEAP");
        this.destination = destination;
        this.base = base;
        this.offset = offset;
    }

    @Override
    public int execute(VM vm) {
      /*  int address = (int) vm.getRegister(base) + offset;
        Object value = vm.load(address);
        vm.setRegister(destination, (Integer) value);*/
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + destination + ", " + base + "[" + offset + "]";
    }
}
