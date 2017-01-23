package blog.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;




public class Test {
	public static void main(String[] args) throws Exception {
		test1();
		
//		Selector open = Selector.open();
		
	}

	private static void test1() throws FileNotFoundException, IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("C:/Users/vfff/Desktop/test.txt","rw");
		FileChannel inChannel = randomAccessFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int read = inChannel.read(buffer);
		StringBuffer strBuffer = new StringBuffer();
		while (buffer.hasRemaining()&&read!=-1) {
			 read = inChannel.read(buffer);
		}
		System.out.println(buffer);
//		while (read!=-1) {
//			buffer.flip();
////			while (buffer.hasRemaining()) {
////				strBuffer=strBuffer.append((char)buffer.get());
////				System.out.println((char)buffer.get());
////			}
//			for (int i = 0; i < 1; i++) {
//				System.out.println((char)buffer.get());
//			}
//			buffer.mark();
//			buffer.compact();
//			System.out.println(111);
//			System.out.println((char)buffer.get());
//			read = inChannel.read(buffer);
//		}
//		System.out.println(strBuffer.toString());
	}
}
