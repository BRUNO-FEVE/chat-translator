package back.entities;

import components.Chat;

public class User {
    private String Message;
    private String Name;
    private String Chat;

    
    public User() {
        this("", "");
    }
    
    public User(String chat) {
        setChat(chat);
    }

    public User(String name, String message) {
        setName(name);
        setMessage(message);  
  }  

   public void setMessage( String Msg )    
   {  Message = Msg;
   }  

   public String getMessage()  
   {  return Message; 
   }  

   public void setChat(String chat) 
   {   Chat = chat;
   }
   
   public String getChat() 
   {  return Chat;
   }

   public void setName( String name )  
   {  Name = name;
   }  

   public String getName()  
   {  return Name; 
   }  

    

   
}



