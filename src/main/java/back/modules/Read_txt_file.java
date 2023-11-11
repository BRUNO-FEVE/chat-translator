package back.modules;

import static back.modules.CaesarCipher.decrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import back.entities.User;

public class Read_txt_file {

    private Scanner input;
    private String filePath = "src/main/java/back/Chat_Messages.txt"; 

    public void openFile()
   {  try
      {  input = new Scanner( new File( filePath ) );
      }  
      catch ( FileNotFoundException fileNotFoundException )
      {  System.err.println( "Error opening file." );
         System.exit( 1 );
      }  
   }  

    public void readFile (User user) {
        
        int shift = 3;
        
        try // read records from file using Scanner object 
        {
                String name = decrypt(input.nextLine(), shift);
                String message = decrypt(input.nextLine(), shift);
                user.setName(name);
                user.setMessage(message);
                
            
        } 
        catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        } 
        catch (IllegalStateException stateException) {
            System.err.println("Error reading from file.");
            System.exit(1);
        } 
    }
    
    public void readWholefile(User user) {
        int shift = 3;
        StringBuilder decryptedChat = new StringBuilder();
    
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String decryptedLine = decrypt(line, shift);
                decryptedChat.append(decryptedLine).append("\n");
            }
    
            user.setChat(decryptedChat.toString());
        } 
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(1);
        }
        catch (IllegalStateException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    

    public void closeFile()
   {  if( input != null )
         input.close(); 
   }  
}