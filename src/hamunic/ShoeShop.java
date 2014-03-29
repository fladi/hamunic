/**
 *
 */
package hamunic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Stack;

/**
 * @author FladischerMichael
 *
 */
public class ShoeShop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Warehouse wh = new Warehouse();
		ArrayList<Shoe> shoes = new ArrayList<Shoe>();
		try {
			shoes = wh.getAllShoes(shoes);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// Preisliste aus Warehouse laden.
		Properties priceList;
		try {
			priceList = wh.getPriceList();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// Arbeiter 1 stapelt die Schuhe übereinander.
		Stack<Shoe> stackedShoes = new Stack<Shoe>();
		for (Shoe shoe : shoes) {
			// Jedes Paar Schuhe wird oben auf den Stapel gelegt.
			stackedShoes.push(shoe);
		}

		// Arbeiter 2 nimmt die Schuhe von Stapel und ermittelt den Preis.
		// Zuerst brauchen wir die LinkedList, in der wir dann alle Schuhe mit den jeweiligen PReisen ablegen wollen.
		LinkedList<Shoe> pricedShoes = new LinkedList<Shoe>();
		// Wir durchlaufen diese while-Schleife solange der stapel nicht leer ist.
		while (!stackedShoes.empty()) {
			// Das oberste Paar Schuhe vom Stapel nehmen.
			Shoe shoe = stackedShoes.pop();
			// Aus der Preisliste den Preis der Schuhe anhand ihres Herstellers und Namens holen.
			String price = priceList.getProperty(shoe.getLabel() + "." + shoe.getName());
			// Wass soll passieren, wenn die Schuhe nicht in der Preisliste stehen?
			// Wir ignorieren sie einfach, fügen sie nicht in die LinkedList ein und machen mit dem nächsten Paar Schuhe weiter.
			if (price != null) {
				//Es kann passieren, dass in der Preisliste kein Geldbetrag hinterlegt ist, was dann?
				try {
					shoe.setPrice(Float.parseFloat(price));
				} catch (NumberFormatException e) {
					continue;
				}
				// Die Schuhe haben einen gültigen Preis, wir können sie an die LinkedList anfügen.
				pricedShoes.add(shoe);
			}
		}

	}

}
