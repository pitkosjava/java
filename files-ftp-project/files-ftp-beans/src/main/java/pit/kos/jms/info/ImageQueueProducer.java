/**
 * 
 */
package pit.kos.jms.info;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.StreamMessage;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * @author Piotr Kosmala 9 kwi 2016 01:18:38
 */
@Named
@ApplicationScoped
public class ImageQueueProducer implements Serializable {

	private static final long serialVersionUID = -2151101938582409187L;

	@Inject
	@Log
	private Logger log;

	@Inject
	private JMSContext context;

	@Resource(mappedName = "java:jboss/exported/jms/queue/example")
	private Queue syncQueue;

	public void sendMessage(BufferedImage imagerec) {
		try {
			
			BufferedImage bufferedImage =imagerec;
			/* add watermark*/
			Graphics graphics = bufferedImage.getGraphics();
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            String watermark = "Piotr Kosmala";
            graphics.drawString(watermark, 0, bufferedImage.getHeight() / 2);
            graphics.dispose();
            /* convert to byte*/
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( bufferedImage, "jpg", baos );
            baos.flush();
        	byte[] imageInByte = baos.toByteArray();
        	baos.close();
        	/* create new message*/
            StreamMessage message = context.createStreamMessage();
			message.writeInt(imageInByte.length);
			message.writeBytes(imageInByte);
			/* send*/
			log.info("JMS ImageQueueProducer to java:jboss/exported/jms/queue/example");
			context.createProducer().send(syncQueue, message);
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
