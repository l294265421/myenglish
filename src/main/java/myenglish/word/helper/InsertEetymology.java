package myenglish.word.helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.liyuncong.application.commontools.FileTools;
import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;

public class InsertEetymology {
	public void changeHref() {
		String html = FileTools.readFile("d:/test/myenglish/etymonline.html");
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByTag("a");
		String hrefPrefix = "/myenglish/word?word=";
		for (Element element : elements) {
			String text = element.text();
			String[] wordPart = text.split("\\(");
			element.attr("href", hrefPrefix + wordPart[0].trim());
		}
		FileTools.writeStringToFile(document.outerHtml(), "d:/test/myenglish/etymonline1.html");
	}
	
	public void toBerkelyDb() {
		String html = FileTools.readFile("d:/test/myenglish/etymonline.html");
		Document document = Jsoup.parse(html);
		Elements dts = document.getElementsByTag("dt");
		Elements dds = document.getElementsByTag("dd");
		String indexDatabase = "eetymologyIndex";
		String databaseHome = "berkelydb";
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, indexDatabase);
		String dataDatabase = "eetymologyData";
		BerkelyDbCRUD berkelyDbCRUD2 = new BerkelyDbCRUD();
		berkelyDbCRUD2.init(databaseHome, dataDatabase);
		Map<String, List<String>> indexs = new HashMap<String, List<String>>();
		for(int i = 0; i < dts.size(); i++) {
			Element dt = dts.get(i);
			String text = dt.text().trim();
			String indexKey = text.split("\\(")[0].trim();
			if (indexs.containsKey(indexKey)) {
				indexs.get(indexKey).add(text);
			} else {
				List<String> children = new LinkedList<String>();
				children.add(text);
				indexs.put(indexKey, children);
			}
			berkelyDbCRUD2.put(text, dds.get(i).html());
		}
		berkelyDbCRUD2.destroy();
		for (Entry<String, List<String>> entry : indexs.entrySet()) {
			StringBuilder value = new StringBuilder();
			for(String child : entry.getValue()) {
				value.append(child);
				value.append("#");
			}
			berkelyDbCRUD.put(entry.getKey(), value.substring(0, value.length() - 1));
		}
		berkelyDbCRUD.destroy();
	}
	
	public void search(String key) {
		String databaseHome = "berkelydb";
		
		String indexDatabase = "eetymologyIndex";
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, indexDatabase);
		
		String children = berkelyDbCRUD.get(key);
		System.out.println(children);
		berkelyDbCRUD.destroy();
		
		String dataDatabase = "eetymologyData";
		BerkelyDbCRUD berkelyDbCRUD2 = new BerkelyDbCRUD();
		berkelyDbCRUD2.init(databaseHome, dataDatabase);
		for(String child : children.split("#")){
			System.out.println("....................");
			System.out.println(child);
			System.out.println(berkelyDbCRUD2.get(child));
		}
		berkelyDbCRUD2.destroy();
		
	}
	
	public static void main(String[] args) {
		InsertEetymology insertEetymology = new InsertEetymology();
//		insertEetymology.changeHref();
//		insertEetymology.toBerkelyDb();
		insertEetymology.search("back down1");
	}
}
