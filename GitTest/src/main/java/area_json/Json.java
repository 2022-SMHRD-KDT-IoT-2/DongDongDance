package area_json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

import com.DAO.PlugDAO;
import com.VO.PlugVO;

public class Json {

	public static void main(String[] args) {
		PlugDAO pDao = new PlugDAO();
		ArrayList<PlugVO> al11 = pDao.selectStatus("1");
		ArrayList<PlugVO> al12 = pDao.selectStatus("0");
		String result = "{\"led\":\"" + 1 + "\"}";
		System.out.println(result);

		String st = "{\"";
//		int[] onlist = new int[al11.size()];

		for (int i = 0; i < al11.size(); i++) {
			System.out.println("al11:" + al11.get(i).getPlugSeq());
		}

		for (int i = 0; i < al12.size(); i++) {
			System.out.println("al12:" + al12.get(i).getPlugSeq());
		}

		
		JSONObject jsonob = new JSONObject();
		JSONArray jsonarr = new JSONArray();

		for (int i = 1; i < 3; i++) {

			JSONObject data = new JSONObject();

			data.put("이름", "인간_" + i);
			data.put("나이", 10 + i);
			data.put("주소", "서울 중앙로 " + i + "길");
			jsonarr.add(data);

		}

		jsonob.put("사람들", jsonarr);

		System.out.println(jsonob);

	}

}
