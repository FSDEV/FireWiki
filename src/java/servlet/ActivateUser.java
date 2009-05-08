package servlet;

import controller.UserController;
import db.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cmiller
 */
public class ActivateUser extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		if(s==null) {
			request.setAttribute("message", "You must log in first to activate your account!");
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}
		User u = (User)s.getAttribute("user");
		if(u==null) {
			request.setAttribute("message", "You must log in first to activate your account!");
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}

		String key = request.getParameter("activate_key");
		if(key==null) {
			request.setAttribute("activate_key", "");
			request.getRequestDispatcher("/WEB-INF/activate.jsp")
					.include(request, response);
			return;
		} else {
			UserController uc = new UserController();
			if(u.getActivationKey().equals(key)) {
				u.setActive(true);
				uc.forceUpdate(u);
				request.getRequestDispatcher("/WEB-INF/activate_success.jsp")
						.include(request, response);
			} else {
				request.setAttribute("activate_key", "");
				request.setAttribute("activate_failure", true);
				request.getRequestDispatcher("/WEB-INF/activate.jsp")
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
