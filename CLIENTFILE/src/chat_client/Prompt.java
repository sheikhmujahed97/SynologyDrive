/*
 LAST NAME -SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO: 1001764918
*/
package chat_client;

import javax.swing.JOptionPane;

/**
 *
 * This thread is used to prompt the user to enter the username
 * 
        //The thread for pormpt is created 
        
        //can be displayed and not be crashed
       
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
 */
public class Prompt extends Thread{
    static int c=0;
     //The thread for pormpt is created 
        
        //can be displayed and not be crashed
       
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
    public void run()        
    {
        c++;
        //The code for the prompt is taken from the below link
        //https://www.javatpoint.com/java-joptionpane
         
        
        
         JOptionPane.showMessageDialog(null,"Enter User Name");
         
    }
}
