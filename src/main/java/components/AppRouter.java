package components;

import Interfaces.User;
import components.Chat;
import infra.ConnectDB;
import server.MessageTranslator;
import server.ServerChatConnection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AppRouter extends JFrame {

    public Connection connection;
    public ConnectDB dataBase;
    public JPanel content;
    public Container box;

    private final String HOST = "127.0.0.1";
    private final int PORT = 4000;

    private User user;

    public AppRouter() throws SQLException {

        this.user = new User("brunofodasp@ail.com", "password123");
        Chat chatPage = new Chat(this.user);

        this.ConnectToServer();

        JPanel chat = new JPanel();
        chat.add(chatPage.content);

        this.box = getContentPane();
        box.setLayout(new FlowLayout());

        this.content = chat;
        box.add(content);

        pack();
        setLocationRelativeTo(null);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void ConnectToServer() {
        System.out.println(" -- Client Console -- ");
        try {
            MessageTranslator translator = new MessageTranslator(this.user.language);

            ServerChatConnection serverChatConnection = new ServerChatConnection(translator);
            serverChatConnection.start(this.HOST, this.PORT, this.user, true);
        } catch (IOException error) {
            System.out.println("Error on Connecting to Server: " + error.getMessage());
        }
        System.out.println(" -- Client Console Stopped -- ");
    }
}
