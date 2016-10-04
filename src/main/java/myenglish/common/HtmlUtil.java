package myenglish.common;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlUtil {
	/**
	 * 
	 * @param html
	 * @param cssQuery
	 * @return 没有找到返回null
	 */
	public static Element getFirstMatchElementByCssQuery(String html, String cssQuery) {
		if (StringUtils.isBlank(html) || StringUtils.isBlank(cssQuery)) {
			return null;
		}
		
		Document document = Jsoup.parse(html);
		Element element = document.select(cssQuery).first();
		return element;
	}
}
