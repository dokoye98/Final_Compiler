package InstructionTest;

import org.junit.Test;
import project.vm.codegen.*;

import java.util.ArrayList;
import java.util.List;

public class InstructionsTest {

    @Test
    public void loopTest(){
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new LoadInstruction(null, RegisterName.R0, "33"));
        instructions.add(new StoreInstruction(null, RegisterName.R0, "a"));
        instructions.add(new LoadInstruction(null, RegisterName.R1, "66"));
        instructions.add(new StoreInstruction(null, RegisterName.R1, "b"));
        instructions.add(new MovInstruction(null, RegisterName.R2, "a"));
        instructions.add(new MovInstruction(null, RegisterName.R3, "b"));
        instructions.add(new AddInstruction(null, RegisterName.R4, RegisterName.R2, RegisterName.R3));
        instructions.add(new MovInstruction(null, "result", RegisterName.R4));
        instructions.add(new CallInstruction(null, "print", RegisterName.R4));

        VM vm = new VM(instructions); //after a commit it started looping want to add dynamic memory again(cause)
        vm.execute();
    }
}
