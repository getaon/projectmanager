package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Project;

public class ProjectManager {
	
	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 

	}
	
	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}
	
	public List<Project> getByName(String name) {
		String sql = "select * from projectmanager.project where projectname like'";
		return (List<Project>)entityManager.createNativeQuery(sql+name+"'", Project.class).getResultList();
	}
	
	public List<Project> getAllProjects() {
		String s = "select * from projectmanager.project ";
		return (List<Project>)entityManager.createNativeQuery(s, Project.class).getResultList();
	}
	
	public List<Project> getActiveProjectCustomer(String userId) {
	String sql = "SELECT* FROM projectmanager.project p "
			+ "inner join projectmanager.customer c on p.customer= c.id "
			+ "inner join projectmanager.users u on c.user = u.id "
			+ " where  p.enddate >= current_date() and u.id ="+userId;
		return (List<Project>)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	public List<Project> getActiveProject() {
		String sql = "SELECT* FROM projectmanager.project p "
				+ " where  p.enddate >= now() order by enddate";
			return (List<Project>)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	

	public List<Project> getEndingProject() {
			String sql = "select * from project where enddate between current_date() "
					+"and current_date() + INTERVAL 100 DAY order by enddate";
		return (List<Project>)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	public Project createProject(String projectname, int customer, String startdate, String enddate,
			String customerprojectmanager, String projectmanageremail, String projectmanagerphone) {

				Customer c =ManagerHelper.getCustomerManager().get(customer);
				Project project = new Project(projectname,c,startdate,enddate,customerprojectmanager,
						projectmanageremail,projectmanagerphone);

			try {
				entityManager.getTransaction().begin();
				entityManager.persist(project);
				entityManager.getTransaction().commit();
				return project;
			} catch (Exception e) {
				return null;
			}
			
	}
	
	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}
	
	
	public Reply updateProject(int id, String projectname, int customer, String startdate, String enddate, String customerprojectmanager, String projectmanageremail, String projectmanagerphone) {
		Customer c =ManagerHelper.getCustomerManager().get(customer);
		Project project = new Project(id,projectname,c,startdate,enddate,customerprojectmanager,projectmanageremail,projectmanagerphone);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}
	
	public Reply deleteProject(int id) {
		Project project = get(id);
		
		
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
				return r;
		}
	}

	

}
