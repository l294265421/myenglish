package temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ModifyEtymonline {
	public static void main(String[] args) throws IOException {
		File file = new File("resources/etymonline.html");
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
		int count = 0;
		String line = "";
		while ((line = randomAccessFile.readLine()) != null) {
			if (count == 73320 || count == 192121 || count == 120003) {
				System.out.println(line);
				System.out.println("-------------");
			}
			count++;
		}
	}
}
