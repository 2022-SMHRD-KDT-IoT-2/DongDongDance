package dummy.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import dummy.DAO.LightDAO;

public class UpdateLightCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String loc = request.getParameter("loc");
		int setting = Integer.parseInt(request.getParameter("setting"));
		String id = request.getParameter("id");
		int plug = Integer.parseInt(request.getParameter("plug"));
		String area = request.getParameter("area");

		LightDAO dao = new LightDAO();
		dao.updateLight(seq, loc, setting, id, plug, area);
		
		return "main2.jsp";
	}

}
