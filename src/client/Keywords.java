package client;

import commands.Command;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public interface Keywords {
    public Map<String, Command> generate(Parser parser);
}
