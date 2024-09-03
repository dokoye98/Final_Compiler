package project.compiler.nodes.arrayNodes;

import project.compiler.nodes.ArrayAssignmentNode;
import project.compiler.nodes.AssignArrNode;
import project.compiler.nodes.ExpressionNode;

import java.util.List;

public class AddArray extends AssignArrNode {
    public AddArray(List<String> variableNames, ArrayAssignmentNode arrayAssignmentNode, String operator, List<ExpressionNode> indexes, String finalVar) {
        super(variableNames, arrayAssignmentNode, operator, indexes , finalVar);
    }


}