package EPS.AppEW.SchulplanerByJAMP;

import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.dao.WeekDayDao;
import EPS.AppEW.SchulplanerByJAMP.entity.WeekDay;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Schedule extends Activity {
	
	

	private WeekDayDao weekDayDao;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);

		weekDayDao = new WeekDayDao(this);
		weekDayDao.open();

		List<WeekDay> weekDays = weekDayDao.getAllWeekDays();

		int[][] viewMapping = new int[][] {};
		
		viewMapping[0][0] = R.id.mon_h1;
		viewMapping[0][1] = R.id.mon_h2;
		viewMapping[0][2] = R.id.mon_h3;
		viewMapping[0][3] = R.id.mon_h4;
		viewMapping[0][4] = R.id.mon_h5;
		viewMapping[0][5] = R.id.mon_h6;
		viewMapping[0][6] = R.id.mon_h7;
		viewMapping[0][0] = R.id.mon_h8;
		
		viewMapping[1][0] = R.id.tue_h1;
		viewMapping[1][1] = R.id.tue_h2;
		viewMapping[1][2] = R.id.tue_h3;
		viewMapping[1][3] = R.id.tue_h4;
		viewMapping[1][4] = R.id.tue_h5;
		viewMapping[1][5] = R.id.tue_h6;
		viewMapping[1][6] = R.id.tue_h7;
		viewMapping[1][7] = R.id.tue_h8;
		
		viewMapping[2][0] = R.id.wen_h1;
		viewMapping[2][1] = R.id.wen_h2;
		viewMapping[2][2] = R.id.wen_h3;
		viewMapping[2][3] = R.id.wen_h4;
		viewMapping[2][4] = R.id.wen_h5;
		viewMapping[2][5] = R.id.wen_h6;
		viewMapping[2][6] = R.id.wen_h7;
		viewMapping[2][7] = R.id.wen_h8;
		
		viewMapping[3][0] = R.id.thu_h1;
		viewMapping[3][1] = R.id.thu_h2;
		viewMapping[3][2] = R.id.thu_h3;
		viewMapping[3][3] = R.id.thu_h4;
		viewMapping[3][4] = R.id.thu_h5;
		viewMapping[3][5] = R.id.thu_h6;
		viewMapping[3][6] = R.id.thu_h7;
		viewMapping[3][7] = R.id.thu_h8;
		
		viewMapping[4][0] = R.id.fri_h1;
		viewMapping[4][1] = R.id.fri_h2;
		viewMapping[4][2] = R.id.fri_h3;
		viewMapping[4][3] = R.id.fri_h4;
		viewMapping[4][4] = R.id.fri_h5;
		viewMapping[4][5] = R.id.fri_h6;
		viewMapping[4][6] = R.id.fri_h7;
		viewMapping[4][7] = R.id.fri_h8;
		
		for(WeekDay weekday : weekDays){
			TextView tv = (TextView) findViewById(viewMapping[weekday.getTag()][weekday.getStunde()]);
			tv.setText(weekday.getLesson().getName());
		}
	}

}
