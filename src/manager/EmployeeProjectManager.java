package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.EmployeeProject;
import entity.Project;

public class EmployeeProjectManager {

	private final EntityManager entityManager;
	
	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}
	
	/**
	 * function that find a connected project and 
	 * employee by there id from database
	 * @param id
	 * @return
	 */
	public EmployeeProject get( Integer id) {
		return entityManager.find(EmployeeProject.class, id);	
	}
	
	/**
	 * function that returns a list of connections 
	 * between employee and project from database
	 * @return
	 */
	public List<EmployeeProject> getAllEmployeeProject() {
		try{
			
			String sql = "select * from projectmanager.employeeproject order by projectid";
			 return (List<EmployeeProject>) entityManager.createNativeQuery(sql, EmployeeProject.class).getResultList();
		}catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * function that returns a list of connected projects
	 * to an employee from the database
	 * @param userId
	 * @return
	 */
	public List<EmployeeProject> getConnectedProject(String userId) {
		String sql = "select ep.id, ep.employeeid, ep.projectid from projectmanager.employeeproject ep "
				+ "inner join projectmanager.project p on ep.projectid = p.id "
				+ "inner join projectmanager.employee e on ep.employeeid = e.id "
				+ "where e.user = "+userId;
		
		return (List<EmployeeProject>)entityManager.createNativeQuery(sql, EmployeeProject.class).getResultList();
	}

	/**
	 *function that create a new connection between
	 *employee and project inside the database 
	 * @param employeeid
	 * @param projectid
	 * @return
	 */
	public Reply belongEmployeeToProject(int employeeid, int projectid) {
		
		Employee employee = ManagerHelper.getEmployeeManager().get(employeeid);
		Project project = ManagerHelper.getProjectManager().get(projectid);
		EmployeeProject employeeProject = new EmployeeProject(employee,project);
		
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();
		
		}catch(Exception e){
			e.printStackTrace();
			
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return null;
		}
	}
	
	/**
	 * function that delete a connection between a employee
	 * and project from the database
	 * @param emproid
	 * @return
	 */
	public Reply deleteEmployeeProject(int emproid) {
		
		EmployeeProject employeeProject = get(emproid);
		try{
	
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
		return new Reply();
		}catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg("fail");
			return r;
		}
	}
		
	
	/**
	 * one of the C.R.U.D functions that get an employeeProject object
	 * and create a new connection between a employee to project
	 * inside the database
	 * @param employeeProject
	 */
	public void create(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * one of the C.R.U.D function that gets an employeeProject object
	 * and delete a connection between employee to project
	 * inside the database
	 * @param employeeProject
	 */
	public void delete(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}

}
