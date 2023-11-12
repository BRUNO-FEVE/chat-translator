package back;

import java.io.IOException;

import back.entities.User;
import back.modules.Create_txt_file;
import back.modules.Read_txt_file;
import static back.modules.Read_txt_file.splitString;
public class testmain {
    public static void main(String[] args) throws IOException {
        Create_txt_file application = new Create_txt_file();
        User user = new User("Bruno", "salve");
        application.openFile();
        application.addRecords(user);
        application.closeFile();

        Read_txt_file application2 = new Read_txt_file();
        
        User user2 = new User();

        application2.openFile();
        application2.readFile(user2);
        application2.readWholefile(user2);   
        application2.closeFile();
        System.out.println(user.getName());
        System.out.println(user.getMessage());
        System.out.println(user2.getChat());

        String entrada = "Gabriel: Vai se fuder";
        String[] resultado = splitString(entrada);
        System.out.println("Usuário: " + resultado[0]);
        System.out.println("Mensagem: " + resultado[1]);
    }
}