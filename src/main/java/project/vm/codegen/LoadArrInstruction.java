package project.vm.codegen;

public class LoadArrInstruction extends Instruction {

    private final String arrPlace;
    private final RegisterName arrDest;
    private final RegisterName arrIndex;


    public LoadArrInstruction(String label, String arrPlace, RegisterName arrDest, RegisterName arrIndex) {
        super(label, "LOAD ARR");
        this.arrPlace = arrPlace;
        this.arrDest = arrDest;
        this.arrIndex = arrIndex;
    }

    @Override
    public int execute(VM vm) {
        int place = (int) vm.getRegister(arrIndex);
        int element = (int) vm.getVariable(arrPlace+"["+place+"]");
        vm.setRegister(arrDest, element);
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return "LOAD ARR  " + arrDest + ", " + arrPlace+"["+arrIndex+"]";
    }
}
