/**
 * 
 */
package pit.kos.ejbfull.services;

import java.util.List;

import javax.ejb.Local;

/**
 * @author Piotr Kosmala
 *3 kwi 2016
 *11:56:13
 */
@Local
public interface BoxWordLocal {
	void addWords(String word);
	List<String> getAllWords();
	void clearBox();

}
