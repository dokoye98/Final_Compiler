package project.compiler.lexer;

import project.compiler.tokens.TokenCheck;

public class VarOperationCheckExtended {

    public static boolean isBinaryVar(TokenCheck type) {
        return type == TokenCheck.VAR_DIV_VAR || type == TokenCheck.VAR_MUL_VAR ||
                type == TokenCheck.VAR_ADD_VAR || type == TokenCheck.VAR_SUB_VAR;
    }

    public static TokenCheck getVarKey(TokenCheck type) {
        switch (type) {
            case VAR_ADD_VAR:
                return TokenCheck.VAR_ADD_VAR;
            case VAR_SUB_VAR:
                return TokenCheck.VAR_SUB_VAR;
            case VAR_MUL_VAR:
                return TokenCheck.VAR_MUL_VAR;
            case VAR_DIV_VAR:
                return TokenCheck.VAR_DIV_VAR;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static TokenCheck getVarOperation(TokenCheck type) {
        switch (type) {
            case VAR_ADD_VAR:
                return TokenCheck.ADDITION;
            case VAR_SUB_VAR:
                return TokenCheck.SUBTRACTION;
            case VAR_MUL_VAR:
                return TokenCheck.MULTIPLICATION;
            case VAR_DIV_VAR:
                return TokenCheck.DIVIDE;
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public static String getOperatorSymbol(TokenCheck type) {
        switch (type) {
            case VAR_ADD_VAR:
                return "+";
            case VAR_SUB_VAR:
                return "-";
            case VAR_MUL_VAR:
                return "*";
            case VAR_DIV_VAR:
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation type");

        }
    }
}
