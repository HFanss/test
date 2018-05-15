package Test001;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 继承Panel类，Panel是Java中的面板类。基本上要操作窗体都会继承此类
 *
 */
public class MyPanel extends Panel implements Runnable,KeyListener{
	int x[] = new int[10];//储存随机字母x轴
	int y[] = new int[10];//储存随机字母y轴
	char c[] = new char[10];//储存随机字母
	int integral =1000;//初始1000积分
	Color p[] =new Color[10];//用来存储颜色
	public MyPanel() {
		for (int i = 0; i < 10; i++) {
			x[i]=(int)(Math.random()*300);
			y[i]=(int)(Math.random()*50);
			c[i]=(char)(Math.random()*26+97);
			p[i]=getRandColorCode();//随机颜色
		}
	}
	/**
	 * 复写Panel类的paint方法
	 */
	public void paint(Graphics g) {
		if (integral>0) {//如果成绩>0，才能进来
			for (int i = 0; i < 10; i++) {
				g.setColor(p[i]);//设置元素颜色
				g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); //设置字体，大小
				//1.出现的元素，2.元素的x轴，3.元素的y轴
				g.drawString(new Character(c[i]).toString().toUpperCase(),x[i],y[i]);
			}   
			//显示积分在屏幕上
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g.drawString("你的成绩是："+integral, 5, 15);
		}else {//成绩<0,游戏结束
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			g.drawString("GAME OVER", 50, 170);
		}
	}
	//重写run方法
	public void run() {
		while (integral>0) {//外层循环，控制线程
			for (int i = 0; i < 10; i++) {
				y[i]++;//字母的y轴 加1，既往下移动1个位置
				if (y[i]>370) {//窗体边框占有一部分像素，所以设置370即可
					y[i]=0;
					x[i]=(int)(Math.random()*300);
					c[i]=(char)(Math.random()*26+97);
					integral-=100;//积分-100
				}
				try {
					Thread.sleep(5);//暂停5毫秒
				} catch (InterruptedException e) {}
				repaint();//重新绘制画面
			}
		}
	}
	
	//键盘录入
	public void keyPressed(KeyEvent e) {
		char keyC = e.getKeyChar();
		int stat=-1;//存储最下面字母的值
		int nowIndex=-1;//存储最下面字母的下标
		for (int i = 0; i < 10; i++) {
			if (keyC==c[i]) {
				if (y[i]>stat) {//第一次进来时，必定大于，
					stat=y[i];//记录坐标值
					nowIndex=i;//记录字母的下标
				}
			}
		}
		if (nowIndex!=-1) {
//如果！=-1，证明输对了。nowIndex为最下面的字母的下标，重新生成。积分+100
			y[nowIndex]=0;
			x[nowIndex]=(int)(Math.random()*300);
			c[nowIndex]=(char)(Math.random()*26+97);
			integral+=100;//积分+100
		}else {//如果==-1，证明输错了。积分-100
			integral-=100;//积分-100
		}
		
	}
	
	/** 
     * 获取十六进制的颜色代码.例如  "#6E36B4" ,
     * @return String 
     */  
	public static Color getRandColorCode(){  
		 int r,b,g;
		 r=(int)(Math.random()*250);
		 b=(int)(Math.random()*250);
		 g=(int)(Math.random()*250);
		  Color c =new Color(r, b, g);
	  return c;  
	 }
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
