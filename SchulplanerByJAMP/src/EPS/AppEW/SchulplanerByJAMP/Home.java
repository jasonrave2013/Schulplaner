package EPS.AppEW.SchulplanerByJAMP;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import EPS.AppEW.SchulplanerByJAMP.Options;
import EPS.AppEW.SchulplanerByJAMP.R;
import android.view.View;
import android.content.Intent;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //Verknüpfung der Klasse mit der Layoutdatei
    }
    
    public void OpenOptions_OnClick(View v)
    {
    	Intent myIntent = new Intent(v.getContext(),
    	  Options.class); //v.getContext=Referenz auf den gerade ausgeführten View
    	startActivityForResult(myIntent, 0);
    } 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
}
