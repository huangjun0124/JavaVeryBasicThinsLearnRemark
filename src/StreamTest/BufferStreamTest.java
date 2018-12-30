package StreamTest;

import com.company.SimpleConsoleLog;

import java.io.*;
/*
重点注释参考行：【49】
计算机中都是用补码来表示二进制数据，一个全为1的byte，会被识别为-1
 */
public class BufferStreamTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        MyBufferReader bis = new MyBufferReader(new FileInputStream("D:\\剑指offer——名企面试官精讲典型编程题.pdf"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\testout.pdf"));
        int byteRead = 0;
        // read a single byte and write it to out stream
        while((byteRead = bis.read())!=-1)
        {
            bos.write(byteRead);;
        }
        long end = System.currentTimeMillis();
        SimpleConsoleLog.WriteLine("total seconds: " + (end-start)/1000);
        bis.close();
        bos.close();
    }
}

class MyBufferReader
{
    private byte[] _bytesRead;
    private InputStream _ins;
    public MyBufferReader(InputStream ins){
        _ins = ins;
        _bytesRead = new byte[1024 * 5];
    }

    private int _pos = 0;
    private int _readCount = 0;
    public int read() throws IOException {
        // 每次返回一个字节，先检查读入的缓冲区中是否有内容可返回，无则再次从流中读入
        if(_readCount == 0)
        {
            _readCount = _ins.read(_bytesRead);
            if(_readCount == -1)
                return -1;
            _pos = 0;
        }
        _readCount--;
        // 这里返回的值要用 字节与255，因为 读取的 byte 被提升为 int，当 byte 为全1，提升后的值会是-1（默认前补1）；按位与255（1111 1111）后可保证只留下最后一个字节的正确数据
        // 否则可能遇到某个字节的读取内容为 -1， 但文件其实并未结束
        return _bytesRead[_pos++] & 0xff;
    }

    public void close() throws IOException {
        _ins.close();
    }
}
