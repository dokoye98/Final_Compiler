package project.compiler.syntaxfunctions;

import project.compiler.tokens.Token;

import java.util.List;

public class StringChecker {
    public static boolean quoteCheck(List<Token> token){
        if(token.get(1).getValue().startsWith("\"") && token.get(1).getValue().endsWith("\"")){
            return true;
        }else{
            System.out.println("Invalid code quote par");
            return false;
        }

    }
    public static boolean quoteCheckLexer(String literal){
        if(literal.startsWith("\"") && literal.endsWith("\"") ){
            return true;
        }else{
            System.out.println("Invalid code quote l");
            return false;
        }

    }
    public static String cleanLiteral(String literal){
        if(quoteCheckLexer(literal)){
            return literal.substring(1,literal.length()-1);
        }else{
            return literal;
        }
    }


}
