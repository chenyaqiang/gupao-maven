package blog.thread.masterslave;


import java.util.concurrent.BlockingQueue;


public class Worker  implements Runnable {
	private BlockingQueue<Task> queue;
	private Thread workThread;
	@Override
	public void run() {
		Task task;
		workThread=Thread.currentThread();
		try {
			while (true) {
				task = queue.take();
				if (task!=null) {
					System.out.println(Thread.currentThread().getId()+"完成任务："+task.getName());
				}else {
					System.out.println("null");
				};
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public BlockingQueue<Task> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Task> queue) {
		this.queue = queue;
	}

	public Thread getWorkThread() {
		return workThread;
	}

	public void setWorkThread(Thread workThread) {
		this.workThread = workThread;
	}
	

}
