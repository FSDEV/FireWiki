package servlet;

import controller.WikiPageController;
import db.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.SAXException;

/**
 *
 * @author cmiller
 */
public class WikiEdit extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String page = null;
		String newPage = null;
		String editText = null;
		boolean preview = false;
		boolean save = false;
		boolean delete = false;
		page = request.getParameter("page");
		newPage = request.getParameter("newPage");
		editText = request.getParameter("editText");
		preview = (request.getParameter("preview")==null)?false:true;
		save = (request.getParameter("save")==null)?false:true;
		delete = (request.getParameter("delete")==null)?false:true;

		if(page == null) {
			request.getRequestDispatcher("/WEB-INF/Error404.jsp")
					.include(request, response);
			return;
		}
		if(request.getSession(false)==null||
				!((User)request.getSession().getAttribute("user")).isActive()) {
			request.setAttribute("message","You must log in to edit pages!");
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}

		request.setAttribute("newPage", page);

		WikiPageController wpc = new WikiPageController();

		if(delete!=false) {
			// show successful deletion page
			response.sendRedirect(request.getContextPath()
					+response.encodeRedirectURL("/wiki"));

			return;
		}
		if(editText!=null&&save==true) {
			log("edit text isn't null, save is true");

			if(!page.equals(newPage)) {
				wpc.movePage(wpc.getPageForPath(page), newPage);
				page = newPage;
			}

			wpc.setPageMarkup(page, editText);

			request.setAttribute("wikiOutput",wpc.getPageHtml(page));

			// TODO: include a wee blurb affiriming that the page was
			// changed
			request.setAttribute("view_changed", true);

			response.sendRedirect(request.getContextPath()
					+response.encodeRedirectURL("/wiki")+"?page="+page);

			return;
		}

		if(wpc.hasPage(page)) {
			log("has page");
			log("edit text is null or is empty");
			if(preview==false) {
				log("preview is false");
				request.setAttribute("editText", wpc.getPageMarkup(page)
						.replaceAll("&", "&amp;"));
			}
			else {
				log("preview is true");
				request.setAttribute("editText", editText
						.replaceAll("&", "&amp;"));
			}
			request.getRequestDispatcher("/WEB-INF/edit.jsp")
					.include(request, response);
		} else {
			log("doesn't have page");
			if(editText==null||editText.isEmpty())
				request.setAttribute("editText", "Your page goes here.");
			else
				request.setAttribute("editText", editText
						.replaceAll("&", "&amp;"));
			request.getRequestDispatcher("/WEB-INF/edit.jsp")
					.include(request, response);
		}
		if(preview) {
			try {
				request.setAttribute("previewOutput",
						helper.MarkupHelper.markup("<fscode>"+editText
						+"</fscode>").toString());
			} catch (SAXException ex) {
				request.getRequestDispatcher("/WEB-INF/preview_fail.jsp")
						.include(request, response);
				return;
			} finally {
				request.getRequestDispatcher("/WEB-INF/preview.jsp")
						.include(request, response);
			}
		}
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
