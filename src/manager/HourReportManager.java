package manager;

import java.util.List;

import javax.persistence.EntityManager;
import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.HourReport;
import entity.Project;
import entity.Users;

public class HourReportManager {
	
	private final EntityManager entityManager;

	public HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public HourReport get(Integer id) {
		return entityManager.find(HourReport.class, id);
	}
	

	public List<HourReport> getLastReports(int userId) {
		try{
		String sql = "SELECT * FROM projectmanager.hourreport h "
				+ "inner join projectmanager.employee e on h.employee = e.id "
				+ "inner join projectmanager.users u on e.user= u.id "
				+ " where h.date >= curdate() - INTERVAL 6 DAY "
				+ "and h.date <= curdate() "
				+ "and u.id ="+userId+" order by h.date";  
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}

	public List<HourReport> getAllHourReports(int userId, String usertype){
		try{
			
		 if(usertype.equals("employee")){
			String sql = "SELECT * FROM projectmanager.hourreport h "
					+ "inner join projectmanager.employee e on h.employee = e.id "
					+ "inner join projectmanager.users u on e.user= u.id "
					+ "where h.date >= now() - INTERVAL 30 DAY "
					+ "and h.date <= current_date() "
					+ "and u.id ="+userId+" order by h.date";
			return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
			
			}else if(usertype.equals("customer")){
				
				String sql = "select * from projectmanager.hourreport h "
						+ "inner join projectmanager.project p on h.project = p.id "
						+ "inner join projectmanager.customer c on p.customer = c.id "
						+ "inner join projectmanager.users u on c.user =u.id "
						+ "where h.date >= current_date()- INTERVAL 30 DAY "
						+ "and h.date <= current_date() "
						+ " and u.id="+userId+" order by h.date";
				return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
			}else{
				return null;
			}

		}catch (Exception e) {
			return null;
		}
			
	}
	
	public HourReport createHourReport(int userId, int projectid,
			String date, String starthour, String endhour, String description) {
	
		System.out.println("userId -----> "+userId);
		System.out.println("projectid -----> "+projectid);
		System.out.println("date -----> "+date);
		System.out.println("starthour -----> "+starthour);
		System.out.println("endhour -----> "+endhour);
		System.out.println("description -----> "+description);
		
		String sql ="select * from projectmanager.employee e "
				+ "inner join projectmanager.users u on e.user= u.id "
				+ "where u.id ="+userId;
		
		Employee employee = (Employee)entityManager.createNativeQuery(sql, Employee.class).getSingleResult();
			
	Project project = ManagerHelper.getProjectManager().get(projectid);

		HourReport hourReport = new HourReport(employee,project,date,starthour,endhour,description);
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(hourReport);
			entityManager.getTransaction().commit();
			return hourReport;
		} catch (Exception e) {
			e.printStackTrace();
			return new HourReport();
		}
	}

	public void create(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourReport);
		entityManager.getTransaction().commit();
	}
	
	public Reply updateHourReport(int id,int employeeid, int projectid, String date, String starthour,
			String endhour, String description) {
		Employee employee = ManagerHelper.getEmployeeManager().get(employeeid);
		Project project = ManagerHelper.getProjectManager().get(projectid);
		HourReport hourReport = new HourReport(id,employee,project,date,starthour,endhour,description);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void update(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();
	}
	
	public Reply deleteHourReport(int id) {
		HourReport hourReport = get(id);
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(hourReport);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch(Exception e){
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public void delete(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourReport);
		entityManager.getTransaction().commit();
	}

	public List<HourReport> getByYearMonth(String year, String month,int projectid, int userId, String usertype) {	

		if(usertype.equals("employee")){
			String sql = "select * from projectmanager.hourreport h "
					+ "inner join projectmanager.project p on h.project = p.id "
					+ "inner join projectmanager.employee e on h.employee= e.id "
					+ "inner join projectmanager.users u on e.user =u.id "
					+ "where year(h.date) like'"+year+"' and month(h.date) like '"+month+"' "
							+ "and u.id="+userId;
			if(projectid != 0){
				sql += " and h.project ="+projectid+" order by h.date ";
			}else{
				sql += " order by h.date ";
			}
			
			return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
			
		}else if(usertype.equals("customer")){
			String sql = "select * from projectmanager.hourreport h "
					+ "inner join projectmanager.project p on h.project = p.id "
					+ "inner join projectmanager.customer c on p.customer = c.id "
					+ "inner join projectmanager.users u on c.user =u.id "
					+ "where year(h.date) like'"+year+"' and month(h.date) like '"+month+"' "
							+ "and u.id="+userId;
			
			if(projectid != 0){
				sql += " and h.project ="+projectid+" order by h.date ";
			}else{
				sql += " order by h.date ";
			}
			return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
		}else{
			return null;
		}
		
	}
	
	public List<HourReport> getByYearMonthManager(String year, String month, int projectid,
			int employeeid, int customerid) {
			
	
			
		String sql = "select * from projectmanager.hourreport h "
				+ "inner join projectmanager.employee e on h.employee = e.id "
				+ "inner join projectmanager.project p on h.project = p.id "
				+ "where year(h.date) like'"+year+"' and month(h.date) like '"+month+"' ";
										
					if(employeeid != 0){
						sql += " and h.employee = " + employeeid   ;
					}
					
					if( projectid != 0){
						sql +=" and  h.project =  " + projectid ;
					}
					
					if(customerid != 0){
						sql += " and p.customer =  " + customerid ;
					}
			
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}


	public List<HourReport> getAllHourReportsManager() {
		String sql = "select * from projectmanager.hourreport h "
				+ " where h.date >=  current_date() - INTERVAL 30 DAY "
				+ "order by h.date;";
		return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getReltiveProjects(String userId, String usertype) {
	
		String sql = "	SELECT * FROM projectmanager.hourreport h  "
				+ " inner join projectmanager.employee e on h.employee = e.id "
				+ " inner join projectmanager.project p on h.project = p.id  "
				+ "inner join projectmanager.customer c on p.customer = c.id "
				+ "where "; 
		
			if(usertype.equals("employee")){
				sql += "    e.user =" +userId+ " group by h.project ";
						
			}else if(usertype.equals("customer")){
				sql += " c.user = "+ userId + " group by h.project ";
			}
			
		return (List<HourReport>)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}
	
}
