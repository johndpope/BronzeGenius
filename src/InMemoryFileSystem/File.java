package InMemoryFileSystem;

public class File extends Entry {
	private String content;
	private int size;
	
	public File(String n, Directory p, int size) {
		super(n, p);
		this.size = size;
	}

	public int size() { return size; }
	public String getContents() { return content;}
	public void setContents(String c) { content = c; }
}
