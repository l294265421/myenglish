package myenglish.word.service.impl;

import myenglish.word.dao.IMeaningsDao;
import myenglish.word.service.IMeaningsService;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MeaningsServiceImpl implements IMeaningsService {
	@Autowired
	@Qualifier("meaningsDaoImpl")
	private IMeaningsDao meaningsDaoImpl;
	public String getMeaningsByWord(String word) {
		// TODO Auto-generated method stub
		return meaningsDaoImpl.getMeaningsByWord(word);
	}
	@Override
	public void saveWordMeanings(String word, String meaning) {
		meaningsDaoImpl.saveWordMeanings(word, meaning);
	}
	@Override
	public String getMeaningFromHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		return "";
	}

}
