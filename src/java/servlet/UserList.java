/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import controller.UserController;
import db.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cmiller
 */
public class UserList extends HttpServlet {
   
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
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}
		User u = (User)s.getAttribute("user");
		if(u==null) {
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		} else if(!u.isAdmin()) {
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}
		// the user is an admin if this code is executing
		UserController uc = new UserController();
		String name  = request.getParameter("userlist_name" );
		String email = request.getParameter("userlist_email");
		int start=0;
		int num=10;

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

		List<User> users = null;

		if(email==null||email.equals("")) {
			if(name==null||name.equals("")) {
				users = uc.getUsers(start, num);
			} else {
				users = uc.getUsersByName("%"+name+"%", start, num);
			}
		} else if(name==null||name.equals("")) {
			users = uc.getUsersByEmail("%"+email+"%", start, num);
		} else {
			users = uc.getUsers("%"+email+"%", "%"+name+"%", start, num);
		}

		request.setAttribute("users", users);
		request.setAttribute("userlist_name", name);
		request.setAttribute("userlist_email", email);
		request.setAttribute("start", start);
		request.setAttribute("num", num);
		request.setAttribute("totalUsers", uc.getTotalUsers());

		request.getRequestDispatcher("/WEB-INF/user_list.jsp")
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
