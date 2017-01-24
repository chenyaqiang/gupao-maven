package blog.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		/**
		 * 传入参数为true时则为公平锁，不传入参数为非公平锁
		 * 当锁被某个线程持有，则其它线程则进入等待队列，先进入等待队列先获取锁，但这影响了吞吐量
		 * 非公平锁则是哪个线程准备好则先获取锁，在等待队列中的线程都被挂起，如果等待队列外有个线程获取正好这时锁释放
		 * 则队列外的线程获取到该锁
		 */
		ReentrantLock lock = new ReentrantLock();
		/**
		 *  trylock 如果发现锁被其它线程持有则立刻返回false，如果获得锁则返回true
		 *  tryLock(long timeout, TimeUnit unit) 如果发现锁被其它线程持有
		 *  则等待传入的时间参数再词获取，如果还被持有则返回false
		 *  正因为获取不到锁会返回从而避免了死锁
		 */
		//test(new Task(),new Task());
		
		/**
		 * lockInterruptibly方法如果获取不到锁则会阻塞，如果获取锁的过程中中断则会抛出异常
		 */
		//test(new Task2(), new Task2());
		/**
		 * lock()方法与synchronized功能类似，在获取锁的过程中和等待锁的过程中不能被中断
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
