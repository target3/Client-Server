package ergasia1client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Ergasia1Client {

    public static void main(String[] args) throws IOException {
     
       
        
       /* JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());*/
String filename;//Here i ask the client to write the name of the file that he wish to send
System.out.println("Enter File Name(with the extension) : ");
Scanner sc=new Scanner(System.in);
filename=sc.nextLine();
sc.close();

Socket s=new Socket("127.0.0.1",8080);  //Here is the address where the file will be send (localhost) and the port
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
try{
String str=""/*, filename=""*/;  
str=din.readUTF();
System.out.println("File accepted");//Here will show that the file has been accepted by server
if(!str.equals("stop")){  
System.out.println("Sending File: "+filename);//Here shows the file name 
dout.writeUTF(filename);  
dout.flush();  
File f=new File(filename);
FileInputStream fin=new FileInputStream(f);//Create of new stream
long sz=(int) f.length();//Here i take the size of the file
byte b[]=new byte [1024]; 
int read;
dout.writeUTF(Long.toString(sz));
dout.flush(); 
System.out.println ("Size: "+(sz/(1024*1024))+"MB");
while((read = fin.read(b)) != -1){
    dout.write(b, 0, read); 
    dout.flush(); 
}
fin.close();
dout.flush(); 
}  
System.out.println("Send Complete");
dout.flush();  
}
catch(Exception e)
{
	e.printStackTrace();
	System.out.println("An error occured");
}
din.close();  
s.close();  

}     
}
    //}
    




