package client;

import java.util.ArrayList;
import java.util.List;

public class ParsingInfo {

    private List<String> tokens;
    private int index;


    public ParsingInfo(List<String> tokens, int index){
        this.tokens = tokens;
        this.index = index;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
