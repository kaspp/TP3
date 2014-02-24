import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Main {

	/**
	 * @param args
	 */
	static JFrame f;
	static JLabel l;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final DoAnalyse da = new DoAnalyse();
		
		
		
		f = new JFrame("Tweet Analyser");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 150);
		f.setResizable(false);
		f.setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		final JTextField tf = new JTextField(30);
		JButton tweet = new JButton();
		tweet.setText("Analyze");
		
		tweet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				//showDialog("You press a button", "Analyzed");
				String temp = tf.getText(), result = "<html>";
				
				ArrayList<String> rtr = da.checkPhrase(temp);
				
				for (int i = 0; i < rtr.size(); i++ ) {
					if (i == 0) {
						result += rtr.get(i) + "<br> ";
					} else if ( i == rtr.size() - 1) {
						result += rtr.get(i) + "</html>";
					} else {
						result += rtr.get(i) + "<br> ";
					}
				}
				
				l.setText(result);
				da.clear();
				
				ConnectTwitter ct = new ConnectTwitter();
			}
		});
		
		
		
		p.add(tf);
		p.add(tweet);
		
		l = new JLabel();
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setVerticalAlignment(SwingConstants.CENTER);
		l.setText("Text");
		
		
		f.add(p, BorderLayout.CENTER);
		f.add(l, BorderLayout.SOUTH);
		f.setVisible(true);
		
		
	}
	
	public static void showDialog(String input, String title) {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel(input);
		panel.add(lbl);
		JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

	}

}
