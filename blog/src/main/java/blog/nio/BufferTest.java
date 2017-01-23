package blog.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferTest {
	public static void main(String[] args) {
		//鍒涘缓缂撳啿鍖�
		ByteBuffer buffer = ByteBuffer.allocate(24); //闈炵洿鎺ョ紦鍐插尯
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(24); //鐩存帴缂撳啿鍖�
		
		//缂撳啿鍖哄睘鎬osition銆乵ark銆乴imit銆乧apacity 
		bufferAttribute(buffer);
		
		//璇诲彇鍜屽啓鍏ョ紦鍐插尯,涓嶇鏄粠缂撳啿鍖鸿杩樻槸鍐欙紝source瑕佸皬浜庣瓑浜巇est鍚﹀垯鎶ラ敊
		bufferReadAndWrite(buffer);
		
		//澶嶅埗鍜屽垎鍖猴紝澶嶅埗鍜屽垎鍖轰笌鍘熺紦鍐插尯鍏辩敤涓�唤鏁版嵁
		bufferDupAndSlice(buffer);
		
		//瀛楄妭椤哄簭锛屼笉鍚宑pu涓庣郴缁熷瓧鑺傞『搴忎笉鍚岋紝jvm榛樿澶х瀛楄妭椤哄簭锛屽鏋滅紦鍐插尯鐨勫瓧鑺傞『搴忎笌绯荤粺涓嶄竴鑷村垯鑰楄垂鎬ц兘
		//瀛楄妭椤哄簭璁剧疆鍚庝笉鑳戒慨鏀�
		buffer.order(ByteOrder.BIG_ENDIAN);
		
		//byte缂撳啿鍖轰笌鍏跺畠绫诲瀷缂撳啿鍖鸿浆鎹�
		bufferConvert(buffer);
	}

	private static void bufferConvert(ByteBuffer buffer) {
		//buffer涓�4杞崲涓篶har绫诲瀷buffer瀹归噺涓�2
		CharBuffer asCharBuffer = buffer.asCharBuffer();
		char char1 = buffer.getChar();
	}

	private static void bufferDupAndSlice(ByteBuffer buffer) {
		ByteBuffer duplicate = buffer.duplicate();
		//鍒囧垎鐨勬槸position涓巐imit涓棿鐨勫尯鍩燂紝鍖呭惈postion鍜宭imit锛屾敞鎰弆imit鏄惁绛変簬capacity
		ByteBuffer slice = buffer.slice();
	}

	private static void bufferReadAndWrite(ByteBuffer buffer) {
		byte b = buffer.get();
		byte[] bytearr=new byte[1024];
		buffer.get(bytearr);
		buffer.get(bytearr, 0, 10);
		buffer.put((byte)1);
		buffer.put(bytearr, 0, 10);
	}

	private static void bufferAttribute(ByteBuffer buffer) {
		int position = buffer.position();
		buffer.mark();//mark灞炴�璁板綍褰撳墠position
		buffer.reset();//position灞炴�閲嶇疆涓簃ark灞炴�鍊�
		buffer.flip();//limit鍊间负褰撳墠position,position灞炴�鍊艰涓�
		buffer.rewind();//璁剧疆position鍊间负0
		buffer.clear();//璁剧疆position鍊间负0锛宭imit鍊间负capacity,鏁版嵁娌℃湁娓呯┖
		//涓㈠純宸茶鍙栫殑鏁版嵁锛屼繚鐣欐湭璇诲彇鏁版嵁锛屼緥 0,1,2,3,4鏁版嵁锛岃鍙�,1 position鍊间负2锛�
		//compact鍘嬬缉鏁版嵁锛�,1鏁版嵁琚涪寮冿紝2銆�銆�琚繚鐣檖osition鍊间负4锛宭imit涓篶apacity鐨勫�
		buffer.compact();
	}
}
