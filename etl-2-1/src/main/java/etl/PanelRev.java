package etl;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;


/**
 * Klasa odpowiedzialna za wyswietlenie informacji
 * dotyczacych pojedynczej opinii
 */
public class PanelRev extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3013711158942311347L;

	/**
	 * Lewy panel
	 */
	private JPanel leftPanel;
	
	/**
	 * Prawy panel
	 */
	private JPanel rightPanel;
	
	/**
	 * Etykieta autor
	 */
	private JLabel authorTextLabel;
	
	/**
	 * Polecam / nie polecam
	 */
	private JLabel recommendedLabel;
	
	/**
	 * Etykieta ocena
	 */
	private JLabel ratingTextLabel;
	
	/**
	 * Data dodania opini
	 */
	private JLabel dateLabel;
	
	/**
	 * Liczba gwiazdek
	 */
	private JLabel ratingLabel;
	
	/**
	 * Etykieta data
	 */
	private JLabel dateTextLabel;
	
	/**
	 * Liczba osob ktore uznaly opinie za przydanta
	 */
	private JLabel okVotesLabel;
	
	/**
	 * Liczba osob ktore uznaly opinie za nieprzydanta
	 */
	private JLabel badVotesLabel;
	
	/**
	 * Ikona z kciukiem w gore
	 */
	private JLabel okIcon;
	
	/**
	 * Ikona z kciukiem w dol
	 */
	private JLabel badIcon;
	
	/**
	 * Etykieta zalety
	 */
	private JLabel prosTextLabel;
	
	/**
	 * Etykieta wady
	 */
	private JLabel consTextLabel;
	
	/**
	 * Pole tekstowe z trescia opini
	 */
	private JTextArea commentTextArea;
	
	/**
	 * Pasek do scrollowania wad
	 */
	private JScrollPane prosScrollPane;
	
	/**
	 * Pasek do scrollowania tresci opini
	 */
	private JScrollPane commentScrollPane;
	
	/**
	 * Pole opini z zaletami
	 */
	private JTextArea prosTextArea;
	
	/**
	 * Pasek do scrollowania zalet
	 */
	private JScrollPane consScrollPane;
	
	/**
	 * Pole tekstowe z wadami
	 */
	private JTextArea consTextArea;
	
	/**
	 * Autor
	 */
	private JLabel authorLabel;
	
	
	/**
	 * Create the panel.
	 */
	public PanelRev() {
		
		leftPanel = new JPanel();
		
		rightPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(rightPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
						.addComponent(leftPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		prosTextLabel = new JLabel("Zalety");
		prosTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		prosScrollPane = new JScrollPane();
		prosScrollPane.setBorder(null);
		
		consTextLabel = new JLabel("Wady");
		consTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		consScrollPane = new JScrollPane();
		consScrollPane.setBorder(null);
		
		ratingTextLabel = new JLabel("Ocena:");
		ratingTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		ratingLabel = new JLabel();
		ratingLabel.setText("5 / 5");
		
		dateTextLabel = new JLabel("Data:");
		dateTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		dateLabel = new JLabel();
		dateLabel.setText("12-12-2012");
		
		okIcon = new JLabel("");
		okIcon.setIcon(new ImageIcon(PanelRev.class.getResource("/img/thumbs-up-icon - Kopia-1.png")));
		
		okVotesLabel = new JLabel("117");
		
		badIcon = new JLabel("");
		badIcon.setIcon(new ImageIcon(PanelRev.class.getResource("/img/thumbs-down-icon - Kopia-1.png")));
		
		badVotesLabel = new JLabel("117");
		
		commentScrollPane = new JScrollPane();
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(ratingTextLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(ratingLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(dateTextLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
							.addComponent(okIcon, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(okVotesLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(badIcon, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(badVotesLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(commentScrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(prosScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(prosTextLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(consTextLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(consScrollPane, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap(17, Short.MAX_VALUE)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ratingTextLabel)
						.addComponent(ratingLabel)
						.addComponent(dateTextLabel)
						.addComponent(dateLabel)
						.addComponent(okIcon)
						.addComponent(okVotesLabel)
						.addComponent(badIcon)
						.addComponent(badVotesLabel))
					.addGap(9)
					.addComponent(commentScrollPane, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(prosTextLabel)
						.addComponent(consTextLabel))
					.addGap(6)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(prosScrollPane, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(consScrollPane, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		consTextArea = new JTextArea();
		consTextArea.setBackground(Color.LIGHT_GRAY);
		consTextArea.setLineWrap(true);
		consTextArea.setWrapStyleWord(true);
		consScrollPane.setViewportView(consTextArea);
		DefaultCaret consCaret =(DefaultCaret)consTextArea.getCaret();
		consCaret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		consScrollPane.setViewportView(consTextArea);
		
		prosTextArea = new JTextArea();
		//prosTextArea.setText("Stabilny, Szybki (szybszy od XP), Latwo konfigurowalny, Rozbudowane opcje administracyjne.");
		prosTextArea.setBorder(null);
		prosTextArea.setBackground(Color.LIGHT_GRAY);
		prosTextArea.setLineWrap(true);
		prosTextArea.setWrapStyleWord(true);
		prosScrollPane.setViewportView(prosTextArea);
		DefaultCaret prosCaret=(DefaultCaret)prosTextArea.getCaret();
		prosCaret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		commentTextArea = new JTextArea();
		commentTextArea.setEditable(false);
		commentTextArea.setLineWrap(true);
		commentTextArea.setWrapStyleWord(true);
		commentScrollPane.setViewportView(commentTextArea);
		DefaultCaret commentCaret=(DefaultCaret)commentTextArea.getCaret();
		commentCaret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		rightPanel.setLayout(gl_rightPanel);
		
		authorTextLabel = new JLabel("Autor:");
		authorTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		recommendedLabel = new JLabel();
		recommendedLabel.setText("NIE POLECAM");
		recommendedLabel.setSize(new Dimension(120, 0));
		recommendedLabel.setMinimumSize(new Dimension(120, 0));
		recommendedLabel.setMaximumSize(new Dimension(120, 0));
		
		authorLabel = new JLabel("Użytkownik Ceneo");
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(authorTextLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(recommendedLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addGap(43)
					.addComponent(authorTextLabel)
					.addGap(2)
					.addComponent(authorLabel)
					.addGap(18)
					.addComponent(recommendedLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		leftPanel.setLayout(gl_leftPanel);
		setLayout(groupLayout);

	}

	/**
	 * Dodaje tresc komentarza
	 * @param text tresc
	 */
	public void addReviewContent(String text){
		commentTextArea.setText(text);
	}
	
	/**
	 * Dodaje zalety
	 * @param text zalety
	 */
	public void addPros(String text){
		prosTextArea.setText(text);
	}
	
	/**
	 * Dodaje wady
	 * @param text wady
	 */
	public void addCons(String text){
		consTextArea.setText(text);
	}
	
	/**
	 * Dodaje liczbe gwiazdek
	 * @param number Liczba gwiazdek
	 */
	public void addNumberOfStars(Integer number){
		ratingLabel.setText(Integer.toString(number) + " / 5");
	}
	
	/**
	 * Dodaje autora
	 * @param text autor
	 */
	public void addAuthor(String text){
		authorLabel.setText(text);
	}
	
	/**
	 * Dodaje date
	 * @param date Data
	 */
	public void addDate(Date date){
		dateLabel.setText(date.toString());
	}
	
	/**
	 * Dodaje napis polecam / nie polecam
	 * @param recommended true albo false
	 */
	public void addRecommended(boolean recommended){
		if(recommended){
			recommendedLabel.setForeground(new Color(50, 205, 50));
			recommendedLabel.setText("POLECAM");
		} else {
			recommendedLabel.setForeground(new Color(178, 34, 34));
			recommendedLabel.setText("NIE POLECAM");
		}
	}
	
	/**
	 * Dodaje ilosc osob ktore uznaly opinie za przydatna
	 * @param number Ilosc osob
	 */
	public void addNumberOfPositiveVotes(Integer number){
		okVotesLabel.setText(number.toString());
	}
	
	/**
	 * Dodaje ilosc osob ktore uznaly opinie za nieprzydatna
	 * @param number Ilosc osob
	 */
	public void addNumberOfNegativeVots(Integer number){
		badVotesLabel.setText(number.toString());
	}
}
