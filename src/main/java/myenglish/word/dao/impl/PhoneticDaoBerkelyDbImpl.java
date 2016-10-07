package myenglish.word.dao.impl;

import myenglish.word.dao.IPhoneticDao;

import org.springframework.stereotype.Repository;

import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;

@Repository
public class PhoneticDaoBerkelyDbImpl implements IPhoneticDao {
	private String databaseHome = "./berkelydb";
	private String databaseName = "phonetic";
	@Override
	public String getPhoneticByWord(String word) {
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, databaseName);
		String phonetic = berkelyDbCRUD.get(word);
		berkelyDbCRUD.destroy();
		return phonetic;
	}

	@Override
	public void saveWordPhonetic(String word, String phonetic) {
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, databaseName);
		berkelyDbCRUD.put(word, phonetic);
		berkelyDbCRUD.destroy();
	}

}
