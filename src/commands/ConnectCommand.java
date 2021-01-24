package commands;

import client.Parser;
import client.Property;
import interpretationErrorsException.InterpretationErrorException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ConnectCommand extends CommandWithParameters{

    public ConnectCommand(Parser parser) {
        super(parser);
    }

    @Override
    public void execute() throws Exception {
        CountDownLatch wait = new CountDownLatch(1);
        new Thread(() -> runClient(wait)).start();
        wait.await();
    }
    private void runClient(CountDownLatch wait)
    {
        int port=0;
        String ip=null;
        try {
             ip=parser.getStringLiteral();
        } catch (InterpretationErrorException e) {
           parser.printErrorAndExit("invalid ip address");
        }
        try {
            port = (int)Double.parseDouble(parser.getParameter());
        } catch (InterpretationErrorException e) {
            parser.printErrorAndExit("invalid port");
        }
        try {
            Socket simulatorServer=new Socket(ip,port);
            wait.countDown();
            PrintWriter writer =new PrintWriter(simulatorServer.getOutputStream());
            BlockingQueue<Property> changedVariables = parser.getVariableManager().getSendToServer();
            while(parser.isAlive())
            {
                Property p= changedVariables.take();
                if(p.getName().equals("bye"))
                	break;
                writer.println("set "+p.getName()+" "+p.getValue());
                writer.flush();
            }
            writer.println("bye");
            writer.flush();
            writer.close();
            simulatorServer.close();
        } catch (IOException e) {
            parser.printErrorAndExit("could not connect to server");
        } catch (InterruptedException e) {
            parser.printErrorAndExit("whomst has awakened me?!");
        }
    }
}
