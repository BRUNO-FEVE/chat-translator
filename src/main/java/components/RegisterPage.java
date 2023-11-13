package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Interfaces.PageModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterPage extends PageModel  {

    private JLabel nameLabel, phoneLabel, emailLabel, passwordLabel, confirmLabel, languageLabel;
    public JTextField nameField, phoneField, emailField, languageField;
    public JPasswordField passwordField, confirmField;
    public JButton registerUserButton;

    private ResourceBundle resourceBundle;

    public RegisterPage(Locale locale) {
        super.superTitle = "Registre-se";

        
        resourceBundle = ResourceBundle.getBundle("Ex", locale);

        nameLabel = new JLabel(resourceBundle.getString("nameLabel"));
        phoneLabel = new JLabel(resourceBundle.getString("telLabel"));
        emailLabel = new JLabel(resourceBundle.getString("emailLabel"));
        passwordLabel = new JLabel(resourceBundle.getString("passwordLabel"));
        confirmLabel = new JLabel(resourceBundle.getString("confirmLabel"));
        languageLabel = new JLabel(resourceBundle.getString("languageLabel"));

        nameField = new JTextField("", 20);
        phoneField = new JTextField("", 20);
        emailField = new JTextField("", 20);
        passwordField = new JPasswordField("", 20);
        confirmField = new JPasswordField("", 20);
        languageField = new JTextField("", 20);

        registerUserButton = new JButton(resourceBundle.getString("registerButton"));

        
        EmptyBorder padding = new EmptyBorder(15, 0, 15, 0);

        super.caixa.setLayout(new FlowLayout());

        JPanel columnPage = new JPanel(new GridLayout(2, 1));
        JPanel column = new JPanel(new GridLayout(8, 1));

        column.setBorder(padding);

        JPanel namePanel = new JPanel(new FlowLayout());
        JPanel telPanel = new JPanel(new FlowLayout());
        JPanel emailPanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JPanel confirmPanel = new JPanel(new FlowLayout());
        JPanel languagePanel = new JPanel(new FlowLayout());
        JPanel buttomPanel = new JPanel(new FlowLayout());

        JPanel nameRow = new JPanel(new BorderLayout());
        JPanel telRow = new JPanel(new BorderLayout());
        JPanel emailRow = new JPanel(new BorderLayout());
        JPanel passwordRow = new JPanel(new BorderLayout());
        JPanel confirmRow = new JPanel(new BorderLayout());
        JPanel languageRow = new JPanel(new BorderLayout());

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        telPanel.add(phoneLabel);
        telPanel.add(phoneField);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPanel.add(confirmLabel);
        confirmPanel.add(confirmField);
        languagePanel.add(languageLabel);
        languagePanel.add(languageField);
        buttomPanel.add(registerUserButton);

        nameRow.add(namePanel, BorderLayout.LINE_END);
        telRow.add(telPanel, BorderLayout.LINE_END);
        emailRow.add(emailPanel, BorderLayout.LINE_END);
        passwordRow.add(passwordPanel, BorderLayout.LINE_END);
        confirmRow.add(confirmPanel, BorderLayout.LINE_END);
        languageRow.add(languagePanel, BorderLayout.LINE_END);

        column.add(nameRow);
        column.add(telRow);
        column.add(emailRow);
        column.add(passwordRow);
        column.add(confirmRow);
        column.add(languageRow);

        columnPage.add(column);
        columnPage.add(buttomPanel);

        super.caixa.add(columnPage);
    }

    public String getNewUser() {
        return "Register;" + nameField.getText() + ";" + emailField.getText() + ";" + new String(passwordField.getPassword()) + ";" + phoneField.getText() + ";" + languageField.getText();
    }

    public void cleanFields() {
        this.nameField.setText("");
        this.phoneField.setText("");
        this.emailField.setText("");
        this.passwordField.setText("");
        this.confirmField.setText("");
    }

    public void updateLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Ex", locale);
        nameLabel.setText(resourceBundle.getString("nameLabel"));
        phoneLabel.setText(resourceBundle.getString("telLabel"));
        emailLabel.setText(resourceBundle.getString("emailLabel"));
        passwordLabel.setText(resourceBundle.getString("passwordLabel"));
        confirmLabel.setText(resourceBundle.getString("confirmLabel"));
        languageLabel.setText(resourceBundle.getString("languageLabel"));
        registerUserButton.setText(resourceBundle.getString("registerButton"));
    }

}
