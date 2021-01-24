package client;

import commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class DefualtKeywords implements Keywords{
    @Override
    public Map<String, Command> generate(Parser parser) {
        Map<String,Command> keywords=new HashMap<>();
        keywords.put("while",new WhileCommand(parser));
        keywords.put("=",new AssignmentCommand(parser));
        keywords.put("var",new VarCommand(parser));
        keywords.put("print",new PrintCommand(parser));
        keywords.put("disconnect",new DisconnectCommand(parser));
        keywords.put("return",new ReturnCommand(parser));
        keywords.put("openDataServer",new OpenDataServerCommand(parser));
        keywords.put("connect",new ConnectCommand(parser));
        
        return keywords;
    }
}
