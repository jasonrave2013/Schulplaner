package EPS.AppEW.SchulplanerByJAMP;

import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.dao.LessonDao;
import EPS.AppEW.SchulplanerByJAMP.dao.TeacherDao;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Subjects extends Activity {

	private LessonDao lessonDao;
	private TeacherDao teacherDAO;
	private ListView lvSubjects;
	
	private ListView lvSubjectsDetails;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjects);

		//get all lessons
		lessonDao = new LessonDao(this);
		lessonDao.open();
		teacherDAO = new TeacherDao(this);
		teacherDAO.open();
		final List<Lesson> lessons = lessonDao.getAllLessons();
		System.out.println(lessons.size());
		
		
		//prepare views
		lvSubjects = (ListView) findViewById(R.id.ListVSubjects); // Referenz der Liste auf das Control der View
		lvSubjectsDetails = (ListView) findViewById(R.id.ListVSubjectsDetails);
		lvSubjects.setAdapter(new LessonAdapter(this, android.R.layout.simple_list_item_1, lessons));
		
		//change detail area if necessary
		lvSubjects.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				Lesson l = lessons.get(position);
				String[] details = {l.getName(), teacherDAO.getTeacherOfId(l.getTeacher()).getName(), l.getRoom()};
			    lvSubjectsDetails.setAdapter(new ArrayAdapter<String>(Subjects.this, android.R.layout.simple_list_item_1, details));
			}
		});
		
	}

	public void SubjectAdd_OnClick(View v) {
		Intent myIntent = new Intent(v.getContext(), NewSubject.class);
		startActivityForResult(myIntent, 0);
	}

	public void BackFromFaecher_OnClick(View v) {
		Intent myIntent = new Intent(v.getContext(), Options.class);
		startActivityForResult(myIntent, 0);
	}

}