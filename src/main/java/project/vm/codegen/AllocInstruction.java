package project.vm.codegen;

public class AllocInstruction extends Instruction{
    private final RegisterName result;
    private final int size;


    public AllocInstruction(String label, RegisterName result, int size) {
        super(label, "ALLOC");
        this.result = result;
        this.size = size;
    }


    @Override
    public int execute(VM vm) {
        //int address = vm.allocateMemory();
        //vm.setRegister(result,address);
        return vm.getProgramCounter()+1;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + ", " + size;
    }
}
