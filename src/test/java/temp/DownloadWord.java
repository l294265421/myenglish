package temp;

import org.apache.http.client.methods.HttpGet;

import com.liyuncong.application.commmontools.net.NetUtil;
import com.liyuncong.application.commontools.FileTools;

public class DownloadWord {
	public static void main(String[] args) {
		NetUtil netUtil = new NetUtil();
		String url = "http://www.iciba.com/far";
		HttpGet httpGet = netUtil.getHttpGet(url);
		String html = netUtil.sendRequest(httpGet);
		FileTools.writeStringToFile(html, "d:/test/test.html");
	}
}
