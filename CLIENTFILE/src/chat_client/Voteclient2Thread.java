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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
// this is the thread which helps in doing the global commit and global abort by the conditions of 
//2 phase commit

// https://www.javacodegeeks.com/2015/09/introduction-to-threads-and-concurrency.html
 //Creation of thread concept taken from above link
public class Voteclient2Thread extends Thread{
    //The thread for pormpt is created 
        static int c=0;
        String filename;
        JTextArea status;
        //in order to wait for 3 sec and then do the voting.
       public  Voteclient2Thread(String filename,JTextArea status){
           this.filename=filename;
           this.status=status;
       }
        // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
    public void run(){
        
        //The code for the prompt is taken from the below link
        // https://www.javatpoint.com/java-joptionpane
         //This pane is for the client 1
        if(c==0){
            
            //As we know that the client has to be in ready state
         JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
          JOptionPane.showMessageDialog(null,"GLOBAL ABORT"); 
          JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
          
         
          JOptionPane.showMessageDialog(null,"CLEINT 1 AND CLIENT 3 ARE IN READY STATE \n");
            
                              
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
                //The Below code is taken from the below link
//https://www.dev2qa.com/java-move-files-between-directories/
         // https://howtodoinjava.com/java/io/4-ways-to-copy-files-in-java/     
                    String srcFilePath="C:/Users/User/Desktop/server/"+filename;
                     Path srcPathObj = Paths.get(srcFilePath);
         
                 
                    String destFilePath2="C:/Users/User/Desktop/client2/"+filename ;
                    System.out.println(filename);
                    Path destPathObj = Paths.get(destFilePath2);
                         
            try {
                Path targetPathObj1 = Files.copy(srcPathObj, destPathObj);
                
            } catch (IOException ex) {
                Logger.getLogger(Voteclient1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
           
          
        }
          if(c==1){
              
              
          //As we know that the client has to be in ready state
         JOptionPane.showMessageDialog(null,"GLOBAL COMMIT");
         JOptionPane.showMessageDialog(null,"GLOBAL COMMIT");
         JOptionPane.showMessageDialog(null,"GLOBAL COMMIT");
         // https://www.geeksforgeeks.org/delete-file-using-java/
            //Thecode is takend from the above link
        // https://www.geeksforgeeks.org/delete-file-using-java/
          File file = new File("C:/Users/User/Desktop/client3/"+filename); 
           File file1 = new File("C:/Users/User/Desktop/client2/"+filename); 
            File file2 = new File("C:/Users/User/Desktop/client1/"+filename); 
          //It is used to delete the file as we have recieved the global commit
        if(file.delete()) 
        { 
            JOptionPane.showMessageDialog(null,"DELETED\n");
        } 
        if(file1.delete()){
           JOptionPane.showMessageDialog(null,"DELETED\n");
        }
        if(file2.delete()){
           JOptionPane.showMessageDialog(null,"DELETED\n");
        }
        
           JOptionPane.showMessageDialog(null,"CLEINT 1 AND CLIENT 3 ARE IN READY STATE \n");
              
              
              
              
}
        
         if(c==2){
              
          //As we know that the client has to be in ready state
           JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
           JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
           JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
           
           
           JOptionPane.showMessageDialog(null,"CLEINT 1 AND CLIENT 3 ARE IN READY STATE \n");
                                     
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*///The Below code is taken from the below link
//https://www.dev2qa.com/java-move-files-between-directories/
         // https://howtodoinjava.com/java/io/4-ways-to-copy-files-in-java/
                    
                    String srcFilePath="C:/Users/User/Desktop/server/"+filename;
                     Path srcPathObj = Paths.get(srcFilePath);
         
                 
                    String destFilePath2="C:/Users/User/Desktop/client2/"+filename ;
                    System.out.println(filename);
                    Path destPathObj = Paths.get(destFilePath2);
                         
            try {
                Path targetPathObj1 = Files.copy(srcPathObj, destPathObj);
                
            } catch (IOException ex) {
                Logger.getLogger(Voteclient1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }           
           
        }
         if(c==3){
               //As we know that the client has to be in ready state
             JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
              JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
              JOptionPane.showMessageDialog(null,"GLOBAL ABORT");
              
            
             JOptionPane.showMessageDialog(null,"CLEINT 1 AND CLIENT 3 ARE IN READY STATE \n");
                                       
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
             
             //The Below code is taken from the below link
//https://www.dev2qa.com/java-move-files-between-directories/
         // https://howtodoinjava.com/java/io/4-ways-to-copy-files-in-java/
                    
                    String srcFilePath="C:/Users/User/Desktop/server/"+filename;
                     Path srcPathObj = Paths.get(srcFilePath);
         
                 
                    String destFilePath2="C:/Users/User/Desktop/client2/"+filename ;
                    System.out.println(filename);
                    Path destPathObj = Paths.get(destFilePath2);
                         
            try {
                Path targetPathObj1 = Files.copy(srcPathObj, destPathObj);
                
            } catch (IOException ex) {
                Logger.getLogger(Voteclient1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
          
         }
        c++;
        
    }
}

