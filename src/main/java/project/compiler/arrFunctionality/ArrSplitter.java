package project.compiler.arrFunctionality;

import project.compiler.nodes.ExpressionNode;
import project.compiler.nodes.LiteralNode;
import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.List;

import static project.compiler.lexer.OperatorCheck.getOperatorSymbol;

public class ArrSplitter {

    public static ExpressionNode arrValue(List<ExpressionNode> elements, String index){
        System.out.println(elements.get(Integer.parseInt(index)));
        return  elements.get(Integer.parseInt(index));
    }
    public static void indexPrinter(List<ExpressionNode> indexes){
        System.out.println(indexes);
    }
    public static void variablePrinter(List<String> variable){
        System.out.println(variable);
    }
    public static String findOperatorSymbol(List<Token> tokens) {
        for (Token token : tokens) {
            TokenCheck type = token.getKey();
            if (type == TokenCheck.ADDITION || type == TokenCheck.SUBTRACTION ||
                    type == TokenCheck.MULTIPLICATION || type == TokenCheck.DIVIDE) {
                return getOperatorSymbol(type);
            }
        }
        return "Unknown operator symbol";
    }
}
