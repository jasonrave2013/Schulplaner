package EPS.AppEW.SchulplanerByJAMP;

import android.app.Activity;
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
    	  Subjects.class); //v.getContext=Referenz auf den gerade ausgeführten View
    	startActivityForResult(myIntent, 0);
    }
    
    public void BackFromOptions_OnClick(View v)
    {
    	Intent myIntent = new Intent(v.getContext(),
    	  Home.class); //v.getContext=Referenz auf den gerade ausgeführten View
    	startActivityForResult(myIntent, 0);
    }

}
