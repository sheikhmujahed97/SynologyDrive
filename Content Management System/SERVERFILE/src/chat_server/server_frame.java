package chat_server;
/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
 */
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The whole code is taken from the below drive link which is from this you tube link
 * //https://www.youtube.com/watch?v=hZgntu7889Q
 * 
 * // The code is taken from the below drive link which is from the above you tube link
* // https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
 *
 * 
 */
public class server_frame extends javax.swing.JFrame 
{
    int i;
    static int flag=0;
   ArrayList clientOutputStreams;
   //The arrau list consists of the users which are used to validate
   ArrayList<String> users;
   //The file event object helps in retrieving the objects stored on the socket input stream
public FileEvent fileEvent=null;

//Client Handler is thread which is created to handle the client by the server 
//It helps in the file tranfer and also user validation 
   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock; 
       PrintWriter client;
ObjectInputStream inputStream;
public File dstFile = null;
public File dstFile1 = null;
public File dstFile2 = null;
/*
//The whole code is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
       public ClientHandler(Socket clientSocket, PrintWriter user, ObjectInputStream inputStream) 
       {
            client = user;
            try 
            {
                this.inputStream = inputStream;
                sock = clientSocket;
                //The input streamd reader creates an input streamd at the server
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                 
            //Here for every thread input stream is being created and sent to the reciever message 
            //to do the respective operation
           
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;
          
//Here we split the data of message as in client we are seding the message as " Usernmae : connected"
            //we split the data and retrieve the username to store in the arrray list and validate it
            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                   
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        ta_chat.append( "\n");
                    }
                    
                    //If the client is disconnected we get the message as "username disconnected" 
                    //By which we split the message and identify the username is disconnected or not
                     if (data[2].equals(disconnect)) 
                    {
                        JOptionPane.showMessageDialog(null,"DisConnected");
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                     else{
//Here we go thtough the whole array list to check if there is any redundancy of the username
                    
                   
                        
                    for(i=0;i<users.size();i++)
                    {
                        boolean x=data[0].equals(users.get(i));
                        
                        if(x)
                        {
                            flag=1;//The flagi used to verify the username if it is redundant
                            
                        }
                    }
                    
                    
                    
                    
                    if(flag==1)
                    {
                        
                        System.out.println("Invalid Username");
                        JOptionPane.showMessageDialog(null,"Invalid Username");
                        flag=0;
                        
                    }
                    //Here we add the data to the array lis named user
                    else{
                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                 
                        userAdd(data[0]);
                         JOptionPane.showMessageDialog(null,"Connected");
                         
                    } 
                    }
                    
                     }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
            
            
                      try {
                /**
* Reading the FileEvent object and copying the file to disk.
*/
                          
                      // http://www.coderpanda.com/java-socket-programming-file-transfer-through-socket-in-java/
try {
fileEvent = (FileEvent) inputStream.readObject();
if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
System.out.println("Error occurred ..So exiting");
System.exit(0);
}
System.out.println("At taking file names");
String outputFile = "C:/Users/User/Desktop/client2/" + fileEvent.getFilename();
String outputFile1 = "C:/Users/User/Desktop/server/" + fileEvent.getFilename();
String outputFile2 = "C:/Users/User/Desktop/client3/" + fileEvent.getFilename();
if (!new File(outputFile).exists()||!new File(outputFile1).exists()||!new File(outputFile2).exists()) {
new File(outputFile).mkdirs();
new File(outputFile1).mkdirs();
new File(outputFile2).mkdirs();
}

dstFile = new File(outputFile);
dstFile1 = new File(outputFile1);
dstFile2 = new File(outputFile);
FileOutputStream fileOutputStream, fileOutputStream1, fileOutputStream2;
fileOutputStream = new FileOutputStream(dstFile);
fileOutputStream1 = new FileOutputStream(dstFile1);
fileOutputStream2 = new FileOutputStream(dstFile2);

fileOutputStream.write(fileEvent.getFileData());
fileOutputStream1.write(fileEvent.getFileData());
fileOutputStream2.write(fileEvent.getFileData());

fileOutputStream.flush();
fileOutputStream.close();
System.out.println("Output file : " + outputFile + " is successfully saved ");
 JOptionPane.showMessageDialog(null,"FILE UPLOADED TO THE SERVER");  
   

} catch (IOException e) {
e.printStackTrace();
} catch (ClassNotFoundException e) {
e.printStackTrace();
} 

                      
               
