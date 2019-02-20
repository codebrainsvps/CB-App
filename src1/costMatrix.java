import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.LinkedList;


public class costMatrix extends JFrame {

	private JPanel contentPane;
	LinkedList<Integer> signal;
	LinkedList<Integer> channel;
	int[][] cost;
   Socket[] sk;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					costMatrix frame = new costMatrix();
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
	 * @param channel 
	 * @param signal 
	 */
	public costMatrix(final Socket[] sk, final LinkedList<Integer> signal, final LinkedList<Integer> channel) {
		setTitle("Cost Matrix Generation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.signal=signal;
		this.channel=channel;
		this.sk=sk;
		JLabel lblCommunicatioMatrixIs = new JLabel("Cost Matrix Is:");
		lblCommunicatioMatrixIs.setForeground(new Color(0, 0, 128));
		lblCommunicatioMatrixIs.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCommunicatioMatrixIs.setBounds(24, 25, 119, 22);
		contentPane.add(lblCommunicatioMatrixIs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 58, 451, 269);
		contentPane.add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Generate Cost Matrix");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(138, 43, 226));
		btnNewButton.addActionListener(new ActionListener() {
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
					textArea.append("\t"+(i));
					line+="------------------------";
				}
				for(int j=0; j<signal.size() ; j++)
		        {
		         textArea.append("\n" + line);
		         textArea.append("\n" +(j));
		         for(int k=0; k<signal.size() ; k++)
		         textArea.append("\t"+cost[j][k] );        
		        } 
				
			}
		});
		btnNewButton.setBounds(279, 27, 196, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new PathIdentification(sk,cost);
			}
		});
		btnNext.setForeground(new Color(138, 43, 226));
		btnNext.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNext.setBounds(288, 338, 89, 23);
		contentPane.add(btnNext);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnCancel.setForeground(new Color(138, 43, 226));
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(386, 338, 89, 23);
		contentPane.add(btnCancel);
		setVisible(true);
	}

}
