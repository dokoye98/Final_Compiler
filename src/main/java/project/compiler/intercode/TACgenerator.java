package project.compiler.intercode;

import project.compiler.nodes.*;
import project.vm.codegen.*;

import java.util.ArrayList;
import java.util.List;

public class TACgenerator {
    private int tempVarCounter = 0;




    public List<Instruction> generateCode(Node ast) {
        List<Instruction> instructions = new ArrayList<>();
        if (ast instanceof PrintStatementNode printNode) {
            ExpressionNode value = printNode.getExpression();
            RegisterName reg1 = getNextRegister();
            if (value instanceof LiteralNode) {
                String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
                instructions.add(new LoadStringInstruction(null, reg1,literalString ));
                instructions.add(new CallInstruction(null, "print", reg1));
            }
            else if (value instanceof VariableNode) {
                String variableName = ((VariableNode) value).getName();
                instructions.add(new MovInstruction(null, RegisterName.R1, variableName));
                instructions.add(new CallInstruction(null, "print", RegisterName.R1));
            }

        } else if (ast instanceof BinaryOperationNode binaryOperationNode) {
            ExpressionNode left = binaryOperationNode.getLeft();
            ExpressionNode right = binaryOperationNode.getRight();
            String operation = binaryOperationNode.getOperator();

            if (left instanceof LiteralNode && right instanceof LiteralNode) {
                RegisterName reg1 = getNextRegister();
                RegisterName reg2 = getNextRegister();
                RegisterName regResult = getNextRegister();
                String value1 = ((LiteralNode) left).getValue();
                String value2 = ((LiteralNode) right).getValue();
                int intValue1 = Integer.parseInt(value1);
                int intValue2 = Integer.parseInt(value2);
                String binValue1 = "\"" +Integer.toBinaryString(intValue1) +"\"" ;
                String binValue2 = "\"" +Integer.toBinaryString(intValue2) +"\"" ;
                instructions.add(new LoadStringInstruction(null, reg1,binValue1 ));
                instructions.add(new LoadStringInstruction(null, reg2,binValue2 ));

                switch (operation) {
                    case "+":
                        instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                        break;
                    case "-":
                        instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                        break;
                    case "*":
                        instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                        break;
                    case "/":
                        instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                        break;
                }

                instructions.add(new MovInstruction(null, "result", regResult));
                instructions.add(new CallInstruction(null, "print", regResult));
            }
        } else if (ast instanceof ArrayAssignmentNode arrayAssignmentNode){
            String varArr = arrayAssignmentNode.getArrayName();
            List<ExpressionNode> elements = arrayAssignmentNode.getElements();
            List<RegisterName> elementRegisters = new ArrayList<>();

            for (ExpressionNode element : elements) {
                if (element instanceof LiteralNode) {
                    RegisterName reg = getNextRegister();
                    String elementValue = ((LiteralNode) element).getValue();
                    instructions.add(new LoadInstruction(null, reg, elementValue));
                    elementRegisters.add(reg);
                }
            }
            for (int i = 0; i < elementRegisters.size(); i++) {
                instructions.add(new StoreInstruction(null, elementRegisters.get(i), varArr + "[" + i + "]"));
            }


        }
        else if (ast instanceof AssignArrNode assignArrNode) {
            String varArr = assignArrNode.getArrayAssignmentNode().getArrayName();
            List<ExpressionNode> elements = assignArrNode.getArrayAssignmentNode().getElements();
            List<RegisterName> elementRegisters = new ArrayList<>();

            for (ExpressionNode element : elements) {
                if (element instanceof LiteralNode) {
                    RegisterName reg = getNextRegister();
                    String elementValue = ((LiteralNode) element).getValue();
                    instructions.add(new LoadInstruction(null, reg, elementValue));
                    elementRegisters.add(reg);
                }
            }
            for (int i = 0; i < elementRegisters.size(); i++) {
                instructions.add(new StoreInstruction(null, elementRegisters.get(i), varArr + "[" + i + "]"));
            }
            ExpressionNode valueOne = assignArrNode.getIndexes().getFirst();
            String indexOne = ((LiteralNode) valueOne).getValue();
            instructions.add(new MovInstruction(null,assignArrNode.getVariableNames().get(1),varArr + "[" + indexOne + "]"));
            ExpressionNode valueTwo = assignArrNode.getIndexes().getLast();
            String indexTwo = ((LiteralNode) valueTwo).getValue();
            instructions.add(new MovInstruction(null,assignArrNode.getVariableNames().get(3),varArr + "[" + indexTwo + "]"));
            String operation = assignArrNode.getOperator();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName regResult = getNextRegister();
            //instructions.add(new MovInstruction(null,reg1,assignArrNode.getVariableNames().get(1)));
            //instructions.add(new MovInstruction(null,reg2,assignArrNode.getVariableNames().get(3)));
            switch (operation) {
                case "+":
                    instructions.add(new AddInstruction(null, assignArrNode.getFinalVar(), assignArrNode.getVariableNames().get(1), assignArrNode.getVariableNames().get(3)));
                    break;
                case "*":
                    instructions.add(new MulInstruction(null, assignArrNode.getFinalVar(), assignArrNode.getVariableNames().get(1), assignArrNode.getVariableNames().get(3)));
                    break;
                case "/":
                    instructions.add(new DivInstruction(null, assignArrNode.getFinalVar(), assignArrNode.getVariableNames().get(1), assignArrNode.getVariableNames().get(3)));
                    break;
                case "-":
                    instructions.add(new SubInstruction(null, assignArrNode.getFinalVar(), assignArrNode.getVariableNames().get(1), assignArrNode.getVariableNames().get(3)));
                    break;
            }
            instructions.add(new MovInstruction(null,RegisterName.R_TOTAL,assignArrNode.getFinalVar()));
            instructions.add(new CallInstruction(null,"print",RegisterName.R_TOTAL));
        }
        else if (ast instanceof LoopArrNode loopArrNode) {
            List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
            String varArr = loopArrNode.getArrayAssignmentNode().getArrayName();
            List<RegisterName> elementRegisters = new ArrayList<>();

            for (ExpressionNode element : elements) {
                if (element instanceof LiteralNode) {
                    RegisterName reg = getNextRegister();
                    String elementValue = ((LiteralNode) element).getValue();
                    instructions.add(new LoadInstruction(null, reg, elementValue));
                    elementRegisters.add(reg);
                }
            }
            for (int i = 0; i < elementRegisters.size(); i++) {
                instructions.add(new StoreInstruction(null, elementRegisters.get(i), varArr + "[" + i + "]"));
            }
            ArrayAssignmentNode arr = loopArrNode.getArrayAssignmentNode();
            RegisterName lenRegister = getNextRegister();
            RegisterName runnerRegister = getNextRegister();
            RegisterName registerHolder = getNextRegister();
            instructions.add(new CountInstruction(null,arr,lenRegister));
            instructions.add(new LoadInstruction(null,runnerRegister,"0"));
            instructions.add(new LoopEndInstruction(null,runnerRegister,0));
            for (int i = 0; i < elementRegisters.size(); i++) {
                instructions.add(new LoadArrInstruction(null,varArr ,registerHolder,runnerRegister));
                instructions.add(new CallInstruction(null,"print",registerHolder));
                instructions.add(new NextElementInstruction(null,runnerRegister));
                instructions.add(new CompareInstruction(null,runnerRegister,lenRegister));
                instructions.add(new JLTInstruction(null,runnerRegister,lenRegister,new LoopEndInstruction(null,runnerRegister,0)));

            }
            instructions.add(new LoopEndInstruction(null,runnerRegister,1));

        }



        else if (ast instanceof BinaryAssignNode binaryAssignNode) {
            VariableAssignmentNode assignOperationOne = binaryAssignNode.getVariableOne();
            VariableAssignmentNode assignOperationTwo = binaryAssignNode.getVariableTwo();
            String operation = binaryAssignNode.getOperation();
            String totalVar = binaryAssignNode.getVariableName();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName reg3 = getNextRegister();
            RegisterName reg4 = getNextRegister();
            RegisterName regResult = getNextRegister();

            if (assignOperationOne != null && assignOperationTwo != null) {
                ExpressionNode value1 = assignOperationOne.getExpression();
                ExpressionNode value2 = assignOperationTwo.getExpression();

                String strValue1 = ((LiteralNode) value1).getValue();
                String strValue2 = ((LiteralNode) value2).getValue();
                int intValue1 = Integer.parseInt(strValue1);
                int intValue2 = Integer.parseInt(strValue2);
                String binValue1 = "\"" +Integer.toBinaryString(intValue1) +"\"" ;
                String binValue2 = "\"" +Integer.toBinaryString(intValue2) +"\"" ;
                instructions.add(new LoadStringInstruction(null, reg1, binValue1));
                instructions.add(new StoreInstruction(null, reg1, assignOperationOne.getVariableName()));
                instructions.add(new LoadStringInstruction(null, reg2,binValue2));
                instructions.add(new StoreInstruction(null, reg2, assignOperationTwo.getVariableName()));
                instructions.add(new MovInstruction(null, reg3, assignOperationOne.getVariableName()));
                instructions.add(new MovInstruction(null, reg4, assignOperationTwo.getVariableName()));
                switch (operation) {
                    case "+":
                        instructions.add(new AddInstruction(null, regResult, reg3, reg4));
                        break;
                    case "-":
                        instructions.add(new SubInstruction(null, regResult, reg3, reg4));
                        break;
                    case "*":
                        instructions.add(new MulInstruction(null, regResult, reg3, reg4));
                        break;
                    case "/":
                        instructions.add(new DivInstruction(null, regResult, reg3, reg4));
                        break;
                }

                instructions.add(new MovInstruction(null, totalVar, regResult));
                instructions.add(new CallInstruction(null, "print", totalVar));
            }
        } else if (ast instanceof BinaryAssignmentNode binaryAssignmentNode) {
            String variableName = binaryAssignmentNode.getVariableName();
            ExpressionNode value1 = binaryAssignmentNode.getValue();
            ExpressionNode value2 = binaryAssignmentNode.getSecondValue();
            String operation = binaryAssignmentNode.getOperation();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName regResult = getNextRegister();
            String strValue1 = ((LiteralNode) value1).getValue();
            String strValue2 = ((LiteralNode) value2).getValue();
            int intValue1 = Integer.parseInt(strValue1);
            int intValue2 = Integer.parseInt(strValue2);
            String binValue1 = "\"" +Integer.toBinaryString(intValue1) +"\"" ;
            String binValue2 = "\"" +Integer.toBinaryString(intValue2) +"\"" ;
            instructions.add(new LoadStringInstruction(null, reg1, binValue1));
            instructions.add(new StoreInstruction(null, reg1, variableName));
            instructions.add(new LoadStringInstruction(null, reg2, binValue2));

            switch (operation) {
                case "+":
                    instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                    break;
                case "-":
                    instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                    break;
                case "*":
                    instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                    break;
                case "/":
                    instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                    break;
            }

            instructions.add(new MovInstruction(null, variableName, regResult));
            instructions.add(new CallInstruction(null, "print", regResult));
        } else if (ast instanceof BinaryVarExtended binaryVarExtended) {
            String variableName = binaryVarExtended.getVariableName();
            ExpressionNode value1 = binaryVarExtended.getExpression();
            ExpressionNode value2 = binaryVarExtended.getValue2();
            String operation = binaryVarExtended.getOperation();
            String variableName2 = binaryVarExtended.getVariableName2();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();
            RegisterName regResult = getNextRegister();
            String strValue1 = ((LiteralNode) value1).getValue();
            String strValue2 = ((LiteralNode) value2).getValue();
            int intValue1 = Integer.parseInt(strValue1);
            int intValue2 = Integer.parseInt(strValue2);
            String binValue1 = "\"" +Integer.toBinaryString(intValue1) +"\"" ;
            String binValue2 = "\"" +Integer.toBinaryString(intValue2) +"\"" ;

            instructions.add(new LoadStringInstruction(null, reg1, binValue1));
            instructions.add(new StoreInstruction(null, reg1, variableName));
            instructions.add(new LoadStringInstruction(null, reg2, binValue2));
            instructions.add(new StoreInstruction(null, reg2, variableName2));

            switch (operation) {
                case "+":
                    instructions.add(new AddInstruction(null, regResult, reg1, reg2));
                    break;
                case "-":
                    instructions.add(new SubInstruction(null, regResult, reg1, reg2));
                    break;
                case "*":
                    instructions.add(new MulInstruction(null, regResult, reg1, reg2));
                    break;
                case "/":
                    instructions.add(new DivInstruction(null, regResult, reg1, reg2));
                    break;
            }

            instructions.add(new MovInstruction(null, variableName, regResult));
            instructions.add(new CallInstruction(null, "print", regResult));
        } else if (ast instanceof LiteralNode) {

            instructions.add(new LoadStringInstruction(null, RegisterName.R0, ((LiteralNode) ast).getValue()));
        } else if (ast instanceof PrintVariableNode) {
            PrintVariableNode printNode = (PrintVariableNode) ast;
            String variableName = printNode.getVariableName();
            ExpressionNode value = printNode.getVariableValue();
            RegisterName reg1 = getNextRegister();
            RegisterName reg2 = getNextRegister();

            String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
            if (value instanceof LiteralNode) {
                instructions.add(new LoadStringInstruction(null, reg1, literalString));
                instructions.add(new StoreInstruction(null, reg1, variableName));
                instructions.add(new MovInstruction(null, reg2,variableName));
                instructions.add(new CallInstruction(null, "print", reg2));
            } else if (value instanceof VariableNode) {
                instructions.add(new MovInstruction(null, RegisterName.R0, ((VariableNode) value).getName()));
                instructions.add(new CallInstruction(null, "print", RegisterName.R0));
            }
        } else if (ast instanceof VariableAssignmentNode variableAssignmentNode) {
            String variableName = variableAssignmentNode.getVariableName();
            ExpressionNode value = variableAssignmentNode.getExpression();
            RegisterName reg = getNextRegister();
            String literalString = "\"" + ((LiteralNode) value).getValue() + "\"";
            if (value instanceof LiteralNode) {
                instructions.add(new LoadStringInstruction(null, reg, literalString));
                instructions.add(new StoreInstruction(null, reg, variableName));
            }
        }

        return instructions;
    }

    private RegisterName getNextRegister() {
        return RegisterName.values()[tempVarCounter++ % RegisterName.values().length];
    }
}
