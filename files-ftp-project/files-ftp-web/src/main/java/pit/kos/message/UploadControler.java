/**
 * 
 */
package pit.kos.message;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 * @author Piotr Kosmala 3 kwi 2016 20:50:16
 */
@Named
@RequestScoped
public class UploadControler {
	private Part uploadedFile;
	private String text;

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void upload() {

		if (null != uploadedFile) {
			try {
				InputStream is = uploadedFile.getInputStream();

			} catch (IOException ex) {
			}
		}
	}

}
