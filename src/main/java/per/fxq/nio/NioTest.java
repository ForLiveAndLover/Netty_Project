package per.fxq.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
	public static void main(String[] args) throws Exception{
		FileInputStream fis = new FileInputStream("input.txt");
		FileOutputStream fos = new FileOutputStream("output.txt");
		
		FileChannel inputChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		
		ByteBuffer allocateBuffer = ByteBuffer.allocate(100);
		long beforetime = System.currentTimeMillis();
		while(true){
			allocateBuffer.clear();
			int read = inputChannel.read(allocateBuffer);
			//System.out.println("读取长度:"+read);
			if(read == -1)break;
			allocateBuffer.flip();
			outChannel.write(allocateBuffer);
		}
		long haoshi = System.currentTimeMillis()-beforetime;
		System.out.println("耗时："+haoshi);
	}
}
