package temp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.liyuncong.application.commontools.FileTools;

public class GetElementByClass {
	public static void main(String[] args) {
		String pathName = "D:\\test\\test.html";
		String html = FileTools.readFile(pathName);
		Document document = Jsoup.parse(html);
		Element elementWithInbaseClass = document.select("[class=in-base]").first();
		if (elementWithInbaseClass == null) {
			return;
		}
		Element ul = elementWithInbaseClass.getElementsByTag("ul").first();
		if (ul != null) {
			Elements needToRemoveClass = ul.select("*");
			for (Element element2 : needToRemoveClass) {
				element2.removeAttr("class");
			}
			System.out.println(ul.outerHtml());
		}
	}
}
