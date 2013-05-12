package EPS.AppEW.SchulplanerByJAMP.entity;


public class weekDay {

	private long id;
	
	private long Lesson_id;

	private long stunde;
	private long tag;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getLessonId() {
		return Lesson_id;
	}

	public void setLessonId(long Lesson_id) {
		this.Lesson_id = Lesson_id;
	}

	public long getStunde() {
		return stunde;
	}

	public void setStunde(long stunde) {
		this.stunde = stunde;
	}
	
	public long getTag() {
		return tag;
	}

	public void setTag(long tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "weekDay [id=" + id + ",lesson_id=" + Lesson_id + ", stunde=" + stunde + "tag=" + tag + "]";
	}
}
