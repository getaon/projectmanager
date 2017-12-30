package manager;

import javax.persistence.EntityManager;
import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Users;
import services.MailHelper;
import javax.mail.MessagingException;

public class UsersManager {
	
	private final EntityManager entityManager;

	public UsersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}
	
	public Users get(Integer id) {
		return entityManager.find(Users.class, id);
	}

	public Users getUser(String username, String password) {
		try{
			String sql = "select * from projectmanager.users where username like '";
			String sql2= "' and password like '";
				return (Users)entityManager.createNativeQuery(sql+username+sql2+password+"'", Users.class).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
				return null;
		}
	}
	
	public Users createUser(String username, String password,String type) {
		Users user = new Users(username,password,type);
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return new Users();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public Users create(Users user) {
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return user;
		}catch (Exception e) {
			
			return null;
		}
	
	}

	public Reply updateUser(int id,String username,String password,String type) {
		Users user = new Users(id,username,password,type);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public Users update(Users user) {
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
			return user;
		}catch(Exception e){
			return null;
		}
	}
	
	public Reply deleteUser(int id) {
		Users user = get(id);
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void delete(Users user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);							
		entityManager.getTransaction().commit();
	}

	public Users forgetPassword(String username) {
		
		String sql = "select * from projectmanager.users u "
				+ "where u.username ='"+username+"'";
		
		Users user = (Users)entityManager.createNativeQuery(sql, Users.class).getSingleResult();
		
	     if(user.getType().equals("employee")){
			
			String sq = "select * from projectmanager.employee e "
					+ "inner join projectmanager.users u on e.user = u.id "
					+ "where u.username ='"+username+"'";

			Employee employee = (Employee)entityManager.createNativeQuery(sq, Employee.class).getSingleResult();
			
			try {
				MailHelper.sendMail(employee.getEmail(), "message subject: forget password",
						"message body text:"+user.getUsername()+", password:"+user.getPassword());
				
				System.out.println("message send for employee");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for employee");
				
				return null;
			}
			
			
			
		}else if(user.getType().equals("customer")){
			String s = "select * from projectmanager.customer c "
					+ "inner join projectmanager.users u on c.user = u.id "
					+ "where u.username ='"+username+"'";
			
			Customer customer = (Customer)entityManager.createNativeQuery(s, Customer.class).getSingleResult();
			
			try {
				MailHelper.sendMail(customer.getEmail(), "message subject: forget password",
						"message body text:"+user.getUsername()+", password:"+user.getPassword());
				
				System.out.println("message send for customer");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for customer");
			}

		}else{
			try {
				MailHelper.sendMail("getaon1@gmail.com", "message subject: forget password",
					"message body text: username:"+user.getUsername()+", password:"+user.getPassword());
				
				System.out.println("message send for manager");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for manager");
			}
		}
		return new Users();
	}


//
//	public String deleteUser(String username, String password, String type) {
//		Users users = new Users(username,password,type);
//		delete(users);
//		return "objectDeleted";
//	}
//
//	public String updateUser(String username, String password, String type) {
//		Users users = new Users(username,password,type);
//			update(users);
//		return null;
//	}

}
