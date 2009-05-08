package db;

/**
 *
 * @author cmiller
 */
public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private String activationKey;
	private boolean active;
	private boolean admin;
	private boolean banned;


	private void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	public String getActivationKey() {
		return this.activationKey;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return this.active;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isAdmin() {
		return this.admin;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	public boolean isBanned() {
		return this.banned;
	}
}
