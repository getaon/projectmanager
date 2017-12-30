package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Project;
import entity.Users;
import manager.ManagerHelper;

@Path("/UsersService")
public class UsersService {
	
	
	
	@GET
	@Path("get")
	public Users getUser(@QueryParam("id") int id){
		return ManagerHelper.getUsersManager().get(id); 
	}
		
	
	@GET
	@Path("getUserByName")
	public Users getProject(@QueryParam("username") String username,@QueryParam("password") String password){
		return ManagerHelper.getUsersManager().getUser(username, password);
	}
	
	
	@GET
	@Path("createNewUser")
	public Users createNewUser(@QueryParam("username") String username,
						@QueryParam("password") String password, @QueryParam("type") String type){
		return ManagerHelper.getUsersManager().createUser(username,password,type);
	}
	
	@GET
	@Path("forgetPassword")
	public Users forgetPassword(@QueryParam("username") String username){
		return ManagerHelper.getUsersManager().forgetPassword(username);
	}

}
