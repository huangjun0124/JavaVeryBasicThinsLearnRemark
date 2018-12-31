package NetWork.NetworkFileTransfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerSide {
    public static void main(String[] args) throws IOException {
        
        System.out.println("Server process is down initing");
        ServerSocket ss = new ServerSocket(25041);
        Socket s = ss.accept();
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String filePath = "C:\\Users\\huang\\Desktop\\" + System.currentTimeMillis()+ "_tmpFromClient.txt";
        PrintWriter pr = new PrintWriter(new FileWriter(new File(filePath)), true);
        String line;
        while ((line=bufIn.readLine()) != null)
        {
            pr.println(line);
        }
        PrintWriter prc = new PrintWriter(new OutputStreamWriter(
            s.getOutputStream(), StandardCharsets.UTF_8), true);
        prc.println("File upload success文件上传成功!");
        System.out.println("Server process is down now");
        prc.close();
        pr.close();
        bufIn.close();
        ss.close();
    }
}
