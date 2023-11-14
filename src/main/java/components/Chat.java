package components;

import Interfaces.PageInterface;
import Interfaces.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Chat extends PageInterface {

    public ArrayList<String> messages;
    private DefaultTableModel tableModel;
    private JTable messageTable;
    public JTextField messageField;
    public JButton sendButton;

    public String language;

    public Chat(User user) {
        super.superTitle = "Chat";

        super.content.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel userName = new JLabel(user.name);
        JLabel userLanguage = new JLabel("(" + this.language + ")");
        topPanel.add(userName);
        topPanel.add(userLanguage);
        super.content.add(topPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new String[]{"User", "Message"}, 0);
        messageTable = new JTable(tableModel);
        messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        messageTable.getColumnModel().getColumn(0).setPreferredWidth(75);
        JScrollPane scrollPane = new JScrollPane(messageTable);
        scrollPane.setPreferredSize(new Dimension(350, 500));
        JPanel mediumPanel = new JPanel(new BorderLayout());
        mediumPanel.add(scrollPane, BorderLayout.CENTER);
        super.content.add(mediumPanel, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Send");
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        super.content.add(bottomPanel, BorderLayout.SOUTH);
    }
}
