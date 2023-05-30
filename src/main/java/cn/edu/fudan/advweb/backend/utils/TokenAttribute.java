package cn.edu.fudan.advweb.backend.utils;

public class TokenAttribute {

    private String value;
    private static final String USERNAME = "username";
    private static final String TYPE = "type";

    public TokenAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
