package StreamTest;

import com.company.SimpleConsoleLog;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeStreamTest {
    public static void main(String[] args) throws IOException {
        PipedInputStream ins = new PipedInputStream();
        PipedOutputStream ous = new PipedOutputStream();
        ins.connect(ous);

        new Thread(new Read(ins)).start();
        new Thread(new Write(ous)).start();
    }
}

class Read implements Runnable{
    private PipedInputStream _ins;
    public Read(PipedInputStream ins){
        _ins = ins;
    }
    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        try
        {
            SimpleConsoleLog.WriteLine("Read 等待管道数据...");
            int len = _ins.read(bytes); // 此方法为阻塞式调用
            String str = new String(bytes,0,len);
            SimpleConsoleLog.WriteLine("Read 读取到数据：" + str);
            _ins.close();
        }
        catch (IOException e){
            SimpleConsoleLog.WriteLine("Read 读取管道失败");
        }
    }
}

class Write implements Runnable{
    private PipedOutputStream _ous;
    public Write(PipedOutputStream ous){
        _ous = ous;
    }
    @Override
    public void run() {
        try
        {
            SimpleConsoleLog.WriteLine("Write 等待10秒再写入数据...");
            Thread.sleep(10 * 1000);
            _ous.write("输出一小段数据哦哈哈".getBytes());
            SimpleConsoleLog.WriteLine("Write 已写入数据...");
            _ous.close();
        }
        catch (IOException | InterruptedException e){
            SimpleConsoleLog.WriteLine("Write 写入管道失败");
        }
    }
}
