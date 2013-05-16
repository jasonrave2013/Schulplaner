package EPS.AppEW.SchulplanerByJAMP.dao;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.DatabaseHelper;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class provides services to handle database transactions regarding Lesson entitys.
 * @author pascal.perau
 */
public class LessonDao {

	private SQLiteDatabase db;

	private DatabaseHelper dbHelper;

	private String[] columns = { DatabaseHelper.TABLE_LESSON_COLUMN_ID,
			DatabaseHelper.TABLE_LESSON_COLUMN_NAME, DatabaseHelper.TABLE_LESSON_COLUMN_TEACHER,
			DatabaseHelper.TABLE_LESSON_COLUMN_ROOM, DatabaseHelper.TABLE_LESSON_COLUMN_NOTE };

	public LessonDao(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
	    dbHelper.close();
	}
	
	public Lesson createLesson(Lesson persistableLesson){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_LESSON_COLUMN_NAME, persistableLesson.getName());
		values.put(DatabaseHelper.TABLE_LESSON_COLUMN_TEACHER, persistableLesson.getTeacherId());
		values.put(DatabaseHelper.TABLE_LESSON_COLUMN_ROOM, persistableLesson.getRoom());
		values.put(DatabaseHelper.TABLE_LESSON_COLUMN_NOTE, persistableLesson.getNote());
		long lessonId = db.insert(DatabaseHelper.TABLE_LESSON, null, values);
		Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, DatabaseHelper.TABLE_LESSON_COLUMN_ID + " = " + lessonId, null, null, null, null);
		cursor.moveToFirst();
		Lesson newLesson = cursorToLesson(cursor);
		cursor.close();
		return newLesson;
	}
	
	/**
	 * Deletes the {@link Lesson} from the database.
	 * Also removes {@link WeekDay}s associated with the lessons id.
	 * @param lesson
	 */
	public void deleteLesson(Lesson lesson) {
	    long id = lesson.getId();
	    System.out.println("Lesson deleted with id: " + id);
	    db.delete(DatabaseHelper.TABLE_LESSON, DatabaseHelper.TABLE_LESSON_COLUMN_ID + " = " + id, null);
	    db.delete(DatabaseHelper.TABLE_WEEKDAY, DatabaseHelper.TABLE_WEEKDAY_COLUMN_LESSONID + " = " + id, null);
	  }

	  public List<Lesson> getAllLessons() {
	    List<Lesson> lessons = new ArrayList<Lesson>();

	    Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Lesson l = cursorToLesson(cursor);
	      lessons.add(l);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return lessons;
	  }
	  
	public Lesson findLessonById(long id){
		Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, DatabaseHelper.TABLE_LESSON_COLUMN_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		return cursorToLesson(cursor);
	}
	
	private Lesson cursorToLesson(Cursor cursor) {
	    Lesson lesson = new Lesson();
	    lesson.setId(cursor.getLong(0));
	    lesson.setName(cursor.getString(1));
	    lesson.setTeacher(cursor.getLong(2));
	    lesson.setRoom(cursor.getString(3));
	    lesson.setNote(cursor.getString(4));
	    return lesson;
	  }
	
}
