package pit.kos.jms.info;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * Message-Driven Bean implementation class for: ImagesReciver ta zdefiniowana
 * na servwerze
 */
//java:jboss/jms/queue/ImageQueue  java:/jms/queue/examplee
@MessageDriven(name = "ImagesReciver", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/example2"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge ") })
public class ImagesReciver implements MessageListener {

	@Inject
	@Log
	private Logger log;
	
	@Inject
	private ImageQueueProducer producerimage;

	public ImagesReciver() {

	}

	public void onMessage(Message message) {
		log.info("Message ImagesReciver from java:jboss/exported/jms/queue/example2");
		try {
			StreamMessage m = (StreamMessage) message;
			
			String photonumber = "test";// m.readString();
			int lenght = m.readInt();
			byte[] data = new byte[lenght];
			m.readBytes(data);
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			BufferedImage bi = ImageIO.read(bais);
			File outputfile = new File("H:\\imagejms-queue" + photonumber
					+ ".jpg");
			ImageIO.write(bi, "jpg", outputfile);
			producerimage.sendMessage(bi);
		} catch (Exception e) {
			log.info("", e);
		}

	}

}
