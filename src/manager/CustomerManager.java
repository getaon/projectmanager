package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Users;

public class CustomerManager {
	
	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	/**
	 * function that goes to the database 
	 * and returns a customer by his id 
	 * @param id
	 * @return
	 */
	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}
	
	/**
	 * function that returns a customer by his name
	 *  from the database 
	 * @param name
	 * @return
	 */
	public List<Customer> getByName(String name) {
		String sql = "select * from projectmanager.customer where companyname like '";
		return (List<Customer>)entityManager.createNativeQuery(sql+name+"'", Customer.class).getResultList();
	}
	
	/**
	 * function that gets no @param and returns
	 * a list of active customer from database
	 * @return
	 */
	public List<Customer> getActiveCustomers() {	
	String sql = "select c.id,c.companyname, c.companynumber,c.contactname,c.email,c.phone, true as isActiv,"
				+ " (select count(projectname) from projectmanager.project p where p.customer=c.id) as 'projects'"
				+ " from customer c where (select count(p.id) from project p where p.customer = c.id "
				+ "and (current_date() between p.startdate and p.enddate) > 0) ";
			return  (List<Customer>)entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	/**
	 * function that gets no @param and returns 
	 * a list of all customers from database
	 * @return
	 */
	public List<Customer> getAllCustomers() {	
			String s = "select * from projectmanager.customer";
		return (List<Customer>)entityManager.createNativeQuery(s, Customer.class).getResultList();
	}
	
	/**
	 * function that creates a new customer and new user
	 * inside that database 
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 * @return
	 */
	public Customer createNewCustomer(String companyname,String companynumber,String contactname,
			String email,String phone,String username,String password) {
	
		Users user = new Users(username, password,"customer");
		user = ManagerHelper.getUsersManager().create(user);
		 
		Customer customer = new Customer(companyname,companynumber,contactname,email,phone,user);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return new Customer();
		}
	}
	
	/**
	 * one of the C.R.U.D function that gets a customer object
	 * and create a new customer inside the database
	 * @param customer
	 */
	public void create(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * function that goes to the database
	 * and update a customer information
	 * @param id
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @return
	 */
	public Reply updateCustomer(int id,String companyname, String companynumber, String contactname,
			String email, String phone) {

		Customer customer = new Customer(id,companyname,companynumber, contactname,email,phone);
		try{
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
			return new Reply();
		}catch(Exception e){
			e.printStackTrace();
			
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	/**
	 * one of the C.R.U.D function that gets a customer object
	 * and update his information inside the database 
	 * @param customer
	 */
	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * function that delete a customer 
	 * from database by his id 
	 * @param id
	 * @return
	 */
	public Reply deleteCustomer(int id) {
		Customer customer = get(id);
		System.out.println("in MANAGER delete function-->");
		try{
			entityManager.getTransaction().begin();
			entityManager.remove(customer);
			entityManager.getTransaction().commit();
			System.out.println("customer deleted-->");
			return new Reply();
			
		}catch(Exception e){
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	/**
	 * one of the C.R.U.D function that gets a customer object 
	 * and delete him inside the database
	 * @param customer
	 */
	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

}
