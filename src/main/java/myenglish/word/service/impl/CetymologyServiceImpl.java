package myenglish.word.service.impl;

import myenglish.word.dao.ICetymologyDao;
import myenglish.word.po.Cetymology;
import myenglish.word.service.ICetymologyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class CetymologyServiceImpl implements ICetymologyService {
	@Autowired
	@Qualifier("cetymologyDaoImpl")
	private ICetymologyDao cetymologyDaoImpl;
	
	public Cetymology getCetymologyByWord(String word) {
		// TODO Auto-generated method stub
		return cetymologyDaoImpl.getCetymologyByWord(word);
	}

	public int insertCetymology(Cetymology cetymology) {
		// TODO Auto-generated method stub
		return cetymologyDaoImpl.insertCetymology(cetymology);
	}

	public int updateCetymology(Cetymology cetymology) {
		// TODO Auto-generated method stub
		return cetymologyDaoImpl.updateCetymology(cetymology);
	}

}
