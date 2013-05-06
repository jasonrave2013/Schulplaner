package EPS.AppEW.SchulplanerByJAMP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
		EditText SubjName = (EditText) findViewById(R.id.EditTAddNameBez);
		String strName = SubjName.getText().toString();
		
		EditText SubjTeacher = (EditText) findViewById(R.id.EditTAddLehrerBez);
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
		
		// TODO @Pascal: Instanzierung der Klasse, etc.
		
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
