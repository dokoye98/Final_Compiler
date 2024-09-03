package project.compiler.nodes.binaryVarsEqual;

import project.compiler.nodes.BinaryAssignNode;
import project.compiler.nodes.VariableAssignmentNode;

public class MulVarEquals extends BinaryAssignNode {

        public MulVarEquals(String operation, String variableName, VariableAssignmentNode variableOne, VariableAssignmentNode variableTwo) {
            super(operation, variableName, variableOne, variableTwo);
        }

        @Override
        public String getType() {
            return "Integer";
        }


}
