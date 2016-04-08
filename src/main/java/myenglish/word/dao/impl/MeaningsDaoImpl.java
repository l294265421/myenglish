/**
 * 
 */
package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.Map;

import myenglish.word.dao.IMeaningsDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author yuncong
 *
 */
@Repository
public class MeaningsDaoImpl implements IMeaningsDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String getMeaningsByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectOne("myenglish.word.IMeaningsDao.getMeaningsByWord", params);
	}

}
