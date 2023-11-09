package back;

import back.entities.User;
import back.modules.Create_txt_file;
import back.modules.Read_txt_file;

public class testmain {
    public static void main(String[] args) {
        Create_txt_file application = new Create_txt_file();
        User user = new User("Gabriel Bianconi", "teste teste teste 1234");
        application.openFile();
        application.addRecords(user);
        application.closeFile();

        Read_txt_file application2 = new Read_txt_file();
        
        User user2 = new User();

        application2.openFile();
        application2.readFile(user2);   
        application2.closeFile();
        System.out.println(user2.getName());
        System.out.println(user2.getMessage());
    }
}