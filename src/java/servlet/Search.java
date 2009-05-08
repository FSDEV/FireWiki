package servlet;

import controller.WikiPageController;
import db.WikiPage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cmiller
 */
public class Search extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String searchTerm;
		int start=0;
		int num=10;

		searchTerm=request.getParameter("searchTerm");
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch(NumberFormatException e) {
			start=0;
		}
		try {
			num = Integer.parseInt(request.getParameter("num"));
		} catch(NumberFormatException e) {
			num=10;
		}

		WikiPageController wpc = new WikiPageController();

		List<WikiPage> pages;

		if(searchTerm==null||searchTerm.isEmpty()) {
			pages = wpc.getWikiPages(start, num);
			log("searching via keyword");
		}
		else {
			pages = wpc.getWikiPages("%"+searchTerm+"%", start, num);
			log("searching without keyword");
		}

		request.setAttribute("pages", pages);
		request.setAttribute("searchTerm", searchTerm);
		request.setAttribute("start", start);
		request.setAttribute("num", num);
		request.setAttribute("totalPages", wpc.getTotalPages());

		request.getRequestDispatcher("/WEB-INF/page_list.jsp")
				.include(request, response);
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
