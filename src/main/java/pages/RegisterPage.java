package pages;

import components.PageModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterPage extends PageModel implements ActionListener {

    private JLabel nameLabel, telLabel, emailLabel, passwordLabel, confirmLabel;
    public JTextField nameField, telField, emailField;
    public JPasswordField passwordField, confirmField;
    public JButton registerButton2;

    private ResourceBundle resourceBundle;

    public RegisterPage(Locale locale) {
        super.superTitle = "Registre-se";

        // Configurando o ResourceBundle para o idioma fornecido
        resourceBundle = ResourceBundle.getBundle("MessagesBundle", locale);

        nameLabel = new JLabel(resourceBundle.getString("nameLabel"));
        telLabel = new JLabel(resourceBundle.getString("telLabel"));
        emailLabel = new JLabel(resourceBundle.getString("emailLabel"));
        passwordLabel = new JLabel(resourceBundle.getString("passwordLabel"));
        confirmLabel = new JLabel(resourceBundle.getString("confirmLabel"));

        nameField = new JTextField("", 20);
        telField = new JTextField("", 20);
        emailField = new JTextField("", 20);
        passwordField = new JPasswordField("", 20);
        confirmField = new JPasswordField("", 20);

        registerButton2 = new JButton(resourceBundle.getString("registerButton"));

        // Layout
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
        JPanel buttomPanel = new JPanel(new FlowLayout());

        JPanel nameRow = new JPanel(new BorderLayout());
        JPanel telRow = new JPanel(new BorderLayout());
        JPanel emailRow = new JPanel(new BorderLayout());
        JPanel passwordRow = new JPanel(new BorderLayout());
        JPanel confirmRow = new JPanel(new BorderLayout());

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        telPanel.add(telLabel);
        telPanel.add(telField);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPanel.add(confirmLabel);
        confirmPanel.add(confirmField);
        buttomPanel.add(registerButton2);

        nameRow.add(namePanel, BorderLayout.LINE_END);
        telRow.add(telPanel, BorderLayout.LINE_END);
        emailRow.add(emailPanel, BorderLayout.LINE_END);
        passwordRow.add(passwordPanel, BorderLayout.LINE_END);
        confirmRow.add(confirmPanel, BorderLayout.LINE_END);

        column.add(nameRow);
        column.add(telRow);
        column.add(emailRow);
        column.add(passwordRow);
        column.add(confirmRow);

        columnPage.add(column);
        columnPage.add(buttomPanel);

        super.menuBar.setVisible(false);

        super.caixa.add(columnPage);

        // Adicionando ouvinte de ação para o botão de registro
        registerButton2.addActionListener(this);
    }

    public Object[] getNewUser() {
        Object[] newUser = {
                this.nameField.getText(),
                this.telField.getText(),
                this.emailField.getText(),
                new String(this.passwordField.getPassword()),
        };
        return newUser;
    }

    public void cleanFields() {
        this.nameField.setText("");
        this.telField.setText("");
        this.emailField.setText("");
        this.passwordField.setText("");
        this.confirmField.setText("");
    }

    public JButton getRegisterButton2() {
        return this.registerButton2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica aqui para lidar com o clique no botão de registro
    }

    public void updateLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("MessagesBundle", locale);
        nameLabel.setText(resourceBundle.getString("nameLabel"));
        telLabel.setText(resourceBundle.getString("telLabel"));
        emailLabel.setText(resourceBundle.getString("emailLabel"));
        passwordLabel.setText(resourceBundle.getString("passwordLabel"));
        confirmLabel.setText(resourceBundle.getString("confirmLabel"));
        registerButton2.setText(resourceBundle.getString("registerButton"));
        // Adicione outras atualizações necessárias
    }
}
