package blog.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


public class FuturetaskTest {

	public static void main(String[] args)  {
		FutureTask futureTask=new FutureTask(new Callable() {
			public Object call() throws Exception {
			    if (1==1) {
					throw new InterruptedException();
				}
				return "1";
			}
		});
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(futureTask);
		//futureTask.cancel(true);
		//System.out.println(futureTask.get()); 
		
		/**
		 * cancel核心方法调用的是interrupt方法
		 * 参数为false
		 *   call方法已经执行或返回，调用cancell方法不会对执行过程产生影响，只会修改状态
		 * 参数为true
		 *   call正则执行，会修改状态为interrupting，然后调用线程中断请求，再将状态修改为interrupted
		 * 对任务是否产生影响要看call方法能否响应中断，如果不响应则无影响
		 *   call已返回，不影响方法执行，但会修改状态
		 */
		futureTask.cancel(true);
		/**
		 * 任务正常完成或取消返回都为true
		 */
		System.out.println(futureTask.isDone());
		/**
		 * 任务被取消返回true
		 */
		System.out.println(futureTask.isCancelled());
//		ExecutorCompletionService executorService = new ExecutorCompletionService(executor)
//		Future future = executorService.submit(new Callable() {
//			public Object call() {
//				return "1";
//			}
//		});
		
		
		
	}

}
