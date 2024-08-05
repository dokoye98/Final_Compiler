package project.vm.codegen;

public class CallInstruction extends Instruction {
    private final String functionName;
    private final Object value;



    public CallInstruction(String label, String functionName, Object value) {
        super(label, "CALL");
        this.functionName = functionName;
        this.value = value;
    }





    @Override
    public int execute(VM vm) {
        if ("print".equals(functionName)) {
           if(value instanceof RegisterName){
               System.out.println(vm.getRegister((RegisterName) value));
           }else if (value instanceof String){
               Object varValue = vm.getVariable((String) value);
               if(varValue == null){
                   System.out.println(value);
               }else{
                   System.out.println(vm.getVariable((String) value));
               }
           }
        }
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return getOpcode() + " " + functionName + ", " + value;
    }
}
