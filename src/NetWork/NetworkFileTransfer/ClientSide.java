package NetWork.NetworkFileTransfer;
/*
编译方法：命令行输入一下命令，不加 classpath 则会出现找不到程序包的错误，因为引用了 SimpleConsoleLog 类
javac  -encoding UTF-8 -classpath ..\..\..\out\production\JavaVeryBasicThinsLearnRemark\ ClientSide.java

单独运行方法：cmd进入包文件夹的上一层目录，即 src 目录，然后命令行：
java NetWork.NetworkFileTransfer.ClientSide
在包下的类，在Java源文件的地方编译后，需要到最外层包的上一级目录下运行，而且类前面需要带包名，以.隔开
 */
import com.company.SimpleConsoleLog;

import java.io.*;
import java.net.Socket;

public class ClientSide {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 25041);
        BufferedReader buf = new BufferedReader(new FileReader("C:\\Users\\huang\\Desktop\\tmpFile.txt"));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        String line = null;
        while ((line=buf.readLine()) != null)
        {
            out.println(line);
        }
        s.shutdownOutput(); // 关闭输出流
        BufferedReader sr = new BufferedReader(new InputStreamReader(s.getInputStream()));
        SimpleConsoleLog.WriteLine(sr.readLine());
        //String rRead = sr.readLine();
        //System.out.println(rRead);
        buf.close();
        sr.close();
        s.close();
    }
}
