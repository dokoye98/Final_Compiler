package project.vm.codegen;

import project.compiler.nodes.ArrayAssignmentNode;
import project.compiler.nodes.ExpressionNode;

import java.util.List;

public class CountInstruction  extends Instruction {

    private final ArrayAssignmentNode array;
    private final RegisterName Destination;

    public CountInstruction(String label, ArrayAssignmentNode array,  RegisterName destination) {
        super(label, "LOAD");
        this.array = array;
        Destination = destination;
    }

    @Override
    public int execute(VM vm) {

int size = array.getElements().size();

        vm.setRegister(Destination, size);


        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "LOAD " + Destination + ", Length("+ array.getArrayName()+ ",(" + array.getElements().size()+ "))" ;
    }
}
