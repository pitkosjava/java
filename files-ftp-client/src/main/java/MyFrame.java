import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.naming.Context;
import javax.naming.InitialContext;


public class MyFrame extends JFrame {

	private JLabel jLabel = new JLabel("Photo");
	private JLabel jLabeldestination = new JLabel("Destination");
	private JComboBox<String> jdestination = new JComboBox<String>();
	private JButton jButton = new JButton("Send");
	private JButton jButtonU = new JButton("Upload");
	private PanelPhoto panelPhoto = new PanelPhoto();

	private JFrame frame;

	private File file;
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://localhost:8080";
	private static final String DEFAULT_USERNAME = "jboss";
	private static final String DEFAULT_PASSWORD = "jbossjboss1";
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/queue/example2";

	public MyFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout());
		frame = this;
		this.setSize(500, 500);
		String[] optionsdestination = { DEFAULT_DESTINATION,
				DEFAULT_DESTINATION }; // you can add more
		jdestination = new JComboBox<String>(optionsdestination);

		jpanel.add(jLabel);
		jpanel.add(jButton);
		jpanel.add(jButtonU);
		jpanel.add(jLabeldestination);
		jpanel.add(jdestination);
		this.add(jpanel, BorderLayout.NORTH);
		this.add(panelPhoto, BorderLayout.CENTER);
		this.setBounds(10, 10, 500, 400);
		this.setVisible(true);

		jButtonU.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					FileDialog fd = new FileDialog(frame, "Choose a file",
							FileDialog.LOAD);
					fd.setDirectory("C:\\");
					fd.setFile("*.jpg");
					fd.setVisible(true);
					file = new File(fd.getDirectory(), fd.getFile());
					BufferedImage bi = ImageIO.read(file);
					panelPhoto.setBufferedImage(bi);
					panelPhoto.repaint();

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});

		jButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
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
							DEFAULT_USERNAME, DEFAULT_PASSWORD);// DEFAULT_USERNAME,DEFAULT_PASSWORD
					Destination destination = (Destination) context
							.lookup((String) jdestination.getSelectedItem());
					Session session = connection.createSession(false,
							Session.AUTO_ACKNOWLEDGE);
					MessageProducer producer = session
							.createProducer(destination);
					connection.start();
					FileInputStream fis = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fis.read(data);
					StreamMessage message = session.createStreamMessage();
					message.writeInt(data.length);
					message.writeBytes(data);
					producer.send(message);
					connection.close();

				} catch (Exception as) {
					System.out.print("Blad wysylania ");
					as.printStackTrace();

				}

			}
		});

	}

	public static void main(String[] args) {
		new MyFrame();
	}

}
