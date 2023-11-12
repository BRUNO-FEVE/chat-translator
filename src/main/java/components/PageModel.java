package components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PageModel {

    public String superTitle, pageId;
    public JPanel caixa;
    public JMenuBar menuBar;
    public JMenuItem back, exit, menu;
    public JMenu languageMenu;
    public JMenuItem englishItem, germanItem, portugueseItem, frenchItem, italianItem;

    public PageModel() {

        this.caixa = new JPanel();

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        JMenu languageMenu = new JMenu("Línguas");

        back = new JMenuItem("Voltar");
        exit = new JMenuItem("Sair");

        menu = new JMenuItem("Menu");

        englishItem = new JMenuItem("English");
        germanItem = new JMenuItem("Deutsch");
        portugueseItem = new JMenuItem("Português");
        frenchItem = new JMenuItem("Français");
        italianItem = new JMenuItem("Italiano");

        fileMenu.add(back);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        languageMenu.add(englishItem);
        languageMenu.add(germanItem);
        languageMenu.add(portugueseItem);
        languageMenu.add(frenchItem);
        languageMenu.add(italianItem);

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

    public JMenuItem getEnglishItemMenuItem() {
        return this.englishItem;
    }

    public JMenuItem getGermanItemMenuItem() {
        return this.germanItem;
    }

    public JMenuItem getItalianItemMenuItem() {
        return this.italianItem;
    }

    public JMenuItem getPortugueseItemMenuItem() {
        return this.portugueseItem;
    }

    public JMenuItem getFrenchItemMenuItem() {
        return this.frenchItem;
    }
}
