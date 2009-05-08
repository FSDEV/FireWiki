/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class Register extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("reg_email");
		String password = request.getParameter("reg_pass");
		String passwordConfirmation = request.getParameter("reg_pass_confirm");
		String name = request.getParameter("reg_name");

		if(email==null)
			email = "";
		if(password==null)
			password = "";
		if(passwordConfirmation==null)
			passwordConfirmation = "";
		if(name==null)
			name = "";

		request.setAttribute("reg_email", email);
		request.setAttribute("reg_pass", password);
		request.setAttribute("reg_pass_confirm", passwordConfirmation);
		request.setAttribute("reg_name", name);

		if(email.isEmpty()&&password.isEmpty()&&passwordConfirmation.isEmpty()
				&&name.isEmpty()) {
			request.setAttribute("reg_invalid", false);

			request.getRequestDispatcher("/WEB-INF/register_form.jsp")
					.include(request, response);

			return;
		}

		UserController uc = new UserController();
		boolean invalid = false;

		if(email==null||
				!email.matches("[\\d\\w\\.-]+@[\\d\\w\\.-]+\\.(com|org|net|edu|biz)")) {
			invalid = true;
			request.setAttribute("reg_email_invalid", true);
		} else if(uc.getUserForEmail(email)!=null) {
			invalid = true;
			request.setAttribute("reg_email_already_exists", true);
		}

		if(password==null||passwordConfirmation==null) {
			invalid = true;
			if(password==null)
				request.setAttribute("reg_pass_empty", true);
			if(passwordConfirmation==null)
				request.setAttribute("reg_pass_conf_empty", true);
		} else if(!password.equals(passwordConfirmation)) {
			invalid = true;
			request.setAttribute("reg_passwords_dont_match", true);
		}

		if(name==null) {
			invalid = true;
			request.setAttribute("reg_name_invalid", true);
		}

		if(invalid) {

			request.setAttribute("reg_invalid", true);

			request.getRequestDispatcher("/WEB-INF/register_form.jsp")
					.include(request, response);
			
		} else {

			User u = uc.createUser(email);
			uc.setPassword(u, password);
			u.setName(name);

			if(!uc.sendUserActivation(u)) {

				uc.deleteUser(u);

				request.setAttribute("reg_email_failed", true);

				request.getRequestDispatcher("/WEB-INF/register_form.jsp")
						.include(request, response);
				
			} else {

				if(uc.getTotalUsers()<=1)
					u.setAdmin(true);

				uc.forceUpdate(u);

				request.getSession().setAttribute("user", u);

				// show the current user and the option to log out
				request.setAttribute("login_userName", u.getName());
				
				response.sendRedirect(request.getContextPath()
					+response.encodeRedirectURL("/activate"));
				
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
