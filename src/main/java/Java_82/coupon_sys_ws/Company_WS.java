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
import core.beans.Coupon;
import core.beans.CouponType;
import core.beans.Customer;
import core.couponClientFacade.AdminFacade;
import core.couponClientFacade.CompanyFacade;
import core.exeptions.CouponSystemExceptions;
import couponSystemSingleton.CouponSystem;

/**
 * Root resource (exposed at "company" path)
 */
@Path("company")
public class Company_WS {

	@Context
	HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	CompanyFacade compFacade = null;

	/**
	 * Method handling HTTP PUT requests. The Method get Coupon and create it in
	 * the dataBase.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createCoupon")
	public Coupon createCoupon(Coupon coupon) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		Coupon coup = new Coupon();
		if (compFacade != null) {
			Collection<Coupon> coupons = compFacade.getAllCouponsNoPrint();
			for (Coupon c : coupons) {
				if (c.getId() == coupon.getId() || c.getTitle().equals(coupon.getTitle())) {
					coup = new Coupon(0, "id or title already exit", null, null, 0, null, null, 0, null);
					break; 
				}
			}
			compFacade.createCoupon(coupon);
			coup = coupon;
		}
		return coup;
	}

	/**
	 * Method handling HTTP DELETE requests. The Method delete Coupon from the
	 * dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// id = coupon ID.
	@Path("/deleteCoupon/{id}")
	public Coupon deleteCoupon(@PathParam("id") long id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		Coupon coup = new Coupon();
		if (compFacade != null) {
			// bring the coupon from the dataBase by the index and delete it.
			coup = compFacade.getCouponDetails(id);
			compFacade.deleteCoupon(coup);
		}
		return coup;
	}

	/**
	 * Method handling HTTP PUT requests. The Method get the new coupon's
	 * details and update it in the dataBase. The Method can update the coupon's
	 * Price and the end Date.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateCoupon")
	public Coupon updateCoupon(Coupon coupon) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		Coupon coup = new Coupon();
		if (compFacade != null) {
			compFacade.updateCoupon(coupon);
			coup = getCoupon(coupon.getId());
		}
		return coup;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the coupon's details
	 * from dataBase by ID.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// id = coupon ID.
	@Path("/coupon/{id}")
	public Coupon getCoupon(@PathParam("id") long id) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		Coupon coupon = new Coupon(0, "id is incorrect", null, null, 0, null, null, 0, null);
		if (compFacade != null) {
			if (!compFacade.getAllCouponsNoPrint().isEmpty()) {
				Coupon coup = compFacade.getCouponDetails(id);
				return coup;
			} else {
				return coupon;
			}
		}
		return coupon;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings all the company
	 * Coupons from the dataBase.
	 * 
	 * @throws SQLException
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coupons")
	public Collection<Coupon> getAllCoupons() throws CouponSystemExceptions, SQLException {
		// create new arrayList
		Collection<Coupon> allCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		if (compFacade != null) {
			allCoupons = compFacade.getAllCouponsNoPrint();
			return allCoupons;
		}
		return null;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings the company Coupons
	 * from the dataBase by type.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// Get the coupon type.
	@Path("/couponType/{couponType}")
	public Collection<Coupon> getCouponsByType(@PathParam("couponType") String strCouponType)
			throws CouponSystemExceptions, SQLException {
		CouponType type = CouponType.valueOf(strCouponType);
		// create new arrayList
		Collection<Coupon> allCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		CompanyFacade compFacade = (CompanyFacade) session.getAttribute("facade");
		if (compFacade != null) {
			allCoupons = compFacade.getCouponsByType(type);
			return allCoupons;
		}
		return null;
	}

}
