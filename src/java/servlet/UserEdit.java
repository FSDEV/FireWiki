package servlet;

import controller.UserController;
import db.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cmiller
 */
public class UserEdit extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		User currentUser = (request.getSession(false) == null) ? null
				:(User)request.getSession().getAttribute("user");
		boolean notAnAdmin = true;
		if(currentUser==null)
			notAnAdmin = true;
		else if(currentUser.isAdmin())
			notAnAdmin = false;
        if(notAnAdmin) {
			request.getRequestDispatcher("/WEB-INF/403_forbidden.jsp")
					.include(request, response);
			return;
		}
		
		int userId = 0;
		if(request.getParameter("id")==null) {
			response.sendRedirect(request.getContextPath()
					+response.encodeRedirectURL("/userlist"));
			return;
		} else {
			try {
				userId=Integer.parseInt(request.getParameter("id"));
			} catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath()
					+response.encodeRedirectURL("/userlist"));
				return;
			}
		}


		boolean banUser = false;
		boolean adminUser = false;
		boolean deactivateUser = false;
		boolean deleteUser = false;
		String changeEmail = "";
		String changeName = "";

		if(request.getParameter("useredit_banUser")!=null)
			if(request.getParameter("useredit_banUser").equals("yes"))
				banUser = true;
		if(request.getParameter("useredit_adminUser")!=null)
			if(request.getParameter("useredit_adminUser").equals("yes"))
				adminUser = true;
		if(request.getParameter("useredit_deactivateUser")!=null)
			if(request.getParameter("useredit_deactivateUser").equals("yes"))
				deactivateUser = true;
		if(request.getParameter("useredit_deleteUser")!=null)
			if(request.getParameter("useredit_deleteUser").equals("yes"))
				deleteUser = true;
		if(request.getParameter("useredit_changeEmail")!=null)
			if(!request.getParameter("useredit_changeEmail").equals(""))
				changeEmail = request.getParameter("useredit_changeEmail");
		if(request.getParameter("useredit_changeName")!=null)
			if(!request.getParameter("useredit_changeName").equals(""))
				changeName = request.getParameter("useredit_changeName");

		UserController uc = new UserController();

		User u = uc.getUserForId(userId);

		if(banUser) {
			u.setBanned(!u.isBanned());
			if(u.isBanned())
				request.setAttribute("useredit_message", "User is now banned!");
			else
				request.setAttribute("useredit_message", "User has now been unbanned!");
		} else if(adminUser) {
			u.setAdmin(!u.isAdmin());
			if(u.isAdmin())
				request.setAttribute("useredit_message", "User has been made an administrator!");
			else
				request.setAttribute("useredit_message", "User is no longer an administrator!");
		} else if(deactivateUser) {
			u.setActive(!u.isActive());
			if(u.isActive())
				request.setAttribute("useredit_message", "User has been deactivated!");
			else
				request.setAttribute("useredit_message", "User has been activated!");
		} else if(deleteUser) {
			uc.deleteUser(u);

			//TODO: set the correct path
			request.getRequestDispatcher("/WEB-INF/")
					.include(request, response);

			return;
		} else if(!changeEmail.equals("")) {
			u.setEmail(changeEmail);
			request.setAttribute("useredit_message", "User's email has been changed!");
		} else if(!changeName.equals("")) {
			u.setName(changeName);
			request.setAttribute("useredit_message", "User's name has been changed!");
		}

		request.setAttribute("user_id", u.getId());
		request.setAttribute("user_email", u.getEmail());
		request.setAttribute("user_name", u.getName());
		request.setAttribute("user_active", u.isActive());
		request.setAttribute("user_banned", u.isBanned());
		request.setAttribute("user_admin", u.isAdmin());

		request.getRequestDispatcher("/WEB-INF/user_edit.jsp")
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
