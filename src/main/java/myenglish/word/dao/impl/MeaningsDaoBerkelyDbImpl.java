/**
 * 
 */
package myenglish.word.dao.impl;

import myenglish.word.dao.IMeaningsDao;

import org.springframework.stereotype.Repository;

import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;

/**
 * @author yuncong
 *
 */
@Repository
public class MeaningsDaoBerkelyDbImpl implements IMeaningsDao {
	private String databaseHome = "berkelydb";
	private String databaseName = "meaning";
	
	@Override
	public String getMeaningsByWord(String word) {
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, databaseName);
		String meaning = berkelyDbCRUD.get(word);
		berkelyDbCRUD.destroy();
		return meaning;
	}

	@Override
	public void saveWordMeanings(String word, String meaning) {
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, databaseName);
		berkelyDbCRUD.put(word, meaning);
		berkelyDbCRUD.destroy();
	}

}
