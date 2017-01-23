package blog.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.FileChannel.MapMode;


public class ChannelTest {
	public static void main(String[] args) throws IOException {
	
		/**
		 *	注意通道的关闭是不可以再被使用，如果再次调用则会抛出异常，如果当前通道正在使用如果线程被中断则通道关闭
			如果另一个线程阻塞等待通道，如果通道关闭则会唤醒阻塞的线程
		 */
		//通道的一般操作，打开、使用和关闭
		FileChannel infileChannel = generalChannel();
		//Scatter/Gather
		scatterAndGather(infileChannel);
		//介绍filechannle
		fileChannel();
		//内存文件映射与传统读写文件相比，在内存中少进行一次拷贝
		MappedByteBuffer mapBuffer=infileChannel.map(MapMode.PRIVATE, 0, 10);
		/**
		 * SocketChannel对客户端socket监听,并发送数据
		 * ServersocketChannel是对服务器端socket监听,并接受数据
		 * 两个通道模拟tcp/ip
		 * 并且两个通道都可以设置为非阻塞，则一个线程可以对多个通道进行监听，哪一个链接好则进行数据传输
		 */
		socketchannel();
		/**
		 * DatagramChannel对DatagramSocket模拟,提供对客户端和服务器端监听
		 * 通道模拟UDP/ip
		 * 并且两个通道都可以设置为非阻塞，则一个线程可以对多个通道进行监听，哪一个链接好则进行数据传输
		 */
		DatagramChannel open = DatagramChannel.open();
		DatagramSocket socket = open.socket();
		/**
		 * 管道用于进程间数据单向通信
		 * pipe与selector组合使用可实现一个线程对多个通道数据有效的接受和处理
		 */
		Pipe open2 = Pipe.open();
		
	}

	private static void socketchannel() throws IOException {
		//sokcetchannel是对客户端socke一种监听
		//socketchannel可以设置为非阻塞模式
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 1000));
		Socket socket = socketChannel.socket();
		socketChannel.isConnected();//判读是否连接成功
		//通过此方法只有在拿到对象的锁的线程可以修改阻塞模式
		Object blockingLock = socketChannel.blockingLock();
		synchronized (blockingLock) {
			
		}
		//serverSocketChannel是对于服务器端socket监听
		ServerSocketChannel channel = ServerSocketChannel.open();
		ServerSocketChannel channel2 = channel.bind(new InetSocketAddress("localhost", 1000));
		ServerSocket serverSocket = channel2.socket();
		SocketChannel socketChannel2 = channel2.accept();//如果使用非阻塞socket，当没有socket连接时返回立即null
		
		
	}

	private static void fileChannel() throws FileNotFoundException, IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		FileInputStream in = new FileInputStream(new File(""));
		FileChannel channel = in.getChannel();
		long position = channel.position();//获取文件当前位置，postion代表读或写的位置
		int read = channel.read(buffer);//
		int write = channel.write(buffer);
		long size = channel.size();//获取文件大小
		channel.truncate(1024);//截取文件大小，当新指定的size大于等于文件的size，则postion会被设置为新size的值
		channel.force(true);//强制将对文件内容的修改，同步到磁盘上，true表示同步元数据
		//1、文件锁的实现根据不同系统可能实现不同，,默认使用共享锁，如果系统不支持，自动提升为独占锁
		//2、文件锁 锁的是文件不是通道，在对文件加锁的情况下，在同一个jvm中起多线程每个线程有自己的通道访问文件是不会阻塞，如果不同的jvm访问文件是会阻塞的
		FileLock lock=channel.lock();
		lock.isShared();//判读是否为共享锁
		boolean shared=true;
		channel.lock(position,size,true);//锁定文件区域，可以设置超出文件大小的区别，并设置锁是否共享
		
		//channel to channel 文件通道间可以相互传递数据
		channel.transferFrom(channel, position, 10);
		channel.transferTo(position, 10, channel);
	}

	private static FileChannel generalChannel() throws IOException,
			FileNotFoundException {
		//打开通道,几种不同channel的打开方式
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(1000));//socketchannel 可以切换到非阻塞
		RandomAccessFile accessFile = new RandomAccessFile("", "");
		FileChannel fileChannel = accessFile.getChannel();//filechannel 不可以切换到非阻塞
		FileInputStream iStream = new FileInputStream(new File(""));
		FileChannel infileChannel = iStream.getChannel();//获取的channel是只读的，写入会报错
		FileOutputStream outputStream = new FileOutputStream(new File(""));
		FileChannel outChannel = outputStream.getChannel();
		
		//使用通道
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (buffer.hasRemaining()) {
			int read = infileChannel.read(buffer);
		}
		
		//关闭通道
		//如果调用通道的线程阻塞，同时线程被设置interrupt status 则通道会被关闭
		infileChannel.isOpen();//判断通道是否关闭
		infileChannel.close();//关闭通道
		return infileChannel;
		
		
		
		
	}

	private static void scatterAndGather(FileChannel infileChannel)
			throws IOException {
		ByteBuffer buffer1 = ByteBuffer.allocate(10);
		ByteBuffer buffer2 = ByteBuffer.allocate(20);
		ByteBuffer[] bufferArr={buffer1,buffer2};
		long read = infileChannel.read(bufferArr);//Gather，将多个buffer按顺序读入到通道
		long write = infileChannel.write(bufferArr);//Scatter，将多个通道中的数据按顺序读入到多个buffer中
	}
}
