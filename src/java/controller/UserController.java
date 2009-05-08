package controller;

import db.HibernateUtil;
import db.User;
import helper.MailHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author cmiller
 */
public class UserController {

	private Session s;

	public UserController() {
		s = HibernateUtil.getSessionFactory().openSession();
	}

	public User createUser(String email) {
		Transaction tx0 = s.beginTransaction();
		User u = new User();
		
		u.setEmail(email);
		u.setActive(false);
		u = generateNewActivationKey(u);
		u.setAdmin(false);
		u.setBanned(false);

		s.persist(u);
		tx0.commit();
		return u;
	}

	public void forceUpdate(User u) {
		Transaction tx0 = s.beginTransaction();
		s.saveOrUpdate(u);
		tx0.commit();
	}

	public List<User> getUsers(int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<User> toReturn = s.createQuery("from User as u order by upper(name) asc")
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public List<User> getUsers(String email, String name, int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<User> toReturn = s.createQuery("from User as u where u.email like :email and u.name like :name order by upper(name) asc")
				.setString("email", email).setString("name", name)
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public List<User> getUsersByEmail(String searchTerm, int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<User> toReturn = s.createQuery(
				"from User as u where u.email like :searchTerm order by upper(email) asc")
				.setString("searchTerm", searchTerm)
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public long getTotalUsers() {
		Transaction tx0 = s.beginTransaction();
		long toReturn = ((Long)s.createQuery(
				"select count(*) from User as u").iterate()
				.next()).intValue();
		tx0.commit();
		return toReturn;
	}

	public List<User> getUsersByName(String searchTerm, int start, int max) {
		Transaction tx0 = s.beginTransaction();
		List<User> toReturn = s.createQuery(
				"from User as u where u.name like :searchTerm order by upper(name) asc")
				.setString("searchTerm", searchTerm)
				.setFirstResult(start).setMaxResults(max).list();
		tx0.commit();
		return toReturn;
	}

	public String crypt(String password) {
		
		MessageDigest encrypter = null;
		try {
			encrypter = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new String(encrypter.digest(password.getBytes()));
		
	}

	public boolean sendUserActivation(User u) {

		generateNewActivationKey(u);

		StringBuilder message = new StringBuilder();

		message.append("Welcome to FireWiki " + u.getName() + "!\n\n");
		message.append("Your activation key is " + u.getActivationKey() + "\n\n");

		return MailHelper.send(message.toString(), "FireWiki Activation", u.getEmail());
		
	}

	public boolean authenticate(User u, String password) {
		if(u.getPassword().equals(crypt(password)))
			return true;
		else
			return false;
	}

	public void setPassword(User u, String password) {
		u.setPassword(crypt(password));
	}

	public User generateNewActivationKey(User u) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		while(sb.length()<100)
			sb.append(Integer.toString(Math.abs(r.nextInt()), 64));
		u.setActivationKey(sb.toString());
		return u;
	}

	public User getUserForEmail(String email) {
		Transaction tx0 = s.beginTransaction();
		User u = (User)s.createQuery("from User where email = lower(:email)")
				.setString("email", email).uniqueResult();
		tx0.commit();
		return u;
	}

	public User getUserForId(int id) {
		Transaction tx0 = s.beginTransaction();
		User u = (User)s.createQuery("from User where id = :id")
				.setInteger("id", id).uniqueResult();
		tx0.commit();
		return u;
	}

	public void deleteUser(User u) {
		Transaction tx0 = s.beginTransaction();
		s.delete(u);
		tx0.commit();
	}

}
