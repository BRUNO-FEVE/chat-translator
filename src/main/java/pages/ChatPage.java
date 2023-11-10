package pages;

import Interfaces.User;
import components.PageModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChatPage extends PageModel {

    public ArrayList<String> messages;
    private DefaultTableModel tableModel;
    private JTable messageTable;
    private JTextField messageField;

    public ResourceBundle resourceBundle;

    public ChatPage(User user, Locale locale) {
        super.superTitle = "Chat";

        // Configurando o ResourceBundle para o idioma fornecido
        resourceBundle = ResourceBundle.getBundle("Ex", locale);

        super.caixa.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel userName = new JLabel(user.name);
        JLabel userLanguage = new JLabel("(" + user.language + ")");
        topPanel.add(userName);
        topPanel.add(userLanguage);
        super.caixa.add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { resourceBundle.getString("userLabel"), resourceBundle.getString("messageLabel") }, 0);
        messageTable = new JTable(tableModel);
        messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        messageTable.getColumnModel().getColumn(0).setPreferredWidth(75);
        JScrollPane scrollPane = new JScrollPane(messageTable);
        scrollPane.setPreferredSize(new Dimension(450, 600));
        JPanel mediumPanel = new JPanel(new BorderLayout());
        mediumPanel.add(scrollPane, BorderLayout.CENTER);
        super.caixa.add(mediumPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        JButton sendButton = new JButton(resourceBundle.getString("sendButtonLabel"));
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        super.caixa.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Ex", locale);
    }
}
