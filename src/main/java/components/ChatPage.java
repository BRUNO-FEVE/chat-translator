package components;

import Interfaces.PageModel;
import Interfaces.User;
import back.modules.Read_txt_file;
import back.entities.ChatUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void updateMessages(ChatUser chatuser) {
        Read_txt_file fileReader = new Read_txt_file();
        fileReader.openFile();
        fileReader.readWholefile(chatuser);
        fileReader.closeFile();
    
        DefaultTableModel model = (DefaultTableModel) messageTable.getModel();
        model.setRowCount(0); 
    
        for (String line : chatuser.getChat().split("\n")) {
            String[] parts = Read_txt_file.splitString(line);
            model.addRow(parts);
        }
    }
    

}
