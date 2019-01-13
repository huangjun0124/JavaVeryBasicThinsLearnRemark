import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/getFileFoc")
public class GetFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "04.JSP.docx";

        //下载文件
        String path = "C:\\Users\\huang\\Desktop\\课件\\servlet jsp\\" + filename;
        FileInputStream fis = new FileInputStream(path);

        //设置请求头,文件名需要UTF-8编码
        filename = URLEncoder.encode(filename, "UTF-8");
        resp.setHeader("Content-disposition", "attachment;filename=" + filename);
        byte[] bs = new byte[1024];
        ServletOutputStream sos = resp.getOutputStream();
        int len = 0;
        while((len = fis.read(bs)) != -1){
            sos.write(bs, 0, len);
        }

        fis.close();
    }
}
