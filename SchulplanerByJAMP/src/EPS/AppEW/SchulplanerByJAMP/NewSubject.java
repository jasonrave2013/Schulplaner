package EPS.AppEW.SchulplanerByJAMP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewSubject extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.acitivity_newsubject);
	
	    // TODO Auto-generated method stub
	}
	
	public void FinalAddSubject_OnClick(View v)
	{
		//TODO Pascal: Instanzierung der Klasse, etc.
		Intent myIntent = new Intent(v.getContext(),
		  Subjects.class);
		startActivityForResult(myIntent, 0);
	}
	
	public void BackFromNewSubject_OnClick(View v)
	{
		Intent myIntent = new Intent(v.getContext(),
		  Subjects.class);
		startActivityForResult(myIntent, 0);
	}

}
