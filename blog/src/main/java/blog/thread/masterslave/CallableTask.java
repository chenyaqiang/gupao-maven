package blog.thread.masterslave;


import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(2000);
		return Thread.currentThread().getName();
	}

}
