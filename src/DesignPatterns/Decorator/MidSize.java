package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class MidSize extends Size
{
    private float size;

    public MidSize()
    {
        size = Config.size_mid;
    }

    public MidSize(float f)
    {
        size = f;
    }

    @Override
    public float getSize()
    {
        return size;
    }
}
