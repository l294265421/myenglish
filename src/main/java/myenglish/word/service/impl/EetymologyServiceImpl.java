package myenglish.word.service.impl;

import java.util.List;

import myenglish.word.dao.IEetymologyDao;
import myenglish.word.po.Eetymology;
import myenglish.word.service.IEetymologyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class EetymologyServiceImpl implements IEetymologyService {
	@Autowired
	@Qualifier("eetymologyDaoBerkelyDbImpl")
    private IEetymologyDao eetymologyDaoBerkelyDbImpl;
	public List<Eetymology> getEetymologyByWord(String word) {
		List<Eetymology> eetymologies = eetymologyDaoBerkelyDbImpl.getEetymologyByWord(word);
		return eetymologies;
	}
	@Override
	public void saveEetymology(Eetymology eetymology) {
		eetymologyDaoBerkelyDbImpl.saveEetymology(eetymology);
	}

}
