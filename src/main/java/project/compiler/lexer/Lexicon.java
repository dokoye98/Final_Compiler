package project.compiler.lexer;

import project.compiler.tokens.Token;
import project.compiler.tokens.TokenCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexicon {


    private final String input;
    private final List<Token> tokens;

    public Lexicon(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
    }

    public void splitter() {
        List<String> lines = LineCheck.lineChecker(input);
        for (String line : lines) {
            //System.out.println(line);
            processor(line);
        }
    }

    public void processor(String line) {
        String remainingInput = line.trim();
        boolean matched = false;

        for (TokenCheck type : TokenCheck.values()) {
            Pattern pattern = Pattern.compile(type.getPattern(), Pattern.CASE_INSENSITIVE);
            Matcher match = pattern.matcher(remainingInput);

            if (match.matches()) {
                matched = true;

                if (type == TokenCheck.NUM_VAR_ADD && match.groupCount() == 3) {
                    String variableTotal = match.group(1);
                    String variableOne = match.group(2);
                    String variableTwo = match.group(3);
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTotal));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableOne));
                    tokens.add(new Token(VarAssignOperation.getVarOperation(type), VarAssignOperation.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTwo));
                }else  if (type == TokenCheck.NUM_VAR_SUB && match.groupCount() == 3) {
                    String variableTotal = match.group(1);
                    String variableOne = match.group(2);
                    String variableTwo = match.group(3);
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTotal));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableOne));
                    tokens.add(new Token(VarAssignOperation.getVarOperation(type), VarAssignOperation.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTwo));
                }
                else if (type == TokenCheck.NUM_VAR_DIV && match.groupCount() == 3) {
                    String variableTotal = match.group(1);
                    String variableOne = match.group(2);
                    String variableTwo = match.group(3);
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTotal));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableOne));
                    tokens.add(new Token(VarAssignOperation.getVarOperation(type), VarAssignOperation.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTwo));
                }
                else if (type == TokenCheck.NUM_VAR_MUL && match.groupCount() == 3) {
                    String variableTotal = match.group(1);
                    String variableOne = match.group(2);
                    String variableTwo = match.group(3);
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTotal));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableOne));
                    tokens.add(new Token(VarAssignOperation.getVarOperation(type), VarAssignOperation.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.NUM_VAR, variableTwo));
                }else if (type == TokenCheck.VARIABLE_ASSIGN && match.groupCount() == 2) {
                    String variableName = match.group(1);
                    String literalValue = match.group(2);
                    tokens.add(new Token(TokenCheck.VARIABLE, variableName));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.LITERAL, literalValue));

                } else if (type == TokenCheck.PRINT_ASSIGN && match.groupCount() == 2) {
                    String literal = match.group(2);
                    tokens.add(new Token(TokenCheck.PRINT, match.group(1)));
                    tokens.add(new Token(TokenCheck.LITERAL, literal));
                } else if (type == TokenCheck.NUM_VAR && match.groupCount() == 2) {
                    String functionCall = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(TokenCheck.NUM_VAR, functionCall));
                    tokens.add(new Token(TokenCheck.VAR_ASSIGN, "="));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                }

                else if (VarOperationCheckExtended.isBinaryVar(type)) {
                    String variableName = match.group(1);
                    String variableName2 = match.group(2);
                    tokens.add(new Token(VarOperationCheckExtended.getVarKey(type), variableName));
                    tokens.add(new Token(VarOperationCheckExtended.getVarOperation(type), VarOperationCheckExtended.getOperatorSymbol(type)));
                    tokens.add(new Token(VarOperationCheckExtended.getVarKey(type), variableName2));
                }
                else if (VarOperationCheck.isBinaryVar(type) && match.groupCount() == 2) {
                   String varName = match.group(1);
                    String variableName = match.group(2);
                    tokens.add(new Token(VarOperationCheck.getVarKey(type), varName));
                    tokens.add(new Token(VarOperationCheck.getVarOperation(type), VarOperationCheck.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.LITERAL, variableName));
                }
                else if (type == TokenCheck.PRINT && match.groupCount() == 2) {
                    String literal = match.group(2);
                    tokens.add(new Token(TokenCheck.PRINT, match.group(1)));
                    tokens.add(new Token(TokenCheck.LITERAL, literal));
                } else if (OperatorCheck.isBasicBinary(type)&& match.groupCount() == 2) {
                    String operand1 = match.group(1);
                    String operand2 = match.group(2);
                    tokens.add(new Token(TokenCheck.LITERAL, operand1));
                    tokens.add(new Token(type,OperatorCheck.getOperatorSymbol(type)));
                    tokens.add(new Token(TokenCheck.LITERAL, operand2));
                }
                else {
                    tokens.add(new Token(type, match.group(0)));
                }
                remainingInput = remainingInput.substring(match.end()).trim();


                break;
            }
        }


        if (!matched) {
            tokens.add(new Token(TokenCheck.UNKNOWN, "Unknown command: " + remainingInput));
        }
    }

    public List<Token> getTokens() {

        return tokens;
    }
}



