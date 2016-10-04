package myenglish.word.dao.impl;

import java.util.LinkedList;
import java.util.List;

import myenglish.word.dao.IEetymologyDao;
import myenglish.word.po.Eetymology;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;
@Repository
public class EetymologyDaoBerkelyDbImpl implements IEetymologyDao {
	
	@Override
	public List<Eetymology> getEetymologyByWord(String word) {
		List<Eetymology> result = new LinkedList<Eetymology>();
		
		String databaseHome = "berkelydb";
		
		String indexDatabase = "eetymologyIndex";
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, indexDatabase);
		String children = berkelyDbCRUD.get(word);
		berkelyDbCRUD.destroy();
		if (StringUtils.isBlank(children)) {
			return result;
		}
		
		String dataDatabase = "eetymologyData";
		BerkelyDbCRUD berkelyDbCRUD2 = new BerkelyDbCRUD();
		berkelyDbCRUD2.init(databaseHome, dataDatabase);
		for(String child : children.split("#")){
			Eetymology eetymology = new Eetymology();
			eetymology.setWord(child);
			eetymology.setEetymology(berkelyDbCRUD2.get(child));
			result.add(eetymology);
		}
		berkelyDbCRUD2.destroy();
		return result; 
	}

	@Override
	public void saveEetymology(Eetymology eetymology) {
	}
}
