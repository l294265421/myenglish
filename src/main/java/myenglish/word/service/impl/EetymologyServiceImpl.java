package myenglish.word.service.impl;

import java.util.List;

import myenglish.word.dao.IEetymologyDao;
import myenglish.word.entity.Eetymology;
import myenglish.word.service.IEetymologyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class EetymologyServiceImpl implements IEetymologyService {
	@Autowired
	@Qualifier("eetymologyDaoImpl")
    private IEetymologyDao eetymologyDaoImpl;
	public List<Eetymology> getEetymologyByWord(String word) {
		// TODO Auto-generated method stub
		List<Eetymology> eetymologies = eetymologyDaoImpl.getEetymologyByWord(word);
		return eetymologies;
	}

}
