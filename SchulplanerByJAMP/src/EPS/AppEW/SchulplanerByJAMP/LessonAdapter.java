package EPS.AppEW.SchulplanerByJAMP;

import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LessonAdapter extends ArrayAdapter<Lesson>{
	
	int resource;
    String response;
    Context context;
    
    //Initialize adapter
    public LessonAdapter(Context context, int resource, List<Lesson> items) {
        super(context, resource, items);
        this.resource=resource;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout lessonView;
        
        //Get the current lesson object
        Lesson l = getItem(position);
         
        //Inflate the view
        if(convertView==null)
        {
            lessonView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, lessonView, true);
        }
        else
        {
        	lessonView = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView lessonName = (TextView)lessonView.findViewById(android.R.id.text1);
        //Assign the appropriate data from our alert object above
        lessonName.setText(l.getName() == null ? "--UNKNOWN--" : l.getName());
         
        return lessonView;
    }
 

}
