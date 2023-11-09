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
        String name = decrypt(input.nextLine(), shift);
        String message = decrypt(input.nextLine(), shift);
        try // read records from file using Scanner object 
        {
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
    

    public void closeFile()
   {  if( input != null )
         input.close(); 
   }  
}