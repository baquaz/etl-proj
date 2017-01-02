/**
 * 
 */
package etl;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Klasa przechowuje przeparsowane informacje o produkcie razem z opiniami
 * oraz metody do parsowania
 *
 */
public class Product{

	/**
	 * Obiekt odpowiedzialny za polaczenie ze strona HTTP i pobranie jej zawartosci
	 */
	private Downloader downloader;
	
	/**
	 * Link URL do produktu
	 */
	private String baseLink;
	
	/**
	 * Lista obiektow z danymi dotyczacymi opini
	 */
	private ArrayList<Review> reviews;
	
	/**
	 * Strona html po przeparsowaniu
	 */
	private Document doc;
	
	/**
	 * Przechowuje wszystkie informacje dotyczace wszystkich opini z jednej strony
	 */
	private Element ol;
	
    /**
     * Przechowuje wszystkie informacje dotyczace pojedynczej opini
     */
    private Elements boxes;
		
	/**
	 * Ilosc wszystkich opini
	 */
	private int numberOfReviews;
	
	/**
	 * Unikalny nr ID produktu
	 */
	private String productId;
	
	/**
	 * Model
	 */
	private String model;
	
	/**
	 * Dodatkowe informacje
	 */
	private String extraInfo;
	
	/**
	 * Kategoria
	 */
	private String category;
	
	/**
	 * Marka
	 */
	private String brand;
	
	/**
	 * Licznik opini
	 */
	private int counter;

	/**
	 * Konstruktor
	 * @param productId ID produktu
	 */
	public Product(String productId) {
		//productId = "";
		model = "";
		baseLink = "";
		extraInfo= "";
		category = "";
		brand = "";
		reviews = new ArrayList<Review>();
		downloader = new Downloader();
		this.productId = productId;				
		buildBaseLink();
		counter = 0;
	}
	
	/**
	 * Pobiera zawartosc strony HTTP z informacjami o produkcie
	 */
	public void downloadProductPage(){
		try {
			downloader.download(baseLink);
			counter++;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		downloader.saveProductPage();		
	}
	
	/**
	 * Pobiera zawartosc stron HTTP z opiniami
	 */
	public void downloadReviewPages(){
		
		int n=1;
		boolean isNext = true;
		while(isNext){
			try {
				downloader.download(baseLink.concat("/opinie-").concat(Integer.toString(n)));			
				downloader.saveReviewPage();
				counter++;
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
			if(Jsoup.parse(downloader.getResponse().toString()).getElementsByClass("page-arrow arrow-next").size()!=0){
				n++;				
			}
			else{
				isNext = false;
			}
		}
		downloader.setNumberOfReviewPages(n);		
	}
	
	/**
	 * Tworzy link do strony HTTP
	 */
	public void buildBaseLink(){
		String ceneoURL = "http://ceneo.pl/";
		baseLink = ceneoURL.concat(productId);
	}

	
	/**
	 * Wydobywa z kazdej zapisanej strony tylko ta czesc, ktora zawiera
	 * informacje o opiniach
	 */
	public void createReviewsArray(){ 
		
		for(int i=0; i<downloader.getNumberOfReviewPages(); i++){
			doc = Jsoup.parse(downloader.getReviewPages(i));
			ol = doc.getElementsByClass("product-reviews js_product-reviews js_reviews-hook").first();
			boxes = ol.getElementsByClass("product-review js_product-review");
			
			for (int j = 0; j < boxes.size(); j++) {
				reviews.add(new Review(boxes.get(j)));				
			}
		} 
		
		numberOfReviews = (downloader.getNumberOfReviewPages()-1)*10 + boxes.size();
	}
	
		
	/**
	 * Parsuje strone z informacjami o produkcie, wydobywa z niej dane
	 * i zapisuje do odpowiednich zmiennych
	 */
	public void parseProduct(){
		  doc = Jsoup.parse(downloader.getProductPage());
		  Elements e = doc.getElementsByClass("js_searchInGoogleTooltip");
		  model = e.first().text();
		  extraInfo = doc.getElementsByClass("ProductSublineTags").get(0).text();
		  category = doc.getElementsByAttribute("data-category-id").last().child(0).text();
		  brand = doc.getElementsByAttributeValue("property", "og:brand").attr("content");
	}
	
	/**
	 * Parsuje wszystkie opinie
	 */
	public void parseReviews(){
		for (int i = 0; i < numberOfReviews; i++) {
			reviews.get(i).parseReviewContent();
			reviews.get(i).parseGoodness();
			reviews.get(i).parseWeakness();
			reviews.get(i).parseStars();
			reviews.get(i).parseAuhtor();
			reviews.get(i).parseDate();
			reviews.get(i).parseRecomend();
			reviews.get(i).parseVotes();
			reviews.get(i).parseId();
		}
	}
	
	
	
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param i Numer strony
	 * @return The review
	 */
	public Review getReviews(int i) {
		return reviews.get(i);
	}
	

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the extraInfo
	 */
	public String getExtraInfo() {
		return extraInfo;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * @return Number of reviews
	 */
	public int getNumberOfReviews() {
		return numberOfReviews;
	}

	/**
	 * @return counter
	 */
	public int getCounter() {
		return counter;
	}
	
}
