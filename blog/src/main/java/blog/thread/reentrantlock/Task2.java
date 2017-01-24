package blog.thread.reentrantlock;


public class Task2 extends AbstractTask {

	@Override
	public void run() {
		try {
			lock.lockInterruptibly();
			System.out.println(Thread.currentThread().getId()+"»ñµÃËø");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
			
		}

	}

}
