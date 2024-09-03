package project.compiler.intercode;

import project.compiler.arrFunctionality.ArrSplitter;
import project.compiler.nodes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TACVisual {
    private int tempVarCounter = 0;

    private String getNextTempVar() {
        return "t" + (tempVarCounter++);
    }

    public List<String> generateTAC(Node ast) {
        List<String> tacCode = new ArrayList<>();
        if (ast instanceof PrintStatementNode printNode) {
            ExpressionNode value = printNode.getExpression();
            if (value instanceof LiteralNode) {
                String tempVar = getNextTempVar();
                tacCode.add(tempVar + " = \"" + ((LiteralNode) value).getValue() + "\"");
                tacCode.add("call print, " + tempVar);
            }

        } else if (ast instanceof BinaryOperationNode binaryOperationNode) {
            ExpressionNode left = binaryOperationNode.getLeft();
            ExpressionNode right = binaryOperationNode.getRight();
            String operation = binaryOperationNode.getOperator();

            if (left instanceof LiteralNode && right instanceof LiteralNode) {
                String t0 = getNextTempVar();
                String t1 = getNextTempVar();
                String t2 = getNextTempVar();
                int leftValue = Integer.parseInt(((LiteralNode) left).getValue());
                int rightValue = Integer.parseInt(((LiteralNode) right).getValue());
                tacCode.add(t0 + " = " + Integer.toBinaryString(leftValue)+ " (" + leftValue+")");
                tacCode.add(t1 + " = " +  Integer.toBinaryString(rightValue) + " ("+ rightValue+")");
                tacCode.add(t2 + " = " + t0 +" "+ operation +" "+ t1);
            }
        } else if(ast instanceof BinaryAssignNode binaryAssignNode){
            VariableAssignmentNode assignOperationOne = binaryAssignNode.getVariableOne();
            VariableAssignmentNode assignOperationTwo = binaryAssignNode.getVariableTwo();
            String operation = binaryAssignNode.getOperation();
            String totalVar = binaryAssignNode.getVariableName();
            if(assignOperationOne != null && assignOperationTwo !=null){
                String t0 = getNextTempVar();
                String t1 = getNextTempVar();
                String t2 = getNextTempVar();
                String t3 = getNextTempVar();
                String t4 = getNextTempVar();
                ExpressionNode value1 = assignOperationOne.getExpression();
                ExpressionNode value2 = assignOperationTwo.getExpression();
                String variableName = assignOperationOne.getVariableName();

                String variableName2 = assignOperationTwo.getVariableName();
                int valueNum = Integer.parseInt(((LiteralNode) value1).getValue());
                int valueNumTwo = Integer.parseInt(((LiteralNode) value2).getValue());
                tacCode.add(t0 + " = " + Integer.toBinaryString(valueNum) + " (" + valueNum + ")");
                tacCode.add(variableName + " = " + t0);
                tacCode.add(t1 + " = " +  Integer.toBinaryString(valueNumTwo) + " (" + valueNumTwo + ")");
                tacCode.add((variableName2+ " = " + t1));
                tacCode.add(t2 + " = " +variableName);
                tacCode.add(t3 + " = " +variableName2);
                tacCode.add(t4 + " = " +t2 + operation + t3);
                tacCode.add(totalVar+ " = "+t4);
                tacCode.add("call print, "+ totalVar);
            }

        }

        else if (ast instanceof BinaryAssignmentNode binaryAssignmentNode) {
            String variableName = binaryAssignmentNode.getVariableName();
            ExpressionNode value = binaryAssignmentNode.getValue();
            ExpressionNode secondValue = binaryAssignmentNode.getSecondValue();
            String operation = binaryAssignmentNode.getOperation();
            if (value instanceof LiteralNode && secondValue instanceof LiteralNode) {
                String t0 = getNextTempVar();
                String t1 = getNextTempVar();
                String t2 = getNextTempVar();
                int valueNum = Integer.parseInt(((LiteralNode) value).getValue());
                int secondValueNum = Integer.parseInt(((LiteralNode) secondValue).getValue());
                //  System.out.println(value + " " + secondValue); - >  using print to check secondValue
                tacCode.add(variableName + " = " + Integer.toBinaryString(valueNum) + " (" + valueNum + ")");
                tacCode.add(t0 + " = " + variableName);
                tacCode.add(t1 + " = " +  Integer.toBinaryString(secondValueNum) + " (" + secondValueNum + ")");
                tacCode.add(t2 + " = " + t0 + " " + operation + " " + t1);

            }
        } else if (ast instanceof BinaryVarExtended binaryVarExtended) {
            String variableName = binaryVarExtended.getVariableName();
            ExpressionNode value = binaryVarExtended.getExpression();
            ExpressionNode secondValue = binaryVarExtended.getValue2();
            String operation = binaryVarExtended.getOperation();
            String variableName2 = binaryVarExtended.getVariableName2();
            if (value instanceof LiteralNode && secondValue instanceof LiteralNode) {
                String t0 = getNextTempVar();
                String t1 = getNextTempVar();
                String t2 = getNextTempVar();
                int valueNum = Integer.parseInt(((LiteralNode) value).getValue());
                int secondValueNum = Integer.parseInt(((LiteralNode) secondValue).getValue());
                //  System.out.println(value + " " + secondValue); - >  using print to check secondValue
                tacCode.add(variableName + " = " +  Integer.toBinaryString(valueNum) + " (" + valueNum + ")");
                tacCode.add(variableName2 + " = " +  Integer.toBinaryString(secondValueNum) + " (" + secondValueNum + ")");
                tacCode.add(t0 + " = " + variableName);
                tacCode.add(t1 + " = " +  variableName2);
                tacCode.add(t2 + " = " + t0 + " " + operation + " " + t1);
            }

        }

        else if (ast instanceof LiteralNode) {
            String tempVar = getNextTempVar();
            tacCode.add(tempVar + " = \"" + ((LiteralNode) ast).getValue() + "\"");
        } else if (ast instanceof PrintVariableNode printNode) {
            String variableName = printNode.getVariableName();
            ExpressionNode value = printNode.getVariableValue();
            // String printer = printNode.getPrinter();

            if (value instanceof LiteralNode) {
                String tempVar = getNextTempVar();
                tacCode.add(variableName + " = \"" + ((LiteralNode) value).getValue() + "\"");
                tacCode.add(tempVar + " = " + variableName);
                tacCode.add("call print, " + tempVar);
            }

        }
        else if (ast instanceof AssignArrNode assignArrNode) {
            String arrayName = assignArrNode.getArrayAssignmentNode().getArrayName();
            List<ExpressionNode> elements = assignArrNode.getArrayAssignmentNode().getElements();
            List<String> tempVars = new ArrayList<>();
            for (ExpressionNode element : elements) {
                if (element instanceof LiteralNode) {
                    String tempVar = getNextTempVar();
                    tacCode.add(tempVar + " = " + ((LiteralNode) element).getValue());
                    tempVars.add(tempVar);
                }
            }
            String arrVariable = getNextTempVar();
            String firstVar = getNextTempVar();
            String secondVar = getNextTempVar();
            tacCode.add(arrayName + " = [" + String.join(", ", tempVars) + "]");
            tacCode.add(arrVariable + " = " + arrayName);
            String value1 = ((LiteralNode) assignArrNode.getIndexes().getFirst()).getValue();
            String value2 = ((LiteralNode) assignArrNode.getIndexes().getLast()).getValue();
            tacCode.add(assignArrNode.getVariableNames().get(1) + " = " + arrVariable+"["+value1+"] " + ArrSplitter.arrValue(elements,value1));
            tacCode.add(assignArrNode.getVariableNames().get(3) + " = " + arrVariable+"["+value2+"] " + ArrSplitter.arrValue(elements,value2));
            tacCode.add((firstVar+ " = " + assignArrNode.getVariableNames().get(1)));
            tacCode.add((secondVar+ " = " + assignArrNode.getVariableNames().get(3)));
            tacCode.add((assignArrNode.getFinalVar()+ " = "+ firstVar + " " + assignArrNode.getOperator()+ " "+ secondVar));
            String finalVar = getNextTempVar();
            tacCode.add((finalVar+ " = " + assignArrNode.getFinalVar()));
            tacCode.add("call print " + finalVar);


        }
        else if (ast instanceof LoopArrNode loopArrNode) {
            String arrayName = loopArrNode.getArrayAssignmentNode().getArrayName();
            List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
            List<String> tempVars = new ArrayList<>();
            String tempArrayName = getNextTempVar();
            tacCode.add(tempArrayName + " = [" + String.join(", ", elements.stream()
                    .map(e -> {
                        String tempVar = getNextTempVar();
                        tacCode.add(tempVar + " = " + ((LiteralNode) e).getValue());
                        return tempVar;
                    }).collect(Collectors.joining(", ")) + "]"));
            tacCode.add(arrayName + " = [" + String.join(", ", tempVars) + "]");
            String looper = getNextTempVar();
            tacCode.add(looper + "=0");
            String loopSt  = "L" + getNextTempVar();
            String loopEnd  = "L" + getNextTempVar();
            tacCode.add(loopSt + ":");
            String arrLen = getNextTempVar();
            tacCode.add(arrLen + " = " + elements.size());
            tacCode.add("if " + looper + " >= " + arrLen + " goto " + loopEnd);
            String tempVar = getNextTempVar();
            tacCode.add(tempVar + " = " + tempArrayName + "[" + looper + "]");
            tacCode.add("call print, " + tempVar);
            tacCode.add(looper + " = " + looper + " + 1");
            tacCode.add("goto " + loopSt);
            tacCode.add(loopEnd + ":");
        }
        else if (ast instanceof ArrayAssignmentNode arrayAssignmentNode) {
            String arrayName = arrayAssignmentNode.getArrayName();
            List<ExpressionNode> elements = arrayAssignmentNode.getElements();
            List<String> tempVars = new ArrayList<>();

            for (ExpressionNode element : elements) {
                if (element instanceof LiteralNode) {
                    String tempVar = getNextTempVar();
                    tacCode.add(tempVar + " = " + ((LiteralNode) element).getValue());
                    tempVars.add(tempVar);
                }
            }

            tacCode.add(arrayName + " = [" + String.join(", ", tempVars) + "]");


        }


        else if (ast instanceof VariableAssignmentNode printNode) {
            String variableName = printNode.getVariableName();
            ExpressionNode value = printNode.getExpression();
            if(value instanceof LiteralNode){
                String tempVar = getNextTempVar();
                tacCode.add(variableName + " = \"" + ((LiteralNode) value).getValue() + "\"");
                tacCode.add(tempVar + " = " + variableName);

            }

        }

        return tacCode;
    }
}
