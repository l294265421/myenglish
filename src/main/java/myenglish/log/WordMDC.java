package myenglish.log;

import org.slf4j.MDC;

public class WordMDC {
	public static void putWordValues(String ip, String word) {
		MDC.put("ip", ip);
		MDC.put("word", word);
	}
	
	public static void clearWordValues() {
		MDC.remove("ip");
		MDC.remove("word");
	}
}
