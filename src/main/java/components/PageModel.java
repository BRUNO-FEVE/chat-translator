package components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PageModel {

    public String superTitle, pageId;
    public JPanel caixa;
    public JMenuBar menuBar;
    public JMenuItem back, exit, menu, viewHour, createHours;
    public JMenu languageMenu;
    public JMenuItem englishItem, spanishItem, portugueseItem;

    public PageModel() {
        this.caixa = new JPanel();

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenu languageMenu = new JMenu("Línguas");

        back = new JMenuItem("Voltar");
        exit = new JMenuItem("Sair");

        menu = new JMenuItem("Menu");

        englishItem = new JMenuItem("English");
        spanishItem = new JMenuItem("Español");
        portugueseItem = new JMenuItem("Português");

        fileMenu.add(back);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        languageMenu.add(englishItem);
        languageMenu.add(spanishItem);
        languageMenu.add(portugueseItem);

        menuBar.add(fileMenu);
        menuBar.add(languageMenu);
    }

    public JMenuItem getExitMenuItem() {
        return this.exit;
    }

    public JMenuItem getBackMenuItem() {
        return this.back;
    }

    public JMenuItem getMenuMenuItem() {
        return this.menu;
    }

    public JPanel getScreanContent() {
        return this.caixa;
    }
}
