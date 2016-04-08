package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.Map;

import myenglish.word.dao.ICetymologyDao;
import myenglish.word.po.Cetymology;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CetymologyDaoImpl implements ICetymologyDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public Cetymology getCetymologyByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectOne("myenglish.word.ICetymologyDao.getCetymologyByWord", params);
	}

	@Override
	public int insertCetymology(Cetymology cetymology) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cetymology", cetymology);
		return sqlSession.insert("myenglish.word.ICetymologyDao.insertCetymology", params);
	}

	@Override
	public int updateCetymology(Cetymology cetymology) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cetymology", cetymology);
		return sqlSession.update("myenglish.word.ICetymologyDao.updateCetymology", params);
	}
}
