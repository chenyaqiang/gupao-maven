package blog.nio;

import java.nio.channels.Selector;

public class Test1 implements Runnable{
	
	public static Selector selector=null;

	@Override
	public void run() {
		try {
			System.out.println("start wake up thread");
			Thread.sleep(3000);
			selector.wakeup();
			System.out.println("selector wakeup");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
