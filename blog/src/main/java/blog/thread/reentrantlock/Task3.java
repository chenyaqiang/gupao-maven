package blog.thread.reentrantlock;

public class Task3 extends AbstractTask {

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getId()+"�����");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			System.out.println(Thread.currentThread().getId()+"�ͷ���");
			lock.unlock();
		}

	}


}
