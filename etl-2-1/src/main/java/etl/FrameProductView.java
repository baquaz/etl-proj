package etl;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;

/**
 * Klasa odpowiedzialna za okno do wyswietlenia
 * inormacji oraz opini o produkcie
 */
public class FrameProductView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3036175410013562801L;
	/**
	 * Dane do polaczenia z baza danych
	 */
	private final String DB_URL = "jdbc:mysql://localhost/etl_db?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private final String USER = "root";
	private final String PASS = "root";
	
	/**
	 * Zarz�dza procesem ETL
	 */
	private ETLManager etlManager;
	
	/**
	 * 
	 */
	private JPanel contentPane;
	
	/**
	 * 
	 */
	private JPanel panel;
	
	/**
	 * 
	 */
	private PanelProduct panelProduct;
	
	/**
	 * Umo�liwa przewijanie opini
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Pasek do przewijania
	 */
	private JScrollBar scb;
	
	/**
	 * Zawiera liste ze wszystkimi opiniami
	 */
	private Box verticalBox;
	
	/**
	 * Przycisk esportuj do CSV
	 */
	private JButton csvBtn;
	
	/**
	 * Tworzy okno
	 * @param etl Menadzer etl
	 * @throws SQLException Blad polaczenia z baza danych
	 */
	public FrameProductView(ETLManager etl) throws SQLException {
		this.etlManager = etl;
		csvBtn = new JButton();
		csvBtn.setText("Eksport do CSV");
		csvBtn.addActionListener(new ExportCsvListener());		

		setBounds(100, 100, 603, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scb = scrollPane.getVerticalScrollBar();
		
		
		scb.setUnitIncrement(8);
		panel.add(scrollPane);
		
		verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);
	
		showData();
						
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			   }
			});
	}
	
	/**
	 * Wyswietla informacje i opinie dla produktu
	 * @throws SQLException Blad polaczenia z baza danych
	 */
	public void showData() throws SQLException{
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String retrieveQueryProduct= "SELECT * FROM products WHERE product_id = '".concat(etlManager.getProductId()).concat("'");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(retrieveQueryProduct);
		
		if(rs.next()){
			String productID = rs.getString("product_id");
			String category = rs.getString("category");
			String company = rs.getString("brand");
			String model = rs.getString("model");		
			String additionalInfo = rs.getString("extra_info");
			
			panelProduct = new PanelProduct(csvBtn);
			panelProduct.addCategory(category);		
			panelProduct.addBrand(company);
			panelProduct.addExtraInfo(additionalInfo);
			panelProduct.addModel(model);
			panelProduct.addProductID(productID);
			verticalBox.add(panelProduct);
		
			JSeparator separator = new JSeparator();
			verticalBox.add(separator);
	
			
			String retrieveQueryReviews= "SELECT * FROM reviews WHERE product_id = '".concat(etlManager.getProductId()).concat("'");
			Statement stmtRev = conn.createStatement();
			ResultSet rsRev = stmtRev.executeQuery(retrieveQueryReviews);
			
			while(rsRev.next()){
				String content = rsRev.getString("content");
				String pros = rsRev.getString("pros");
				String cons = rsRev.getString("cons");
				Integer numberOfStars = rsRev.getInt("number_of_stars");
				String author = rsRev.getString("author");
				Date date = rsRev.getDate("date");
				boolean recommended = rsRev.getBoolean("recommended");
				Integer numberOfPositiveVotes = rsRev.getInt("number_of_positive_votes");
				Integer numberOfNegativeVotes = rsRev.getInt("number_of_negative_votes");
				
				PanelRev panelReview = new PanelRev();
				panelReview.addReviewContent(content);
				panelReview.addPros(pros);
				panelReview.addCons(cons);
				panelReview.addNumberOfStars(numberOfStars);
				panelReview.addAuthor(author);
				panelReview.addDate(date);
				panelReview.addRecommended(recommended);
				panelReview.addNumberOfPositiveVotes(numberOfPositiveVotes);
				panelReview.addNumberOfNegativeVots(numberOfNegativeVotes);
				verticalBox.add(panelReview);
				
				JSeparator separator2 = new JSeparator();
				verticalBox.add(separator2);
			}
			
				rs.close();
				rsRev.close();
				stmt.close();
				stmtRev.close();
			    conn.close();
		}
		else
			GUI.printText("Nie ma produktu id: "+etlManager.getProductId());
		
	}
	
	/**
	 * Eksportuje wyswietlone informacje do pliku CSV
	 * @throws SQLException Blad polaczenia z baza danych
	 */
	public void exportToCsv() throws SQLException{
		try {
			//WINDOWS DESKTOP PATH
//			String path = "C:/Users/PanSzlachcic/Desktop/".
//					concat(etlManager.getProductId().concat(".csv"));				
			
			//MAC OS X DESKTOP PATH
			String path = "/Users/piotrblachewicz/Desktop/".concat(etlManager.getProductId()).concat(".csv");
			
			PrintWriter pw;
			
			pw = new PrintWriter(new File(path));
			
			String row ="";		
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String retrieveQueryProduct= "SELECT model FROM products WHERE product_id = '".concat(etlManager.getProductId()).concat("'");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(retrieveQueryProduct);

			rs.next();
			String model = rs.getString("model");			
			row = row.concat(model).concat("\n\n");
			String retrieveQueryReviews= "SELECT * FROM reviews WHERE product_id = '".concat(etlManager.getProductId()).concat("'");
			Statement stmtRev = conn.createStatement();
			ResultSet rsRev = stmtRev.executeQuery(retrieveQueryReviews);
			
			row += "Content;pros;cons;rating;author;date;recommended;okVotes;badVotes\n";	
			while(rsRev.next()){
				String content = rsRev.getString("content");
				String pros = rsRev.getString("pros");
				String cons = rsRev.getString("cons");
				Integer numberOfStars = rsRev.getInt("number_of_stars");
				String author = rsRev.getString("author");
				Date date = rsRev.getDate("date");
				boolean recommended = rsRev.getBoolean("recommended");
				Integer numberOfPositiveVotes = rsRev.getInt("number_of_positive_votes");
				Integer numberOfNegativeVotes = rsRev.getInt("number_of_negative_votes");
				
				row=row.concat(content).concat(";").
						concat(pros).concat(";").
						concat(cons).concat(";").
						concat(Integer.toString(numberOfStars)).concat(";").
						concat(author).concat(";").
						concat(date.toString()).concat(";").
						concat(Boolean.toString(recommended)).concat(";").
						concat(Integer.toString(numberOfPositiveVotes)).concat(";").
						concat(Integer.toString(numberOfNegativeVotes)).concat("\n");
			}
			
			pw.write(row);
			pw.close();
			rs.close();
			rsRev.close();
			stmt.close();
			stmtRev.close();
		    conn.close();
		    GUI.printText("Dane zostały zapisane do pliku " + path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * Obsluga przycisku eksport do CSV
	 * @author PanSzlachcic
	 *
	 */
	private class ExportCsvListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				exportToCsv();				
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
		}
	}
	
	
}
