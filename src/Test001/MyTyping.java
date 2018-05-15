package Test001;

import java.awt.Frame;

public class MyTyping {
	/**
	 思路：
	  1.一个窗体
	  2.在窗体绘制元素（随机出现的字母）
	  3.加入线程，实现动态效果（字母下坠）
	  4.优化线程（字母超出窗体从最上面重新生成）
	  5.加入键盘事件
	  6.加入计数器（分数）
	  7.匹配键盘录入字母和屏幕上的字母
	  	一致：消除，并且从最上面重新生成.积分+
	  	不一致：不消除，积分-
	  	没录入：不消除，积分-
	  8.积分<0,游戏结束
	 	核心代码就这么多，练习练习逻辑就好了，有心的可以尝试优化下！
	 */
	public static void main(String[] args) {
		Frame f = new Frame();//创建一个窗体
		f.setLocationRelativeTo(null);//窗体置中
		f.setSize(300, 400);//设置窗体大小
		MyPanel p = new MyPanel();
		f.add(p);//将元素放入窗体中
		f.addKeyListener(p);//注册键盘事件
		p.addKeyListener(p);//注册键盘事件
		Thread t = new Thread(p);
		t.start();
		//f.show();//显示窗体，方法过时
		f.setVisible(true);//显示窗体
		
		
	}
	
}
