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
		 * cancel���ķ������õ���interrupt����
		 * ����Ϊfalse
		 *   call�����Ѿ�ִ�л򷵻أ�����cancell���������ִ�й��̲���Ӱ�죬ֻ���޸�״̬
		 * ����Ϊtrue
		 *   call����ִ�У����޸�״̬Ϊinterrupting��Ȼ������߳��ж������ٽ�״̬�޸�Ϊinterrupted
		 * �������Ƿ����Ӱ��Ҫ��call�����ܷ���Ӧ�жϣ��������Ӧ����Ӱ��
		 *   call�ѷ��أ���Ӱ�췽��ִ�У������޸�״̬
		 */
		futureTask.cancel(true);
		/**
		 * ����������ɻ�ȡ�����ض�Ϊtrue
		 */
		System.out.println(futureTask.isDone());
		/**
		 * ����ȡ������true
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
