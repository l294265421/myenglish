package myenglish.common;

import static org.junit.Assert.*;

import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.liyuncong.application.commmontools.net.NetUtil;

public class HtmlUtilTest {

	@Test
	public void testGetTextByCssPath() {
		NetUtil netUtil = new NetUtil();
		String url = "http://www.iciba.com/far";
		HttpGet httpGet = netUtil.getHttpGet(url);
		String html = netUtil.sendRequest(httpGet);
		Document document = Jsoup.parse(html);
		Element elementContainPhonetic = document.select(".base-top-voice").first();
		if (elementContainPhonetic != null) {
			System.out.println(elementContainPhonetic.outerHtml());
		}
	}

}
