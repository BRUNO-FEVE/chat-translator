package back;

import java.io.IOException;

import back.entities.ChatUser;
import back.modules.Create_txt_file;
import back.modules.Read_txt_file;
import static back.modules.Read_txt_file.splitString;
public class testmain {
    public static void main(String[] args) throws IOException {
//        Create_txt_file application = new Create_txt_file();
//        ChatUser user = new ChatUser("Bruno", "Vai se fuder bobo a ha ");
//        application.openFile();
//        application.addRecords(user);
//        application.closeFile();
//
//
//        ChatUser user22 = new ChatUser("Bruno", "POKAS");
//        application.openFile();
//        application.addRecords(user22);
//        application.closeFile();
//
//
//
//        String entrada = "Gabriel: Vai se fuder";
//        String[] resultado = splitString(entrada);
//        System.out.println("Usuário: " + resultado[0]);
//        System.out.println("Mensagem: " + resultado[1]);
//
//        ChatUser user3 = new ChatUser(resultado[0], resultado[1]);
//        Create_txt_file application3 = new Create_txt_file();
//        application3.openFile();
//        application3.addRecords(user3);
//        application3.closeFile();
        
        Read_txt_file application2 = new Read_txt_file();
        ChatUser user2 = new ChatUser();
        application2.openFile();
        application2.readFile(user2);
        application2.readWholefile(user2);   
        application2.closeFile();
        //System.out.println(user.getName());
        //System.out.println(user.getMessage());
        //System.out.println(user2.getChat());
        System.out.println(user2.getChat());
    }
}