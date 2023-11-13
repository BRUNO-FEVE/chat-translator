package server;

import Interfaces.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerChatConnection implements Runnable {


    private ClientSocket clientSocket;
    private Scanner scanner;
    private MessageTranslator translator;

    public ServerChatConnection(MessageTranslator translator) {
        this.scanner = new Scanner(System.in);
        this.translator = translator;
    }

    public void start(String host, int port, User user, boolean isLogin) throws IOException {
        try {
            clientSocket = new ClientSocket(new Socket(host, port));
            new Thread(this).start();

            initialMessage(isLogin, user);

            messageLoop();
        } finally {
            clientSocket.close();
        }
    }

    @Override
    public void run() {
        String message;
        while ((message = clientSocket.getMessage()) != null) {
            String finalMessage;
            try {
               finalMessage = this.translator.translate(message);
            } catch (IOException error) {
                finalMessage = message;
            }

            System.out.println("Message: " + finalMessage);
            System.out.print("Write message: ");
        }
    }

    private void messageLoop() throws IOException {
        String message;
        System.out.print("Write message (or <stop> to end): ");
        do {
            message = scanner.nextLine();
            clientSocket.sendMessage(message);
        } while (!message.equalsIgnoreCase("stop"));
    }

    public void initialMessage(boolean isLogin, User user) {
        if (isLogin) {
            clientSocket.sendMessage("Login;" + user.email + ";" + user.password);
        } else {
            clientSocket.sendMessage("Register;" + user.name + ";" + user.email + ";" +  user.password + ";" + user.phoneNumber + ";" + user.language);
        }
    }
}
