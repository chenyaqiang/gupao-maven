package blog.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		/**
		 * �������Ϊtrueʱ��Ϊ��ƽ�������������Ϊ�ǹ�ƽ��
		 * ������ĳ���̳߳��У��������߳������ȴ����У��Ƚ���ȴ������Ȼ�ȡ��������Ӱ����������
		 * �ǹ�ƽ�������ĸ��߳�׼�������Ȼ�ȡ�����ڵȴ������е��̶߳�����������ȴ��������и��̻߳�ȡ������ʱ���ͷ�
		 * ���������̻߳�ȡ������
		 */
		ReentrantLock lock = new ReentrantLock();
		/**
		 *  trylock ����������������̳߳��������̷���false�����������򷵻�true
		 *  tryLock(long timeout, TimeUnit unit) ����������������̳߳���
		 *  ��ȴ������ʱ������ٴʻ�ȡ��������������򷵻�false
		 *  ����Ϊ��ȡ�������᷵�شӶ�����������
		 */
		//test(new Task(),new Task());
		
		/**
		 * lockInterruptibly���������ȡ��������������������ȡ���Ĺ������ж�����׳��쳣
		 */
		//test(new Task2(), new Task2());
		/**
		 * lock()������synchronized�������ƣ��ڻ�ȡ���Ĺ����к͵ȴ����Ĺ����в��ܱ��ж�
		 */
		test(new Task3(), new Task3());		
	}

	
	private static void test(AbstractTask task,AbstractTask task2) {
		ReentrantLock lock = new ReentrantLock();
		task.setLock(lock);
		task2.setLock(lock);
		Thread thread = new Thread(task);
		Thread thread2 = new Thread(task2);
		thread.start();
		try {
			Thread.sleep(1000);
			thread2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread2.interrupt();
	}
}
