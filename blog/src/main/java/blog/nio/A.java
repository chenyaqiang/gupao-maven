package blog.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.mysql.jdbc.Connection;


public class A {
	
	private static class B{
		private static final A INSTANCE=new A();
	}
	
	private A(){}
	
	public static final A getInstance(){
		
		return B.INSTANCE;
	}
	
	public static void main(String[] args) throws Exception {
		DataSource d=null;
		 Connection con = (Connection) DataSourceUtils.getConnection(d);
//		RandomAccessFile file = new RandomAccessFile("", "");
//		FileChannel channel = file.getChannel();
//		ByteBuffer byteBuffer = ByteBuffer.allocate(7);
//		CharBuffer asCharBuffer = byteBuffer.asCharBuffer();
//		IntBuffer asIntBuffer = byteBuffer.asIntBuffer();
//		System.out.println(111);
		System.out.println( Integer.toBinaryString(26));
	}
	
}
