package db;

import java.util.List;

/**
 *
 * @author cmiller
 */
public class WikiPage {

	private int pageId;
	private String path;
	private List<String> cachedOutput;
	private List<String> markupSource;

	private void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPageId() {
		return this.pageId;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}

	public void setCachedOutput(List<String> cachedOutput) {
		this.cachedOutput = cachedOutput;
	}
	public List<String> getCachedOutput() {
		return this.cachedOutput;
	}

	public void setMarkupSource(List<String> markupSource) {
		this.markupSource = markupSource;
	}
	public List<String> getMarkupSource() {
		return this.markupSource;
	}
}
