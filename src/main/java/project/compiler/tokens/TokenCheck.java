package project.compiler.tokens;

public enum TokenCheck {
    PRINT("print", "^(print)\\s*\"([^\"]*)\""),
    ADDITION("addition", "(\\d+)\\s*\\+\\s*(\\d+)"),
    SUBTRACTION("subtraction", "(\\d+)\\s*\\-\\s*(\\d+)"),
    MULTIPLICATION("multiply", "(\\d+)\\s*\\*\\s*(\\d+)"),
    DIVIDE("divide", "(\\d+)\\s*\\/\\s*(\\d+)"),
    STRING_KEYWORD("String", "\\bstring\\b"),
    VARIABLE_ASSIGN("variable_assignment", "\\b(string)\\s+(\\w+)\\s*=\\s*\"([^\"]*)\""),
    VARIABLE("variable","\\s+\\w"),
    LITERAL("literal", "\"([^\"]*)\""),
    PRINT_ASSIGN("print_assign", "^(print)\\s+(\\w+)"),
    ASSIGN_PRINT("assign_print", "^\\s*(print)\\s*(\\w+)"),
    VAR_ASSIGN("=","^=$"),
    INTEGER_KEYWORD("int", "\\bint\\b"),
    NUM_VAR("number_Var", "\\b(int)\\s+(\\w+)\\s*(=)\\s*(\\d+)"),

    NUM_VAR_ADD("number_Var_add","^\\b(int)\\s+(\\w+)\\s*=\\s*(\\w+)\\s*\\+\\s*(\\w+)"),
    NUM_VAR_SUB("number_Var_add","^(\\w+)\\s*=\\s*(\\w+)\\s*\\-\\s*(\\w+)"),
    NUM_VAR_MUL("number_Var_add","^(\\w+)\\s*=\\s*(\\w+)\\s*\\*\\s*(\\w+)"),
    NUM_VAR_DIV("number_Var_add","^(\\w+)\\s*=\\s*(\\w+)\\s*\\/\\s*(\\w+)"),
    VAR_ADD("add_Var","(\\w+)\\s*\\+\\s*(\\d+)"),
    VAR_ADD_VAR("add_Var","(\\w+)\\s*\\+\\s*(\\w+)"),
    VAR_MUL_VAR("mul_Var","(\\w+)\\s*\\*\\s*(\\w+)"),
    VAR_SUB_VAR("sub_Var","(\\w+)\\s*\\-\\s*(\\w+)"),
    VAR_DIV_VAR("div_Var","(\\w+)\\s*\\/\\s*(\\w+)"),
    VAR_SUB("add_Var","(\\w+)\\s*\\-\\s*(\\d+)"),
    VAR_DIV("add_Var","(\\w+)\\s*\\/\\s*(\\d+)"),
    VAR_MUL("add_Var","(\\w+)\\s*\\*\\s*(\\d+)"),
    ARRAY_ASSIGN("array_assign", "(\\w+)\\s*=\\s*\\[(.*?)\\]"),
    ARRAY_LITERAL("array_literal", "\\[(.*?)\\]"),
    ARRAY_ELEMENT("array_element", "(\\d+)"),
    ARRAY_INDEX("array_index", "^(\\w+)\\[(\\d+)\\]"),
    ARRAY_INDEX_VAR("array_index_var", "^(\\w+)\\[(\\w+|\\d+)\\]"),
    VAR_ASSIGN_ARRAY("array_index_var", "^\\b(int)\\s+(\\w+)\\s*=\\s*(\\w+)\\[(\\d+)\\]"),
    FOR_LOOP("for_loop", "^(for)\\s+(\\w+)\\s+(in)\\s+(\\w+)"),
    IN_KEYWORD("in_keyword", "^(in)"),
    UNKNOWN("unknown", ".*");

    private final String keyword;
    private final String pattern;

    TokenCheck(String keyword, String pattern) {
        this.keyword = keyword;
        this.pattern = pattern;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getPattern() {
        return pattern;
    }
}
