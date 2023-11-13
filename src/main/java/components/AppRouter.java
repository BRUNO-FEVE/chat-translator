package components;

import Interfaces.PageModel;
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
import java.util.Locale;
import java.util.ResourceBundle;
import back.entities.ChatUser;

public class AppRouter extends JFrame implements ActionListener, Runnable {

    private final String HOST = "127.0.0.1";
    private final int PORT = 4000;

    public Connection connection;
    public ConnectDB dataBase;
    public Container box;

    public User user = new User();

    public String request;

    public Chat chatPage;

    public ArrayList<String> messages = new ArrayList<String>();

    public ServerChatConnection server;

    // novo

    private String title = "Login";
    private JPanel content, loginPanel, registerPanel, chatPanel, findPanel;
    private Container caixa;
    private JMenuBar menuBar;
    
    private ArrayList<PageModel> lastPages = new ArrayList<PageModel>();

    private RegisterPage registerContent;
    private ChatPage chatContent;
    private FindPage findContent;
    private LanguagePage languageContent;
    private LoginPage loginContent;

    private ResourceBundle resourceBundle;

    public boolean loginValidated = false;

    public AppRouter() {
        setTitle(title);
//        this.user = new User("brunofodasp@email", "senha123");
//        this.user = new User( "BrunoPokas", "brunofodasp@email", "senha123", "11957705558", "chinese");
        chatPage = new Chat(this.user);

        JPanel chat = new JPanel();

        chat.add(chatPage.content);

        this.login("brunofodasp@email", "senha123");

        this.box = getContentPane();
        box.setLayout(new FlowLayout());
        
        languageContent = new LanguagePage();
        resourceBundle = ResourceBundle.getBundle("Ex", Locale.getDefault());
        loginContent = new LoginPage(resourceBundle.getLocale());

        
        caixa = getContentPane();
        caixa.setLayout(new FlowLayout());

        loginPanel = new JPanel();
        loginPanel.add(loginContent.getScreenContent());

        
        registerContent = new RegisterPage(resourceBundle.getLocale());
        chatContent = new ChatPage(user, resourceBundle.getLocale());

        ChatUser chatUser = new ChatUser();
        chatContent.updateMessages(chatUser);
        findContent = new FindPage(resourceBundle.getLocale());

        registerPanel = new JPanel();
        chatPanel = new JPanel();
        findPanel = new JPanel();

        registerPanel.add(registerContent.getScreanContent());
        findPanel.add(findContent.getScreanContent());
        chatPanel.add(chatContent.getScreanContent());

        loginContent.getRegisterButton().addActionListener(this);
        loginContent.getLoginButton().addActionListener(this);
        registerContent.registerUserButton.addActionListener(this);
        findContent.getFindButton().addActionListener(this);

        findContent.getExitMenuItem().addActionListener(this);
        chatContent.getExitMenuItem().addActionListener(this);
        registerContent.getExitMenuItem().addActionListener(this);

        findContent.getBackMenuItem().addActionListener(this);
        chatContent.getBackMenuItem().addActionListener(this);
        registerContent.getBackMenuItem().addActionListener(this);
        
        this.content = loginPanel;
        caixa.add(content);

        
        setJMenuBar(loginContent.menuBar);

        chatPage.sendButton.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocale(resourceBundle.getLocale());
        setVisible(true);
    }

    public void updatePage(PageModel page) {
        this.lastPages.add(page);
//        System.out.println(lastPages);
        this.title = page.superTitle;

        this.caixa.removeAll();
        this.caixa.add(page.getScreanContent());

        
        setJMenuBar(page.menuBar);

        setTitle(title);

        revalidate();
        repaint();
    }

    public void updateTitle(String title) {
        this.title = title;
        setTitle(this.title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginContent.getRegisterButton()) {
            this.updatePage(registerContent);
            loginContent.cleanFields();
        } else if (e.getSource() == loginContent.getLoginButton()) {
            String requestLogin = loginContent.getLoginProps();
            this.server.sendMessage(requestLogin);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            if (this.server.responseStatus) {
                updatePage(findContent);
                loginContent.cleanFields();
            } else {
                JOptionPane.showMessageDialog(null, "Senha/Email incorretos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == registerContent.registerUserButton) {
            String userData = registerContent.getNewUser();
            System.out.println(userData);
            this.server.sendMessage(userData);

            this.updatePage(loginContent);
            loginContent.cleanFields();
        } else if (e.getSource() == findContent.getFindButton()) {
            this.updatePage(chatContent);
            loginContent.cleanFields();
        } else if (e.getSource() == findContent.getExitMenuItem() || e.getSource() == chatContent.getExitMenuItem()
                || e.getSource() == registerContent.getExitMenuItem()
                || e.getSource() == loginContent.getExitMenuItem()) {
            this.updatePage(loginContent);
        } else if (e.getSource() == chatPage.sendButton) {
            String message = chatPage.messageField.getText();
            messages.add(message);
            server.sendMessage(message);
        }

        if (e.getSource() == findContent.getBackMenuItem() || 
            e.getSource() == chatContent.getBackMenuItem() || 
            e.getSource() == registerContent.getBackMenuItem() ||
            e.getSource() == loginContent.getBackMenuItem()
            ) {
            if (this.lastPages.size() >= 2) {
                if (this.lastPages.get(this.lastPages.size() - 2) instanceof ChatPage) {
                    this.updatePage(loginContent);
                } else {
                    this.updatePage(this.lastPages.get(this.lastPages.size() - 2));
                }
            } else {
                this.updatePage(loginContent);
            }}
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
