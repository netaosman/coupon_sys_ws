package Java_82.coupon_sys_ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import core.beans.ClientType;
import core.beans.Coupon;
import core.beans.CouponType;
import core.couponClientFacade.CustomerFacade;
import core.exeptions.CouponSystemExceptions;
import couponSystemSingleton.CouponSystem;

/**
 * Root resource (exposed at "customer" path)
 */
@Path("customer")
public class Customer_WS {
	

	@Context
	HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	CustomerFacade custFacade = null;

	/**
	 * Method handling HTTP PUT requests. The Method get Coupon and create it in
	 * the dataBase as a purchased coupon.
	 * 
	 * @throws SQLException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/purchaseCoupon")
	public Coupon purchaseCoupon(Coupon coupon) throws CouponSystemExceptions, SQLException {
		HttpSession session = request.getSession(false);
		CustomerFacade custFacade = (CustomerFacade) session.getAttribute("facade");
		Coupon coup = new Coupon();
		if (custFacade != null) {
			custFacade.PurchaseCoupon(coupon);
			Collection<Coupon> coupons = new ArrayList<>();
			coupons = custFacade.getAllCoupons();
			for (Coupon c : coupons) {
				if (c.getId() == coupon.getId()) {
					coup = c;
					break;
				}
			Coupon co = new Coupon(0, "id or title already exit", null, null, 0, null, null, 0, null);
				coup = co;
			}

		}
		return coup;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings all the customers
	 * Coupons from the dataBase.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coupons")
	public Collection<Coupon> getAllPurchasedCoupons() throws CouponSystemExceptions, SQLException {
		// create new arrayList
		Collection<Coupon> allCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		CustomerFacade custFacade = (CustomerFacade) session.getAttribute("facade");
		if (custFacade != null) {
			allCoupons = custFacade.getAllCoupons();
			return allCoupons;
		}
		return null;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings all the customer
	 * Coupons from the dataBase by type.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// Get the coupon type.
	@Path("/coupon/{couponType}")
	public Collection<Coupon> getCouponsByType(@PathParam("couponType") String couponType)
			throws CouponSystemExceptions, SQLException {
		CouponType type = CouponType.valueOf(couponType);
		// create new arrayList
		Collection<Coupon> allCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		CustomerFacade custFacade = (CustomerFacade) session.getAttribute("facade");
		if (custFacade != null) {
			allCoupons = custFacade.getAllPurchasedCouponsByType(type);
			return allCoupons;
		}
		return null;
	}

	/**
	 * Method handling HTTP GET requests. The Method brings all the customer
	 * Coupons from the dataBase up to the price that mentioned.
	 * 
	 * @throws SQLException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// Get the coupon type.
	@Path("/coupons/{price}")
	public Collection<Coupon> getCouponsByPrice(@PathParam("price") double price)
			throws CouponSystemExceptions, SQLException {
		// create new arrayList
		Collection<Coupon> allCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		CustomerFacade custFacade = (CustomerFacade) session.getAttribute("facade");
		if (custFacade != null) {
			allCoupons = custFacade.getCouponsUpToPrice(price);
			return allCoupons;
		}
		return null;
	}

}
