package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;


import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {
    @Id
	private long id;
	
    @Required
	private String fullName;
	
	private String company;
	
    @Email
	private String email;
	
	private String passWord;
	
	public static Finder<Long,User> find = new Finder(
		    Long.class, User.class
		  );

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public static void save(User user){
		
	}
	
   public static void delete(Long id){
		find.ref(id).delete();
	}
   
   public static void update(User u,Long id){
	    User user = User.find.byId(id);
		user.setFullName(u.getFullName());
		user.setCompany(u.getCompany());
		user.setEmail(u.getEmail());
		user.update();
	}


	
	public static List<User> all(){
		return find.all();
	}
}
