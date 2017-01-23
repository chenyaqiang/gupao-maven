package blog.thread.masterslave;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		ArrayBlockingQueue<Task> queue = new ArrayBlockingQueue(5);
		Master master = new Master();
		master.setQueue(queue);
		newFixedThreadPool.execute(master);
		List<Worker> workList = new ArrayList();
		for (int i = 0; i < 2; i++) {
			Worker worker = new Worker();
			worker.setQueue(queue);
			newFixedThreadPool.execute(worker);
			workList.add(worker);
		}
		while (true) {
			Iterator<Worker> iterator = workList.iterator();
			if (master.getFlag() && queue.isEmpty()) {
				while (iterator.hasNext()) {
					Worker worker = iterator.next();
					State state = worker.getWorkThread().getState();
					if (state.toString().equals(state.WAITING.toString())) {
						worker.getWorkThread().interrupt();
						iterator.remove();
					}
				}
				if (workList.isEmpty()) {
					break;
				}
			}
		}
		newFixedThreadPool.shutdown();
	}

}
