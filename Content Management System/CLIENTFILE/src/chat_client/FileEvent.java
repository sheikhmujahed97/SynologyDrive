/*
 LAST NAME-SHEIKH
FIRST NAME-MUJAHED KHALED
ID NO - 1001764918

The whole code is taken from the below link
This is a getter setter class which is used to set the attibutes for the object of file
 * in order to get the destinatoin , surce directories
http://www.coderpanda.com/java-socket-programming-file-transfer-through-socket-in-java/
 
*/
package chat_client;

/**
 *
 * This is a getter setter class which is used to set the attibutes for the object of file
 * in order to get the destinatoin , surce directories
 * The code below is taken from the below link
 * http://www.coderpanda.com/java-socket-programming-file-transfer-through-socket-in-java/
 */
import java.io.Serializable;

public class FileEvent implements Serializable {

public FileEvent() {
}

private static final long serialVersionUID = 1L;

private String destinationDirectory;
private String sourceDirectory;
private String filename;
private long fileSize;
private byte[] fileData;
private String status;
//To get the destination directory from the object crated and written on output stream
public String getDestinationDirectory() {
return destinationDirectory;
}
//To set the destination directory from the object crated and written on output stream

public void setDestinationDirectory(String destinationDirectory) {
this.destinationDirectory = destinationDirectory;
}
//To get the source directory from the object crated and written on output stream

public String getSourceDirectory() {
return sourceDirectory;
}

//To get the set source directory from the object created and written on output stream 

public void setSourceDirectory(String sourceDirectory) {
this.sourceDirectory = sourceDirectory;
}
//To get the file name from the object crated and written on output stream
public String getFilename() {
return filename;
}
//To set the file name from the object crated and written on output stream

public void setFilename(String filename) {
this.filename = filename;
}
//To get the file size from the object crated and written on output stream

public long getFileSize() {
return fileSize;
}
//To set the file size from the object crated and written on output stream

public void setFileSize(long fileSize) {
this.fileSize = fileSize;
}
//To get the status from the object crated and written on output stream

public String getStatus() {
return status;
}
//To get the set status on the object crated and written on output stream

public void setStatus(String status) {
this.status = status;
}
//To get the file data from the object crated and written on output stream

public byte[] getFileData() {
return fileData;
}
//To set the file data on the object crated and written on output stream

public void setFileData(byte[] fileData) {
this.fileData = fileData;
}
}