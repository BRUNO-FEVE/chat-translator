package back.entities;


public class ChatUser {
    private String Message;
    private String Name;
    private String Chat;

    
    public ChatUser() {
        this("", "");
    }
    
    public ChatUser(String chat) {
        setChat(chat);
    }

    public ChatUser(String name, String message) {
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



