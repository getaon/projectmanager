	package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.Users;

public class EmployeeManager {
	
	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}
	
	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}
	
	public List<Employee> getByName(String firstname) {
		String sql = "select * from projectmanager.employee where firstname like'";
		return (List)entityManager.createNativeQuery(sql+firstname+"'", Employee.class).getResultList();
	}
	
	public List<Employee> getAllEmployees() {
		String sql ="select * from projectmanager.employee";
		return (List)entityManager.createNativeQuery(sql, Employee.class).getResultList();
	}

	
	public  Employee createNewEmployee(String firstname, String lastname, String email, String phone,
			String username, String password) {
		
		Users user = new Users(username, password,"employee");
		 user = ManagerHelper.getUsersManager().create(user);
			
			 Employee employee = new Employee(firstname,lastname,email, phone,user);
				try{
					entityManager.getTransaction().begin();
					entityManager.persist(employee);
					entityManager.getTransaction().commit();
					return employee;
				}catch(Exception e){
					
					return new Employee();
				}
	
	}
	
	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}
	
	public Reply updateEmployee(int id,String firstname, String lastname, String email, String phone,
			String username, String password) {
		
		Users user = new Users(username,password,"employee");
		 user = ManagerHelper.getUsersManager().update(user);
		 
		Employee employee = new Employee(id,firstname,lastname,email, phone,user);
		System.out.println("in MANAGER delete function-->");
		try{
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
			entityManager.getTransaction().commit();
			System.out.println("customer deleted-->");
			return new Reply();
			
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}
	
	public Reply deleteEmployee(int id) {
		Employee employee = get(id);
		System.out.println("in MANAGER delete function-->");
		try{
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
			System.out.println("customer deleted-->");
			return new Reply();
			
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	
//	public String updateEmployee(String firstname, String lastname, String email, String phone, int user) {
//		Employee employee = new Employee(firstname,lastname,email,phone,user);
//			update(employee);
//		return null;
//	}

//	public String deleteEmployee(String firstname, String lastname, String email, String phone, int user) {
//			Employee employee = new Employee(firstname,lastname,email,phone,user);
//			delete(employee);
//		return null;
//	}

//	public String createingEmployee(String firstname, String lastname, String email, String phone, int user) {
//		Employee employee = new Employee(firstname,lastname,email,phone,user);
//		create(employee);
//		return null;
//	}


}
