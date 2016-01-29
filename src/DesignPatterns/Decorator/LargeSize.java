package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class LargeSize extends Size
{
    private float size;

    public LargeSize()
    {
        size = Config.size_large;
    }

    public LargeSize(float f)
    {
        size = f;
    }

    @Override
    public float getSize()
    {
        return size;
    }
}
