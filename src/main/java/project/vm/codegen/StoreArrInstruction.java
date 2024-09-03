package project.vm.codegen;

import java.util.ArrayList;
import java.util.List;

public class StoreArrInstruction  extends Instruction {
    private final String arrayName;
    private final List<RegisterName> elements;

    public StoreArrInstruction(String label, String arrayName, List<RegisterName> elements) {
        super(label, "STORE_ARRAY");
        this.arrayName = arrayName;
        this.elements = elements;
    }

    @Override
    public int execute(VM vm) {
        List<Object> array = new ArrayList<>();
        for (RegisterName reg : elements) {
            array.add(vm.getRegister(reg));
        }
        vm.setVariable(arrayName, array);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "STORE_ARRAY " + arrayName + " = " + elements;
    }
}
