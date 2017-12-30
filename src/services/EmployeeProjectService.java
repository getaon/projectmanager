package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.EmployeeProject;
import entity.Project;
import manager.ManagerHelper;
import manager.Reply;

@Path("/EmployeeProjectService")
public class EmployeeProjectService {
	
	
	/**
	 * function that redirect to a function that 
	 * create a connection between a employee to a project 
	 * @param employeeid
	 * @param projectid
	 * @return
	 */
	@GET
	@Path("belongEmployeeToProject")
	public Reply belongEmployeeToProject(@QueryParam("employeeid")int employeeid,
			@QueryParam("projectid")int projectid){
		return ManagerHelper.getEmployeeProjectManager().belongEmployeeToProject(employeeid,projectid);
	}

	/**
	 * function that redirect to a function that returns 
	 * a list of connection row between employees and projects
	 * @return
	 */
	@GET
	@Path("getAllEmployeeProject")
	public List<EmployeeProject> getAllEmployeeProject(){
		return ManagerHelper.getEmployeeProjectManager().getAllEmployeeProject();
	}
	
	/**
	 * function that redirect to a function that 
	 * delete a connection between employee and project
	 * @param emproid
	 * @return
	 */
	@GET
	@Path("deleteEmployeeProject")
	public Reply deleteEmployeeProject(@QueryParam("emproid")int emproid){
		return ManagerHelper.getEmployeeProjectManager().deleteEmployeeProject(emproid);
	}
	
	/**
	 * function that redirect to a function that 
	 * returns a list of projects that connected to employee
	 * by his userId
	 * @param userId
	 * @return
	 */
	@GET
	@Path("getConnectedProject")
	public List<EmployeeProject> getConnectedProject(@QueryParam("userId")String userId){
		return ManagerHelper.getEmployeeProjectManager().getConnectedProject(userId);
	}
}
