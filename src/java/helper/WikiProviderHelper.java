/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import fscode.tags.WikiProvider;
import controller.WikiPageController;

/**
 *
 * @author cmiller
 */
public class WikiProviderHelper implements WikiProvider {

	private WikiPageController wpc;

	public WikiProviderHelper() {
		wpc = new WikiPageController();
	}

	@Override
	public boolean hasPage(String page) {
		return wpc.hasPage(page);
	}

	@Override
	public String getUrlForPage(String page) {
		return "wiki?page="+page;
	}

}
