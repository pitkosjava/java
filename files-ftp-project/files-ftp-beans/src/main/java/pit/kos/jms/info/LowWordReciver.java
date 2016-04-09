package pit.kos.jms.info;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * Message-Driven Bean implementation class for: ImagesReciver
 */
@MessageDriven(name = "LowWordReciver", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/jms/queue/messageQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'LOW'"),
		@ActivationConfigProperty(propertyName="acknowledgeMode",propertyValue="Auto-acknowledge ")})
public class LowWordReciver implements MessageListener {

	@Inject
	@Log
	private Logger log;

	public LowWordReciver() {

	}

	public void onMessage(Message message) {
		
		try {
			final String text = message.getBody(String.class);
			log.info(" JMS LowWordReciver message  " + text);
		} catch (JMSException ex) {
			log.info(ex.toString());
		}

	}

}
