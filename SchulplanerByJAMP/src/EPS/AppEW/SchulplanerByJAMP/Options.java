package EPS.AppEW.SchulplanerByJAMP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Options extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_options);
	
	    // TODO Auto-generated method stub
	}
	
    public void OpenFaecher_OnClick(View v)
    {
    	Intent myIntent = new Intent(v.getContext(),
    	  Subjects.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void DeleteStundenplan_OnClick(View v)
    {
    	new AlertDialog.Builder(this)
        	.setIcon(android.R.drawable.ic_delete)
        	.setTitle("Stundenplan löschen")
        	.setMessage("Soll der Stundenplan wirklich gelöscht werden?")
        	.setPositiveButton("Ja", new DialogInterface.OnClickListener()
        	{
        		@Override
        		public void onClick(DialogInterface dialog, int which) 
        		{
        			// TODO @ALL: Stundenplantabelle leeren, etc.   
        		}
        	})
        	.setNegativeButton("Nein", null)
        	.show();
    }
    
    public void BackFromOptions_OnClick(View v)
    {
    	Intent myIntent = new Intent(v.getContext(),
    	  Home.class);
    	startActivityForResult(myIntent, 0);
    }

}
