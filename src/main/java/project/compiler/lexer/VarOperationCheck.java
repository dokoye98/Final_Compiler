package project.compiler.lexer;

import project.compiler.tokens.TokenCheck;

public class VarOperationCheck {


    public static boolean isBinaryVar(TokenCheck type) {
        return type == TokenCheck.VAR_DIV || type == TokenCheck.VAR_MUL ||
                type == TokenCheck.VAR_ADD || type == TokenCheck.VAR_SUB;
    }

    public static TokenCheck getVarKey(TokenCheck type) {
        switch (type) {
            case VAR_ADD:
                return TokenCheck.VAR_ADD;
            case VAR_SUB:
                return TokenCheck.VAR_SUB;
            case VAR_MUL:
                return TokenCheck.VAR_MUL;
            case VAR_DIV:
                return TokenCheck.VAR_DIV;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static TokenCheck getVarOperation(TokenCheck type) {
        switch (type) {
            case VAR_ADD:
                return TokenCheck.ADDITION;
            case VAR_SUB:
                return TokenCheck.SUBTRACTION;
            case VAR_MUL:
                return TokenCheck.MULTIPLICATION;
            case VAR_DIV:
                return TokenCheck.DIVIDE;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static String getOperatorSymbol(TokenCheck type) {
        switch (type) {
            case VAR_ADD:
                return "+";
            case VAR_SUB:
                return "-";
            case VAR_MUL:
                return "*";
            case VAR_DIV:
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation type");

        }
    }
}
