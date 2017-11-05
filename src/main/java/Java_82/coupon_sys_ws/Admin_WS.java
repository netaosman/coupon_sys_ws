package Java_82.coupon_sys_ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import core.beans.ClientType;
import core.beans.Company;
import core.beans.Customer;
import core.couponClientFacade.AdminFacade;
import core.exeptions.CouponSystemExceptions;
import couponSystemSingleton.CouponSystem;

/**
 * Root resource (exposed at "admin" path)
 */
@Path("admin")
public class Admin_WS {

	@Context
	HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	
	// CouponSystem sys = null;
	AdminFacade adminFacade = null;

	/**
	 * Method handling HTTP PUT requests. The Method get Company and create it
	 * in the dataBase.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createCompany")
	public Company createCompany(Company company) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Company comp = new Company();
		if (adminFacade != null) {
			Collection<Company> companies = getAllCompanies();
			for (Company c : companies) {
				if (c.getId() == company.getId() || c.getCompanyName().equals(company.getCompanyName())) {
					comp = new Company(0, "The name and ID already exist.", null, null);
					break;
				}
			}
			adminFacade.createCompany(company);
			comp = adminFacade.getCompany(company.getId());
		}
		return comp;
	}

	/**
	 * Method handling HTTP DELETE requests. The Method delete Company from the
	 * dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// index = company ID.
	@Path("/deleteCompany/{id}")
	public Company deleteCompany(@PathParam("id") int id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Company comp = new Company();
		if (adminFacade != null) {
			// bring the company from the dataBase by the index and delete it.
			comp = adminFacade.getCompany(id);
			adminFacade.deleteCompany(comp);
		}
		return comp;
	}

	/**
	 * Method handling HTTP PUT requests. The Method get the new company's
	 * details and update it in the dataBase. The Method can't update the
	 * company Name and ID.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateCompany")
	public Company updateCompany(Company company) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Company comp = new Company();
		if (adminFacade != null) {
			adminFacade.updateCompany(company);
			comp = adminFacade.getCompany(company.getId());
		}
		return comp;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the company details
	 * from dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// id = company ID.
	@Path("/company/{id}")
	public Company getCompany(@PathParam("id") int id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		if (adminFacade != null) {
			Company comp = adminFacade.getCompany(id);
			return comp;
		}
		return null;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the companies list
	 * from the dataBase.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/companies")
	public Collection<Company> getAllCompanies() throws CouponSystemExceptions, SQLException {
		// create new arrayList
		Collection<Company> allCompanies = new ArrayList<>();
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		if (adminFacade != null) {
			allCompanies = adminFacade.getAllCompanies();
		}
		return allCompanies;
	}

	/**
	 * Method handling HTTP PUT requests. The Method get Company and create it
	 * in the dataBase.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createCustomer")
	public Customer createCustomer(Customer customer) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Customer cust = new Customer();
		if (adminFacade != null) {
			Collection<Customer> customers = getAllCustomers();
			for (Customer c : customers) {
				if (c.getId() == customer.getId() || c.getCustName().equals(customer.getCustName())) {
					cust = new Customer(0, "The name and ID already exist.", null);
					break;
				}
				adminFacade.createCustomer(customer);
				cust = adminFacade.getCustomer(customer.getId());
			}
		}
		return cust;
	}
	
	/**
	 * Method handling HTTP DELETE requests. The Method delete the Customer from
	 * the dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// id = customer ID.
	@Path("/deleteCustomer/{id}")
	public Customer deleteCustomer(@PathParam("id") int id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Customer cust = new Customer();
		if (adminFacade != null) {
			// bring the customer from the dataBase by the index and delete it.
			cust = adminFacade.getCustomer(id);
			adminFacade.deleteCustomer(cust);
		}
		return cust;
	}

	/**
	 * Method handling HTTP PUT requests. The Method get the new customer's
	 * details and update it in the dataBase. The Method can't update the
	 * customer Name and ID.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateCustomer")
	public Customer updateCustomer(Customer customer) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		Customer cust = new Customer();
		if (adminFacade != null) {
			adminFacade.updateCustomer(customer);
			cust = adminFacade.getCustomer(customer.getId());
		}
		return cust;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the customers list
	 * from the dataBase.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers")
	public Collection<Customer> getAllCustomers() throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		// create new arrayList
		Collection<Customer> allCustomers = new ArrayList<>();
		if (adminFacade != null) {
			allCustomers = adminFacade.getAllCustomers();
			return allCustomers;
		}
		return null;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the customer details
	 * from dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// id = customer ID.
	@Path("/customer/{id}")
	public Customer getCustomer(@PathParam("id") int id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		if (adminFacade != null) {
			Customer cust = adminFacade.getCustomer(id);
			return cust;
		}
		return null;
	}

}
