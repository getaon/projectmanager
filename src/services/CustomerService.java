package services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.Users;
import manager.ManagerHelper;
import manager.Reply;

@Path("/customer")
public class CustomerService {
	
	/**
	 * function redirect to a function that 
	 * finds customer by his id
	 * @param id
	 * @return
	 */
	
	@GET
	@Path("get")
	public Customer getCustomer(@QueryParam("id") int id){
		return ManagerHelper.getCustomerManager().get(id); 
	}
	
	/**
	 * function that redirect to a function that 
	 * finds customer by his name
	 * @param name
	 * @return
	 */
	
	@GET
	@Path("getByName")
	public List<Customer> getByName(@QueryParam("companyname")String name){
		return ManagerHelper.getCustomerManager().getByName(name);
	}
	
	/**
	 * function that redirect to a function that returns list 
	 * of active customers and there amount of projects
	 * @return
	 */
	@GET
	@Path("getActiveCustomers")
	public List<Customer> getActiveCustomers(){
		return ManagerHelper.getCustomerManager().getActiveCustomers();
	}
	
	/**
	 * function that redirect to a function that returns 
	 * a list of all customers
	 * @return
	 */
	@GET
	@Path("getAllCustomers")
	public List<Customer> getAllCustomers(){
		return ManagerHelper.getCustomerManager().getAllCustomers();
	}
	 
	/**
	 * function that redirect to a function that 
	 * create a new customer and new user
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 * @return
	 */
	@GET
	@Path("createNewCustomer")
	public Customer createNewCustomer(@QueryParam("companyname")String companyname,
			@QueryParam("companynumber")String companynumber,@QueryParam("contactname")String contactname,
			@QueryParam("email")String email,@QueryParam("phone")String phone,
			@QueryParam("username")String username,@QueryParam("password")String password){
		return ManagerHelper.getCustomerManager().createNewCustomer(companyname,companynumber,
				contactname,email,phone,username,password); 
	}
	
	/**
	 * function that redirect to a function that 
	 * update an information from exists customer   
	 * @param id
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @return
	 */
	
	@GET
	@Path("updateCustomer")
	public Reply updateCustomer(@QueryParam("id")int id,@QueryParam("companyname")String companyname,
			@QueryParam("companynumber")String companynumber,@QueryParam("contactname")String contactname,
			@QueryParam("email")String email,@QueryParam("phone")String phone){
		return ManagerHelper.getCustomerManager().updateCustomer(id,companyname,companynumber,
				contactname,email,phone);
	}
	
	/**
	 * function that redirect to a function that 
	 * delete a customer by his id
	 * @param id
	 * @return
	 */
	@GET
	@Path("deleteCustomer")
	public Reply deleteCustomer(@QueryParam("id") int id){
		return ManagerHelper.getCustomerManager().deleteCustomer(id);
	}
	
}
