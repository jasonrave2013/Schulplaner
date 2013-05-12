package EPS.AppEW.SchulplanerByJAMP.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME = "Schulplaner.db";
	
	public static final String TABLE_LESSON = "lesson";
	
	public static final String TABLE_LESSON_COLUMN_ID = "_id";
	public  static final String TABLE_LESSON_COLUMN_NAME = "name";
	public static final String TABLE_LESSON_COLUMN_TEACHER = "teacher_ID";
	public static final String TABLE_LESSON_COLUMN_ROOM = "room";
	public static final String TABLE_LESSON_COLUMN_NOTE = "note";
	
	public static final String TABLE_TEACHER = "teacher";
	
	public static final String TABLE_TEACHER_COLUMN_ID = "_id";
	public  static final String TABLE_TEACHER_COLUMN_NAME = "name";
	
	public static final String TABLE_WEEKDAY = "weekday";
	
	public static final String TABLE_WEEKDAY_COLUMN_ID = "_id";
	public  static final String TABLE_WEEKDAY_COLUMN_LESSONID = "lessons_id";
	public static final String TABLE_WEEKDAY_COLUMN_HOUR = "stunde";
	public static final String TABLE_WEEKDAY_COLUMN_DAY = "tag";
	
	private static final String CREATE_TABLE_WEEKDAY = "CREATE TABLE " + TABLE_WEEKDAY + "("
			+ TABLE_WEEKDAY_COLUMN_ID + " integer primary key autoincrement, " 
			+ TABLE_WEEKDAY_COLUMN_LESSONID + " integer not null, " 
			+ TABLE_WEEKDAY_COLUMN_HOUR + " integer, "
			+ TABLE_WEEKDAY_COLUMN_DAY + " integer);";
	
	private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + TABLE_LESSON + "("
			+ TABLE_LESSON_COLUMN_ID + " integer primary key autoincrement, " 
			+ TABLE_LESSON_COLUMN_NAME + " text not null, " 
			+ TABLE_LESSON_COLUMN_TEACHER + " integer, "
			+ TABLE_LESSON_COLUMN_ROOM + " text, "
			+ TABLE_LESSON_COLUMN_NOTE + " text);";
	
	private static final String CREATE_TABLE_TEACHER = "CREATE TABLE " + TABLE_TEACHER + "("
			+ TABLE_TEACHER_COLUMN_ID + " integer primary key autoincrement, " 
			+ TABLE_TEACHER_COLUMN_NAME + " text not null );";
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_LESSON);
		db.execSQL(CREATE_TABLE_TEACHER);
		db.execSQL(CREATE_TABLE_WEEKDAY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEKDAY);
		    onCreate(db);
	}
	
	
	

}
