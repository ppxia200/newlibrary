package ssm.library.servlet;

import java.awt.*;  
import java.awt.image.*;  
import java.io.*;  
import java.util.*;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import javax.imageio.ImageIO;  

@WebServlet(urlPatterns={"/authCode.servlet"})
public class MyVercode extends HttpServlet {  
  
	 private static final int WIDTH = 80;//设置验证码图片宽度
	 private static final int HEIGHT = 30;//设置验证码图片高度
	 private static final int LENGTH = 4;//设置验证码长度
	 //设置验证码随机出现的字符
	 private static final String str = "1234567890" +
	            "abcdefghijklmnopqrstuvwxyz" +
	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[] chars = str.toCharArray();//将字符放在数组中方便随机读取
    private static Random random = new Random();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {

        //设置输出的类型为图片
        response.setContentType("image/jpeg");

        //设置不进行缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //相当于image
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);

        //画笔
        Graphics graphics = image.getGraphics();

        //设置背景颜色并绘制矩形背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //用于记录生成的验证码
        String code = "";

        //生成验证码并绘制
        graphics.setFont(new Font("楷体", Font.PLAIN, 22));//设置字体
        for (int i = 0; i < LENGTH; i++) {
            String c = chars[random.nextInt(str.length())] +"";
            graphics.setColor(getColor());
            graphics.drawString(c, 15 * i + 15, 22 + (i % 2)*3);
            code += c;
        }

        //生成干扰点
        for (int i = 0; i < 100; i++) {
            graphics.setColor(getColor());
            graphics.drawOval(random.nextInt(80), random.nextInt(30), 1, 1);
        }

        //将生成的验证码存入session中，以便进行校验
        HttpSession session = request.getSession();
        session.setAttribute("code", code);

        //绘制图片
        graphics.dispose();

        //将图片输出到response中
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    //随机生成颜色
    private Color getColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}  
