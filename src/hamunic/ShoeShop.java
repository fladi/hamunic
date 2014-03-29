/**
 *
 */
package hamunic;

import java.util.ArrayList;

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
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("Anzahl der Schuhe: " + shoes.size());
		for (Shoe shoe : shoes) {
			System.out.println(" " + shoe);
		}

	}

}
