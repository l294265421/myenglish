package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;

import myenglish.word.dao.IEetymologyDao;
import myenglish.word.po.Eetymology;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class EetymologyDaoImpl implements IEetymologyDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Eetymology> getEetymologyByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectList("myenglish.word.IEetymologyDao.getEetymologyByWord", params);
	}

}
