package helper;

import fscode.tags.WikiProvider;
import java.util.HashMap;
import org.xml.sax.SAXException;

/**
 *
 * @author cmiller
 */
public class MarkupHelper {

	private static HashMap<String, Object> config;

	public static synchronized StringBuilder markup(String code)
			throws SAXException {
		return new fscode.FSCode(code, getConfig()).parse().emitHtml();
	}

	private static HashMap<String, Object> getConfig() {
		if(config==null) {
			config = new HashMap<String, Object>();
			config.put("isWiki", "YES");
			config.put("lang", "en_us");
			config.put("tagset", "default_tagset");
			HashMap<String, WikiProvider> wikis
					= new HashMap<String, WikiProvider>();
			wikis.put("", new WikiProviderHelper());
			config.put("wikiProviders", wikis);
		}
		return config;
	}

}
