package etl;

import java.awt.EventQueue;
/**
 * Klasa odpowiedzialna za uruchomienie programu.
 * Tutaj zostaje stworzony obiekt klasy ETLManager zarzadzajacy procesem ETL
 * oraz obiekt klasy GUI odpowiedzialny za okienkowa wersje aplikacji
 */
public class Main {

	/**
	 * Start programu
	 * @param args lista parametrow
	 * @throws Exception Blad polaczenia
	 */
	public static void main(String[] args) throws Exception {
//	idiki 39054626 37164441 37384661 44279808 44703567
		
		ETLManager etl = new ETLManager();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI(etl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
