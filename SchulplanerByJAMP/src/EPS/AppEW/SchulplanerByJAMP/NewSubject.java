package EPS.AppEW.SchulplanerByJAMP;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.dao.LessonDao;
import EPS.AppEW.SchulplanerByJAMP.dao.TeacherDao;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import EPS.AppEW.SchulplanerByJAMP.entity.Teacher;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewSubject extends Activity {
	private LessonDao lessonDao;
	private TeacherDao teacherDAO;
	
	private List<Teacher> teacherList;
	
	private AutoCompleteTextView actv;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.acitivity_newsubject);
	
	    // TODO Auto-generated method stub
	    lessonDao = new LessonDao(this);
		lessonDao.open();
		teacherDAO = new TeacherDao(this);
		teacherDAO.open();
		teacherList = teacherDAO.getAllLehrer();
		
		actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		List<String> teacherNames = new ArrayList<String>();
		
		for (Teacher teacher : teacherList) {
			teacherNames.add(teacher.getName());	
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,teacherNames);
		actv.setThreshold(1);
		actv.setAdapter(adapter);
	}
	
	public void FinalAddSubject_OnClick(View v)
	{
		EditText SubjName = (EditText) findViewById(R.id.EditTAddNameBez);
		String strName = SubjName.getText().toString();
		
		AutoCompleteTextView SubjTeacher = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		String strTeacher = SubjTeacher.getText().toString();
		
		EditText SubjRoom = (EditText) findViewById(R.id.EditTAddRaumBez);
		String strRoom = SubjRoom.getText().toString();
		
		if ((strName.length() == 0) || (strTeacher.length() == 0) || strRoom.length() == 0) 
		{
			new AlertDialog.Builder(this)
	        	.setIcon(android.R.drawable.ic_dialog_alert)
	        	.setTitle("Angaben unvollständig")
	        	.setMessage("Es wurden nicht alle Felder ausgefüllt!")
	        	.setNeutralButton("OK", null)
	        	.show();
			return;
		}
		
		long idTeacher = -1;
		for (Teacher teacher: teacherDAO.getAllLehrer()) {
			if (teacher.getName().compareTo(strTeacher)==0) {
				idTeacher = teacher.getId();
			}
		}
		
		if (idTeacher == -1) {
			idTeacher = 
			teacherDAO.createLehrer(new Teacher(strTeacher)).getId();
		}
		
		Lesson l = new Lesson();
		l.setName(strName);
		
		l.setTeacher(idTeacher);
		l.setRoom(strRoom);
		
		l = lessonDao.createLesson(l);
		lessonDao.close();
		
		Toast.makeText(this, "Lesson: " + l.getName() + "(id - " + l.getId() + ") created.", Toast.LENGTH_LONG).show();
		Intent myIntent = new Intent(v.getContext(), Subjects.class);
		startActivityForResult(myIntent, 0);
	}
	
	public void BackFromNewSubject_OnClick(View v)
	{
		Intent myIntent = new Intent(v.getContext(),
		  Subjects.class);
		startActivityForResult(myIntent, 0);
	}

}
