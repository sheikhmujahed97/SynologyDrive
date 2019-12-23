/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918
 */
package chat_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
**
 * The whole code is taken from the below drive link which is from this you tube link
 * // https://www.youtube.com/watch?v=hZgntu7889Q
 * 
 * // The code is taken from the below drive link which is from the above you tube link
* // https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
public class Client2 extends javax.swing.JFrame {

    /**
     * Creates new form Client3
     */
    
    static int tc=0;
    //An output stream is used to write the object to the socket.
    ObjectOutputStream outputStream;
    String username, address = "localhost";
    //The array list is used to store the users in order to verify the username duplicatoin
    ArrayList<String> users = new ArrayList();
    int port = 2222;
   //This boolean is used to check the uesr connection
    Boolean isConnected = false;
    //This socket object helps in creating the client socket object
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    //--------------------------//
    //This is the function used to connect to the server and sending the username 
    // with the help of buffer which is contined in the Incoming Reader background thread
    //This function is taken from the below youtube link itself
    // https://www.youtube.com/watch?v=hZgntu7889Q
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new Client2.IncomingReader());
         IncomingReader.start();
    }
      // https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
    // it is the watcher background thread used to detect any deletion done ot the file.
    public class Watcher implements Runnable
    {
    
ObjectOutputStream outputStream;
        public Watcher(ObjectOutputStream outputStream) {
            //To change body of generated methods, choose Tools | Templates.
            this.outputStream = outputStream;
        }

       
        @Override
        public void run() 
        { 
            try {
                // the watcher code is taken from the below link
                // https://javapapers.com/core-java/monitor-a-folder-using-java/
                //<------------------------------------------------------------------------------------------------------------------------------->
                
                //MONITORING FILE
//* The whole code is taken from the below link
                //* https://javapapers.com/core-java/monitor-a-folder-using-java/
                //It is running in background it reacts when file is added
                Path faxFolder = Paths.get("C:/Users/User/Desktop/client2");
                
                WatchService watchService = FileSystems.getDefault().newWatchService();
                faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_DELETE);
                
                
                boolean valid = true;
                
                do {
                    WatchKey watchKey = watchService.take();
                    
                    for (WatchEvent event : watchKey.pollEvents()) {
                        WatchEvent.Kind kind = event.kind();
                        if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
                 String fileName = event.context().toString();
                  status.setText("");
                   //The concept of setting status using threads is taken from below link 
// https://www.youtube.com/watch?v=DvE_Jw8Upn0
                         JOptionPane.showMessageDialog(null,"Client2 you are a coordinator now");
                         
                        //The below thread is used for the communication of random yes or no from VoteclientThreads2
                                                     
                         VoteclientThreads2 part = new VoteclientThreads2(status);
                         part.sleep(6000);
                         part.start();
                        //THe below thread is used to issue global commit and abort based on the 2 phase commit.
                         Voteclient2Thread thread2= new Voteclient2Thread(fileName,status);
                         thread2.sleep(6000);
                         thread2.start();
                         
                            
                        }
                    }
                    valid = watchKey.reset();
                    
                } while (valid);
                
                //end of try
            } catch (IOException ex) {
                Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            }
                              

            
            
        }
    }
    
    
    
   //--------------------------//
    /*
//The whole function is taken from the below drive link which is from this you tube link
  //https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
*/
    //This function helps in adding hte user to arraylist 
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    ///The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    // This helpas in making a duplicate of the users list
    //The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    // this is the function called when the disconnect button is clicked
   // Here we juz remove the user from the print writer object
    //The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    //--------------------------//
    //this function is also called when the diconnect button is clicked 
    //but here we close the connectoin.
    public void Disconnect() 
    {
        try 
        {
            
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
       
    }
    
    public Client2() {
        initComponents();
    }

    
    
    
    //The below functin is used to split the data send by the user as we are sending the "usernmae: connected"
    // we split the data and get the username to addd in the userlist.
    //--------------------------//
    //The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                
                //here the reader is of the buffer reader which is usd to write data from the client to its object
                // then it is read from the object
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
  //the data is checked if it is connected
                     
                     
                      if (data[2].equals(connect))
                     {
                         //if it is connected the data is addded to the user array list.
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     //if it is disconnected it is remove from the array list.
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_send = new javax.swing.JButton();
        tf_username = new javax.swing.JTextField();
        disconnect = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        status = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("Send");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        disconnect.setText("Disconnect");
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        status.setColumns(20);
        status.setRows(5);
        jScrollPane2.setViewportView(status);

        jLabel1.setText("CLIENT2");

        jLabel2.setText("User Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(disconnect)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(disconnect))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_send)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_usernameActionPerformed
//The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    //This is the function which is used to send 
    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        // TODO add your handling code here:
         
        //it check wether the user is connected or not if not it gets the username  from the text to initiate.
         if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(true);

            try 
            {
               //Here we create a socket at the client with respective address and port number.
                sock = new Socket(address, port);
                //The object outputstream is  created at the client socket to send it to the server.
                outputStream = new ObjectOutputStream(sock.getOutputStream());
                //Here we create the stream reader to read the data from the socket.
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
               //The input stream reader helps in creating buffer reader .
                reader = new BufferedReader(streamreader);
                
                //The print writer is also used to write the data at the client socket and read at the server.
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        }
        //It is the thread counter to avoid creation of threads if double clicked.
         
//This is created using the concept of creating the threads and sending the text field using the
        //below youtube link
        //// https://www.youtube.com/watch?v=DvE_Jw8Upn0 
         if(tc==0)
         {
              Thread Watcher = new Thread(new Client2.Watcher(outputStream));
         Watcher.start();
         System.out.println("Delete Thread stared");
        Client2WatcherThread watcher2=new Client2WatcherThread(status,outputStream);
/*      
       * https://stackoverflow.com/questions/2213340/what-is-a-daemon-thread-in-java
         * Checked for Daemon thread from the above code in the link
*/
        watcher2.setDaemon(true);
        watcher2.start();
        tc++;
         }
         
    }//GEN-LAST:event_b_sendActionPerformed
//The whole function is taken from the below drive link which is from this you tube link
  // https://www.youtube.com/watch?v=hZgntu7889Q
  
  // The code is taken from the below drive link which is from the above you tube link
// https://drive.google.com/drive/folders/0B4fPeBZJ1d19WkR3blE4ZVNTams
    //This is the function used to disconnect
    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        // TODO add your handling code here:
        // These are the two function called to disconnect the user
        //First is to remove the user and the other is close the socket
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_disconnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client2().setVisible(true);
            }
        });
        
        
        
        
         
        //The thread for pormpt is created 
        //Thread sleeps for 1000ms so that the client form
        //can be displayed and not be crashed
        //The concept of sleep and creating thread taken from the below link
        //https://dzone.com/articles/java-thread-tutorial-creating-threads-and-multithr
   Prompt promp= new Prompt();
   promp.sleep(1000);
   promp.start();
  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_send;
    private javax.swing.JButton disconnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea status;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
