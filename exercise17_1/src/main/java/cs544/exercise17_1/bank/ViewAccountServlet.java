package cs544.exercise17_1.bank;

import cs544.exercise17_1.bank.domain.Account;
import cs544.exercise17_1.bank.service.AccountService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			int accountnumber = Integer.parseInt(req.getParameter("accountnumber"));
// get customerService bean from spring
		ServletContext context = getServletContext();
		WebApplicationContext applicationContext =
				WebApplicationContextUtils.getWebApplicationContext(context);
		AccountService accServ = applicationContext.getBean(
				"accountService", AccountService.class);
// make customer available in request, for view rendering
		Account acc = accServ.getAccount(accountnumber);
		req.setAttribute("acc", acc);
// forward to view customer page
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
