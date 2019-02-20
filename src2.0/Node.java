import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.jws.WebParam.Mode;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import javax.swing.UIManager;


public class Node extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JList list;
	int toid;
	String primeval="";
    //DefaultListModel model;
	PrintStream pr;
	BufferedReader br;
	Random random=new Random();
	int i=0;Socket sk;
	int signal,channel;
	private JTextField textField_2;
	private JTextField textField_3;
   String message;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_7;
   final JTextArea textArea;
	ArrayList<BigInteger> prime=new ArrayList<BigInteger>(); 
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Node frame = new Node();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param s 
	 */
	public Node(Socket sk) {
		//setTitle("Node");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.sk=sk;
		JLabel lblId = new JLabel("ID:");
		lblId.setBackground(Color.BLACK);
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblId.setBounds(21, 31, 31, 22);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(51, 34, 113, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSignal = new JLabel("Signal Strength:");
		lblSignal.setBackground(Color.BLACK);
		lblSignal.setForeground(new Color(0, 0, 0));
		lblSignal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSignal.setBounds(21, 64, 124, 22);
		contentPane.add(lblSignal);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 65, 56, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTheMessageIs = new JLabel("Enter The Transferring Message:");
		lblTheMessageIs.setBackground(Color.BLACK);
		lblTheMessageIs.setForeground(new Color(0, 0, 0));
		lblTheMessageIs.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTheMessageIs.setBounds(21, 125, 248, 22);
		contentPane.add(lblTheMessageIs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 158, 392, 83);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(0, 0, 0));
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExit.setBounds(324, 466, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblToId = new JLabel("To ID:");
		lblToId.setBackground(Color.BLACK);
		lblToId.setForeground(new Color(0, 0, 0));
		lblToId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblToId.setBounds(199, 64, 56, 22);
		contentPane.add(lblToId);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField_5.getText().isEmpty())
				{
				 JOptionPane.showMessageDialog(null,"The Cipher Format Data Should not Empty!","Information",JOptionPane.INFORMATION_MESSAGE);	
				}
				else{
				message=textField_5.getText();
				System.out.println("message:"+message);
				
				pr.println("msg:"+toid+"&"+message+"&"+prime);
				}
			}
		});
		btnSend.setForeground(UIManager.getColor("Button.disabledForeground"));
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSend.setBounds(21, 322, 392, 23);
		contentPane.add(btnSend);
		
		JLabel lblTheShortestPath = new JLabel("The Shortest Path Is:");
		lblTheShortestPath.setBackground(Color.BLACK);
		lblTheShortestPath.setForeground(new Color(0, 0, 0));
		lblTheShortestPath.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTheShortestPath.setBounds(20, 94, 159, 20);
		contentPane.add(lblTheShortestPath);
		
		textField_2 = new JTextField();
		textField_2.setBounds(177, 96, 141, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSend_1 = new JButton("Send");
		btnSend_1.setBackground(UIManager.getColor("Button.background"));
		btnSend_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int fromid=Integer.parseInt(textField.getText());
				//kit.f=fromid;
				System.out.println("from id:"+fromid);
				if(textField_3.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Destination Id should not empty", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else{
				
				toid=Integer.parseInt(textField_3.getText());
				System.out.println("to id:"+toid);
				//pr.println("ID:"+fromid+","+toid);
				try
				  {
				  Conn con=new Conn();			/** establis connection to databse*/
				  java.sql.Statement st=con.getConn();
				  st.executeUpdate("delete from ids ");
			      st.executeUpdate("insert into ids values("+fromid+","+toid+")");
			       }
				   catch(Exception e)
				   {System.out.println("error"+e.getMessage());}
				
				}
			}
		});
		btnSend_1.setForeground(Color.BLACK);
		btnSend_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSend_1.setBounds(324, 64, 89, 23);
		contentPane.add(btnSend_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(251, 67, 69, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnReceive = new JButton("Receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String val = null;
			        Conn con=new Conn();			/** establis connection to databse*/
					java.sql.Statement st=con.getConn();
					ResultSet rs=st.executeQuery("select * from shortestpath");
				 while(rs.next())
				  {
				    val=rs.getString(1);
				    
				   }
				 textField_2.setText(val);
					
					
				}
				   catch(Exception ex) 
				   {
			        System.out.println("sfdfd"+ex.getMessage());
				 
			       }
			
			}
		});
		btnReceive.setForeground(Color.BLACK);
		btnReceive.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnReceive.setBounds(324, 95, 89, 23);
		contentPane.add(btnReceive);
		
		JLabel lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBackground(Color.GRAY);
		lblIpAddress.setForeground(new Color(0, 0, 0));
		lblIpAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblIpAddress.setBounds(174, 31, 89, 20);
		contentPane.add(lblIpAddress);
		
		textField_4 = new JTextField();
		textField_4.setBounds(273, 34, 140, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTheCipherData = new JLabel("The Encrypted Data Is:");
		lblTheCipherData.setForeground(new Color(0, 0, 0));
		lblTheCipherData.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTheCipherData.setBounds(21, 258, 159, 22);
		contentPane.add(lblTheCipherData);
		
		JLabel lblTheCipherData_1 = new JLabel("The Cipher Data Is:");
		lblTheCipherData_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTheCipherData_1.setBounds(21, 356, 124, 22);
		contentPane.add(lblTheCipherData_1);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(textArea.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Plase Enter The Transferring Message!","INFORMATION",JOptionPane.INFORMATION_MESSAGE); 
				}
				else{
				String msg=textArea.getText();
				Random random=new Random();
				ArrayList<BigInteger> xorval=new ArrayList<BigInteger>();
				
			  for(int i=0;i<msg.length();i++)
				{
					
					BigInteger prime1=BigInteger.probablePrime(8,random);
					System.out.println("prime:"+prime1);
					prime.add(prime1);
					
				}
				for(int j=0;j<msg.length();j++)
				{
					char ch=msg.charAt(j);
					int val=(int)ch;
					BigInteger bigIntValue = new BigInteger(Integer.toString(val));
					BigInteger primeval=prime.get(j);
					BigInteger xor=bigIntValue.xor(primeval);
					xorval.add(xor);
					//System.out.println(xor);
					
				}
				String levelone="";
				for(int k=0;k<xorval.size();k++)
				{
					BigInteger bi=xorval.get(k);
					int ascii=bi.intValue();
					String binary1=Integer.toBinaryString(ascii);
					System.out.println("binary:"+binary1);
					for(int l=binary1.length();l<8;l++)
					{
						binary1='0'+binary1;
						
					}
				    StringBuilder st=new StringBuilder(binary1);
				    String binary=st.reverse().toString();
					for (int l = 0; l <binary.length()/2; l++)
					{

				        String a = binary.substring(2*l,(l+1)*2);
				        if(a.equalsIgnoreCase("01"))
				        {
				        	levelone+='a';
				        }
				        else if(a.equalsIgnoreCase("00"))
				        {
				        	levelone+='b';
				        }
				        else if(a.equalsIgnoreCase("10"))
				        {
				        	levelone+='c';
				        }
				        else 
				        {
				        	levelone+='d';
				        }
				        
				        System.out.println("levelone:"+levelone);
				    }
					String cipher="";
					for (int l = 0; l <levelone.length()/2; l++)
					{

				        String a = levelone.substring(2*l,(l+1)*2);
				        if(a.equalsIgnoreCase("aa"))
				        {
				        	cipher+='e';
				        }
				        else if(a.equalsIgnoreCase("ab"))
				        {
				        	cipher+='f';
				        }
				        else if(a.equalsIgnoreCase("ac"))
				        {
				        	cipher+='g';
				        }
				        else if(a.equalsIgnoreCase("ad"))
				        {
				        	cipher+='h';
				        }
				        else if(a.equalsIgnoreCase("bb"))
				        {
				        	cipher+='i';
				        }
				        else if(a.equalsIgnoreCase("bc"))
				        {
				        	cipher+='j';
				        }
				        else if(a.equalsIgnoreCase("bd"))
				        {
				        	cipher+='k';
				        }
				        else if(a.equalsIgnoreCase("cc"))
				        {
				        	cipher+='l';
				        }
				        else if(a.equalsIgnoreCase("cd"))
				        {
				        	cipher+='m';
				        }
				        else if(a.equalsIgnoreCase("dd"))
				        {
				        	cipher+='n';
				        }
				        else if(a.equalsIgnoreCase("ba"))
				        {
				        	cipher+='o';
				        }
				        else if(a.equalsIgnoreCase("ca"))
				        {
				        	cipher+='p';
				        }
				        else if(a.equalsIgnoreCase("da"))
				        {
				        	cipher+='q';
				        }
				        else if(a.equalsIgnoreCase("cb"))
				        {
				        	cipher+='r';
				        }
				        else if(a.equalsIgnoreCase("db"))
				        {
				        	cipher+='s';
				        }
				       
				       
				        else 
				        {
				        	cipher+='t';
				        }
				        
				        
				        
				        
				    }
					System.out.println("level two:"+cipher);
					
					
					textField_5.setText(cipher);
					
				}
				}
				
			}
		});
		btnEncrypt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnEncrypt.setBounds(321, 251, 89, 23);
		contentPane.add(btnEncrypt);
		
		textField_5 = new JTextField();
		textField_5.setBounds(21, 291, 389, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(144, 356, 269, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Decrypt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(textField_6.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"The Incoming message not Received","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else{
				String cipher1=textField_6.getText();
				String cipher="",binaryval="";
				for (int l = 0; l <cipher1.length(); l++)
				{

			        char a = cipher1.charAt(l);
			        if(a=='e')
			        {
			        	cipher+="aa";
			        }
			        else if(a=='f')
			        {
			        	cipher+="ab";
			        }
			        else if(a=='g')
			        {
			        	cipher+="ac";
			        }
			        else if(a=='h')
			        {
			        	cipher+="ad";
			        }
			        else if(a=='i')
			        {
			        	cipher+="bb";
			        }
			        else if(a=='j')
			        {
			        	cipher+="bc";
			        }
			        else if(a=='k')
			        {
			        	cipher+="bd";
			        }
			        else if(a=='l')
			        {
			        	cipher+="cc";
			        }
			        else if(a=='m')
			        {
			        	cipher+="cd";
			        }
			        else if(a=='n')
			        {
			        	cipher+="dd";
			        }
			        else if(a=='o')
			        {
			        	cipher+="ba";
			        }
			        else if(a=='p')
			        {
			        	cipher+="ca";
			        }
			        else if(a=='q')
			        {
			        	cipher+="da";
			        }
			        else if(a=='r')
			        {
			        	cipher+="cb";
			        }
			        else if(a=='s')
			        {
			        	cipher+="db";
			        }
			       
			       
			        else 
			        {
			        	cipher+="dc";
			        }
			        
			        
			        
			        System.out.println("level two:"+cipher);
			    }
				for (int l = 0; l <cipher.length(); l++)
				{

			        char a = cipher.charAt(l);
			        if(a=='a')
			        {
			        	binaryval+="01";
			        }
			        else if(a=='b')
			        {
			        	binaryval+="00";
			        }
			        else if(a=='c')
			        {
			        	binaryval+="10";
			        }
			        else 
			        {
			        	binaryval+="11";
			        }
				}
				ArrayList<BigInteger> ascii=new ArrayList<BigInteger>();
				for (int l = 0; l <binaryval.length()/8; l++)
				{

					StringBuilder st=new StringBuilder(binaryval.substring(8*l,(l+1)*8));
					String binary=st.reverse().toString();
			        int a = Integer.parseInt(binary,2);
			        BigInteger bigInt = new BigInteger(String.valueOf(a));
			        ascii.add(bigInt);
			        }
				
				String primenum = primeval.replaceAll("\\[", "").replaceAll("\\]","");
			       ArrayList<BigInteger> primevals=new ArrayList();
			       for(String st:primenum.split(","))
			       {
			    	   primevals.add(new BigInteger(st.trim()));
			    	  // pr[Integer.parseInt(st.trim())].println("msg:"+t2[1]);
			    	   System.out.println("id of node:"+st);
			    	   
			       }
			       String plaintext="";
			       
				for(int m=0;m<primevals.size();m++)
				{
					BigInteger asccii1=ascii.get(m);
					BigInteger prime1=primevals.get(m);
					BigInteger xorval=prime1.xor(asccii1);
					char ch=(char)xorval.intValue();
					plaintext+=ch;
				}
				
				textField_7.setText(plaintext);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton.setBounds(21, 393, 392, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblThePlainMessage = new JLabel("The Plain Message Is:");
		lblThePlainMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblThePlainMessage.setBounds(21, 432, 143, 22);
		contentPane.add(lblThePlainMessage);
		
		textField_7 = new JTextField();
		textField_7.setBounds(157, 435, 256, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		setVisible(true);
		connect();
		getuKey();
		//getchannel();
		getmessage();
		//getMsg();
	}
	
	 public void connect()		
     {
       try{
              br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
              pr = new PrintStream(sk.getOutputStream());
           }
            catch(Exception e){System.out.println("Error at connect method"+e.getMessage());}
      }
	 public void getuKey()
     {
      try{
           String t=br.readLine();   //get user id
           String[] temp=t.split(",");
           String noR=temp[0];
           String sig=temp[1];
           String cha=temp[2];
           String address=temp[3];
           System.out.println(noR);
           textField.setText(noR);
           textField_1 .setText(sig);
           textField_4.setText(address);
           setTitle("Node "+noR);
           //String[] ids=new String[4];
           //Object[] data =new Object[4];
          // LinkedList<Integer> ids=new LinkedList<Integer>();
           /*int count=0;
           for(int i=0;i<5;i++)
           {
        	   if(i!=Integer.parseInt(noR))
        	   {
        		data[count]=i;
        		count++;
        	   }
        	   
           }
           System.out.println("data:"+data);
           
           
           list = new JList(data);*/
          }
          catch(Exception e)
                     {System.out.println("hi"+e.getMessage());}
          }	 
	 
	 public void getmessage()
     {
       try{
while(true)
{
String temp=br.readLine();
String[] temp1=temp.split(":");
if(temp1[0].equals("path"))
{textField_2.setText(temp1[1]);}

else if(temp1[0].equals("msg"))
{
	String[] msg=temp1[1].split("&");
	
    textArea.append("Message coming from :"+msg[0]+"\n");
    //textArea.append("to id:"+msg[1]+"\n");
    if(Integer.parseInt(msg[1])==Integer.parseInt(textField.getText()))
    		{
    	 textField_6.setText(msg[2]); 	
    	 primeval=msg[3];
    		}
   
    //textArea.append("prime:"++"\n");
    
}
}
}
             catch(Exception e){System.out.println("Error3"+e.getMessage());}
   }
}
