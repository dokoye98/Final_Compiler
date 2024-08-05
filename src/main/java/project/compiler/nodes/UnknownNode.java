package project.compiler.nodes;

public class UnknownNode extends Node {


    private final String errorMessage;


    public UnknownNode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "UnknownNode{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
