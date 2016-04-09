/**
 * 
 */
package pit.kos.ejbfull.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * @author Piotr Kosmala
 *3 kwi 2016
 *11:55:46
 */
@Stateful
public class BoxWordBean implements BoxWordLocal{

	private List<String> listWord;
	@Inject @Log
	private Logger log;
	
	@PostConstruct
	@PostActivate
	private void init(){
		listWord= new ArrayList<String>();
		log.info("BoxWordBean init");
	}
	
	@PrePassivate
	@PreDestroy
	private void saveState(){
		log.info("BoxWordBean saveState");
		listWord=null;
	}
	@Remove
	public void destroy(){
		log.info("BoxWordBean destroy");
		listWord=null;
	}
	
	@Override
	public void addWords(String word) {
		log.info("BoxWordBean addWords "+word);
		listWord.add(word);
	}
	
	@Override
	public List<String> getAllWords() {
		log.info("BoxWordBean getAllWords ");
		return listWord;
	}

	
	@Override
	public void clearBox() {
		log.info("BoxWordBean clearBox ");
		init();
	}
}
