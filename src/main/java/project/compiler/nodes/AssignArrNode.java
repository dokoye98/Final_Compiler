package project.compiler.nodes;

import project.compiler.nodes.ArrayAssignmentNode;
import project.compiler.nodes.ExpressionNode;

import java.beans.Expression;
import java.util.List;

public class AssignArrNode extends ExpressionNode {
    private final List<String> variableNames;
    private final ArrayAssignmentNode arrayAssignmentNode;
    private final String operator;
    private final List<ExpressionNode> indexes;
    private final String finalVar;

    public AssignArrNode(List<String> variableNames, ArrayAssignmentNode arrayAssignmentNode, String operator, List<ExpressionNode> indexes, String finalVar) {
        this.variableNames = variableNames;
        this.arrayAssignmentNode = arrayAssignmentNode;
        this.operator = operator;
        this.indexes = indexes;
        this.finalVar = finalVar;
    }

    @Override
    public String getType() {
        return "ArrayElementAssignment";
    }

    public List<String> getVariableNames() {
        return variableNames;
    }

    public ArrayAssignmentNode getArrayAssignmentNode() {
        return arrayAssignmentNode;
    }

    public String getOperator() {
        return operator;
    }

    public List<ExpressionNode> getIndexes() {
        return indexes;
    }

    public String getFinalVar() {
        return finalVar;
    }

    @Override
    public String toString() {
        return "VariableAssignment : void\n  |\n  +-- Variable(" + getVariableNames().get(1) + ")\n  +-- "+ getArrayAssignmentNode().getArrayName() + "["+ getIndexes().getFirst() +"]"+
                "\nVariableAssignment : void\n  |\n  +-- Variable(" + getVariableNames().get(3) + ")\n  +-- " + getArrayAssignmentNode().getArrayName() + "["+ getIndexes().getLast() +"]" +
                "\n" + getClass().getSimpleName() + " (" + getOperator() + "): " + getFinalVar() + "\n  |\n  +-- Variable(" + getVariableNames().get(1) + ")\n  +-- Variable(" +getVariableNames().get(3)+ ")";

    }
}
