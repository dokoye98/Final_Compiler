package project.vm.codegen;

public class StoreHeapInstruction extends Instruction{
    private final RegisterName base;
    private final int offset;
    private final Object value;

    public StoreHeapInstruction(String label, RegisterName base, int offset, Object value) {
        super(label, "STORE_HEAP");
        this.base = base;
        this.offset = offset;
        this.value = value;
    }

    @Override
    public int execute(VM vm) {
      /*  int address = (int) vm.getRegister(base) + offset;
        vm.getMemory().store(address, value);*/
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + base + "[" + offset + "], " + value;
    }
}
