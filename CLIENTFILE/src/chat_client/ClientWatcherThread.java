
 
/*
LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
*/
package chat_client;

import chat_client.FileEvent;
import chat_client.Prompt;
import java.io.FileInputStream;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

//https://www.youtube.com/watch?v=DvE_Jw8Upn0
//The concept and code is taken from this video
//As this video includes 
//2)Creating threads for clients
public class ClientWatcherThread extends Thread {
    String servertrack;
    JTextArea status;
   static int flag=0;
    public FileEvent fileEvent = null;
private  String destinationPath = "C:/Users/User/Desktop/server/";
private  String sourceFilePath="" ;
private  String location="";
private String warning="";
private String destinationPathclient1 = "C:/Users/User/Desktop/client1/";
private String sourceFilePathserver="" ;
public ObjectOutputStream outputStream1;
    // https://www.javacodegeeks.com/2015/09/introduction-to-threads-and-concurrency.html
   
//Getting the text and outputstream from the client form
    public ClientWatcherThread(JTextArea status,ObjectOutputStream outputstream) {
        this.status=status;
        this.outputStream1=outputstream;
        //To change body of generated methods, choose Tools | Templates.
    }
    
   
 
    @Override
    public void run() {
        
        
        
        
       try{
                    //CLIETNT WATCHER
//<------------------------------------------------------------------------------------------------------------------------------->

                //MONITORING FILE
//* The whole code is taken from the below link
 //* https://javapapers.com/core-java/monitor-a-folder-using-java/
       //It is running in background it reacts when file is added   
		Path faxFolder = Paths.get("C:/Users/User/Desktop/client1");
                
		WatchService watchService = FileSystems.getDefault().newWatchService();
		faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		boolean valid = true;
                
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();    
                                        
					System.out.println("File Created:" + fileName);
                                        status.append(fileName+"\n");
                                        
        
         JOptionPane.showMessageDialog(null,"FILE ADDED");
        
   //<-------------------------------------------------------------------------------------------->                                        
//https://www.javatpoint.com/java-joptionpane
          //  JOptionPane.showMessageDialog(null,fileName +" "+"File added in the client"); 
                                        
                                        
       // <------------------------------------------------------------------------------------------------------------------------->                                
                                        //Getting the path name of the file from the folder is taken from the below link
                                      //  http://www.codebind.com/java-tutorials/java-io-get-file-name-file-path/
                                       // File myFile = new File("C:" + "/" + "Users" + "/"+"User"+"/"+"Desktop"+"/"+"server"+"/"+fileName);
        //System.out.println(myFile.getName()+" I am in main thread");
        //System.out.println(myFile.getPath()+"I am in main thread");
       
        System.out.println("I am in mAIN and collected the path of the sourced directory ");
     // This variable is static as the variable is been running on he static function
        
        
        //From here the it goes to upload function
        
       sourceFilePath="C:" + "/" + "Users" + "/"+"User"+"/"+"Desktop"+"/"+"client1"+"/"+fileName;
     
        System.out.println(sourceFilePath+"source path updated in the main thread");
           
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
    //Here it checks for the file in the folder in order to avoid file copy exception
File servercheck = new File("C:/Users/User/Desktop/server/"+fileName);
boolean serverexists = servercheck.exists();

System.out.println("In client 1 thread "+serverexists);
        //This condtion checks if the same file is present in the destination folder or not
      //If there is not copy or redundancy then it goes to proceed for the upload functionality
            if(!serverexists)
            {
                               
              if(fileName!=null)
              {
             
      //<-------------------------------------------------------------------------------------------------------------->
        //FILE HANDLING
//  http://www.coderpanda.com/java-socket-programming-file-transfer-through-socket-in-java/
//The whole function is taken from the above code link 
 System.out.println("I am in upload function of upload"+sourceFilePath);
 
 //The source file path is found using the above file watcher which gives the sourcefile path of the 
 //client so that it can be sent to the server.
fileEvent = new FileEvent();
//File Event is a class which is a getter and setter class which helps in setting and getting 
//the attributes of the oject like source path and destinatoin path
fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
String path = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/") + 1);

//In this Object he is settin up both source and destination directories
//So that when we transfer this object to the server 
//It takes the destination directory and writes into it from the source directory
fileEvent.setDestinationDirectory(destinationPath);
fileEvent.setFilename(fileName);
fileEvent.setSourceDirectory(sourceFilePath);

try{
  //It sets the status to sucess if the attributes are written rightly
fileEvent.setStatus("Success");
}
catch (Exception e) {
e.printStackTrace();
//If the errror is found then we write onto the object as error;
//So that sever can read from the object if there is an erorr
fileEvent.setStatus("Error");
}
} else {
System.out.println("path specified is not pointing to a file");
fileEvent.setStatus("Error");
}
//Now writing the FileEvent object to socket
//Here we have to write the object to the output stream
              //So that it can be read by the server


 JOptionPane.showMessageDialog(null,"File Uploaded");
System.out.println("Done...Going to exit");

 

            }             
                System.out.println("I am near main upload");   
                
                 //The Below code is taken from the below link
//https://www.dev2qa.com/java-move-files-between-directories/   
                 try
		{
                    
                    
                    String srcFilePath="C:/Users/User/Desktop/client1/"+fileName;
                    
                    
                    
                    String destFilePath="C:/Users/User/Desktop/client2/"+fileName;
                              
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
    //Here it checks for the file in the folder in order to avoid file copy exception
                                                 
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
                    File tempFile = new File("C:/Users/User/Desktop/client2/"+fileName);
boolean exists = tempFile.exists();


                    String destFilePath1="C:/Users/User/Desktop/client3/"+fileName  ;
                              
/*   
* To check if file  exists in a folder is taken from the below link
* https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/

*/
    //Here it checks for the file in the folder in order to avoid file copy exception
                   File tempFile1 = new File("C:/Users/User/Desktop/client3/"+fileName);
                   
                 
                    String destFilePath2="C:/Users/User/Desktop/server/"+fileName ;
                    
 //Here it checks for the file in the folder in order to avoid file copy exception
                   File tempFile2 = new File("C:/Users/User/Desktop/server/"+fileName);
                   
boolean exists1 = tempFile1.exists();

                 
boolean exists2 = tempFile2.exists();

                    //Here we transfer the file 
			if(srcFilePath!=null && srcFilePath.trim().length()>0 && destFilePath!=null && destFilePath.trim().length()>0)
			{
                            Path srcPathObj = Paths.get(srcFilePath); 
				
				//It helps in creating an instance of the file present in the url
                            //Which in return helps in accessing it
				Path destPathObj = Paths.get(destFilePath);
                                
                                Path destPathObj1 = Paths.get(destFilePath1);
                                
                                
                                Path destPathObj2 = Paths.get(destFilePath2);
                                
                                
				
                            if(!exists)
                            {
				//if there is not redundancy in the destination folder then it transfers it
                                Path targetPathObj1 = Files.copy(srcPathObj, destPathObj);
                            }
                            
                            if(!exists1)
                            {
                                //if there is not redundancy in the destination folder then it transfers it
				Path targetPathObj = Files.copy(srcPathObj, destPathObj1);
                                
                            }
                            
                            if(!exists2)
                            {
                                //if there is not redundancy in the destination folder then it transfers it
				Path targetPathObj2 = Files.copy(srcPathObj, destPathObj2);
                                
                            }
                            
                            
                            
				System.out.println("Use java new io to move success from " + srcFilePath + " to " + destFilePath);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
//<------------------------------------------------------------------------------------------>
              }
     
                            
                  //If the file is not found 
                 
                  Prompt1 prompt = new Prompt1();
                  prompt.sleep(1000);
                  prompt.start();
                  flag=0;
			
                           
                             
                                        
                                        //Here the loop is constantly running until the client exists itself
                                //This helps in constantly keep the watch on the folder
				}
                        valid = watchKey.reset();
			}while(valid);
                
                
                  

		} catch (IOException ex) { 
            Logger.getLogger(ClientWatcherThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientWatcherThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
                System.out.println("I am near send watcher");
//<------------------------------------------------------------------------------------------>
            
       


       }//End of try
       
       
//<------------------------------------------------------------------------------------------>
        
        
  
  
                //To change body of generated methods, choose Tools | Templates.
           }//End of run method
    

     
    
    
