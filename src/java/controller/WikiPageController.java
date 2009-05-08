package controller;

import db.HibernateUtil;
import db.WikiPage;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xml.sax.SAXException;

/**
 *
 * @author cmiller
 */
public class WikiPageController {

	private Session s;

	private static final int STRING_LENGTH  =255;

	public WikiPageController() {
		s = HibernateUtil.getSessionFactory().openSession();
	}

	public void end() {
		s.flush();
	}

	public List<WikiPage> getWikiPages() {
		Transaction tx0 = s.beginTransaction();
		List<WikiPage> toReturn = s.createQuery("from WikiPage").list();
		tx0.commit();
		return toReturn;
	}

	public List<WikiPage> getWikiPages(int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<WikiPage> toReturn = s.createQuery("from WikiPage order by upper(path) asc")
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public List<WikiPage> getWikiPages(String searchTerm) {
		Transaction tx0 = s.beginTransaction();
		List<WikiPage> toReturn = s.createQuery(
				"from WikiPage as page where page.path like :searchTerm order by upper(path) asc")
				.setString("searchTerm", searchTerm).list();
		tx0.commit();
		return toReturn;
	}

	public List<WikiPage> getWikiPages(String searchTerm, int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<WikiPage> toReturn = s.createQuery(
				"from WikiPage as page where page.path like :searchTerm order by upper(path) asc")
				.setString("searchTerm", searchTerm)
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public long getTotalPages() {
		Transaction tx0 = s.beginTransaction();
		long toReturn = ((Long)s.createQuery(
				"select count(*) from WikiPage as page").iterate()
				.next()).intValue();
		tx0.commit();
		return toReturn;
	}

	public WikiPage getPageForPath(String path) {
		Transaction tx0 = s.beginTransaction();
		WikiPage toReturn = (WikiPage)s.createQuery(
				"from WikiPage where path = :path")
				.setParameter("path", path).uniqueResult();
		tx0.commit();
		return toReturn;
	}

	public String getPageMarkup(String path) {
		WikiPage wpage = getPageForPath(path);
		if(wpage == null)
			return null;
		return mergeString(wpage.getMarkupSource());
	}

	public WikiPage createPage(String path) {
		WikiPage wpage = getPageForPath(path);
		if(wpage==null) {
			wpage = new WikiPage();
			wpage.setPath(path);
			Transaction tx0 = s.beginTransaction();
			s.save(wpage);
			s.flush();
			tx0.commit();
		}
		return wpage;
	}

	public void setPageMarkup(String path, String markup) {
		WikiPage wpage = getPageForPath(path);
		if(wpage == null) {
			wpage = createPage(path);
		}
		Transaction tx0 = s.beginTransaction();
		wpage.setMarkupSource(cutString(markup, STRING_LENGTH));
		wpage.setCachedOutput(new LinkedList<String>());
		s.saveOrUpdate(wpage);
		tx0.commit();
	}

	public void setPageHtml(String path, String html) {
		WikiPage wpage = getPageForPath(path);
		if(wpage == null) {
			return;
		}
		Transaction tx0 = s.beginTransaction();
		wpage.setCachedOutput(cutString(html, STRING_LENGTH));
		s.saveOrUpdate(wpage);
		tx0.commit();
	}

	public String getPageHtml(String path) {
		WikiPage wpage = getPageForPath(path);

		if(wpage == null)
			return "no such page";
		else if(wpage.getCachedOutput().isEmpty()) {
			String markup = getPageMarkup(path);
			try {

				String output = helper.MarkupHelper.markup("<fscode>"+
						new String(markup)+"</fscode>").toString();

				setPageHtml(path, output);

				return output;
			} catch (SAXException ex) {
				Logger.getLogger(WikiPageController.class.getName())
						.log(Level.SEVERE, null, ex);
				return "error displaying page!";
			}
		}
		else
			return mergeString(wpage.getCachedOutput());
	}

	private List<String> cutString(String str, int length) {
		LinkedList<String> toReturn = new LinkedList<String>();
				//(int)Math.ceil((str.length()%length)));
		
		String tmp = new String(str);
		while(tmp.length()>0) {
			toReturn.add(tmp.substring(0,
					(tmp.length()>=length)?length:tmp.length()
					));
			tmp = tmp.substring((tmp.length()>=length)?
				length:tmp.length());
		}

		return toReturn;
	}

	private String mergeString(List<String> arr0) {
		StringBuilder sb = new StringBuilder();

		for(String str:arr0)
			sb.append(str);
		
		return sb.toString();
	}

	public boolean hasPage(String path) {
		return getPageForPath(path)!=null;
	}

}
