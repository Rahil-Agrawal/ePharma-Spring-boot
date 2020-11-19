package ePharma.springboot.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	
	@Size(min=5)
	private String password;
	
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	/**
	 * 
	 */
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
}
