package myenglish.word.service.impl;

import myenglish.word.dao.IPhoneticDao;
import myenglish.word.service.IPhoneticsService;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liyuncong.application.commmontools.net.NetUtil;

@Service
public class PhoneticServiceImpl implements IPhoneticsService{
	private Logger logger = LoggerFactory.getLogger(PhoneticServiceImpl.class);
	@Autowired
	@Qualifier("phoneticDaoBerkelyDbImpl")
    private IPhoneticDao phoneticDaoBerkelyDbImpl;
	
	public String getPhoneticByWord(String word) {
		if (StringUtils.isBlank(word)) {
			return "";
		}
		
		String phonetic = phoneticDaoBerkelyDbImpl.getPhoneticByWord(word);
		if (StringUtils.isBlank(phonetic)) {
			phonetic = getPhoneticFromInternet(word);
			if (StringUtils.isNoneBlank(phonetic)) {
				saveWordPhonetic(word, phonetic);
			}
		}
		
		return phonetic;
	}

	@Override
	public void saveWordPhonetic(String word, String phonetic) {
		phoneticDaoBerkelyDbImpl.saveWordPhonetic(word, phonetic);
	}

	@Override
	public String getPhoneticFromInternet(String word) {
		if (StringUtils.isBlank(word)) {
			logger.error("word is empty");
			return "";
		}
		String urlPrefix = "http://www.iciba.com/";
		String url = urlPrefix + word;
		
		NetUtil netUtil = new NetUtil();
		HttpGet httpGet = netUtil.getHttpGet(url);
		String html = netUtil.sendRequest(httpGet);
		
		String phonetic = getPhoneticFromHtml(html);
		
		return phonetic;
	}

	@Override
	public String getPhoneticFromHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		Document document = Jsoup.parse(html);
		Element elementContainPhonetic = document.select(".base-speak").first();
		if (elementContainPhonetic == null) {
			return "";
		}
		return elementContainPhonetic.text();
	}

}
