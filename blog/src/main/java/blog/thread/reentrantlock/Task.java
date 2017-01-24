package blog.thread.reentrantlock;

import java.util.concurrent.TimeUnit;

public class Task extends AbstractTask  {

	@Override
	public void run() {
		try {
			if (lock.tryLock(10, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getId() + "��ȡ��");
				Thread.sleep(2000);
			} else {
				System.out.println(Thread.currentThread().getId() + "û�л�ȡ��");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}


}
