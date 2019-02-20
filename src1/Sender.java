import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
class Sender extends  JFrame 
{
  ServerSocket ss;
  Socket s;
  Sender()
   {
     super("Sender");
     setLayout(null);
     try
     {
       Object a=JOptionPane.showInputDialog(null,"Port Number","Port No",JOptionPane.QUESTION_MESSAGE,null,null,"6666");
       int pno=Integer.parseInt(a.toString());
       ss=new ServerSocket(pno);
       System.out.println("Server Connected\nWaiting for Client");
       SenderFrame send=new SenderFrame();
       int n=0;
       while(n<5)
        {
          s=ss.accept();
          send.newRecv(s);
        }
       }catch(Exception e)
{
  System.out.println("Error:"+e);
}
}
public static void main(String args[])
{
  new Sender();
}
}