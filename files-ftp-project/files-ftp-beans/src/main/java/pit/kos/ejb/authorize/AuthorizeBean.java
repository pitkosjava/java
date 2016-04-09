package pit.kos.ejb.authorize;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import pit.kos.ejb.utils.Log;

/**
 * Session Bean implementation class AuthorizeBean SessionContext allows you to
 * obtain information such as the current user that is invoking on the EJB, or
 * to look up entries within the EJB’s Enterprise Naming Context (ENC). Let’s
 * look at the javax.ejb.SessionContext interface:
 */
@Stateless(mappedName = "AuthorizeBean")
public class AuthorizeBean implements AuthorizeBeanRemote, AuthorizeBeanLocal {
	
	@Inject @Log
	private Logger log;
	/**
	 * Passphrase to use for the key in cipher operations; lazily initialized
	 * and loaded via SessionContext.lookup
	 */
	private String ciphersPassphrase;
	/**
	 * Algorithm to use in message digest (hash) operations, injected via @Resource
	 * annotation
	 */
	
	@Resource
	private String messageDigestAlgorithm;

	@Resource
	private SessionContext context;

	public AuthorizeBean() {
		
	}

	@PostConstruct
	private void init() {
		getEnvironmentEntryAsString();
		log.info("messageDigestAlgorithm is "+messageDigestAlgorithm+" ciphersPassphrase is "+ciphersPassphrase);
	}

	@Override
	public boolean authorize(String password, String user) {
		if("kosmala".equals(password) && "piotr".equals(user)){
			return true;
		}
		
		return false;
	}

	private String getEnvironmentEntryAsString() {

		String envEntryName="ciphersPassphrase";
		
		// Get the SessionContext from the injected instance member
		final SessionContext context = this.context;
		// Lookup in the Private JNDI ENC via the injected SessionContext
		Object lookupValue = null;
		try {
			lookupValue = context.lookup(envEntryName);
			log.info("Obtained environment entry \"" + envEntryName + "\": "
					+ lookupValue);
		} catch (final IllegalArgumentException iae) {
			// Not found defined within this EJB's Component Environment,
			// so return null and let the caller handle it
			log.warn("Could not find environment entry with name: "
					+ envEntryName);
			return null;
		}
		// Cast
		String returnValue = null;
		try {
			returnValue = String.class.cast(lookupValue);
		} catch (final ClassCastException cce) {
			IllegalStateException ie= new IllegalStateException("The specified environment entry, "
					+ lookupValue + ", was not able to be represented as a "
					+ String.class.getName(), cce);
			log.warn("",ie);
		}
		return returnValue;
	}

}
/***
 * Two of these properties, ciphersPassphrase and messageDigestAlgorithm,
 * represent configurable attributes that we’ll provide via externalized
 * variables called environment entries. An environment entry is a value placed
 * into JNDI under a private namespace called the Enterprise Naming Context
 * (ENC), which is visible only to our EJB. There are a variety of ways to get
 * at the values within the ENC, and this example will illustrate both manual
 * lookup and injection. The messageDigestAlgorithm is annotated with @Resource;
 * this tells the EJB container that when an instance of the bean class is
 * created, this field must be initialized with values in the container’s ENC.
 * When the EJB container is deployed, the ENC is populated both with metadata
 * embedded in annotations such as @Resource and with information stored in the
 * optional EJB XML deployment descriptor. For example, the annotation alone
 * suggests a named value within the ENC should be used to initialize the field
 * externally. This named value can be configured by defining environment
 * entries within the EJB’s XML deployment
 **/
