package EPS.AppEW.SchulplanerByJAMP.dao;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.DatabaseHelper;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import EPS.AppEW.SchulplanerByJAMP.entity.WeekDay;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class provides services to handle database transactions regarding weekdays.
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
	
	public WeekDay createWeekday(WeekDay persistableWeekDay){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_DAY, persistableWeekDay.getTag());
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_HOUR, persistableWeekDay.getStunde());
		values.put(DatabaseHelper.TABLE_WEEKDAY_COLUMN_LESSONID, persistableWeekDay.getLessonId());

		long weekDayId = db.insert(DatabaseHelper.TABLE_WEEKDAY, null, values);
		Cursor cursor = db.query(DatabaseHelper.TABLE_WEEKDAY, columns, DatabaseHelper.TABLE_WEEKDAY_COLUMN_ID + " = " + weekDayId, null, null, null, null);
		cursor.moveToFirst();
		WeekDay newWeekDay = cursorToweekDay(cursor);
		cursor.close();
		return newWeekDay;
	}
	
	public void deleteWeekday(WeekDay weekday) {
	    long id = weekday.getId();
	    System.out.println("Weekday deleted with id: " + id);
	    db.delete(DatabaseHelper.TABLE_WEEKDAY, DatabaseHelper.TABLE_WEEKDAY_COLUMN_ID+ " = " + id, null);
	  }

	  public List<WeekDay> getAllWeekDays() {
	    List<WeekDay> weekDays = new ArrayList<WeekDay>();
	    
	    Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_WEEKDAY + " a LEFT OUTER JOIN " + DatabaseHelper.TABLE_LESSON + " b ON a." + DatabaseHelper.TABLE_WEEKDAY_COLUMN_LESSONID + "= b." + DatabaseHelper.TABLE_LESSON_COLUMN_ID, null);

	    //Cursor cursor = db.query(DatabaseHelper.TABLE_WEEKDAY, columns, null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      WeekDay l = cursorToweekDay(cursor);
	      weekDays.add(l);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return weekDays;
	  }
	
	private WeekDay cursorToweekDay(Cursor cursor) {
	    WeekDay weekday = new WeekDay();
	    weekday.setId(cursor.getLong(0));
	    
	    Lesson lesson = new Lesson();
	    lesson.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TABLE_LESSON_COLUMN_ID)));
	    lesson.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LESSON_COLUMN_NAME)));
	    lesson.setTeacher(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TABLE_LESSON_COLUMN_TEACHER)));
	    lesson.setRoom(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LESSON_COLUMN_ROOM)));
	    lesson.setNote(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LESSON_COLUMN_NOTE)));
	    
	    weekday.setLesson(lesson);
	    weekday.setTag(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_WEEKDAY_COLUMN_DAY)));
	    weekday.setStunde(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_WEEKDAY_COLUMN_HOUR)));
	    
	    return weekday;
	  }
	
}
