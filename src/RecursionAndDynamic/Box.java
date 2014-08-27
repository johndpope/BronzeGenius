package RecursionAndDynamic;

public class Box {
	private float width;
	private float height;
	private float length;
	
	public Box()
	{
		width = height = length = 0;
	}
	
	public Box(float width, float height, float length)
	{
		this.width = width;
		this.height = height;
		this.length = length;
	}
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	public boolean isLarger(Box box)
	{
		return this.width > box.width && this.height > box.height && this.length > box.length;
	}
	
	public boolean isEqual(Box box)
	{
		return this.width == box.width && this.height == box.height && this.length == box.length; 
	}
	
	public float getVolumn()
	{
		return width * height * length;
	}
}
