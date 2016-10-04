package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.Map;

import myenglish.word.dao.IPhoneticDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;

@Repository
public class PhoneticDaoBerkelyDbImpl implements IPhoneticDao {
	private String databaseHome = "berkelydb";
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
