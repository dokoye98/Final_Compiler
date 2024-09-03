package project.compiler.tokens;

public class Token {
    private TokenCheck key;
    private String value;


    public Token(TokenCheck key, String value) {
        this.key = key;
        this.value = value;
    }

    public TokenCheck getKey() {
        return key;
    }

    public void setKey(TokenCheck key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{ " + "key=" + key + ", value='" + value  + " }";
    }

}