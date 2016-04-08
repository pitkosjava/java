import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ReciverJMS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1375827040203696631L;
	private JComboBox<String> jdestination = new JComboBox<String>();
	private PanelPhoto panelphoto = new PanelPhoto();
	private JLabel jLabeldestination = new JLabel("Destination");
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://localhost:8080";
	private static final String DEFAULT_USERNAME = "jboss";
	private static final String DEFAULT_PASSWORD = "jbossjboss1";
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/queue/example2";
	private static final String DEFAULT_DESTINATION2 = "jms/topic/exampletopic2";
	private boolean statuson;

	public ReciverJMS() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(panelphoto, BorderLayout.CENTER);
		this.setBounds(10, 10, 500, 400);
		this.setSize(500, 500);
		this.setTitle("ReciverJMS");
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout());

		String[] optionsdestination = { DEFAULT_DESTINATION,
				DEFAULT_DESTINATION2 }; // you can add more
		jdestination = new JComboBox<String>(optionsdestination);

		jpanel.add(jLabeldestination);
		jpanel.add(jdestination);
		this.add(jpanel, BorderLayout.NORTH);
		this.setVisible(true); // (String)jdestination.getSelectedItem()
		jdestination.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runapp((String) jdestination.getSelectedItem());
			}
		});
	}

	private void runapp(String destinations) {
		if (!statuson) {
			try {
				statuson = true;
				Properties env = new Properties();

				env.put(Context.INITIAL_CONTEXT_FACTORY,
						INITIAL_CONTEXT_FACTORY);
				env.put(Context.PROVIDER_URL, PROVIDER_URL);
				env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
				env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);
				Context context = new InitialContext(env);

				ConnectionFactory connectionFactory = (ConnectionFactory) context
						.lookup(DEFAULT_CONNECTION_FACTORY);
				Connection connection = connectionFactory.createConnection(
						DEFAULT_USERNAME, DEFAULT_PASSWORD);
				Destination destination = (Destination) context
						.lookup(destinations);
				Session session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);

				MessageConsumer consumer = session.createConsumer(destination);
				consumer.setMessageListener(new MessageListener() {

					public void onMessage(Message arg0) {
						try {
							StreamMessage m = (StreamMessage) arg0;
							int lenght = m.readInt();
							byte[] data = new byte[lenght];
							m.readBytes(data);
							ByteArrayInputStream bais = new ByteArrayInputStream(
									data);
							BufferedImage bi = ImageIO.read(bais);
							panelphoto.setBufferedImage(bi);
							panelphoto.repaint();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				connection.start();
			} catch (Exception as) {
				as.printStackTrace();

			}
		}

	}

	public static void main(String[] args) {
		new ReciverJMS();
	}

}
