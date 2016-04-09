/**
 * 
 */
package pit.kos.message;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import pit.kos.ejb.message.MessageLocal;
import pit.kos.ejbfull.services.BoxWordLocal;

/**
 * @author Piotr Kosmala 3 kwi 2016 12:32:08
 */
@Named
@SessionScoped
public class MessageControler implements Serializable,
		MessageControleInterfaces {

	private static final long serialVersionUID = -4522905112438600090L;

	@EJB
	private MessageLocal messageBean;

	@EJB
	private BoxWordLocal boxWord;

	private String message;
	
	private String messageBox;
	
	@Override
	public void addMessage() {
		messageBean.sendMessage(message);
		setMessage("");
	}

	@Named
	@Produces
	@Override
	public List<String> getAllMessage() {
		return messageBean.getAllMessage();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void addToBox() {
		boxWord.addWords(getMessageBox());
		setMessageBox("");
	}

	@Named
	@Produces
	public List<String> getAllBox() {
		return boxWord.getAllWords();
	}

	public void clearBox() {
		boxWord.clearBox();
	}

	public String getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(String messageBox) {
		this.messageBox = messageBox;
	}
	

}
