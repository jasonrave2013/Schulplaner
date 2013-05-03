package EPS.AppEW.SchulplanerByJAMP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Subjects extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_subjects);
	
	/* BEISPIEL Begin*/
	String[] SubjectItems = {"Deutsch", "Mathe", "Englisch", "Wirtschaftslehre", "Geografie"};
	
	ListView lvSubjects = (ListView) findViewById(R.id.ListVSubjects); //Referenz der Liste auf das Control der View
	
	lvSubjects.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SubjectItems));
	
	
	String[] SubjectItemsDetails = {"Name: Deutsch", "Lehrer: Herr Schneider", "Raum: 208"};
	
	ListView lvSubjectsDetails = (ListView) findViewById(R.id.ListVSubjectsDetails); //Referenz der Liste auf das Control der View
	
	lvSubjectsDetails.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SubjectItemsDetails));
	/* BEISPIEL End*/
	
	    // TODO Auto-generated method stub
	}
	
	public void BackFromFaecher_OnClick(View v)
	{
		Intent myIntent = new Intent(v.getContext(),
		  Options.class);
		startActivityForResult(myIntent, 0);
	}

}