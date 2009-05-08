
import controller.UserController;
import db.User;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cmiller
 */
public class ActivationKeyViewer {

	public static void main(String[] args) {
		/*String userEmail = "lordsauronthegreat@gmail.com";

		UserController uc = new UserController();

		User u = uc.getUserForEmail(userEmail);

		u = uc.generateNewActivationKey(u);

		System.out.println(u.getActivationKey());*/

		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		while(sb.length()<100)
			sb.append(Integer.toString(Math.abs(r.nextInt()), 64));
		System.out.println(sb.toString());
	}

}
