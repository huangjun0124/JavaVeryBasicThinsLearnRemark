import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/getHtml")
public class ReturnHtmlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String filePath = this.getServletContext().getRealPath("/OtherHtml/test.html");
        InputStream fis = new FileInputStream(filePath);
        OutputStream os = resp.getOutputStream();
        byte[] bis = new byte[1024];
        while(-1 != fis.read(bis)){
            os.write(bis);
        }
        fis.close();
        os.flush();
        os.close();
    }
}
