package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.Reply;

@Path("/Properties")
public class Properties {

	@GET
	@Path("getHourList")
	public String getHourList() {
		return PropsHelper.get("hours");
	}

	@GET
	@Path("setHourList")
	public String setHourList(@QueryParam("beginHour") String beginHour,
			@QueryParam("endHour") String endHour) {
	
		String timeofWork = beginHour+","+endHour;
		System.out.println(timeofWork);
		 PropsHelper.set("hours",timeofWork);
		
		 return Reply.OK_STR;
	}
	

	@GET
	@Path("/getdays")
	public String getdays(){
		String days = PropsHelper.get("days");
		return days;
	}

	@GET
	@Path("/setdaysofWork")
	public String setDays(@QueryParam("sunday")String sunday,@QueryParam("monday")String monday
			,@QueryParam("tuesday")String tuesday,@QueryParam("wednesday")String wednesday
			,@QueryParam("thursday") String thursday,@QueryParam("friday")String friday
			,@QueryParam("saturday")String saturday){
		
		return PropsHelper.set("days"," "+sunday+","+" "+monday+","+" "+tuesday+","+" "+wednesday+","
				+" "+thursday+","+" "+friday+","+" "+saturday+",");	
	}
	
}
