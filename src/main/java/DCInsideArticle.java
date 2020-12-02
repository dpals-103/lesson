
public class DCInsideArticle {
	
	private int id; 
	private String title; 
	private String name; 
	private String ipStart; 
	private String date; 
	private int count; 
	private int recommend; 
	

	public DCInsideArticle(int id, String title, String name, String ipStart, String date, int count, int recommend) {
		
		this.id = id;
		this.title = title;
		this.name = name; 
		this.ipStart = ipStart;
		this.date = date;
		this.count = count;
		this.recommend = recommend;
	
	} 
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	

	
	public String toString() {
		return "[" + id + "/" + title + "/" + name + "/" + ipStart + "/" + date + "/" + count + "/"+ recommend + "]";
		
	}
	
	
}
