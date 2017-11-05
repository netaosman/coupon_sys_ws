package Java_82.coupon_sys_ws;

import java.io.IOException;
import java.sql.SQLException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.beans.ClientType;
import core.couponClientFacade.CouponClientFacade;
import core.exeptions.CouponSystemExceptions;
import couponSystemSingleton.CouponSystem;

/**
 * Servlet implementation class Login
 */
@WebServlet("/loginMain")

public class loginMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponSystem system;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginMain() {
		super();
	}

	// initiate CouponsSystem
	@Override
	public void init() throws ServletException {
		try {
			system = CouponSystem.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		session = request.getSession(true); // create a new session

		// getting the parameters from the login.html form
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String clientType = request.getParameter("type");
		ClientType type = ClientType.valueOf(clientType);

		CouponClientFacade facade;
		
		try {
			facade = system.login(username, password, type);
			if (facade != null) {
				session.setAttribute("facade", facade);
				// forward to the right page according to the Client Type
				switch (type) {
				case Admin:
					request.getRequestDispatcher("/htmlMain/admin.html").forward(request, response);
					break;

				case Company:
					request.getRequestDispatcher("/htmlMain/company.html").forward(request, response);
					break;

				case Customer:
					request.getRequestDispatcher("/htmlMain/customer.html").forward(request, response);
					break;

				default:
					break;
				}
			}

			else {
				// return to the Login form if the user name or password are
				// incorrect
				// request.getSession().setAttribute("facade", null);
				response.sendRedirect("http://localhost:8080/coupon_sys_ws/webapi/loginMain");
			}

		} catch (CouponSystemExceptions e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
