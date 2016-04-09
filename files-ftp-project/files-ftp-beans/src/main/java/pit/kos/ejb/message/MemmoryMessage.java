/**
 * 
 */
package pit.kos.ejb.message;

import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;
import pit.kos.jms.info.Priority;
import pit.kos.jms.info.StringCompletionListener;
import pit.kos.jms.info.StringQueueProducer;

/*This eager initialization is triggered*/
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MemmoryMessage implements Serializable {

	/**
	 * 
	 */
	private boolean PRIORITY;
	private static final int MAX_MESSGAE = 300;

	private static final long serialVersionUID = 7528858194577434584L;

	private List<String> listMessage;

	@Inject
	private StringQueueProducer queueProducer;

	@Inject
	@Log
	private Logger log;

	@PostConstruct
	private void init() {
		listMessage = new ArrayList<String>();
	}

	/*
	 * In this example we denote that the read operation should wait on an
	 * available write lock a maximum of 15 seconds before giving up and
	 * returning a javax.ejb. ConcurrentAccessTimeoutException.
	 */
	@Lock(LockType.WRITE)
	@AccessTimeout(value = 15, unit = java.util.concurrent.TimeUnit.SECONDS)
	public void addMessage(String message) {
		listMessage.add(message);
		log.info("EJB Singleton: MemmoryMessage" + message);
		if (listMessage.size() > MAX_MESSGAE) {
			init();
		}
		PRIORITY=!PRIORITY;
		if(PRIORITY){
			queueProducer.sendMessage(message, Priority.HIGH);
		}
		else {
			queueProducer.sendMessage(message, Priority.LOW);
		}
		
	}

	@Lock(LockType.READ)
	public List<String> getAllMessage() {
		return Collections.unmodifiableList(listMessage);
	}

}
