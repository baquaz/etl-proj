package etl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//import com.mysql.cj.jdbc.MysqlDataSource;


/**
 * Klasa odpowiedzialna za polaczenie z baza danych
 * oraz wczytanie do niej pobranych informacji
 */
public class DatabaseManager {

	/**
	 * 
	 */
	private final String DB_URL = "jdbc:mysql://localhost/etl_db?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private final String USER = "root";
	private final String PASS = "root";
	
	/**
	 * 
	 */
	private Product product;
	
	/**
	 * @param product Produkt
	 */
	public DatabaseManager(Product product){
		this.product = product;
	}
	
	/**
	 * Wczytanie do bazy danych
	 * @throws SQLException Blad polaczenia z baza danych
	 */
	public void loadToDatabase() throws SQLException{		
		int counter = 0;
		int nullCounter = 0;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
		String insertProductQuery = "INSERT INTO products () VALUES (?,?,?,?,?)";
		String insertReviewQuery = "INSERT INTO reviews () VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String checkProductQuery = "SELECT product_id FROM products WHERE product_id = ".concat(product.getProductId());
		String checkOpinionQuery;		
		
		Statement stmt = conn.createStatement();
		PreparedStatement preparedStmt = conn.prepareStatement(insertProductQuery);		
		
		ResultSet rs = stmt.executeQuery(checkProductQuery);
		
		if (!rs.isBeforeFirst() ) {			
			preparedStmt.setString(1, product.getProductId());
			preparedStmt.setString(2, product.getCategory());
			preparedStmt.setString(3, product.getBrand());
			preparedStmt.setString(4, product.getModel());
			preparedStmt.setString(5, product.getExtraInfo());
			preparedStmt.execute();
			counter++;
		}
		else{
			nullCounter++;
		}			
			
			
		PreparedStatement preparedReviewStmt = conn.prepareStatement(insertReviewQuery);			
		
		for (int i = 0; i < product.getNumberOfReviews(); i++) {
			
			checkOpinionQuery = "SELECT review_id from reviews where review_id = "
					+ "'".concat(product.getReviews(i).getId().concat("'")); 
			
			rs = stmt.executeQuery(checkOpinionQuery);
		
			if(!rs.isBeforeFirst()){
				preparedReviewStmt.setString(1, product.getReviews(i).getId());
				preparedReviewStmt.setString(2, product.getReviews(i).getReviewContent());
				preparedReviewStmt.setString(3, product.getReviews(i).getPros());
				preparedReviewStmt.setString(4, product.getReviews(i).getCons());
				preparedReviewStmt.setInt(5, product.getReviews(i).getNumberOfStars());			
				preparedReviewStmt.setString(6, product.getReviews(i).getAuthor());
				preparedReviewStmt.setDate(7, product.getReviews(i).getDate());
				preparedReviewStmt.setBoolean(8, product.getReviews(i).isRecomended());
				preparedReviewStmt.setInt(9, product.getReviews(i).getNumberOfPositiveVotes());
				preparedReviewStmt.setInt(10, product.getReviews(i).getNumberOfNegativeVotes());
				preparedReviewStmt.setString(11, product.getProductId());
				preparedReviewStmt.execute();
				counter++;
			}
			else{
				nullCounter++;
			}
		
		}
		
		preparedStmt.close();
		preparedReviewStmt.close();
	    conn.close();
	    
	    if(nullCounter > 0)
	    	GUI.printText(nullCounter + " rekordow juz znajduje sie w bazie danych");
	    GUI.printText(counter + " rekordow zostalo zaladowanych do bazy danych");
	}
}
