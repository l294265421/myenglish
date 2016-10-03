package myenglish.word.service.impl;

import myenglish.word.dao.IMeaningsDao;
import myenglish.word.service.IMeaningsService;

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

}
