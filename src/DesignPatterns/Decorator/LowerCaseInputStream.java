package DesignPatterns.Decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuch on 2016/1/27.
 */
public class LowerCaseInputStream extends FilterInputStream
{
    public LowerCaseInputStream(InputStream in)
    {
        super(in);
    }

    @Override
    public int read() throws IOException
    {
        int seek = super.read();
        return ( seek == -1 ? seek : Character.toLowerCase((char)seek) );
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException
    {
        int seek = super.read(b, off, len);
        for ( int i = 0; i < off + len; i++ ) b[i] = (byte) Character.toLowerCase((char)b[i]);
        return seek;
    }
}
