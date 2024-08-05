package project.compiler.tokens;

public enum TokenCheck {
    PRINT("print", "^(print)\\s*\"([^\"]*)\""),
    ADDITION("addition", "(\\d+)\\s*\\+\\s*(\\d+)"),
    SUBTRACTION("subtraction", "(\\d+)\\s*\\-\\s*(\\d+)"),
    MULTIPLICATION("multiply", "(\\d+)\\s*\\*\\s*(\\d+)"),
    DIVIDE("divide", "(\\d+)\\s*\\/\\s*(\\d+)"),
    VARIABLE_ASSIGN("variable_assignment", "(\\w+)\\s*=\\s*\"([^\"]*)\""),
    VARIABLE("variable","\\s+\\w"),
    LITERAL("literal", "\"([^\"]*)\""),
    PRINT_ASSIGN("print_assign", "^(print)\\s+(\\w+)"),
    ASSIGN_PRINT("assign_print", "^\\s*(print)\\s*(\\w+)"),
    VAR_ASSIGN("=","^=$"),
    NUM_VAR("number_Var","(\\w+)\\s*=\\s*(\\d+)"),
    NUM_VAR_ADD("number_Var_add","^(\\w+)\\s*=\\s*(\\w+)\\s*\\+\\s*(\\w+)"),
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
