package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.openjpa.persistence.jdbc.Unique;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Unique
	private String username;
	private String password;
	private String type;
	
	
	
		public Users() {
			
		}
		public Users(int id,String username,String password, String type){
			this.id=id;
			this.username = username;
			this.password = password;
			this.type = type;
		
		}
		
		public Users(String username,String password, String type){
			this.username = username;
			this.password = password;
			this.type = type;
		
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
}
