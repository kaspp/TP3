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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

	/**
	 * @param args
	 */
	static JFrame f, fr;
	static JLabel l;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final DoAnalyse da = new DoAnalyse();
		final AnalyseTrend at = new AnalyseTrend();

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

		JButton allAna = new JButton("Analyse Report");

		tweet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				// showDialog("You press a button", "Analyzed");
				String temp = tf.getText(), result = "<html>";

				ArrayList<String> rtr = da.checkPhrase(temp);

				for (int i = 0; i < rtr.size(); i++) {
					if (i == 0) {
						result += rtr.get(i) + "<br> ";
					} else if (i == rtr.size() - 1) {
						result += rtr.get(i) + "</html>";
					} else {
						result += rtr.get(i) + "<br> ";
					}
				}

				l.setText(result);
				da.clear();

				// ConnectTwitter ct = new ConnectTwitter();
				// AnalyseTrend at = new AnalyseTrend();
				// at.insert("bubbletea", "positive");

			}
		});

		allAna.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				fr = new JFrame("Analysed Data");
				
				fr.setLayout(new BorderLayout());
				
				JPanel AllWords = new JPanel(), tt = new JPanel();
				
				JScrollPane aw = new JScrollPane(AllWords), tfive = new JScrollPane(tt);
	
				JTabbedPane tabbedPane = new JTabbedPane();
				
				
				// ================== SHOW ALL WORDS ================== //
				JLabel view = new JLabel();

				fr.setSize(500, 400);
				ArrayList<String> food = at.AnalysedFood();

				String forAllWords = "<html>";

				for (String temp : food) {

					forAllWords += "<b>" + temp + "</b>" + "<br> Positive Count :"
							+ at.getPositive(temp) + " <br> Negative Count"
							+ ":" + at.getNegative(temp) + "<br><br>";
				}

				AllWords.setLayout(new BorderLayout());
				view.setText(forAllWords);

				JButton close = new JButton("Close");
				AllWords.add(view, BorderLayout.CENTER);
				close.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						fr.setVisible(false);
					}
				});
				
				
				// ================== TOP TREND ================== //
				
				
				JLabel displayTT = new JLabel();
				String forTT = "<html> <b> TOP 5 Food Trending </b><br><br>";
				
				ArrayList<String> tf = at.topFive(1);
				int count = 1;
				for (String temp : tf) {
					forTT += count + ".&nbsp;&nbsp;&nbsp;&nbsp;" + temp + "<br>";
					count++;
				}
				
				forTT += "<br> <b> BOTTOM 5 Food Trending </b> <br><br>";
				
				tf = at.topFive(2);
				count = 1;
				for (String temp : tf) {
					forTT += count + ".&nbsp;&nbsp;&nbsp;&nbsp;" + temp + "<br>";
					count++;
				}
				
				
				displayTT.setText(forTT);
				tt.add(displayTT);
				
				tabbedPane.addTab("All Words", aw);
				tabbedPane.addTab("Top Trend", tfive);
				
				
				
				
				fr.add(close, BorderLayout.SOUTH);				
				fr.add(tabbedPane);
				fr.setVisible(true);

				
				
				
			}
		});

		p.add(tf);
		p.add(tweet);
		p.add(allAna);

		l = new JLabel();
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setVerticalAlignment(SwingConstants.CENTER);
		l.setText("Text");

		f.add(p, BorderLayout.CENTER);
		f.add(l, BorderLayout.SOUTH);
		f.setVisible(true);

	}

	public static void showDialog(String input, String title) {
		String[] options = { "OK" };
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel(input);
		panel.add(lbl);
		JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

	}

}
