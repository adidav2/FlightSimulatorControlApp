package expressionizedParameterHandling;

import client.ParsingInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class  Prearranger {
    protected ParsingInfo parsingInfo;
    protected Hierarchy hierarchy;

    public abstract ParsingInfo prearrange (ParsingInfo parsingInfo, Hierarchy hierarchy);
}
