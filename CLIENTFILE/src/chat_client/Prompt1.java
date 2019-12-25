/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
 */
package chat_client;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */

//The thread for pormpt is created 
        
        //can be displayed and not be crashed
       
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
public class Prompt1 extends Thread{
    
    static int c=0;
     
    public void run()        
    {
       
        //The code for the prompt is taken from the below link
        // https://www.javatpoint.com/java-joptionpane
         //This pane is for the client 1
           JOptionPane.showMessageDialog(null,"FILE UPLOADED TO SERVER");  
           
           
           
        }     
        
         
    }


