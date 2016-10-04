package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.Map;

import myenglish.word.dao.IPhoneticDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneticDaoMysqlImpl implements IPhoneticDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getPhoneticByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectOne("myenglish.word.IPhoneticDao.getPhoneticByWord", params);
	}

	@Override
	public void saveWordPhonetic(String word, String phonetic) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("word", word);
		parameter.put("phonetic", phonetic);
		sqlSession.insert("myenglish.word.IPhoneticDao.saveWordPhonetic", parameter);
	}

}
