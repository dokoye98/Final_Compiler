package project.compiler.lexer;

import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;

public class VarAssignOperation {

    public static TokenCheck getVarOperation(TokenCheck type) {
        return switch (type) {
            case NUM_VAR_ADD -> TokenCheck.ADDITION;
            case NUM_VAR_SUB -> TokenCheck.SUBTRACTION;
            case NUM_VAR_MUL -> TokenCheck.MULTIPLICATION;
            case NUM_VAR_DIV -> TokenCheck.DIVIDE;
            default -> throw new IllegalArgumentException("Unknown operation type");
        };
    }
    public static boolean isBasicBinary(TokenCheck type) {
        return type == TokenCheck.NUM_VAR_ADD || type == TokenCheck.NUM_VAR_SUB ||
                type == TokenCheck.NUM_VAR_MUL || type == TokenCheck.NUM_VAR_DIV;
    }


    public static String getOperatorSymbol(TokenCheck type) {
        return switch (type) {
            case NUM_VAR_ADD -> "+";
            case NUM_VAR_SUB -> "-";
            case NUM_VAR_MUL -> "*";
            case NUM_VAR_DIV -> "/";
            default -> throw new IllegalArgumentException("Unknown operation type");
        };

    }

}