//<----------------------------------------------------------------------------------------------------------->
            //FILE ACTION FROM HERE
               //In the send action button we write the username to dos(output stream
               //The dos and dis are sent to this class once the thread of the client is \
               //created the username is read her from dis
// The below code is taken from the below link
//http://www.coderpanda.com/java-socket-programming-file-transfer-through-socket-in-java/

              
   //In the client form we write the data inro the output stream and from input stream we read the data 
          
fileEvent = (FileEvent)inputStream.readObject();
             
       System.out.println("In the recieve message");
System.out.println(fileEvent);
if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
System.out.println("Error occurred ..So exiting");

}

//Now the fileEvent object has many attributes
//As throufh input stream we got tha object
//Now we can transfer the file
//Now from the object which is written on client side is read here on the server side
//For the smooth transfer
String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
String sourceFile = fileEvent.getSourceDirectory() ;

System.out.println("Source file is "+sourceFile+"Destination file is "+ outputFile);



//Moving files code from the below link
                //The Below code is taken from the below link
//https://www.dev2qa.com/java-move-files-between-directories/   
                
                 try
		{
                    String srcFilePath=sourceFile;
                    String destFilePath=outputFile;
                       
                    
			if(srcFilePath!=null && srcFilePath.trim().length()>0 && destFilePath!=null && destFilePath.trim().length()>0)
			{
				/* The instance of the object is created to again access. */
				Path srcPathObj = Paths.get(srcFilePath); 
				
				/* The instance of the object is created to again access. */
				Path destPathObj = Paths.get(destFilePath);
                                
                               
				
				/* The instance of the object is created to again access. */
				Path targetPathObj = Files.copy(srcPathObj, destPathObj);
                                
				
				System.out.println("File transfer succes from " + srcFilePath + " to " + destFilePath);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}


} catch (IOException e) {
e.printStackTrace();
}          catch (ClassNotFoundException ex) { 
               System.out.println("ERROR");
           } 
             
            
            
            
	} 
    }

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        lb_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_users.setText("Online Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });

        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        lb_name.setText("TechWorld3g");
        lb_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_users, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_name)
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_start)
                    .addComponent(b_users))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_clear)
                    .addComponent(b_end))
                .addGap(4, 4, 4)
                .addComponent(lb_name))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("");
        ta_chat.append("");
        
        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    
    //This is the function used to start the server
    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }//GEN-LAST:event_b_usersActionPerformed

    //This is to clear the ouptu of server
    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            
            //The server form is prompted by the main method
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    /*
//The whole function is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
    //Server Start is a thread which helps in creating a socket connectoin at the server
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                //The socket is created at port numnber 2222
                ServerSocket serverSock = new ServerSocket(2222);

                //In order to accept connections from the client 
                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);
                                
                                //Here we create the input stream to read the objects written on the client side
 ObjectInputStream inputStream = new ObjectInputStream(clientSock.getInputStream());
 //The thread client handler is started which is assig=ned to each client
				Thread listener = new Thread(new ClientHandler(clientSock, writer, inputStream));
				listener.start();
				ta_chat.append("");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("\n");
            }
        }
    }
    
   /*
//The whole function is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
    //This functin helps in adding the user to the array list to keep track of the user
    public void userAdd (String data) 
    {
        String message, add = "", done = "", name = data;
        
        //As the message retirved at server side is not just username it is"Usernmae connected" or "Username disconnnected"
        
        ta_chat.append(" " + "" + " ");
        users.add(name);
        ta_chat.append(" " + "" + " ");
        //So we split the data to retrieve the username to store it in the array list
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    /*
//The whole function is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
    //SO when the user is disconnected it is removed from the list 
    
    public void userRemove (String data) 
    {
        
        String message, add = "", done = "", name = data;
        users.remove(name);
        //The array list is stored into temp array and then the respective username is removed
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
//Here it taken from the temp and stored into he message
        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    
    /*
//The whole function is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
    //This method is used to notify in order to check the message is rightly recived
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();
//It itterates though all the outuput streams and printing it to verify it
        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.append("");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_name;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
