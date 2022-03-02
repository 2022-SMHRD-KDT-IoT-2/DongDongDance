package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.LightSenDAO;

public class DeleteLightSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

	    LightSenDAO dao = new LightSenDAO();
		dao.deleteLightSen(seq);
		
		return "#.jsp";
	}

}
