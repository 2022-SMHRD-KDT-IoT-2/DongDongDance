package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.AirconditionerDAO;

public class DeleteAcCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

		AirconditionerDAO dao = new AirconditionerDAO();
		dao.deleteAc(seq);

		return "main2.jsp";
	}

}
