package components;

import Interfaces.User;
import components.Chat;
import infra.ConnectDB;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AppRouter extends JFrame {

    public Connection connection;
    public ConnectDB dataBase;
    public JPanel content;
    public Container box;
    public AppRouter() throws SQLException {

        this.dataBase = new ConnectDB();
        this.connection = dataBase.connection();

        User user = new User("Bruno", "11957705558", "pt-br", "/tmp/picture");
        Chat chatPage = new Chat(user);

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
}
