package blog.thread.join;


/**
 * joint用法，一个线程等待另一个线程结束再执行
 * 例子为保证t1、t2、t3线程顺序执行
 * @author wangye
 *
 */
public class Test {
	public static void main(String[] args) {
		Task task1 = new Task();
		Task2 task2 = new Task2();
		Task3 task3 = new Task3();
		Thread thread = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		task2.setT1(thread);
		task3.setT2(thread2);
		thread3.start();
		thread2.start();
		thread.start();
	}
}
