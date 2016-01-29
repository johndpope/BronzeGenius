package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class SmallSize extends Size
{
    private float size;
    public SmallSize()
    {
        size = Config.size_small;
    }

    public SmallSize(float f)
    {
        size = f;
    }
    @Override
    public float getSize()
    {
        return size;
    }
}
