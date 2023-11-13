package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Interfaces.PageModel;

public class LanguagePage extends PageModel implements ActionListener {
private ResourceBundle resourcebundle = null;

public LanguagePage() {
    int op = Integer.parseInt(JOptionPane.showInputDialog("Select the language\n\n1- Portugues\n2- English\n3- Francaise\n4- Italian\n5- Deutsch"));

    switch (op) {
        case 1:
            resourcebundle = ResourceBundle.getBundle("ex", new Locale("pt", "BR"));
            break;
        case 2:
            resourcebundle = ResourceBundle.getBundle("ex", Locale.US);
            break;
        case 3:
            resourcebundle = ResourceBundle.getBundle("ex", Locale.FRANCE);
            break;
        case 4:
            resourcebundle = ResourceBundle.getBundle("ex", Locale.ITALY);
            break;
        case 5:
            resourcebundle = ResourceBundle.getBundle("ex", Locale.GERMANY);
            break;
    }

    
    if (resourcebundle != null) {
        Locale.setDefault(resourcebundle.getLocale());
    } else {
        
        Locale.setDefault(Locale.ENGLISH);
    }
}


@Override
public void actionPerformed(ActionEvent e) {

}
}
