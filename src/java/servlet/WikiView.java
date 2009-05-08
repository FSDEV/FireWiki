package servlet;

import controller.WikiPageController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Treats a wiki page request.
 * 
 * @author cmiller
 */
public class WikiView extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		String page = request.getParameter("page");
		if(page==null) {
			page = "WikiStart";
		}
		
		WikiPageController wpc = new WikiPageController();

		if(!wpc.hasPage(page)) {
			// include the error page
			response.sendRedirect(request.getContextPath()
				+response.encodeRedirectURL("/edit")+"?page="+page);
		}
		else {
			String output = wpc.getPageHtml(page);
			// include the show-the-freaking-page page
			request.setAttribute("wikiOutput", output);
			request.getRequestDispatcher("/WEB-INF/PageView.jsp")
					.include(request, response);
		}

		wpc.end();
		
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
