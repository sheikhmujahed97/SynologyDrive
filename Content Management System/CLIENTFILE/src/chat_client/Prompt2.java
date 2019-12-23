/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
 */
package chat_client;

import static chat_client.Prompt1.c;
import javax.swing.JOptionPane;

//The thread for pormpt is created 
        
        //can be displayed and not be crashed
       
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
public class Prompt2 extends Thread{
    
    static int c=0;
     //The thread for pormpt is created 
        
        //can be displayed and not be crashed
       
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
    public void run()        
    {
        c++;
        //The code for the prompt is taken from the below link
        //https://www.javatpoint.com/java-joptionpane
         
        //this option pane is for the client 2 
           JOptionPane.showMessageDialog(null,"FILE UPLOADED TO SERVER");
           
           
           
           
        }     
        
         
    }

