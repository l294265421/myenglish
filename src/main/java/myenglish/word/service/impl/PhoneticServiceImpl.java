package myenglish.word.service.impl;

import myenglish.word.dao.IPhoneticDao;
import myenglish.word.service.IPhoneticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PhoneticServiceImpl implements IPhoneticsService{
	@Autowired
	@Qualifier("phoneticDaoImpl")
    private IPhoneticDao phoneticDaoImpl;
	
	public String getPhoneticByWord(String word) {
		// TODO Auto-generated method stub
		String phonetic = phoneticDaoImpl.getPhoneticByWord(word);
		return phonetic;
	}

}
