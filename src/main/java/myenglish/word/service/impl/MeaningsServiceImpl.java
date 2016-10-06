package myenglish.word.service.impl;

import myenglish.word.dao.IMeaningsDao;
import myenglish.word.service.IMeaningsService;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liyuncong.application.commmontools.net.NetUtil;

@Service
public class MeaningsServiceImpl implements IMeaningsService {
	private Logger logger = LoggerFactory.getLogger(MeaningsServiceImpl.class);
	@Autowired
	@Qualifier("meaningsDaoBerkelyDbImpl")
	private IMeaningsDao meaningsDaoBerkelyDbImpl;
	public String getMeaningsByWord(String word) {
		if (StringUtils.isBlank(word)) {
			logger.error("word is empty");
			return "";
		}
		String meaning = meaningsDaoBerkelyDbImpl.getMeaningsByWord(word);
		if (StringUtils.isBlank(meaning)) {
			meaning = getMeaningFromInternet(word);
			if (StringUtils.isNoneBlank(meaning)) {
				saveWordMeanings(word, meaning);
			}
		}
		
		return meaning;
	}
	@Override
	public void saveWordMeanings(String word, String meaning) {
		meaningsDaoBerkelyDbImpl.saveWordMeanings(word, meaning);
	}
	@Override
	public String getMeaningFromHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		Document document = Jsoup.parse(html);
		Element elementWithInbaseClass = document.select("[class=in-base]").first();
		if (elementWithInbaseClass == null) {
			return "";
		}
		Element ul = elementWithInbaseClass.getElementsByTag("ul").first();
		if (ul == null) {
			return "";
		}
		Elements needToRemoveClass = ul.select("*");
		for (Element element2 : needToRemoveClass) {
			element2.removeAttr("class");
		}
		
		return ul.outerHtml();
	}
	@Override
	public String getMeaningFromInternet(String word) {
		if (StringUtils.isBlank(word)) {
			logger.error("word is empty");
			return "";
		}
		String urlPrefix = "http://www.iciba.com/";
		String url = urlPrefix + word;
		
		NetUtil netUtil = new NetUtil();
		HttpGet httpGet = netUtil.getHttpGet(url);
		String html = netUtil.sendRequest(httpGet);
		
		String meaning = getMeaningFromHtml(html);
		return meaning;
	}

}
