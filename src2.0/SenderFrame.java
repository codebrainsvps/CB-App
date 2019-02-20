import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SenderFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Socket s;
	 int noR=0;int gkey;
	int i=0;
	int[][] cost;
	Socket[] sk=new Socket[5];
	static BufferedReader br[]=new BufferedReader[5];
	static PrintStream pr[]=new PrintStream[5];
	String[][] maintable=new String[5][5];
	String[] head={"ID","IP Address","Signal strength","Channel capacity"};
	Random r=new Random();
	LinkedList<Integer> signal=new LinkedList<Integer>();
	LinkedList<Integer> channel=new LinkedList<Integer>();
	int temp2,temp1;
	private JTextField textField;
	private JTextField textField_1;
	String spath;
	int from,toid;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenderFrame frame = new SenderFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public SenderFrame() throws IOException {
		setTitle("Sender Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 500, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNodeDetails = new JLabel("Nodes Details:");
		lblNodeDetails.setBackground(new Color(0, 0, 0));
		lblNodeDetails.setForeground(new Color(0, 0, 0));
		lblNodeDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNodeDetails.setBounds(25, 27, 104, 22);
		contentPane.add(lblNodeDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 60, 442, 106);
		contentPane.add(scrollPane);
		
		table = new JTable(maintable,head);
		scrollPane.setViewportView(table);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(25, 271, 457, 304);
		contentPane.add(tabbedPane);
		setVisible(true);
		final JTextArea textArea = new JTextArea();
		JScrollPane scrollPane_1 = new JScrollPane(textArea);
		tabbedPane.add("Cost Matrix",scrollPane_1);
		
		
		//scrollPane_1.setColumnHeaderView(textArea);
		
		final JTextArea textArea_1 = new JTextArea();
		JScrollPane scrollPane_2 = new JScrollPane(textArea_1);
		
		scrollPane_2.setViewportView(textArea_1);
		tabbedPane.add("Shortest Path Identification",scrollPane_2);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button.setBounds(393, 586, 89, 23);
		contentPane.add(button);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			 	
			 //new costMatrix(sk,signal,channel);
				/*try
				  {
				  Conn con=new Conn();			
				  java.sql.Statement st=con.getConn();
				  st.executeUpdate("delete from shortestpath ");
			      st.executeUpdate("insert into shortestpath values('"+spath+"')");
			       }
				   catch(Exception e)
				   {System.out.println("error"+e.getMessage());}*/
				pr[from].println("path:"+spath);
				pr[toid].println("path:"+spath);
				getMsgs();
			}
		});
		btnSend.setForeground(new Color(0, 0, 0));
		btnSend.setBounds(309, 586, 74, 23);
		contentPane.add(btnSend);
		
		JButton btnGenerateCostMatrix = new JButton("Generate cost Matrix");
		btnGenerateCostMatrix.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnGenerateCostMatrix.setForeground(new Color(0, 0, 0));
		btnGenerateCostMatrix.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) 
			{
				LinkedList<Integer> totalcapacity=new LinkedList<Integer>();
				for(int i=0;i<signal.size();i++)
				{
					totalcapacity.add(signal.get(i)+channel.get(i));
				}
				cost=new int[signal.size()][channel.size()];
				for(int i=0;i<signal.size();i++)
				{
					for(int j=0;j<channel.size();j++)
					{
						cost[i][j]=Math.abs(totalcapacity.get(i)-(channel.get(j)+signal.get(j)));
					}
				}
				
				
				textArea.append("\n==============Cost Matrix============\n");
				 String line = "-";
				for(int i=0;i<signal.size();i++)
				{
					textArea.append("\t"+(i+1));
					line+="------------------------";
				}
				for(int j=0; j<signal.size() ; j++)
		        {
		         textArea.append("\n" + line);
		         textArea.append("\n" +(j+1));
		         for(int k=0; k<signal.size() ; k++)
		         textArea.append("\t"+cost[j][k] );        
		        } 

							}
		});
		btnGenerateCostMatrix.setBounds(26, 237, 172, 23);
		contentPane.add(btnGenerateCostMatrix);
		
		JButton btnFindShortestPath = new JButton("Find Shortest Path");
		btnFindShortestPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField.getText().isEmpty() && textField_1.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"From id and To id should not empty!","INFORMATION",JOptionPane.INFORMATION_MESSAGE); 
					
					
				}
				else
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
				textArea_1.append(""+path+"\n");
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
						textArea_1.append(kval+"------>"+lval+"=="+costval+"\n");
						l+=1;
						cost1=cost1+costval;
					    
				}
				costvalues[i]=cost1;
				System.out.println("cost values:"+costvalues[i]);
				textArea_1.append("\n********************************\n");
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
					textArea_1.append("\nThe shorest paht is:"+val+"\n");
					System.out.println("val xvsdcs:"+val);
					spath=val;
					}
					count++;
				}
			
				}
				
			}
		});
		btnFindShortestPath.setForeground(new Color(0, 0, 0));
		btnFindShortestPath.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnFindShortestPath.setBounds(253, 236, 145, 23);
		contentPane.add(btnFindShortestPath);
		
		JLabel lblFromId = new JLabel("From Id:");
		lblFromId.setForeground(new Color(0, 0, 0));
		lblFromId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFromId.setBounds(28, 197, 57, 22);
		contentPane.add(lblFromId);
		
		textField = new JTextField();
		textField.setBounds(87, 199, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblToId = new JLabel("To Id:");
		lblToId.setForeground(new Color(0, 0, 0));
		lblToId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblToId.setBounds(219, 198, 42, 20);
		contentPane.add(lblToId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(268, 199, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRetrive = new JButton("Retrive");
		btnRetrive.addActionListener(new ActionListener() {
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
			
			}
		});
		btnRetrive.setForeground(new Color(0, 0, 0));
		btnRetrive.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRetrive.setBounds(378, 197, 89, 23);
		contentPane.add(btnRetrive);
		setVisible(true);
		
	}
	
	public void newRecv(Socket s)
	{

	System.out.println("Client Connected");
	sk[noR]=s;
	connect(s);
	maintable[noR][0]=""+(noR);
	System.out.println(""+noR);
	String temp=s.getInetAddress().toString();
	maintable[noR][1]=temp.substring(1,temp.length());
	try{

		while(true)
		{
		int sig=r.nextInt(100)+1;
		int cha=r.nextInt(150)+1;
		if(check(sig,cha,noR,maintable))
			{
			maintable[noR][2]=""+sig;
			maintable[noR][3]=""+cha;
			pr[noR].println(noR+","+sig+","+cha+","+temp.substring(1,temp.length()));
			System.out.println("Key Sended");
			signal.add(sig);
			channel.add(cha);
			break;
			}
		}


	noR++;
	table.updateUI();
	}catch(Exception e)
	{System.out.println("error1"+e.getMessage());}

	}
	public void connect(Socket sk1)
	  {
	    try{
	             br[noR]=new BufferedReader(new InputStreamReader(sk1.getInputStream()));
	             pr[noR]= new PrintStream(sk1.getOutputStream());
	       }
	    catch(Exception e){
	      System.out.println("Error at connect method:"+e.getMessage());
	    }
	  }
	
	public boolean check(int sig,int cha,int n,String t[][])
	{
	if(n==0)
		return(true);
	else
		{
		for(int i=0;i<n;i++)
		{
			if(Integer.parseInt(t[i][2])==sig)
			{return(false);}
		   	else if(Integer.parseInt(t[i][3])==cha)
		    {return(false);}
		   
	}
		}
	return(true);

	}
	public void getMsgs()
	  {
	    try{
	     	for(int i=0;;)
		{
		      if(br[i].ready())
		         {
		            String temp=br[i].readLine();
		            System.out.println(temp);
		            vMsg(temp);
		         }
		   i=(i+1)%noR;	
	              }
	         }
	    catch(Exception e)
	      {
	          System.out.println("Error1"+e.getMessage());
	       }
	 }
	         public void vMsg(String temp)
	             {
		try{
		System.out.println("temp"+temp);
		String t1[]=temp.split(":");
		
		if(t1[0].equals("msg"))

		{
	       String t2[]=t1[1].split("&");	
	       System.out.println("message:"+t2[1]);
	       System.out.println("to id:"+t2[0]);
	       System.out.println("path length:"+spath.length());
	       spath = spath.replaceAll("\\[", "").replaceAll("\\]","");
	       ArrayList<Integer> pathids=new ArrayList();
	       for(String st:spath.split(","))
	       {
	    	   pathids.add(Integer.parseInt(st.trim()));
	    	  // pr[Integer.parseInt(st.trim())].println("msg:"+t2[1]);
	    	   System.out.println("id of node:"+st);
	    	   
	       }
	       for(int i=0;i<pathids.size()-1;i++)
	       {
	    	   pr[pathids.get(i+1)].println("msg:"+pathids.get(i)+"&"+t2[0]+"&"+t2[1]+"&"+t2[2]);
	       }
		  // pr[Integer.parseInt(t2[0])].println("msg:"+t2[1]);
		   
			
		}
	}
	 
	    catch(Exception e){
	      System.out.println("Error2"+e.getMessage());
	    }
	}
}

	

