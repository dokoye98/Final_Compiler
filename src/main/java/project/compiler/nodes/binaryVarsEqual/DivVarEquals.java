package project.compiler.nodes.binaryVarsEqual;

import project.compiler.nodes.BinaryAssignNode;
import project.compiler.nodes.VariableAssignmentNode;

public class DivVarEquals extends BinaryAssignNode {

        public DivVarEquals(String operation, String variableName, VariableAssignmentNode variableOne, VariableAssignmentNode variableTwo) {
            super(operation, variableName, variableOne, variableTwo);
        }

        @Override
        public String getType() {
            return "Integer";
        }


}
