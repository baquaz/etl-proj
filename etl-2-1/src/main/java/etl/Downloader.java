/**
 * 
 */
package etl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za polaczenie ze strona HTTP
 * oraz pobranie jej zawartosci i zapisanie w pamieci komputera
 */
public class Downloader {

	/**
	 * 
	 */
	private final String USER_AGENT = "Mozilla/5.0";
	
	/**
	 * Bufer przechowujacy zawartosc pobieranej strony
	 */
	protected StringBuffer response;
	
	/**
	 * Ilosc stron z komentarzami
	 */
	private int numberOfReviewPages;
	
	/**
	 * Zawartosc strony HTTP z informacjami o produkcie
	 */
	protected String productPage;
	
	/**
	 * Lista z zawartosciami stron HTTP z informacjami o opiniach
	 */
	protected ArrayList<String> reviewPages;

	/**
	 * Konstruktor
	 */
	public Downloader() {
		reviewPages = new ArrayList<String>();
	}

	/**
	 * @return the productPage
	 */
	public String getProductPage() {
		return productPage;
	}

	/**
	 * Zapisuje zawartosc pobranej strony z informacjami o produkcie
	 */
	public void saveProductPage() {
		this.productPage = response.toString();
	}

	/**
	 * @param i numer strony
	 * @return zawartosc strony o podanym numerze
	 */
	public String getReviewPages(int i) {
		return reviewPages.get(i);
	}

	/**
	 * Zapisuje zawartosc pobranej strony z opiniami
	 */
	public void saveReviewPage(){
		reviewPages.add(response.toString());
	}

	/**
	 * Pobiera zawartosc strony HTTP
	 * @param urlString adres URL 
	 */
	public void download(String urlString){

		try {
			String inputLine;
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Charset", "UTF8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();		
		}catch(Exception e1){
			GUI.printText("Niepoprawne ID produktu");
		}
	}

	/**
	 * @return the response
	 */
	public StringBuffer getResponse() {
		return response;
	}


	/**
	 * @return the numberOfReviewPages
	 */
	public int getNumberOfReviewPages() {
		return numberOfReviewPages;
	}

	/**
	 * @param numberOfReviewPages the numberOfReviewPages to set
	 */
	public void setNumberOfReviewPages(int numberOfReviewPages) {
		this.numberOfReviewPages = numberOfReviewPages; 
	}

}
