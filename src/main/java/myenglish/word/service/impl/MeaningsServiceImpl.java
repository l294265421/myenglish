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
		Element element = document.select(".base-list").first();
		if (element == null) {
			return "";
		}
		Elements needToRemoveClass = element.select("*");
		for (Element element2 : needToRemoveClass) {
			element2.removeAttr("class");
		}
		
		return element.outerHtml();
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
