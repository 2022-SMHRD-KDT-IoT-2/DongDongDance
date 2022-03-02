package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.LightSenDAO;

public class RegLightSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int plug = Integer.parseInt(request.getParameter("plug"));
		int value = Integer.parseInt(request.getParameter("setting"));

	    LightSenDAO dao = new LightSenDAO();
		dao.regLightSen(plug, value);
		
		return "#.jsp";
	}

}
