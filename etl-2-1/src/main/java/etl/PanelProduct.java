package etl;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JButton;

/**
 * Klasa odpowiedzialna za wyswietlenie 
 * informacji o produkcie w formie graficznej
 */
public class PanelProduct extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7646569695092041541L;

	/**
	 * Model
	 */
	private JTextArea modelLabel;
	
	/**
	 * Dodatkowe informacje
	 */
	private JTextArea extraInfoLabel;
	
	/**
	 * Etykieta id prodyktu
	 */
	private JLabel productIDTextLabel;
	
	/**
	 * Etykieta kategoria
	 */
	private JLabel categoryTextLabel;
	
	/**
	 * Kategoria
	 */
	private JLabel categoryLabel;
	
	/**
	 * Etykieta marka
	 */
	private JLabel brandTextLabel;
	
	/**
	 * Marka
	 */
	private JLabel brandLabel;
	
	/**
	 * ID produktu
	 */
	private JLabel productIDLabel;
	
	/**
	 * Etykieta opinie
	 */
	private JLabel reviewsTextLabel;
	
	/**
	 * Przycisk ekportuj do CSV
	 */
	private JButton exportCSVBtn;
	
	/**
	 * Konstruktor
	 * @param csvBtn przycisk eksport do CSV
	 */
	public PanelProduct(JButton csvBtn) {
		setBackground(SystemColor.window);
		setForeground(Color.BLACK);
		
		exportCSVBtn = csvBtn;
		
		productIDTextLabel = new JLabel("ID produktu:");
		productIDTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		
		modelLabel = new JTextArea();
		modelLabel.setWrapStyleWord(true);
		modelLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 18));
		modelLabel.setLineWrap(true);
		modelLabel.setEditable(false);
		modelLabel.setBackground(SystemColor.window);
		
		categoryTextLabel = new JLabel("Kategoria:");
		categoryTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		categoryLabel = new JLabel("[kat]");
		
		brandTextLabel = new JLabel("Producent:");
		brandTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		brandLabel = new JLabel("[prod]");
		
		extraInfoLabel = new JTextArea();
		extraInfoLabel.setRows(4);
		extraInfoLabel.setBounds(new Rectangle(1, 1, 1, 1));
		extraInfoLabel.setWrapStyleWord(true);
		extraInfoLabel.setLineWrap(true);
		extraInfoLabel.setBackground(SystemColor.window);
		extraInfoLabel.setEditable(false);
		
		productIDLabel = new JLabel("[id prod]");
		
		reviewsTextLabel = new JLabel("Opinie:");
		reviewsTextLabel.setForeground(Color.BLUE);
		reviewsTextLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(productIDTextLabel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(brandTextLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(categoryTextLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(brandLabel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
							.addGap(146))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(categoryLabel, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
								.addComponent(productIDLabel, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
							.addGap(222)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(extraInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addComponent(modelLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
					.addGap(30))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(reviewsTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
					.addComponent(exportCSVBtn)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(modelLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(extraInfoLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(productIDTextLabel)
						.addComponent(productIDLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(categoryLabel)
						.addComponent(categoryTextLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(brandLabel)
						.addComponent(brandTextLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(reviewsTextLabel)
						.addComponent(exportCSVBtn))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	
	/**
	 * Dodaje model
	 * @param text nazwa modelu
	 */
	public void addModel(String text){
		modelLabel.setText(text);
	}
		
	/**
	 * Dodaje dodatkowe informacje
	 * @param text informacje
	 */
	public void addExtraInfo(String text){
		extraInfoLabel.setText(text);
	}

	/**
	 * Dodaje ID produktu
	 * @param text id
	 */
	public void addProductID(String text){
		productIDLabel.setText(text);
	}

	/**
	 * Dodaje kategorie
	 * @param text kategoria
	 */
	public void addCategory(String text){
		categoryLabel.setText(text);
	}

	/**
	 * Dodaje marke
	 * @param text marka
	 */
	public void addBrand(String text){
		brandLabel.setText(text);
	}
}
