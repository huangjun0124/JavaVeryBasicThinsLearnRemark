package utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/util/getVerifyCode")
public class VerifyCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 120, height = 30;
        //创建内存对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //图像
        Graphics g = image.getGraphics();
        g.setColor(Color.YELLOW);
        ////设置背景填充黄色
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        //加干扰线-蓝色
        for(int i=0;i<4;i++){
            int xStart = new Random().nextInt(width);
            int yStart = new Random().nextInt(height);
            int xEnd = new Random().nextInt(width);
            int yEnd = new Random().nextInt(height);
            g.drawLine(xStart, yStart, xEnd, yEnd);
        }

        int xPos = 20;
        //画 4 个随机数
        for(int i = 0; i< 4;i++){
            g.drawString(new Random().nextInt(9) + "", xPos, 20);
            xPos+=20;
        }

        //输出图片到客户端，设置响应格式
        resp.setContentType("image/jpeg");
        ImageIO.write(image, "JPEG",resp.getOutputStream());
    }
}
