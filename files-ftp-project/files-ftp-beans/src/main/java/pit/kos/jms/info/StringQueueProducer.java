/**
 * 
 */
package pit.kos.jms.info;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * @author Piotr Kosmala 3 kwi 2016 14:47:54
 */
@Named
@ApplicationScoped
public class StringQueueProducer implements Serializable {
	@Inject
	@Log
	private Logger log;

	@Inject
	private StringCompletionListener listner;

	private static final long serialVersionUID = -8316442083625011199L;
	@Inject
	private JMSContext context;
	@Resource(mappedName = "java:jboss/jms/queue/messageQueue")
	private Queue syncQueue;

	public void sendMessage(String txt, Priority priority) {
		log.info("JMS StringQueueProducer" + txt);
		context.createProducer().setAsync(listner)
		.setProperty("priority", priority.toString())
		.send(syncQueue, txt);
	}
}

