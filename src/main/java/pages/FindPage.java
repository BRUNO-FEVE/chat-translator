package pages;

import components.PageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class FindPage extends PageModel implements ActionListener {

    private JLabel conversationLabel;
    private JTextField chatTextField;
    public JButton findButton;

    private ResourceBundle resourceBundle;

    public FindPage(Locale locale) {
        super();

        // Configurando o ResourceBundle para o idioma fornecido
        resourceBundle = ResourceBundle.getBundle("Ex", locale);

        conversationLabel = new JLabel(resourceBundle.getString("conversationLabel"));
        chatTextField = new JTextField(15); // Ajuste o tamanho conforme necessário
        findButton = new JButton(resourceBundle.getString("findButton"));

        // Configuração do layout usando GridBagLayout
        caixa.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Ajuste a folga conforme necessário
        caixa.add(conversationLabel, gbc);

        gbc.gridy = 1;
        caixa.add(chatTextField, gbc);

        gbc.gridy = 2;
        caixa.add(findButton, gbc);

        // Adicionando um ouvinte de ação para o botão
        findButton.addActionListener(this);
    }

    public JTextField getChatTextField() {
        return chatTextField;
    }

    public JButton getFindButton() {
        return findButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica aqui para lidar com o clique no botão
    }

    public void updateLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("Ex", locale);
        conversationLabel.setText(resourceBundle.getString("conversationLabel"));
        findButton.setText(resourceBundle.getString("findButton"));
        // Adicione outras atualizações necessárias
    }
}
