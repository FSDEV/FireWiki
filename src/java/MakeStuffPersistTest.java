
import controller.WikiPageController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cmiller
 */
public class MakeStuffPersistTest {

	public static void main(String[] args) {
		
		WikiPageController wpc = new WikiPageController();

		String page = "foo";

		StringBuilder originalText = new StringBuilder();
		try {
			FileReader fr = new FileReader("textMarkup.fscode.xml");
			while(fr.ready())
					originalText.append((char)fr.read());
		} catch (FileNotFoundException ex) {
			Logger.getLogger(MakeStuffPersistTest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException e) {
			
		}

		wpc.setPageMarkup(page, new String(originalText.toString()));

		String markup = wpc.getPageMarkup(page);

		if(!markup.equalsIgnoreCase(originalText.toString())) {
			System.out.println("======= MAJOR PROBLEM, APPARENTLY THE DB IS MANGLING THE TEXT!!!");
			if(markup.length()!=originalText.length()) {
				System.out.println("markup.length = " + markup.length()
						+ "\noriginaltext.length = " + originalText.length());
			}
		}

		System.out.println("========= MARKUP =========");
		System.out.println(markup);

		System.out.println("========== HTML ==========");
		System.out.println(wpc.getPageHtml(page));

	}

}
