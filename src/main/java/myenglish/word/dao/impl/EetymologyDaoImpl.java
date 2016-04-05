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
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession sqlSession;
	
	private String tableName = "eetymology";
	
	public List<Eetymology> getEetymologyByWord1(String word) {
		// TODO Auto-generated method stub
		List<Eetymology> eetymologies = null;
		String sql = "SELECT * from " + tableName + " WHERE word LIKE '" + word + " (%)'";
		String sql2 = "SELECT * from " + tableName + " WHERE word = '" + word + "'";
		try {
			BeanPropertyRowMapper<Eetymology> rowMapper = new BeanPropertyRowMapper<Eetymology>(Eetymology.class);
			eetymologies = jdbcTemplate.query(sql, rowMapper);
			Eetymology eetymology = jdbcTemplate.queryForObject(sql2, rowMapper);
			eetymologies.add(eetymology);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return eetymologies;
	}

	@Override
	public List<Eetymology> getEetymologyByWord(String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("word", word);
		return sqlSession.selectList("myenglish.word.po.Eetymology.getEetymologyByWord", params);
	}

}
