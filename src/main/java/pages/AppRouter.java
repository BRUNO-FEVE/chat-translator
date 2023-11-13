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
    public JMenuItem englishItem, germanItem, portugueseItem, frenchItem, italianItem;

    private ArrayList<PageModel> lastPages = new ArrayList<>();

    private LoginPage loginContent;
    private RegisterPage registerContent;
    private ChatPage chatContent;
    private FindPage findContent;

    private User currentUser;

    private ResourceBundle resourceBundle;

    public AppRouter() {
        setTitle(title);

        // Initialize resourceBundle first
        resourceBundle = ResourceBundle.getBundle("Ex", Locale.getDefault());

        loginContent = new LoginPage(resourceBundle.getLocale());

        // Initialize other components
        caixa = getContentPane();
        caixa.setLayout(new FlowLayout());

        loginPanel = new JPanel();
        loginPanel.add(loginContent.getScreenContent());

        // Create the rest of the components
        registerContent = new RegisterPage(resourceBundle.getLocale());
        currentUser = new User("Carlos Costa", "123456789", "Português", "path/to/picture.jpg");
        chatContent = new ChatPage(currentUser, resourceBundle.getLocale());
        findContent = new FindPage(resourceBundle.getLocale());

        registerPanel = new JPanel();
        chatPanel = new JPanel();
        findPanel = new JPanel();

        registerPanel.add(registerContent.getScreanContent());
        findPanel.add(findContent.getScreanContent());
        chatPanel.add(chatContent.getScreanContent());

        loginContent.getRegisterButton().addActionListener(this);
        loginContent.getLoginButton().addActionListener(this);
        registerContent.getRegisterButton2().addActionListener(this);
        findContent.getFindButton().addActionListener(this);

        findContent.getExitMenuItem().addActionListener(this);
        chatContent.getExitMenuItem().addActionListener(this);
        registerContent.getExitMenuItem().addActionListener(this);

        findContent.getBackMenuItem().addActionListener(this);
        chatContent.getBackMenuItem().addActionListener(this);
        registerContent.getBackMenuItem().addActionListener(this);

        this.content = loginPanel;
        caixa.add(content);

        // Set the menu bar for the initial page
        setJMenuBar(loginContent.menuBar);

        pack();
        setLocationRelativeTo(null);
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocale(resourceBundle.getLocale());
        setVisible(true);
    }

    public void updatePage(PageModel page) {
        this.lastPages.add(page);
        System.out.println(lastPages);
        this.title = page.superTitle;

        this.caixa.removeAll();
        this.caixa.add(page.getScreanContent());

        // Set the menu bar directly for the frame
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
            this.updatePage(findContent);
            loginContent.cleanFields();
        } else if (e.getSource() == registerContent.getRegisterButton2()) {
            this.updatePage(loginContent);
            loginContent.cleanFields();
        } else if (e.getSource() == findContent.getFindButton()) {
            this.updatePage(chatContent);
            loginContent.cleanFields();
        } else if (e.getSource() == findContent.getExitMenuItem() || e.getSource() == chatContent.getExitMenuItem()
                || e.getSource() == registerContent.getExitMenuItem()
                || e.getSource() == loginContent.getExitMenuItem()) {
            this.updatePage(loginContent);

        }

        if (e.getSource() == findContent.getBackMenuItem() || e.getSource() == chatContent.getBackMenuItem()
                || e.getSource() == registerContent.getBackMenuItem()
                || e.getSource() == loginContent.getBackMenuItem()) {
            if (this.lastPages.size() >= 2) {
                // Check if the second-to-last page is ChatPage
                // If yes, go back to LoginPage instead of the second-to-last page
                if (this.lastPages.get(this.lastPages.size() - 2) instanceof ChatPage) {
                    this.updatePage(loginContent);
                } else {
                    this.updatePage(this.lastPages.get(this.lastPages.size() - 2));
                }
            } else {
                this.updatePage(loginContent);
            }
        }

        if (e.getSource() == englishItem) {
            setLocaleAndRefresh(Locale.ENGLISH);
        } else if (e.getSource() == germanItem) {
            setLocaleAndRefresh(Locale.GERMAN);
        } else if (e.getSource() == portugueseItem) {
            setLocaleAndRefresh(new Locale("pt", "BR"));
        } else if (e.getSource() == frenchItem) {
            setLocaleAndRefresh(Locale.FRENCH);
        } else if (e.getSource() == italianItem) {
            setLocaleAndRefresh(Locale.ITALIAN);
        }
    }

    private void setLocaleAndRefresh(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Ex", locale);
        setLocale(locale);

    }
}
