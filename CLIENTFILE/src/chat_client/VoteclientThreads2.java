/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
//This thread handles the creation of random yes and no for the clients to communicate with the coordiantor
//In order to do that a thread has been created
// https://www.javacodegeeks.com/2015/09/introduction-to-threads-and-concurrency.html
 //Creation of thread concept taken from above link
public class VoteclientThreads2 extends Thread {
    static int c=0;
    JTextArea status;
   String[] votes = {"Yes", "No", "Yes", "No"};
   public VoteclientThreads2(JTextArea status){
       this.status=status;
   }
    //Here we store all the responses
    public void run(){
                 if   (c==0)
                 {
                     
                      // Here we notify if the clients have two yes
                     if(votes[1]==votes[3]){
                         
        //The code for the prompt is taken from the below link
        // https://www.javatpoint.com/java-joptionpane
                         JOptionPane.showMessageDialog(null,"VOTE YES FROM CLIENT1");
                         JOptionPane.showMessageDialog(null,"TIME OUT AT CLIENT3");
                         //Here it appends the message of the client 1 and client 3
                         //code of taking status in the thread and updating the UI is taken from the below video
//https://www.youtube.com/watch?v=DvE_Jw8Upn0
            status.append("YES FROM CLIENT1\n TIME OUT AT CLIENT3 ");
                     }
                     
                     
                     
                     
                    
                     }
                 if(c==1){
                     
                 
                     //here we notify the client if it has a no and a yes
                 if(votes[0]==votes[2]){
                     
       
        //The code for the prompt is taken from the below link
        // https://www.javatpoint.com/java-joptionpane
                    JOptionPane.showMessageDialog(null,"VOTE YES FROM CLIENT1");
                         JOptionPane.showMessageDialog(null,"VOTE YES FROM CLIENT3");
                         //Here it appends the message of the client 1 and client 3
                         //code of taking status in the thread and updating the UI is taken from the below video
//https://www.youtube.com/watch?v=DvE_Jw8Upn0
           status.append("YES FROM CLIENT1\n YES FROM CLIENT3 ");
                 }
        
    }
                 if(c==2){
                     
       
        //The code for the prompt is taken from the below link
        // https://www.javatpoint.com/java-joptionpane
                     //here als we notify the user based on the yues and no voted received.
                     if(votes[0]!=votes[1]){
                     JOptionPane.showMessageDialog(null,"VOTE YES FROM CLIENT1");
                     JOptionPane.showMessageDialog(null,"VOTE NO FROM CLIENT3");
                     //Here it appends the message of the client 1 and client 3
                     //code of taking status in the thread and updating the UI is taken from the below video
//https://www.youtube.com/watch?v=DvE_Jw8Upn0
                      status.append("YES FROM CLIENT1\n NO FROM CLIENT3 ");
                 }
                     
                 
    
    }
                 if(c==3){
                      if(votes[0]!=votes[1]){
                     JOptionPane.showMessageDialog(null,"VOTE NO FROM CLIENT1");
                     JOptionPane.showMessageDialog(null,"VOTE NO FROM CLIENT3");
                     //Here it appends the message of the client 1 and client 3
                     //code of taking status in the thread and updating the UI is taken from the below video
//https://www.youtube.com/watch?v=DvE_Jw8Upn0
                      status.append("NO FROM CLIENT1\n NO FROM CLIENT3 ");
                 }
                 }
                 
                 c++;
}//run end
}// thread end
