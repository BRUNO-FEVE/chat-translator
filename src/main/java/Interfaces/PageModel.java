package Interfaces;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PageModel {
    private ResourceBundle resourceBundle;
    public String superTitle, pageId;
    public JPanel caixa;
    public JMenuBar menuBar;
    public JMenuItem back, exit, menu;

    public PageModel() {

        

        this.caixa = new JPanel();

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("menu");

        back = new JMenuItem("voltar");
        exit = new JMenuItem("sair");


        fileMenu.add(back);
        fileMenu.addSeparator();
        fileMenu.add(exit);


        menuBar.add(fileMenu);

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
