package pit.kos.ejb.message;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Session Bean implementation class Message
 */
@Stateless
public class Message implements MessageRemote, MessageLocal {

	@Inject
	private MemmoryMessage messageMemmory;
	
    public Message() {
       
    }
	
	@Override
	public boolean sendMessage(String message) {
		messageMemmory.addMessage(message);
		return true;
	}
	
	@Override
	public List<String> getAllMessage() {
		return messageMemmory.getAllMessage();
	}
	

}
