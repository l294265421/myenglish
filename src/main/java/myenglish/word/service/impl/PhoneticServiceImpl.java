package myenglish.word.service.impl;

import myenglish.word.dao.IPhoneticDao;
import myenglish.word.service.IPhoneticsService;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PhoneticServiceImpl implements IPhoneticsService{
	@Autowired
	@Qualifier("phoneticDaoImpl")
    private IPhoneticDao phoneticDaoImpl;
	
	public String getPhoneticByWord(String word) {
		String phonetic = phoneticDaoImpl.getPhoneticByWord(word);
		return phonetic;
	}

	@Override
	public void saveWordPhonetic(String word, String phonetic) {
		phoneticDaoImpl.saveWordPhonetic(word, phonetic);
	}

	@Override
	public String getPhoneticFromInternet(String word) {
		return null;
	}

	@Override
	public String getPhoneticFromHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		Document document = Jsoup.parse(html);
		Element elementContainPhonetic = document.select("html body div.screen div.container div.container-left div.js-base-info div.info-article.info-base div.in-base div.in-base-top.clearfix div.base-top-voice div.base-speak").first();
		if (elementContainPhonetic == null) {
			return "";
		}
		return elementContainPhonetic.text();
	}

}
