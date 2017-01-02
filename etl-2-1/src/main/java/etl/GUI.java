package etl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 * Klasa odpowiedzialna za uruchomienie glownego okna aplikacji
 */
public class GUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8884330399808909588L;

	/**
	 * Obiekt do zarzadzania przebiegiem procesu ETL
	 */
	private ETLManager etlManager;

	/**
	 * 
	 */
	private final String DB_URL = "jdbc:mysql://localhost/etl_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";	
	private final String USER = "root";
	private final String PASS = "root";
	
	/**
	 * Glowne okno aplikacji
	 */
	public JFrame frame;
	
	/**
	 * Panel glowny przechowujacy wszystkie elementy graficzne
	 */
	private JPanel panel;
	
	/**
	 * Umozliwia przewijanie konsoli
	 */
	private static JScrollPane scrollPane; 
	
	/**
	 * Okno do wyswietlania
	 */
	private FrameProductView frame2;
	
	/**
	 * Pole tekstowe do wprowadzania numeru id
	 */
	private JTextField idTextField;
	
	/**
	 * Etykieta ID produktu
	 */
	private JLabel idLabel;
	
	/**
	 * Przycisk pobierz
	 */
	private JButton extractBtn;
	
	/**
	 * Przycisk przeksztalc
	 */
	private JButton transformBtn;
	
	/**
	 * Przycisk zaladuj
	 */
	private JButton loadBtn;
	
	/**
	 * Przycisk etl
	 */
	private JButton etlBtn;
	
	/**
	 * Przycisk wyswietl
	 */
	private JButton printBtn;
	
	/**
	 * Przycisk wyczysc baze danych
	 */
	private JButton clearDBbtn;
	
	/**
	 * Konsola do wyswietlania informacji
	 */
	private static JTextArea consoleTextArea;

	/**
	 * Create the application.
	 * @param etlManager menadzer ETL
	 */
	public GUI(ETLManager etlManager) {
		this.etlManager = etlManager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
							.addGap(16))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setLineWrap(true);
		consoleTextArea.setWrapStyleWord(true);
		DefaultCaret caret=(DefaultCaret)consoleTextArea.getCaret(); //pobiera pozycje karetki
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); //automatycznie aktualizuje pozycję karetki
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		scrollPane.setViewportView(consoleTextArea);
		
		
		idTextField = new JTextField();
		idTextField.setColumns(10);
		
		idLabel = new JLabel("ID Produktu: ");
		extractBtn = new JButton("Pobierz");
		transformBtn = new JButton("Przeksztalc");
		transformBtn.setEnabled(false);
		loadBtn = new JButton("Laduj");
		loadBtn.setEnabled(false);
		etlBtn = new JButton("ETL");
		printBtn = new JButton("Wyswietl");
		clearDBbtn = new JButton("Wyczysc baze danych");
		
		extractBtn.addActionListener(new ExtractListener());
		transformBtn.addActionListener(new TransformListener());
		loadBtn.addActionListener(new LoadListener());
		etlBtn.addActionListener(new EtlListener());
		printBtn.addActionListener(new PrintListener());
		clearDBbtn.addActionListener(new ClearListener());
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(GUI.class.getResource("/img/etl_logo.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(clearDBbtn)
										.addComponent(printBtn))
									.addGap(95)
									.addComponent(etlBtn))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(extractBtn)
									.addGap(18)
									.addComponent(transformBtn)
									.addGap(18)
									.addComponent(loadBtn))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(182)
							.addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(200)))
					.addGap(8))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(logoLabel)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(idLabel)
							.addComponent(extractBtn)
							.addComponent(transformBtn)
							.addComponent(loadBtn)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(printBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(clearDBbtn))
						.addComponent(etlBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	/**
	 * Wyswietla tekst w konsoli
	 * @param text tekst do wypisania
	 */
	public static void  printText(String text) {
		text += "\n";
	    consoleTextArea.append(text);
	    consoleTextArea.update(consoleTextArea.getGraphics());
	}
	
	/**
	 * Obsluga przycisku pobierz
	 * @author piotrblachewicz
	 *
	 */
	private class ExtractListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
//			extractBtn.setEnabled(false);
			try {
				etlManager.setProductId(idTextField.getText());
//				GUI.printText("ID:"+etlManager.getProductId()+"!");
				etlManager.extract();
				transformBtn.setEnabled(true);
			} catch (Exception e1) {
//				extractBtn.setEnabled(true);
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Obsluga przycisku przeksztalc
	 * @author piotrblachewicz
	 *
	 */
	private class TransformListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				etlManager.transform();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			transformBtn.setEnabled(false);
			loadBtn.setEnabled(true);
		}
	}
	
	/**
	 * Obsluga przycisku zaladuj
	 * @author piotrblachewicz
	 *
	 */
	private class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				etlManager.load();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			loadBtn.setEnabled(false);
			extractBtn.setEnabled(true);
		}
	}
	
	/**
	 * Obsluga przycisku wyswietl
	 * @author piotrblachewicz
	 *
	 */
	private class PrintListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			etlManager.setProductId(idTextField.getText());
			try {
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
				String retrieveQueryProduct= "SELECT product_id FROM products WHERE product_id ='".
						concat(etlManager.getProductId()).concat("'");
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(retrieveQueryProduct);
				
				if(rs.next()){
					frame2 = new FrameProductView(etlManager);
					frame2.setVisible(true);
				}
				else
					GUI.printText("Nie ma w bazie danych produktu o id: "+etlManager.getProductId());
				stmt.close();
			    conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
	}
	
	/**
	 * Obsluga przycisku wyczysc baze danych
	 * @author piotrblachewicz
	 *
	 */
	private class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				int reviewsDeleted;
				int productsDeleted;
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
				String deleteProducts= "DELETE FROM products";
				String deleteReviews = "DELETE FROM reviews";
				PreparedStatement preparedStmt = conn.prepareStatement(deleteReviews);
				reviewsDeleted = preparedStmt.executeUpdate();
				
				preparedStmt = conn.prepareStatement(deleteProducts);
				productsDeleted = preparedStmt.executeUpdate();
				GUI.printText("Usunieto "+productsDeleted+" produktow i "+reviewsDeleted+" opini z bazy danych");

				preparedStmt.close();
				conn.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Obsluga przycisku etl
	 * @author piotrblachewicz
	 *
	 */
	private class EtlListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				etlManager.setProductId(idTextField.getText());
				etlManager.extract();
				etlManager.transform();
				etlManager.load();
				extractBtn.setEnabled(true);
				transformBtn.setEnabled(false);
				loadBtn.setEnabled(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}


