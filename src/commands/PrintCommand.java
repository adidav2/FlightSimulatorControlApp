package commands;

import client.Parser;
import client.VariableProperty;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class PrintCommand extends CommandWithParameters{
    public PrintCommand(Parser parser)
    {
        super(parser);
    }
    @Override
    public void execute() throws Exception {
         List<String> list=parser.getUncalculatedParameter();
         try
         {
             System.out.println(parser.getVariableManager().get(list.get(0)));
         }catch (Exception e)
         {
             System.out.println(parser.calculateUncalculatedParameter(list));
         }
    }
}