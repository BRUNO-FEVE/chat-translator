package server;

import Interfaces.User;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;



public class ServerChatConnection implements Runnable {


    private ClientSocket clientSocket;
    private Scanner scanner;
    public MessageTranslator translator;

    public String userData;

    private UserDataCallback callback;

    public ServerChatConnection(MessageTranslator translator) {
        this.scanner = new Scanner(System.in);
        this.translator = translator;
    }

    public ServerChatConnection() {
        this.scanner = new Scanner(System.in);
    }

    public interface UserDataCallback {
        void onUserDataReceived(String userData) throws SQLException;
    }

    public void setUserDataCallback(UserDataCallback callback) {
        this.callback = callback;
    }

    public void start(String host, int port, String request) throws IOException {
        try {
            clientSocket = new ClientSocket(new Socket(host, port));
            new Thread(this).start();

            initialMessage(request);

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

            String[] serverData = message.split(";");
            if (serverData[0].equals("Response")) {
                this.userData = message;
                if (this.callback != null) {
                    try {
                        this.callback.onUserDataReceived(this.userData);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (translator != null && message.split(";").length < 2) {
                try {
                    finalMessage = this.translator.translate(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Message: " + finalMessage);
                System.out.print("Write message (or <stop> to end): ");
            } else if (message.split(";").length < 2){
                System.out.println("Message: " + message);
                System.out.print("Write message (or <stop> to end): ");
            }


        }
    }

    public void sendMessage(String message) {
        clientSocket.sendMessage(message);
    }

    private void messageLoop() throws IOException {
        String message;
        System.out.print("Write message (or <stop> to end): ");
        do {
            message = scanner.nextLine();
            clientSocket.sendMessage(message);
        } while (!message.equalsIgnoreCase("stop"));
    }

    public void initialMessage(String message) {
        clientSocket.sendMessage(message);
    }
}
