package project.vm.codegen;

import project.compiler.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    private int registerCounter = 0;

    private RegisterName getNextRegister() {
        return RegisterName.values()[registerCounter++];
    }


    public List<Instruction> generateCode(Node ast) {
        List<Instruction> instructions = new ArrayList<>();
         if (ast instanceof LiteralNode) {
            String literlValue = ((LiteralNode) ast).getValue();
            instructions.add(new LoadInstruction(null, RegisterName.R1, literlValue));
        } else if (ast instanceof VariableAssignmentNode) {
            VariableAssignmentNode variableAssignment = (VariableAssignmentNode) ast;
            if (variableAssignment.getExpression() instanceof LiteralNode) {
                String variableName = variableAssignment.getVariableName();
                String literalValue = ((LiteralNode) variableAssignment.getExpression()).getValue();
                instructions.add(new LoadInstruction(null, RegisterName.R0, "\"" + literalValue + "\""));
                instructions.add(new StoreInstruction(null, RegisterName.R0, variableName));
            }
        } else if (ast instanceof PrintStatementNode) {
            PrintStatementNode printNode = (PrintStatementNode) ast;
            if (printNode.getExpression() instanceof LiteralNode) {

                LiteralNode literalNode = (LiteralNode) printNode.getExpression();
                RegisterName register = getNextRegister();
                instructions.add(new LoadInstruction(null, register, "\"" + literalNode.getValue() + "\""));
                instructions.add(new CallInstruction(null, "print", register));
            } else if (printNode.getExpression() instanceof VariableNode) {
                VariableNode variableNode = (VariableNode) printNode.getExpression();
                RegisterName register = getNextRegister();
                instructions.add(new LoadInstruction(null, register, variableNode.getName()));
                instructions.add(new CallInstruction(null, "print", register));
            }
        } /*else if(ast instanceof BinaryAssignNode){
             BinaryAssignNode binaryAssignNode = (BinaryAssignNode) ast;
             String operator = binaryAssignNode.getOperation();

             instructions.add(new LoadInstruction(null, RegisterName.R0, ((LiteralNode) binaryAssignNode.getValue()).getValue()));
             instructions.add(new LoadInstruction(null, RegisterName.R1, ((LiteralNode) binaryAssignNode.getSecondValue()).getValue()));
             instructions.add(new AddInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
             instructions.add(new MovInstruction(null,binaryAssignNode.getVariableName(),RegisterName.R2));
            // instructions.add(new CallInstruction(null, "print", ));
         }*/
         else if (ast instanceof BinaryOperationNode) {
             BinaryOperationNode binaryOperationNode = (BinaryOperationNode) ast;
             String operator = binaryOperationNode.getOperator();


                 instructions.add(new LoadInstruction(null, RegisterName.R0, ((LiteralNode) binaryOperationNode.getLeft()).getValue()));
             instructions.add(new LoadInstruction(null, RegisterName.R1, ((LiteralNode) binaryOperationNode.getRight()).getValue()));
             switch (operator){
                 case "+":
                     instructions.add(new AddInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                     break;
                 case "-":
                     instructions.add(new SubInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                     break;
                 case "*":
                     instructions.add(new MulInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                     break;
                 case "/":
                     instructions.add(new DivInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                     break;
             }


         }else if(ast instanceof PrintVariableNode){
            PrintVariableNode printNode = (PrintVariableNode) ast;
            String variableName = printNode.getVariableName();
            ExpressionNode value = printNode.getVariableValue();
            if(value instanceof LiteralNode){
                String literalValue =  ((LiteralNode) value).getValue();
                instructions.add(new LoadInstruction(null, RegisterName.R0, "\"" + literalValue + "\""));
                instructions.add(new StoreInstruction(null, RegisterName.R0, variableName));
                instructions.add(new LoadInstruction(null, RegisterName.R0, variableName));

                instructions.add(new CallInstruction(null, "print", RegisterName.R0));

            }
        }else if(ast instanceof BinaryAssignmentNode){
            BinaryAssignmentNode binaryAssignmentNode = (BinaryAssignmentNode) ast;
            String operator = binaryAssignmentNode.getOperation();


                instructions.add(new LoadInstruction(null, RegisterName.R0, ((LiteralNode) binaryAssignmentNode.getValue()).getValue()));
                instructions.add(new StoreInstruction(null, RegisterName.R0,binaryAssignmentNode.getVariableName() ));
                instructions.add(new LoadInstruction(null, RegisterName.R1, ((LiteralNode) binaryAssignmentNode.getSecondValue()).getValue()));

            switch (operator){
                case "+":
                    instructions.add(new AddInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                    break;
                case "-":
                    instructions.add(new SubInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                    break;
                case "*":
                    instructions.add(new MulInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                    break;
                case "/":
                    instructions.add(new DivInstruction(null,RegisterName.R2,RegisterName.R0,RegisterName.R1));
                    break;
            }

        }else if(ast instanceof BinaryVarExtended){
            BinaryVarExtended binaryVarExtended = (BinaryVarExtended) ast;
            String variableName1 = binaryVarExtended.getVariableName();
            String variableName2 = binaryVarExtended.getVariableName2();
            instructions.add(new LoadInstruction(null, RegisterName.R0, ((LiteralNode) binaryVarExtended.getExpression()).getValue()));
            instructions.add(new StoreInstruction(null, RegisterName.R0,variableName1 ));
            instructions.add(new LoadInstruction(null, RegisterName.R1, ((LiteralNode) binaryVarExtended.getValue2()).getValue()));
            instructions.add(new StoreInstruction(null, RegisterName.R1,variableName2 ));
            instructions.add(new MovInstruction(null,RegisterName.R2,variableName1));
            instructions.add(new MovInstruction(null,RegisterName.R3,  variableName2));
            switch (binaryVarExtended.getOperation()){
                case "+":
                    instructions.add(new AddInstruction(null,RegisterName.R4,RegisterName.R2,RegisterName.R3));
                    break;
                case "-":
                    instructions.add(new SubInstruction(null,RegisterName.R4,RegisterName.R2,RegisterName.R3));
                    break;
                case "*":
                    instructions.add(new MulInstruction(null,RegisterName.R4,RegisterName.R2,RegisterName.R3));
                    break;
                case "/":
                    instructions.add(new DivInstruction(null,RegisterName.R4,RegisterName.R2,RegisterName.R3));
                    break;
            }
        }
        return instructions;
    }

}