package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import entity.HourReport;
import entity.Project;
import entity.Users;
import manager.ManagerHelper;
import manager.Reply;

@Path("/hourReport")
public class HourReportService {

	@GET
	@Path("get")
	public HourReport getReportById(@QueryParam("id") int id){
		return ManagerHelper.getHourReportManager().get(id); 
	}
	
	@GET
	@Path("getLastReports")
	public List<HourReport> getLastReports(@QueryParam("userId")int userId){
		return ManagerHelper.getHourReportManager().getLastReports(userId);
	} 

	@GET
	@Path("getByYearMonth")
	public List<HourReport> getByYearMonth(@QueryParam("year") String year,
			@QueryParam("month")String month,@QueryParam("projectid")int projectid, @QueryParam("userId")int userId,@QueryParam("usertype")String usertype){
			
		return ManagerHelper.getHourReportManager().getByYearMonth(year,month,projectid,userId,usertype);
	} 
	

	@GET
	@Path("getByYearMonthManager")
	public List<HourReport> getByYearMonthManager(@QueryParam("year") String year,
					@QueryParam("month")String month,@QueryParam("projectid")int projectid,
					@QueryParam("employeeid")int employeeid,@QueryParam("customerid")int customerid){
		return ManagerHelper.getHourReportManager().getByYearMonthManager(year,month,projectid,
				employeeid,customerid);
	} 
	
	@GET
	@Path("getAllHourReports")
	public List<HourReport> getAllHourReports(@QueryParam("userId")int userId,@QueryParam("usertype")String usertype){
		return ManagerHelper.getHourReportManager().getAllHourReports(userId,usertype);
	}
	
	@GET
	@Path("getAllHourReportsManager")
	public List<HourReport> getAllHourReportsManager(){
		return ManagerHelper.getHourReportManager().getAllHourReportsManager();
	}
	
	@GET
	@Path("getReltiveProjects")
	public List<HourReport> getReltiveProjects(@QueryParam("userId")String userId,
			@QueryParam("usertype")String usertype){
		return  ManagerHelper.getHourReportManager().getReltiveProjects(userId, usertype);
	}
	
	
	@GET
	@Path("createHourReport")
	public HourReport createHourReport(@QueryParam("employee")int userId,@QueryParam("project")int projectid,
			@QueryParam("date")String date,@QueryParam("satrthour")String satrthour,
			@QueryParam("endhour")String endhour,@QueryParam("description")String description){
		return ManagerHelper.getHourReportManager().createHourReport(userId,projectid,date,
				satrthour,endhour,description);
	}
	
	@GET
	@Path("updateHourReport")
	public Reply updateHourReport(@QueryParam("id")int id,@QueryParam("employee")int employeeid,
			@QueryParam("date")String date,@QueryParam("project")int projectid,
			@QueryParam("starthour")String starthour,@QueryParam("endhour")String endhour,
			@QueryParam("description")String description){
		return ManagerHelper.getHourReportManager().updateHourReport(id, employeeid, projectid,date,starthour,
					endhour, description); 
	}
	

	@GET
	@Path("deleteHourReport")
	public Reply deleteHourReport(@QueryParam("id")int id){
		return ManagerHelper.getHourReportManager().deleteHourReport(id); 
	}

}
