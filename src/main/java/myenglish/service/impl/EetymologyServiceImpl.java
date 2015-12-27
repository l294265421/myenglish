package myenglish.service.impl;

import java.util.List;

import myenglish.dao.IEetymologyDao;
import myenglish.entity.Eetymology;
import myenglish.service.IEetymologyService;

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
