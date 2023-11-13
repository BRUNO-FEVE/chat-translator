package components;

import javax.swing.*;

import Interfaces.PageModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class FindPage extends PageModel implements ActionListener {

    private JLabel conversationLabel;
    private JTextField chatTextField;
    public JButton findButton;

    private ResourceBundle resourceBundle;

    public FindPage(Locale locale) {
        super();

        
        resourceBundle = ResourceBundle.getBundle("Ex", locale);

        conversationLabel = new JLabel(resourceBundle.getString("conversationLabel"));
        chatTextField = new JTextField(15); 
        findButton = new JButton(resourceBundle.getString("findButton"));

        
        caixa.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); 
        caixa.add(conversationLabel, gbc);

        gbc.gridy = 1;
        caixa.add(chatTextField, gbc);

        gbc.gridy = 2;
        caixa.add(findButton, gbc);

        
        findButton.addActionListener(this);
    }

    public JTextField getChatTextField() {
        return chatTextField;
    }

    public JButton getFindButton() {
        return findButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public void updateLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Ex", locale);
        conversationLabel.setText(resourceBundle.getString("conversationLabel"));
        findButton.setText(resourceBundle.getString("findButton"));
        
    }

}
