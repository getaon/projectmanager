package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Employee;
import entity.Project;
import manager.ManagerHelper;
import manager.Reply;

@Path("/project")
public class ProjectService {
	
	
	@GET
	@Path("get")
	public Project getProject(@QueryParam("id") int id){
		return ManagerHelper.getProjectManager().get(id); 
	}
	
	@GET
	@Path("getByName")
	public List<Project> getByName(@QueryParam("projectname")String name){
		return ManagerHelper.getProjectManager().getByName(name);
	}

	@GET
	@Path("getAllProjects")
	public List<Project> getAllProjects(){
		return ManagerHelper.getProjectManager().getAllProjects();
	}
	
	@GET
	@Path("getActiveProjectCustomer")
	public List<Project> getActiveProjectCustomer(@QueryParam("userId")String userId){
		return ManagerHelper.getProjectManager().getActiveProjectCustomer(userId);
	}
	
	@GET
	@Path("getActiveProject")
	public List<Project> getActiveProject(){
		return ManagerHelper.getProjectManager().getActiveProject();
	}
	
	@GET
	@Path("getEndingProject")
	public List<Project> getEndingProject(){
		return ManagerHelper.getProjectManager().getEndingProject();
	}
	

	@GET
	@Path("createProject")
	public Project createProject(@QueryParam("projectname")String projectname,@QueryParam("customer")int customer,
			@QueryParam("startdate")String startdate,@QueryParam("enddate")String enddate,
			@QueryParam("customerprojectmanager")String customerprojectmanager,@QueryParam("projectmanageremail")String projectmanageremail,
			@QueryParam("projectmanagerphone")String projectmanagerphone){
		return ManagerHelper.getProjectManager().createProject(projectname,customer,startdate,enddate,customerprojectmanager,projectmanageremail,projectmanagerphone); 
	}
	@GET
	@Path("deleteProject")
	public Reply deleteProject(@QueryParam("id")int id){
		return ManagerHelper.getProjectManager().deleteProject(id); 
	}
	
	@GET
	@Path("updateProject")
	public Reply updateProject(@QueryParam("id")int id,@QueryParam("projectname")String projectname,@QueryParam("customer")int customer,
			@QueryParam("startdate")String startdate,@QueryParam("enddate")String enddate,
			@QueryParam("customerprojectmanager")String customerprojectmanager,@QueryParam("projectmanageremail")String projectmanageremail,
			@QueryParam("projectmanagerphone")String projectmanagerphone){
		return ManagerHelper.getProjectManager().updateProject(id,projectname,customer,startdate,enddate,
				customerprojectmanager,projectmanageremail,projectmanagerphone); 
	}

}
