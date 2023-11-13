package back.modules;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import back.entities.User;
import static back.modules.CaesarCipher.encrypt;

public class Create_txt_file
{  private Formatter output; 
   private String filePath = "src/main/java/back/Chat_Messages.txt"; 
   public void openFile() throws IOException  
   {  try
      {  output = new Formatter(new BufferedWriter(new FileWriter(filePath, true)));
      }  
      catch( SecurityException securityException )
      {  System.err.println( "You do not have write access to this file." );
         System.exit( 1 );
      }  
      catch( FileNotFoundException filesNotFoundException )
      {  System.err.println( "Error creating file." );
         System.exit( 1 );
      } 
   } 

   public void addRecords(User userInput)   
   {  
      User record = new User();
      Scanner sc = new Scanner(System.in);
      int shift = 3;
      String name = encrypt(userInput.getName(), shift);
      String message = encrypt(userInput.getMessage(), shift);
      
       try // output values to file
         {  
            record.setName( name ); 
            record.setMessage( message ); 
            

            output.format("" + record.getName() + " \n" + record.getMessage() + " \n");
            
         }
            
         catch ( FormatterClosedException formatterClosedException )
         {  System.err.println( "Error writing to file." );
            return;
         } 
         catch ( NoSuchElementException elementException )
         {  System.err.println( "Invalid input. Please try again." );
            sc.nextLine(); 
         }  
         
   } 

   public void closeFile() 
   {  if ( output != null )
         output.close();
   }  
}  


