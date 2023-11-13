package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Interfaces.PageModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginPage extends PageModel implements ActionListener {

    private JLabel loginLabel, passwordLabel, logoLabel;
    private ImageIcon logoIcon, scaledIcon;
    private Image scaledImage;

    public JPasswordField passwordField;
    public JTextField emailField;
    public JButton loginButton, registerButton;
    public BorderLayout PageLayout = new BorderLayout(10, 10);

    private ResourceBundle resourceBundle;

    public LoginPage(Locale locale) {
        super.superTitle = "Login";

        
        resourceBundle = ResourceBundle.getBundle("Ex", locale);

        loginLabel = new JLabel(resourceBundle.getString("loginLabel"));
        passwordLabel = new JLabel(resourceBundle.getString("passwordLabel"));

        emailField = new JTextField("", 30);
        passwordField = new JPasswordField("", 30);

        registerButton = new JButton(resourceBundle.getString("registerButton"));
        loginButton = new JButton(resourceBundle.getString("loginButton"));

        EmptyBorder margin = new EmptyBorder(50, 0, 0, 0);
        
        logoIcon = new ImageIcon("./assets/logoMaua.png");
        scaledImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(scaledIcon);
        logoLabel.setBorder(margin);

        
        Dimension buttonSize = new Dimension(110, 40);
        registerButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);

        super.caixa.setLayout(new BorderLayout(10, 10));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        centerPanel.setBorder(margin);

        
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(loginLabel, gbc);
        gbc.gridy = 1;
        centerPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(emailField, gbc);
        gbc.gridy = 1;
        centerPanel.add(passwordField, gbc);

        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        super.caixa.add(centerPanel, BorderLayout.CENTER);
        super.caixa.add(buttonPanel, BorderLayout.SOUTH);
        super.caixa.add(logoLabel, BorderLayout.NORTH);

        
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    public String getLoginProps() {
        return "Login;" + this.emailField.getText() + ";" + new String(this.passwordField.getPassword());
    }

    public void cleanFields() {
        this.emailField.setText("");
        this.passwordField.setText("");
    }

    public JButton getLoginButton() {
        return this.loginButton;
    }

    public JButton getRegisterButton() {
        return this.registerButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    
    public JPanel getScreenContent() {
        return super.caixa;
    }
}
