package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeProject {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employeeid;
	@ManyToOne
	@JoinColumn(name="projectid")
	private Project projectid;
	
	public EmployeeProject(){
		
	}
	public EmployeeProject(Employee employeeid, Project projectid) {
		this.employeeid = employeeid;
		this.projectid = projectid;
	}
	
	public EmployeeProject(int id, Employee employeeid, Project projectid) {

		this.id = id;
		this.employeeid = employeeid;
		this.projectid = projectid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Employee employeeid) {
		this.employeeid = employeeid;
	}
	public Project getProjectid() {
		return projectid;
	}
	public void setProjectid(Project projectid) {
		this.projectid = projectid;
	} 
	

}
