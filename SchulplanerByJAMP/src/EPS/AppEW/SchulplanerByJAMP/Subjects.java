package EPS.AppEW.SchulplanerByJAMP;

import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.dao.LessonDao;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Subjects extends Activity {
	
	private LessonDao lessonDao;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_subjects);
	    
	    // TODO @Andre: hier sind alle Fächer
	    lessonDao = new LessonDao(this);
	    lessonDao.open();
	    List<Lesson> lessons = lessonDao.getAllLessons();
	    System.out.println(lessons.size());
	    lessonDao.close();
	    
	
	/* BEISPIEL Begin*/
	String[] SubjectItems = {"Deutsch", "Mathe", "Englisch", "Wirtschaftslehre", "Geografie"};
	
	ListView lvSubjects = (ListView) findViewById(R.id.ListVSubjects); //Referenz der Liste auf das Control der View
	
	lvSubjects.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SubjectItems));
	
	
	String[] SubjectItemsDetails = {"Name: Deutsch", "Lehrer: Herr Schneider", "Raum: 208"};
	
	ListView lvSubjectsDetails = (ListView) findViewById(R.id.ListVSubjectsDetails);
	
	lvSubjectsDetails.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SubjectItemsDetails));
	/* BEISPIEL End*/
	
	    // TODO Auto-generated method stub
	}
	
	public void SubjectAdd_OnClick(View v)
	{
		Intent myIntent = new Intent(v.getContext(),
		  NewSubject.class);
		startActivityForResult(myIntent, 0);
	}
	
	public void BackFromFaecher_OnClick(View v)
	{
		Intent myIntent = new Intent(v.getContext(),
		  Options.class);
		startActivityForResult(myIntent, 0);
	}

}