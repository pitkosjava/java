/**
 * 
 */
package pit.kos.ejb.message;

import java.util.List;

/**
 * @author Piotr Kosmala
 *3 kwi 2016
 *11:44:41
 */
public interface MessageBasic {
	boolean sendMessage(String message);
	List<String> getAllMessage();
}
