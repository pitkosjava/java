/**
 * 
 */
package pit.kos.ejb.authorize;

/**
 * @author Piotr Kosmala
 *1 kwi 2016
 *20:03:39
 */
public interface AuthorizeBasicIntrfaces {
	boolean authorize(String password,String user);
}
