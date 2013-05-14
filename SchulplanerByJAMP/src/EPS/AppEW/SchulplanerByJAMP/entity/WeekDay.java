package EPS.AppEW.SchulplanerByJAMP.entity;


public class WeekDay {

	private long id;
	
	private Lesson lesson;

	private int stunde;
	private int tag;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getLessonId() {
		return lesson == null ? -1l : lesson.getId();
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public int getStunde() {
		return stunde;
	}

	public void setStunde(int stunde) {
		this.stunde = stunde;
	}
	
	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "weekDay [id=" + id + ",lesson_id=" + getLessonId() + ", stunde=" + stunde + "tag=" + tag + "]";
	}
}
