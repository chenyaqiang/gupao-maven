package blog.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SelectorTest {
	
	/**
	 * @author wy
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception  {
		//创建选择器
		Selector selector=createSelector();
		//使用选择键
		userSelectionKey(selector);
		//使用选择器
		userSelector(selector);
		/**
		 *selector的并发性
		 *1、selector本身是线程安全的，但是返回的键的集合不是线程安全的
		 *keys返回的是已注册的键集合是只读的，selectkeys返回的是已选择的键的集合是可以被修改的
		 *2、selector本身对各种key集合操作是同步的，注意在多线程下，不同的操作对集合的操作顺序不同可能造成死锁
		 *3、close()方法和select()方法同步方式是一样的，所以close方法有可能被阻塞
		 */
		
		/**
		 *selector的异步关闭
		 * select选择过程中键是可以被取消的，所以取到的键可能是无效的，所以为了保证键的
		 * 有效性使用selectkeys方法不要自己维护键的集合
		 */
		
		/**
		 * selector的同步扩展
		 * 1、在一般多个通道需要管理时，使用一个线程使用selector，获取的通道分发给不同线程，线程通过线程池进行管理
		 * 2、如果有些连接对响应要求比较高，可以对连接分类使用不同的selector进行控制，在不同的线程中进行管理
		 * 示例4-2:通过使用线程池对多个通道进行读取数据，为了防止一个通道被多个线程重复读取，当线程读取通道前将
		 * interops设置为不可读状态，当读取完毕后再设置回可读取状态。为了防止主线程阻塞，要在设置完状态后调用wakeup方法
		 */
	}

	private static void userSelector(Selector selector) throws IOException {
		//返回已注册的键集合（生效的+失效的）
		Set<SelectionKey> keys = selector.keys();
		//返回已准备好的键的集合
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		//已取消的键的集合,不能直接获取，为什么要有这类集合？需要了解一下选择过程
		/**
		 * select执行过程
		 * 1、检查已取消集合中是否存在键，存在则移除另外两个集合中的键，并注销相关通道
		 * 2、检查已选择集合中每个键的interest集合，如果通道的interest集合中任意一个操作就绪，则检查已选择集合中该键是否存在，
		 * 不存在则清空键的ready集合（比特值）并将键加入到已选择的集合中，如果存在则更新ready集合（比特值），如果没有就绪的通道则方法阻塞
		 * 3、步骤2话费时间较多，会再一次执行第一步就在步骤2过程取消的键相关的通道进行注销
		 * 4、select方法返回的是每次方法中就绪的键数，而不是就绪键的总数
		 * 优化:取消键操作是非常消耗时间的，所以使用取消集合，达到取消操作非阻塞
		 * 
		 * 三个方法的区别在于阻塞，第一个会无限阻塞直到有一个键已就绪，或者调用选择器的wakeup则返回0
		 * 第二个是设置超时时间，第三个是非阻塞的
		 */
		int num = selector.select();
		int num2 = selector.select(1000);
		int num3 = selector.selectNow();
		/**
		 * 使选择器上第一个没有返回的select操作立即返回
		 * 当wakeup操作在select之前
		 * 或之后都会使select操作立即返回
		 * 除非select之前有selectNow操作select操作任然会阻塞
		 */
		selector.wakeup();
		/**
		 * 当close操作会将阻塞中的线程全部唤醒就像调用了wakeup
		 * 相关的键被取消，通道被注销
		 */
		selector.close();
		/**
		 * 通过interrupted()方法中断线程,selector会抛出异常并调用wakeup方法
		 */
		Thread.interrupted();
	}

	/**
	 * 使用选择键，注意选择键本身是线程安全的
	 * @author wy
	 * @param selector
	 */
	private static void userSelectionKey(Selector selector) {
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		for (SelectionKey selectionKey : selectedKeys) {
			//获取对应的选择器和选择键
			Selector selector2 = selectionKey.selector();
			SelectableChannel selectChannel=selectionKey.channel();
			//键会立即失效但注册不会被取消，通过selectionKey.isValid()判断是否有效
			//注意：如果通道被关闭则相关的键被取消，如果选择器关闭则与之相关的通道则被注销，相关的键无效化
			selectionKey.cancel();
			boolean valid = selectionKey.isValid();
			//注册时关系操作编码、相关通道已经就绪的操作
			int interestOps = selectionKey.interestOps();
			//注意：readops方法返回就绪状态指示一个提示，
			//其它线程在通道上的操作可能会影响它的状态，并且要注意操作系统的特点
			int readyOps = selectionKey.readyOps();
			//判断通道的就绪状态
			boolean acceptable = selectionKey.isAcceptable();
			boolean connectable = selectionKey.isConnectable();
			//任意对象与键进行关联,注意清除附件的对象负责该对象不能被垃圾回收
			selectionKey.attach(new HashMap());
		}
	}

	private static Selector createSelector() {
		Selector selector=null;
		try {
			selector = Selector.open();
		    ServerSocketChannel channel = ServerSocketChannel.open();
		    channel.configureBlocking(false);
		    channel.socket().setReuseAddress(true);
			channel.socket().bind(new InetSocketAddress(10000));
			//注意不同channel实现，兴趣集合(read、write、connect、accept)可能不同，
			channel.register(selector,SelectionKey.OP_ACCEPT);
		    //可以通过channel.validOps()获取通道支持的兴趣集合
		    int validOps = channel.validOps();
		    //解析通道支持的操作
		    Set set=getValidOps(validOps);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (selector!=null) {
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return selector;
	}

	private static Set getValidOps(int validOps) {
		String binaryStr = Integer.toBinaryString(validOps);
		Set set = new HashSet();
		for(int i=0;i<binaryStr.length();i++){
			if ("1".equals(String.valueOf(binaryStr.charAt(i)))) {
				set.add(Math.pow(2, binaryStr.length()-1-i));
			}
		}
		return set;
	}
	
}
