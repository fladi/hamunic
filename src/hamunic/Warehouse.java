package hamunic;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Properties;

public class Warehouse {

	private final String shoeData = "shoes.dat";
	
	private final String priceListData = "priceList.dat";
	
	
	public <C extends Collection<Shoe>> C  getAllShoes(C collection) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(shoeData));
		try {
			Shoe shoe;
			while((shoe = (Shoe)in.readObject()) != null) {
				collection.add(shoe);
			}
		} catch (EOFException e) {
			// end of stream reached
		} finally {
			in.close();
		}
		return collection;
	}
	
	
	public Properties getPriceList() throws IOException {
		Properties priceList = new Properties();
		InputStream priceStream = new FileInputStream(priceListData);
		priceList.load(priceStream);
		priceStream.close();
		return priceList;
	}
}
