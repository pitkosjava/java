/**
 * 
 */
package pit.kos.jms.info;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.CompletionListener;
import javax.jms.Message;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * @author Piotr Kosmala 3 kwi 2016 16:00:30
 */
@Named
@ApplicationScoped
public class StringCompletionListener implements CompletionListener {

	@Inject
	@Log
	private Logger log;

	@Override
	public void onCompletion(Message message) {
		try {
			final String text = message.getBody(String.class);
			log.info("Send was successful: " + text);
		} catch (Throwable e) {
			log.error("Problem with message format");
		}
	}

	@Override
	public void onException(Message message, Exception arg1) {
		try {
			final String text = message.getBody(String.class);
			log.info("Send failed " + text);
		} catch (Throwable e) {
			log.warn("Problem with message format");
		}
	}

}
