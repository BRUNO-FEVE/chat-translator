package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import Interfaces.User;
import components.PageModel;

public class AppRouter extends JFrame implements ActionListener {

    private String title = "Login";
    private JPanel content, loginPanel, registerPanel, chatPanel, findPanel;
    private Container caixa;
    private JMenuBar menuBar;

    private ArrayList<PageModel> lastPages = new ArrayList<>();

    private LoginPage loginContent;
    private RegisterPage registerContent;
    private ChatPage chatContent;
    private FindPage findContent;

    private User currentUser;

    private ResourceBundle resourceBundle;

    // Adicionamos um construtor que aceita um Locale opcional
    public AppRouter(int languageChoice) {
        setTitle(title);

        // Configurando o ResourceBundle para o idioma padrão ou o idioma fornecido
        switch (languageChoice) {
            case 1:
                resourceBundle = ResourceBundle.getBundle("ex", new Locale("pt", "BR"));
                break;
            case 2:
                resourceBundle = ResourceBundle.getBundle("ex", Locale.US);
                break;
            case 3:
                resourceBundle = ResourceBundle.getBundle("ex", Locale.FRANCE);
                break;
            case 4:
                resourceBundle = ResourceBundle.getBundle("ex", Locale.ITALY);
                break;
            case 5:
                resourceBundle = ResourceBundle.getBundle("ex", Locale.JAPAN);
                break;
            default:
                resourceBundle = ResourceBundle.getBundle("ex");
                break;
        }

        caixa = getContentPane();
        caixa.setLayout(new FlowLayout());

        loginContent = new LoginPage(resourceBundle.getLocale());
        registerContent = new RegisterPage(resourceBundle.getLocale());
        currentUser = new User("Beto Costa", "123456789", "Português", "path/to/picture.jpg");
        chatContent = new ChatPage(currentUser, resourceBundle.getLocale());
        findContent = new FindPage(resourceBundle.getLocale());

        loginPanel = new JPanel();
        registerPanel = new JPanel();
        chatPanel = new JPanel();
        findPanel = new JPanel();

        loginPanel.add(loginContent.getScreanContent());
        registerPanel.add(registerContent.getScreanContent());
        findPanel.add(findContent.getScreanContent());
        chatPanel.add(chatContent.getScreanContent());

        loginContent.registerButton.addActionListener(this);
        loginContent.loginButton.addActionListener(this);
        registerContent.registerButton2.addActionListener(this);
        findContent.findButton.addActionListener(this);

        findContent.exit.addActionListener(this);
        chatContent.exit.addActionListener(this);
        registerContent.exit.addActionListener(this);

        findContent.back.addActionListener(this);
        chatContent.back.addActionListener(this);
        registerContent.back.addActionListener(this);

        this.content = loginPanel;
        caixa.add(content);

        this.menuBar = createMenuBar();
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu languageMenu = new JMenu(resourceBundle.getString("languageMenu"));

        JMenuItem ptMenuItem = new JMenuItem("Português");
        ptMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Locale("pt", "BR"));
            }
        });
        languageMenu.add(ptMenuItem);

        JMenuItem enMenuItem = new JMenuItem("English");
        enMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(Locale.ENGLISH);
            }
        });
        languageMenu.add(enMenuItem);

        menuBar.add(languageMenu);

        return menuBar;
    }

    public void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("MessagesBundle", locale);

        loginContent.updateLocale(locale);
        registerContent.updateLocale(locale);
        findContent.updateLocale(locale);
        chatContent.updateLocale(locale);

        // Update other components or do additional actions if needed

        // Update the menu to reflect the language change
        setJMenuBar(createMenuBar());

        // Redraw the user interface
        revalidate();
        repaint();
    }

    public void updatePage(PageModel page) {
        this.lastPages.add(page);
        System.out.println(lastPages);
        this.content = page.getScreanContent();
        this.menuBar = page.menuBar;
        this.title = page.superTitle;

        this.caixa.removeAll();
        this.caixa.add(content);

        setTitle(title);
        setJMenuBar(menuBar);

        revalidate();
        repaint();
    }

    public void updateTitle(String title) {
        this.title = title;
        setTitle(this.title);
    }

    public void updateMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
        setJMenuBar(this.menuBar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginContent.getRegisterButton()) {
            this.updatePage(registerContent);
            loginContent.cleanFields();
        }
        if (e.getSource() == loginContent.getLoginButton()) {
            this.updatePage(findContent);
            loginContent.cleanFields();
        }

        if (e.getSource() == registerContent.getRegisterButton2()) {
            this.updatePage(loginContent);
            loginContent.cleanFields();
        }

        if (e.getSource() == findContent.getFindButton()) {
            this.updatePage(chatContent);
            loginContent.cleanFields();
        }

        if (e.getSource() == findContent.getExitMenuItem() || e.getSource() == chatContent.getExitMenuItem()
                || e.getSource() == registerContent.getExitMenuItem()) {
            this.updatePage(loginContent);
        } else if (e.getSource() == findContent.getBackMenuItem() || e.getSource() == chatContent.getBackMenuItem()
                || e.getSource() == registerContent.getBackMenuItem()) {
            this.updatePage(this.lastPages.get(this.lastPages.size() - 2));
        }
    }

    // Adicionamos um construtor padrão que usa o idioma padrão (português - Brasil)
    public AppRouter() {
        this(1); // O valor 1 corresponde a pt_BR
    }
}
