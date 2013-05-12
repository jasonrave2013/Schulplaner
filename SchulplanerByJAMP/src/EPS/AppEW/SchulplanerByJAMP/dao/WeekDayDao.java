package EPS.AppEW.SchulplanerByJAMP.dao;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.DatabaseHelper;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import EPS.AppEW.SchulplanerByJAMP.entity.weekDay;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class privides services to handle database transacgtions.
 * @author janine.hoffmann
 */
public class WeekDayDao {

	private SQLiteDatabase db;

	private DatabaseHelper dbHelper;

	private String[] columns = { DatabaseHelper.TABLE_WEEKDAY_COLUMN_ID,
			DatabaseHelper.TABLE_WEEKDAY_COLUMN_LESSONID, DatabaseHelper.TABLE_WEEKDAY_COLUMN_DAY,
			DatabaseHelper.TABLE_WEEKDAY_COLUMN_HOUR};

	public WeekDayDao(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
	    dbHelper.close();
	}
	
	public weekDay createWeekday(weekDay persistableWeekDay){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_DAY, persistableWeekDay.getTag());
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_HOUR, persistableWeekDay.getStunde());
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_LESSONID, persistableWeekDay.getLessonId());

		long weekDayId = db.insert(DatabaseHelper.TABLE_WEEKDAY, null, values);
		Cursor cursor = db.query(DatabaseHelper.TABLE_WEEKDAY, columns, DatabaseHelper.TABLE_WEEKDAY_COLUMN_ID + " = " + weekDayId, null, null, null, null);
		cursor.moveToFirst();
		weekDay newWeekDay = cursorToweekDay(cursor);
		cursor.close();
		return newWeekDay;
	}
	
	public void deleteWeekday(weekDay weekday) {
	    long id = weekday.getId();
	    System.out.println("Weekday deleted with id: " + id);
	    db.delete(DatabaseHelper.TABLE_WEEKDAY, DatabaseHelper.TABLE_WEEKDAY_COLUMN_ID+ " = " + id, null);
	  }

	  public List<weekDay> getAllWeekDays() {
	    List<weekDay> weekDays = new ArrayList<weekDay>();

	    Cursor cursor = db.query(DatabaseHelper.TABLE_WEEKDAY, columns, null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      weekDay l = cursorToweekDay(cursor);
	      weekDays.add(l);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return weekDays;
	  }
	
	private weekDay cursorToweekDay(Cursor cursor) {
	    weekDay weekday = new weekDay();
	    weekday.setId(cursor.getLong(0));
	    weekday.setLessonId(cursor.getLong(1));
	    weekday.setTag(cursor.getLong(2));
	    weekday.setStunde(cursor.getLong(3));
	    return weekday;
	  }
	
}
