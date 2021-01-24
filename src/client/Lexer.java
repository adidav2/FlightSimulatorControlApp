package client;

import java.util.List;

public interface Lexer {
    public List<String> lex(String str);
    public String insertElementsIntoTokens(String str);
}
