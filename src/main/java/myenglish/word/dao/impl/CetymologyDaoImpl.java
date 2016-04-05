package myenglish.word.dao.impl;

import myenglish.word.dao.ICetymologyDao;
import myenglish.word.po.Cetymology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CetymologyDaoImpl implements ICetymologyDao {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private String tableName = "cetymology";

	public Cetymology getCetymologyByWord(String word) {
		// TODO Auto-generated method stub
		Cetymology cetymology = null;
		String sql = "SELECT * FROM " + tableName + " WHERE word = '" + word
				+ "'";
		try {
			RowMapper<Cetymology> rowMapper = new BeanPropertyRowMapper<Cetymology>(
					Cetymology.class);
			cetymology = jdbcTemplate.queryForObject(sql, rowMapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cetymology;
	}

	public int insertCetymology(Cetymology cetymology) {
		// TODO Auto-generated method stub
		String sql = "INSERT " + tableName + " VALUES(?, ?, ?)";
		return jdbcTemplate.update(sql, cetymology.getWord(),
				cetymology.getParent(), cetymology.getCetymology());
	}

	public int updateCetymology(Cetymology cetymology) {
		// TODO Auto-generated method stub
		String sql = "UPDATE " + tableName + " SET parent=?, cetymology=? WHERE word = ?";
		return jdbcTemplate.update(sql, cetymology.getParent(),
				cetymology.getCetymology(), cetymology.getWord());
	}
}
