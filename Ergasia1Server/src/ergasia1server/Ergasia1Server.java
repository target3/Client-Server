
package ergasia1server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ergasia1Server {

  
    public static void main(String[] args) throws IOException {
       
while(true)
{
	
ServerSocket ss=new ServerSocket(8080); //Create the connection
System.out.println ("Waiting for request");
Socket s=ss.accept();  //Acceptance of the connection
System.out.println ("Connected With "+s.getInetAddress().toString());//Show with who is connected
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

System.out.println("Write download to receive the file");
String str="",filename="";  
try{
while(!str.equals("download")){//With the write of word "download" it starts the movement of the file from the client to server
	str=br.readLine(); 
 
	dout.writeUTF(str); 
	dout.flush();  
	
	filename=din.readUTF(); 
	System.out.println("Receving file: "+filename);
	filename= filename;
	System.out.println("Saving file: "+filename);
}
long sz=Long.parseLong(din.readUTF());
System.out.println ("File Size: "+(sz/(1024*1024))+" MB");

byte b[]=new byte [1024];
System.out.println("Receving file");
FileOutputStream fos=new FileOutputStream(new File(filename),true);
long bytesRead;
do
{
bytesRead = din.read(b, 0, b.length);
fos.write(b,0,b.length);
}while(!(bytesRead<1024));
System.out.println("Comleted");
fos.close(); 
dout.close();  	
s.close();
}
catch(EOFException e)
{
	//do nothing
}
 
 
    }
    
}
}
