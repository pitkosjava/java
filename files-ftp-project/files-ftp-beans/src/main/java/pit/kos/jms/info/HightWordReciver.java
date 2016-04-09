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
@MessageDriven(name = "HightWordReciver", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/jms/queue/messageQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'HIGH'"),
		@ActivationConfigProperty(propertyName="acknowledgeMode",propertyValue="Auto-acknowledge ")})
public class HightWordReciver implements MessageListener {

	@Inject
	@Log
	private Logger log;

	public HightWordReciver() {

	}

	public void onMessage(Message message) {
		
		try {
			final String text = message.getBody(String.class);
			log.info(" JMS HightWordReciver message  " + text);
		} catch (JMSException ex) {
			log.info(ex.toString());
		}

	}

}
