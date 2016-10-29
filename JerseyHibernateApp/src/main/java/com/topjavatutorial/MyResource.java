package com.topjavatutorial;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.topjavatutorial.dao.Employee;
import com.topjavatutorial.dao.EmployeeDao;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/employees")
public class MyResource {

	@SuppressWarnings("unchecked")
	@GET
	@Produces("application/json")
	public List<Employee> getEmployee() {
		EmployeeDao dao = new EmployeeDao();
		@SuppressWarnings("rawtypes")
		List employees = dao.getEmployees();
		return employees;
		}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addEmployee(EmployeeBean emp){
		emp.setName(emp.getName());
		emp.setAge(emp.getAge());
		EmployeeDao dao = new EmployeeDao();
		dao.addEmployee(emp);
		return Response.ok().build();
		}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteEmployee(@PathParam("id") int id){
		EmployeeDao dao = new EmployeeDao();
		int count = dao.deleteEmployee(id);
		if(count==0){
			return Response.status(Response.Status.BAD_REQUEST).build();
			}
		return Response.ok().build();
		}
	}
