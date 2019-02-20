import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
class Receiver extends JFrame
{
Socket s;
Receiver()
{
try{ 
Object a1=JOptionPane.showInputDialog(null, "Server IP", "Server Details",JOptionPane.QUESTION_MESSAGE, null, null, "localhost");
String ip=a1.toString();
Object a2=JOptionPane.showInputDialog(null, "Port Number", "Server Details",JOptionPane.QUESTION_MESSAGE, null, null, "6666");
int pno=Integer.parseInt(a2.toString());
s = new Socket(ip,pno);
System.out.println("Client connected");
new Node(s);
}catch(Exception e){
System.out.println("Error:"+e);
}
}
public static void main(String args[])
{
new Receiver();
}
}