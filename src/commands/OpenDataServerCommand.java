package commands;
import client.Parser;
import interpretationErrorsException.InterpretationErrorException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class OpenDataServerCommand extends CommandWithParameters {
    public OpenDataServerCommand(Parser p) {
        super(p);
    }

    @Override
    // blocks until a client connects or a timeout has passed
    public void execute() throws Exception {
        CountDownLatch wait = new CountDownLatch(1);
        new Thread(() -> runServer(wait)).start();
        wait.await();
    }

    private void runServer(CountDownLatch wait) {
        int port = 0, timesPerSec = 0;
        try {
            port = (int)Double.parseDouble(parser.getParameter());
        } catch (InterpretationErrorException e) {
            parser.printErrorAndExit("invalid port");
        }
        try {
            timesPerSec = (int)Double.parseDouble(parser.getParameter());
        } catch (InterpretationErrorException e) {
            parser.printErrorAndExit("invalid times per sec");
        }

        try {
            ServerSocket server = new ServerSocket(port);
            Socket clientSocket = server.accept();
            Scanner s = new Scanner(clientSocket.getInputStream());
            while (parser.isAlive()) {
                if (s.hasNext()) {
                    List<String> values = Arrays.asList(s.next().split(","));
                    Double simX = Double.parseDouble(values.get(0));
                    Double simY = Double.parseDouble(values.get(1));
                    Double simZ = Double.parseDouble(values.get(2));
                    parser.getVariableManager().addForeignVariable("simX", simX);
                    parser.getVariableManager().addForeignVariable("simY", simY);
                    parser.getVariableManager().addForeignVariable("simZ", simZ);
                    wait.countDown();
                }
                Thread.sleep(1000 / timesPerSec);
            }
            s.close();
            clientSocket.close();
            server.close();
        } catch (IOException e) {
        	e.printStackTrace();
            parser.printErrorAndExit("could not establish server");
        } catch (InterruptedException e) {
            parser.printErrorAndExit("whomst has awakened me?!");
        }


    }
}
