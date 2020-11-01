package dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateImage {

	private static final int width=100;
	private static final int height=30;
	private static final int length=4;
	public static final int linecount=20;
	
	//去掉不易辨识的0oO1I
	private static final String str="23456789"+"ABCDEFGHIJKLMNPQRSTUVWXYZ"+"abcdefghijklmnpqrstuvwxyz";

	private static Random random=new Random();
	
	//组合4位验证码
	public String createCode(){
		String code="";
		for(int i=0;i<4;i++){
			char c=str.charAt(random.nextInt(str.length()));
			code=code+c;
		}
		return code;
	}
	
	//获取不同颜色
	public Color getColor(){
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
		
	}
	
	//获取不同样式
	public Font getFont(){
		return new Font("Fixedsys",Font.CENTER_BASELINE,22);
	}
	
	//绘制字符
	public void drawChar(Graphics g,String code){
		g.setFont(getFont());
		for(int i=0;i<length;i++){
			char c=code.charAt(i);
			g.setColor(getColor());
			g.drawString(c+"", 20*i+10, 20);
		}
	}
	
	//绘制随机线
	public void drawLine(Graphics g){
		int x=random.nextInt(width);
		int y=random.nextInt(height);
		int xl=random.nextInt(13);
		int yl=random.nextInt(15);
		g.setColor(getColor());
		g.drawLine(x, y, x+xl, y+yl);
	}
	
	//绘制验证码图片
	public BufferedImage CreateImage(String code){
		//随机画笔
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=image.getGraphics();
		//设置背景颜色并绘制矩形背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//验证码绘制
		drawChar(g,code);
		//随机线的绘制
		for(int i=0;i<linecount;i++)
			drawLine(g);
		//绘制图片
		g.dispose();
		//返回生成的图片
		return image;
	}
}
