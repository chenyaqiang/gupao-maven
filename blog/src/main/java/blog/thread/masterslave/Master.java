package blog.thread.masterslave;

import java.util.concurrent.BlockingQueue;


public class Master implements Runnable {
	private BlockingQueue<Task> queue;
	private ExecutorTest executorTest;
	private Thread mainThread;
	private boolean flag=false;
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Task task = new Task();
			task.setName("任务"+i);
			try {
				queue.put(task);
				System.out.println(Thread.currentThread().getId()+"生成任務"+i);
			} catch (InterruptedException e) {
				flag=true;
				e.printStackTrace();
			}
		}
		flag=true;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Thread getMainThread() {
		return mainThread;
	}

	public void setMainThread(Thread mainThread) {
		this.mainThread = mainThread;
	}

	public BlockingQueue<Task> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Task> queue) {
		this.queue = queue;
	}

	public ExecutorTest getExecutorTest() {
		return executorTest;
	}

	public void setExecutorTest(ExecutorTest executorTest) {
		this.executorTest = executorTest;
	}
	
	

}
