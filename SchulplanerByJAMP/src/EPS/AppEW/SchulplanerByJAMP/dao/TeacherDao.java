package EPS.AppEW.SchulplanerByJAMP.dao;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.DatabaseHelper;
import EPS.AppEW.SchulplanerByJAMP.entity.Teacher;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class privides services to handle database transacgtions.
 * @author janine.hoffmann
 */
public class TeacherDao {

	private SQLiteDatabase db;

	private DatabaseHelper dbHelper;

	private String[] columns = { DatabaseHelper.TABLE_TEACHER_COLUMN_ID,
		 DatabaseHelper.TABLE_TEACHER_COLUMN_NAME };

	public TeacherDao(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
	    dbHelper.close();
	}
	
	public Teacher createLehrer(Teacher persistableLehrer){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_TEACHER_COLUMN_NAME, persistableLehrer.getName());
		long teacherId = db.insert(DatabaseHelper.TABLE_TEACHER, null, values);
		Cursor cursor = db.query( DatabaseHelper.TABLE_TEACHER, columns, DatabaseHelper.TABLE_TEACHER_COLUMN_ID + " = " + teacherId, null, null, null, null);
		cursor.moveToFirst();
		Teacher newLehrer = cursorToLehrer(cursor);
		cursor.close();
		return newLehrer;
	}
	
	public Teacher getTeacherOfId(long teacher_id) {
		Cursor cursor = db.query( DatabaseHelper.TABLE_TEACHER, columns, DatabaseHelper.TABLE_TEACHER_COLUMN_ID + " = " + teacher_id, null, null, null, null);
		cursor.moveToFirst();
		Teacher newLehrer = cursorToLehrer(cursor);
		cursor.close();
		return newLehrer;		
	}
	
	public void deleteLehrer(Teacher lehrer) {
	    long id = lehrer.getId();
	    System.out.println("Lehrer deleted with id: " + id);
	    db.delete(DatabaseHelper.TABLE_TEACHER, DatabaseHelper.TABLE_TEACHER_COLUMN_ID + " = " + id, null);
	  }

	  public List<Teacher> getAllLehrer() {
	    List<Teacher> alleLehrer = new ArrayList<Teacher>();

	    Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Teacher l = cursorToLehrer(cursor);
	      alleLehrer.add(l);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return alleLehrer;
	  }
	
	private Teacher cursorToLehrer(Cursor cursor) {
		Teacher lehrer = new Teacher();
		lehrer.setId(cursor.getLong(0));
		lehrer.setName(cursor.getString(1));
	    return lehrer;
	  }
	
}
