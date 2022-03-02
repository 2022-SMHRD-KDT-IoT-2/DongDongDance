package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.LightDAO;

public class UpdateLightStCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String status = request.getParameter("status");
		
		LightDAO dao = new LightDAO();
		dao.updateStatus(seq, status);
		
		return "#.jsp";
	}

}
