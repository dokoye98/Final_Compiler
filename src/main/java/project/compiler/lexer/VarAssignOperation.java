package project.compiler.lexer;

import project.compiler.tokens.TokenCheck;

public class VarAssignOperation {

    public static TokenCheck getVarOperation(TokenCheck type) {
        switch (type) {
            case NUM_VAR_ADD:
                return TokenCheck.ADDITION;
            case NUM_VAR_SUB:
                return TokenCheck.SUBTRACTION;
            case NUM_VAR_MUL:
                return TokenCheck.MULTIPLICATION;
            case NUM_VAR_DIV:
                return TokenCheck.DIVIDE;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static String getOperatorSymbol(TokenCheck type) {
        switch (type) {
            case NUM_VAR_ADD:
                return "+";
            case NUM_VAR_SUB:
                return "-";
            case NUM_VAR_MUL:
                return "*";
            case NUM_VAR_DIV:
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation type");

        }
    }
}
