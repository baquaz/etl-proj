package etl;

/**
 * Klasa sluzaa do zarzadzanie przebiegiem procesu ETL
 */
public class ETLManager {
	
	/**
	 * ID produktu
	 */
	private String productId;
	
	/**
	 * Parsuje i przechowuje informacje o produkcie oraz opinie
	 */
	private Product product;
	
	
	/**
	 * Pobiera strony HTTP z informacjami o produkcie i opiniami
	 * oraz zapisuje w pami�ci
	 * @throws Exception Blad polaczenia
	 */
	public void extract() throws Exception{
		product = new Product(productId);
		GUI.printText("Pobieram dane dla id: "+productId+"...");
		product.downloadProductPage();		
		product.downloadReviewPages();
		GUI.printText(product.getCounter()+" plikow zostalo pobranych");
	}
	
	/**
	 * Przeksztalca pobrane dane
	 */
	public void transform(){
		product.createReviewsArray();
		product.parseReviews();
		product.parseProduct();
		GUI.printText("Dane zostały przekształcone");
	}
	
	
	/**
	 * Wczytuje przeksztalcone dane do bazy danych
	 * @throws Exception Blad polaczenia z baza danych
	 */
	public void load() throws Exception{
		DatabaseManager dbManager = new DatabaseManager(product);
		dbManager.loadToDatabase();
//		dbManager.print();
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
}
