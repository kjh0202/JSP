package member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BbsMember {
	private static final Logger LOG = LoggerFactory.getLogger(BbsMember.class);

	private int id; 
	private String title;
	private String name;
	private String date;
	private String content;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String fullString() {
		return "BbsFull [id=" + id + ", title=" + title + ", name=" + name + 
				", date=" + date + "\n         content=" + content + "]";
	}
	@Override
	public String toString() {
		LOG.debug("toString");
		return "BbsMember [id=" + id + ", title=" + title + ", name=" + name + 
				", date=" + date + "]";
	}
}