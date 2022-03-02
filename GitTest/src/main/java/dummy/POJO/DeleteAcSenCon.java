package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.AirconditionerSenDAO;

public class DeleteAcSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

	    AirconditionerSenDAO dao = new AirconditionerSenDAO();
		dao.deleteAcSen(seq);
		
		return "#.jsp";
	}

}
