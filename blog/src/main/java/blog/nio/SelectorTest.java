package blog.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SelectorTest {
	
	public static void main(String[] args) throws Exception {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
			selector = Selector.open();
			// Create a new server socket and set to non blocking mode
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			// Bind the server socket to the local host and port
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(10000));
			// Register accepts on the server socket with the selector. This
			// step tells the selector that the socket wants to be put on the
			// ready list when accept operations occur, so allowing multiplexed
			// non-blocking I/O to take place.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			wakeupTest(selector);
		} catch (ClosedChannelException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				selector.close();
			} catch (Exception ex) {
			}
			try {
				serverSocketChannel.close();
			} catch (Exception ex) {
			}
		}

	}
	
	

	/**
	 * 测试wakeup方法 当select方法阻塞，通过启动另一个线程调用同一个selector对象的wakeup方法，
	 * 使select方法立即返回，如果没有准备好的channel则返回0
	 * 
	 * @author wy
	 * @throws IOException
	 */
	private static void wakeupTest(Selector selector) throws IOException {
		Test1.selector = selector;
		Thread thread = new Thread(new Test1());
		thread.start();
		System.out.println(selector.select());

	}
}
