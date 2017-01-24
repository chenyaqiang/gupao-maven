package blog.thread.join;

public class Task2 implements Runnable {
	private Thread t1;
	@Override
	public void run() {
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ïß³Ì2Æô¶¯");
	}
	public Thread getT1() {
		return t1;
	}
	public void setT1(Thread t1) {
		this.t1 = t1;
	}
	
	

}
