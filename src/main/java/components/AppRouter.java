package components;

import Interfaces.User;
import components.Chat;
import infra.ConnectDB;
import server.MessageTranslator;
import server.ServerChatConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

public class AppRouter extends JFrame implements ActionListener, Runnable {

    private final String HOST = "127.0.0.1";
    private final int PORT = 4000;

    public Connection connection;
    public ConnectDB dataBase;
    public JPanel content;
    public Container box;

    public User user = new User();

    public String request;

    public Chat chatPage;

    public ArrayList<String> messages = new ArrayList<String>();

    public ServerChatConnection server;
    public AppRouter() {

//        this.user = new User("brunofodasp@email", "senha123");
//        this.user = new User( "BrunoPokas", "brunofodasp@email", "senha123", "11957705558", "chinese");
        chatPage = new Chat(this.user);

        JPanel chat = new JPanel();

        chat.add(chatPage.content);

        this.login("brunofodasp@email", "senha123");

        this.box = getContentPane();
        box.setLayout(new FlowLayout());

        this.content = chat;
        box.add(content);

        chatPage.sendButton.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == chatPage.sendButton) {
            String message = chatPage.messageField.getText();
            messages.add(message);
            server.sendMessage(message);
        }
    }

    @Override
    public void run() {
    }

    private void login(String email, String password) {
        this.request = "Login;" + email + ";" + password;
    }

    public void register(String name, String email, String password, String phoneNumber, String language) {
        this.request = "Register;" + name + ";" + email + ";" +  password + ";" + phoneNumber + ";" + language;
    }
}
