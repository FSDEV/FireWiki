/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import org.hibernate.Session;

/**
 *
 * @author cmiller
 */
public class Controller {

	protected static Session s;

	public Controller() {
		if(Controller.s==null)
			s = db.HibernateUtil.getSessionFactory().openSession();
	}

}
