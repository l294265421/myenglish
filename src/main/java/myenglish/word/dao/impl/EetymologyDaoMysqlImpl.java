package myenglish.word.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;

import myenglish.word.dao.IEetymologyDao;
import myenglish.word.po.Eetymology;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.liyuncong.learn.learnbberkelydb.BerkelyDbCRUD;
@Repository
public class EetymologyDaoMysqlImpl implements IEetymologyDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Eetymology> getEetymologyByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectList("myenglish.word.IEetymologyDao.getEetymologyByWord", params);
	}

	@Override
	public void saveEetymology(Eetymology eetymology) {
		// TODO Auto-generated method stub
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("eetymology", eetymology);
		sqlSession.insert("myenglish.word.IEetymologyDao.saveEetymology", parameter);
	}

}
