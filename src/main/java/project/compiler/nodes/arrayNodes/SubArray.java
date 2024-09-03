package project.compiler.nodes.arrayNodes;

import project.compiler.nodes.ArrayAssignmentNode;
import project.compiler.nodes.AssignArrNode;
import project.compiler.nodes.ExpressionNode;

import java.util.List;

public class SubArray extends AssignArrNode {
    public SubArray(List<String> variableNames, ArrayAssignmentNode arrayAssignmentNode, String operator, List<ExpressionNode> indexes, String finalVar) {
        super(variableNames, arrayAssignmentNode, operator, indexes , finalVar);
    }


}
