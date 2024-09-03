package project.compiler.lexer;

import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;

public class OperatorCheck {

    public static String getOperatorSymbol(TokenCheck type) {
        return switch (type) {
            case ADDITION -> "+";
            case SUBTRACTION -> "-";
            case MULTIPLICATION -> "*";
            case DIVIDE -> "/";
            default -> throw new IllegalArgumentException("Unknown operation type");
        };
    }
    public static boolean isBasicBinary(TokenCheck type) {
        return type == TokenCheck.ADDITION || type == TokenCheck.SUBTRACTION ||
                type == TokenCheck.MULTIPLICATION || type == TokenCheck.DIVIDE;
    }

    public static boolean isBasicBinaryList(List<Token> tokens){
        return tokens.get(1).getKey() == TokenCheck.ADDITION || tokens.get(1).getKey()  == TokenCheck.SUBTRACTION ||
                tokens.get(1).getKey()  == TokenCheck.MULTIPLICATION || tokens.get(1).getKey()  == TokenCheck.DIVIDE;
    }


}