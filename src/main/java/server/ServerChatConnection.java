package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerChatConnection implements Runnable {


    private ClientSocket clientSocket;
    private Scanner scanner;

    public ServerChatConnection() {
        this.scanner = new Scanner(System.in);
    }

    public void start(String host, int port) throws IOException {
        try {
            clientSocket = new ClientSocket(new Socket(host, port));
            new Thread(this).start();
            messageLoop();
        } finally {
            clientSocket.close();
        }
    }

    @Override
    public void run() {
        String message;
        while ((message = clientSocket.getMessage()) != null) {
            System.out.println("Message: " + message);
            System.out.println("Write message: ");
        }
    }

    private void messageLoop() throws IOException {
        String message;
        System.out.println("Write message (or <stop> to end): ");
        do {
            message = scanner.nextLine();
            clientSocket.sendMessage(message);
        } while (!message.equalsIgnoreCase("stop"));
    }
}
