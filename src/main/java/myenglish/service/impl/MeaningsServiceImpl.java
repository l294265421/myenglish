package myenglish.service.impl;

import myenglish.dao.IMeaningsDao;
import myenglish.service.IMeaningsService;

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

}
