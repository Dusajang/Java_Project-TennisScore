package TenisProject;

import java.io.Serializable;

class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1425821168775014880L;
	private String name;
	private String gender;

	Player(String name, String gender){
		this.name = name;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return this.name + " / " + this.gender;
	}

}