package etl;

import java.sql.Date;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Klasa przechowuje dane dotyczace pojedynczej opini
 * oraz metody do parsowania
 */
public class Review{
	
	/**
	 * Tresc opini
	 */
	private String reviewContent;
	
	/**
	 * Wady i zalety
	 */
	private String pros, cons;
	
	/**
	 * Liczba gwiazdek
	 */
	private int numberOfStars;
	
	/**
	 * Autor
	 */
	private String author;
	
	/**
	 * Data
	 */
	private Date date;
	
	/**
	 * Polecam / Nie polecam
	 */
	private boolean isRecomended;
	
	/**
	 * Ilosc osob ktora uznala opinie za przydatna
	 */
	private int numberOfPositiveVotes;
	
	/**
	 * Ilosc osob ktora uznala opinie za nieprzydatna
	 */
	private int numberOfNegativeVotes;
	
	/**
	 * Unikalny nr ID opini
	 */
	private String id;

	/**
	 * Fragment kodu html zawierajacy wszystkie informacje dotyczace danej opini
	 */
	private Element box;
	
	/**
	 * Konstruktor
	 * @param box nieprzeparsowana opinia
	 */
	public Review(Element box){
		this.box = box;	
		reviewContent = "";	
		pros = "";
		cons = "";
		numberOfStars = 0;
		author = "";
		date = null;
		isRecomended = false;
		numberOfPositiveVotes = 0;
		numberOfNegativeVotes = 0;
		id = "";
	}
	
	
	/**
	 * Wydobywa ID opini i zapisuje do zmiennej
	 */
	public void parseId(){
		Element attribute = box.getElementsByAttribute("data-review-id").first();
		id = attribute.attr("data-review-id");		
	}
	
	/**
	 * Wydobywa tresc opini i zapisuje do zmiennej
	 */
	public void parseReviewContent(){		
		Element review =  box.getElementsByClass("product-review-body").first();
		reviewContent = review.text().replaceAll("'", "\"");		
	}
	
	
	/**
	 * Wydobywa zalety opini i zapisuje do zmiennej
	 */
	public void parseGoodness(){
		pros = "";
		Elements tmp = box.getElementsByClass("pros");
		
		if(!tmp.isEmpty()){			
			Elements ul = tmp.get(0).nextElementSibling().children();
			for (int i = 0; i < ul.size()-1; i++) {								
				pros = pros.concat(ul.get(i).text()).concat(", ");
			}
			pros = pros.concat(ul.get(ul.size()-1).text());
		}		
	}
	
	/**
	 * Wydobywa wady opini i zapisuje do zmiennej
	 */
	public void parseWeakness(){
		cons = "";
		Elements tmp = box.getElementsByClass("cons");
		
		if(!tmp.isEmpty()){
			Elements ul = tmp.get(0).nextElementSibling().children();
						
			for (int i = 0; i < ul.size()-1; i++) {
				cons = cons.concat(ul.get(i).text()).concat(", ");
			}
			cons = cons.concat(ul.get(ul.size()-1).text());
	
		}		
	}
	
	/**
	 * Wydobywa ocene opini i zapisuje do zmiennej
	 */
	public void parseStars(){
		numberOfStars = Integer.parseInt(box.getElementsByClass("review-score-count").text().substring(0, 1));
	}
	
	/**
	 * Wydobywa autora opini i zapisuje do zmiennej
	 */
	public void parseAuhtor(){
		String tmp = box.getElementsByClass("product-reviewer").text();
		if(tmp.contains("ytkownik Ceneo"))
			author = "Anonim";
		else
			author = tmp;		
	}
	
	/**
	 * Wydobywa date dodania opini i zapisuje do zmiennej
	 */
	public void parseDate(){		
		String tmp = box.getElementsByAttribute("datetime").attr("datetime").substring(0, 10);
		date = Date.valueOf(tmp);
	}
	
	/**
	 * Wydobywa informacje czy autor opini poleca produkt
	 */
	public void parseRecomend(){
		try {
			if(box.getElementsByClass("product-review-summary").get(0).child(0).text().equals("Polecam"))
				isRecomended = true;
			else
				isRecomended = false;
		} catch (Exception e) {
			System.out.println("hello world");
			e.printStackTrace();
		}
	}
	
	/**
	 * Wydobywa ilosc osob ktore uznaly opinie za przydatna, a ile za nieprzydatna
	 */
	public void parseVotes(){
		numberOfPositiveVotes = Integer.parseInt(box.getElementsByClass("js_product-review-usefulness vote").get(0).child(0).child(0).text());
		numberOfNegativeVotes = Integer.parseInt(box.getElementsByClass("js_product-review-usefulness vote").get(0).child(1).child(0).text());
	}
	
	/**	  
	 * @return the reviewContent
	 */
	public String getReviewContent() {
		return reviewContent;
	}

	/**
	 * @return the goodness
	 */
	public String getPros() {
		return pros;
	}

	/**
	 * @return the weakness
	 */
	public String getCons() {
		return cons;
	}

	/**
	 * @return the nrOfStars
	 */
	public int getNumberOfStars() {
		return numberOfStars;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return the recomend
	 */
	public boolean isRecomended() {
		return isRecomended;
	}
	
	/**
	 * @return the numberOfUsefullVotes
	 */
	public int getNumberOfPositiveVotes() {
		return numberOfPositiveVotes;
	}

	/**
	 * @return the numberOfUselessVotes
	 */
	public int getNumberOfNegativeVotes() {
		return numberOfNegativeVotes;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
}
