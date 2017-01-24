package blog.thread.join;

public class Task3 implements Runnable {
	private Thread t2;
	@Override
	public void run() {
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ïß³Ì3Æô¶¯");
	}
	public Thread getT2() {
		return t2;
	}
	public void setT2(Thread t2) {
		this.t2 = t2;
	}
	
	

}
