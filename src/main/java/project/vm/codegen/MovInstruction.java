package project.vm.codegen;

public class MovInstruction extends Instruction{
    private final Object endPoint; //variable manipulation
    private final Object source; // been having issues with load class so need to offload issues here

    public MovInstruction(String label,  Object endPoint, Object source) {
        super(label, "MOV");
        this.endPoint = endPoint;
        this.source = source;
    }

    @Override
    public int execute(VM vm) {
       // Object value;
      if (endPoint instanceof String && source instanceof String) {
            // RegisterName register = (RegisterName) source;
            Object varValue =  vm.getVariable((String) source);
            vm.setVariable((String)endPoint,varValue);

        }else if(endPoint instanceof RegisterName){
         RegisterName register = (RegisterName) endPoint;
         try{
             int varValue = (int) vm.getVariable((String) source);
             vm.setRegister(register,varValue);
         }catch (Exception e){
             String varValue = (String) vm.getVariable((String) source);
             vm.setRegister(register,varValue);
         }

     } else if (endPoint instanceof String) {
        // RegisterName register = (RegisterName) source;
         Object varValue =  vm.getRegister((RegisterName) source);
         vm.setVariable((String)endPoint,varValue);

     } else {
         throw new IllegalArgumentException("Unknown end point type.");
     }
        return vm.getProgramCounter() + 1;
    }

    @Override
    public String toString() {
        return  getOpcode() + " " + endPoint + ", " + source;
    }
}
