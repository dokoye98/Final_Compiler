package project.compiler.assemblyInstructions;

import project.compiler.nodes.*;

public class AssemblyGenerator {
    public static String assemble(Node ast) {

        if (ast instanceof PrintVariableNode) {
            return PrintVariableSetUp.printVariableAssemble(ast);
        } else if (ast instanceof PrintStatementNode) {
            return PrintSetUp.printAssembly(ast);

        } else if (ast instanceof BinaryOperationNode) {
            return BinarySetUp.binaryOperationAssemble(ast);
        } else if (ast instanceof BinaryAssignmentNode) {
            return BinaryVarSetUp.binaryVarSetUp(ast);
        }else if(ast instanceof BinaryAssignNode ){
            return PrintBinarySetUp.printVarBinarySetUp(ast);
        }
            else {
            return null;
        }

    }
}
