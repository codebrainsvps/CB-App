import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.net.Socket;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PathIdentification extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	BufferedReader br[]=new BufferedReader[5];
	PrintStream pr[]=new PrintStream[5];
	Socket[] sk;
	int[][] cost;
	String spath;
	int from,toid;
	int temp2;
	//BufferedReader br;
	//PrintStream pr;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PathIdentification frame = new PathIdentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param sk 
	 * @param cost 
	 */
	public PathIdentification(Socket[] sk, final int[][] cost) {
		setTitle("Shrotest path Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.sk=sk;
		this.cost=cost;
		JLabel lblShortestPathIs = new JLabel("Shortest Path Is:");
		lblShortestPathIs.setForeground(new Color(0, 0, 128));
		lblShortestPathIs.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblShortestPathIs.setBounds(21, 62, 128, 22);
		contentPane.add(lblShortestPathIs);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 95, 389, 272);
		contentPane.add(scrollPane_1);
		
		final JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				  {
				  Conn con=new Conn();			/** establis connection to databse*/
				  java.sql.Statement st=con.getConn();
				  st.executeUpdate("delete from shortestpath ");
			      st.executeUpdate("insert into shortestpath values('"+spath+"')");
			       }
				   catch(Exception e)
				   {System.out.println("error"+e.getMessage());}
				
				//SenderFrame.pr[from].println("path:"+spath);
				//SenderFrame.pr[toid].println("path:"+spath);
				//getMsgs();
			}
		});
		btnSend.setForeground(new Color(138, 43, 226));
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSend.setBounds(227, 378, 81, 23);
		contentPane.add(btnSend);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(138, 43, 226));
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(321, 378, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnFindShortestPath = new JButton("Find shortest path");
		btnFindShortestPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String path1="";
				from=Integer.parseInt(textField.getText());
				toid=Integer.parseInt(textField_1.getText());
				//String  path1=new LinkedList<Integer>();
				int[] costvalues=new int[5];
				for(int i=0;i<5;i++)
				{
			    	LinkedList<Integer>  path=new LinkedList<Integer>();
			    	path.addFirst(from);
					path.addLast(toid);
				int count=1;
			 	while(true)
				{
					Random random=new Random();
					int id=random.nextInt(5);
					if(!path.contains(id))
					{
						path.add(count,id);
						count++;
					}
					if(path.size()==5)
					{
						//path.addLast(toid);
						break;
					}
	                
				}
				textArea.append(""+path+"\n");
				path1+=path+"&";
				int l=0;
				int cost1=0;
				for(int k=1;k<path.size();k++)
				{
					
						int kval=path.get(l);
						int lval=path.get(k);
						System.out.println("k value:"+kval+"\nl value:"+lval);
						int costval=cost[kval][lval];
						System.out.println("cost value:"+costval);
						textArea.append(kval+"------>"+lval+"=="+costval+"\n");
						l+=1;
						cost1=cost1+costval;
					    
				}
				costvalues[i]=cost1;
				System.out.println("cost values:"+costvalues[i]);
				textArea.append("\n********************************\n");
				/*if(path1.isEmpty())
				{	
				path1.addAll(path);
				}
				else if(!path1.containsAll(path))
				{path1.addAll(path);}
					
				if(path1.size()==25)
				{break;}*/
				}
				
				int count=0;int max=0;
				int index=0;
				max=costvalues[0];
				//System.out.println("max:"+max);
				for(int m=1;m<5;m++)
				{
					int min=costvalues[m];
					System.out.println("min:"+min);
					if(max<min)
					{
						max=min;
						index=m;
					}
					
				}
				System.out.println("index:"+index);
				System.out.println("max:"+max);
			
				StringTokenizer str=new StringTokenizer(path1,"&");
				while(str.hasMoreElements())
				{
					String val=str.nextToken();
					System.out.println("val:"+val);
					if(count==index)
					{
					textArea.append("\nThe shorest paht is:"+val+"\n");
					spath=val;
					}
					count++;
				}
				
				
			}
		});
		btnFindShortestPath.setForeground(new Color(138, 43, 226));
		btnFindShortestPath.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnFindShortestPath.setBounds(252, 63, 155, 23);
		contentPane.add(btnFindShortestPath);
		
		JLabel lblFromId = new JLabel("From ID:");
		lblFromId.setForeground(new Color(0, 0, 128));
		lblFromId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFromId.setBounds(22, 32, 74, 14);
		contentPane.add(lblFromId);
		
		textField = new JTextField();
		textField.setBounds(94, 31, 66, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblToId = new JLabel("To ID:");
		lblToId.setForeground(new Color(0, 0, 128));
		lblToId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblToId.setBounds(170, 31, 61, 17);
		contentPane.add(lblToId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 31, 66, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnReceive = new JButton("Receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String val = null;
				String val1=null;
				 try{
				        Conn con=new Conn();			/** establis connection to databse*/
						java.sql.Statement st=con.getConn();
						ResultSet rs=st.executeQuery("select * from ids");
					 while(rs.next())
					  {
					    val=rs.getString(1);
					    val1=rs.getString(2);
					   }
					 textField.setText(val);
						textField_1.setText(val1);
						
					}
					   catch(Exception ex) 
					   {
				        System.out.println("sfdfd"+ex.getMessage());
					 
				       }
				 /*int id=Integer.parseInt(val);
				String temp = null;
				//kit ids=new kit();
					System.out.println("id:"+id);
				try {
					temp =SenderFrame.br[id].readLine();
					System.out.println("id:"+id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				//vMsg(temp);
				
			}
		});
		btnReceive.setForeground(new Color(138, 43, 226));
		btnReceive.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnReceive.setBounds(318, 29, 89, 23);
		contentPane.add(btnReceive);
		setVisible(true);
		//getMsgs();
		//connect(sk);
		//getID();
	}
	
	/*public void connect(Socket sk1)
	  {
	    try{
	             br[noR]=new BufferedReader(new InputStreamReader(sk1.getInputStream()));
	             pr[noR]= new PrintStream(sk1.getOutputStream());
	       }
	    catch(Exception e){
	      System.out.println("Error at connect method:"+e.getMessage());
	    }
	  }*/
	
	public void getMsgs()
	  {
	    	String temp = null;
				try {
					for(int i=0;;)
					{
					if(SenderFrame.br[i].ready())
					{
					 temp=SenderFrame.br[i].readLine();
					 vMsg(temp);
					}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	  }
	
	/*public void getID()
	
	{
		try {
			String temp=br.readLine();
			String[] temp1=temp.split(",");
			String temp2=temp1[0];
			String temp3=temp1[1];
			textField.setText(temp2);
			textField_1.setText(temp3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	public void vMsg(String temp)
	{
		String t1[]=temp.split(":");
		System.out.println("t1[0]:"+t1[0]);
		/*if(t1[0].equals("ID"))
		{
		String t2[]=t1[1].split(",");
		String temp1=t2[0];
		temp2=Integer.parseInt(t2[1]);
		System.out.println("temp1:"+temp1);
		textField.setText(temp1);
		textField_1.setText(""+temp2);
		//pr[Integer.parseInt(t2[0])].println("H:"+t2[1]+","+t2[2]);
		}*/
		if(t1[0].equals("msg"))
		{
			System.out.println("hello");
		    pr[temp2].println("msg:"+t1[1]);
		}
		
	}

}
