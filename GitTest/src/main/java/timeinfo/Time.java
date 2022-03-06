package timeinfo;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Time {

	public int time_return(){ // 일단 일과 1 , 이외 0 으로 라벨링
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		SimpleDateFormat fourteen_format = new SimpleDateFormat("HHmm");  // 포맷
		int time =  Integer.parseInt(fourteen_format.format(date_now));
		System.out.println(time); // 시간, 분만 나오게함.
		
		if(time >= 0600 && time <= 1800) {
			System.out.println("일과시간");
			return 1;
		}
		else {
			System.out.println("일과 이외 시간");
			return 0;
		}
	}
}
