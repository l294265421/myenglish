package myenglish.word.dao.impl;

import myenglish.word.dao.IPhoneticDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneticDaoImpl implements IPhoneticDao {
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private String tableName = "phonetics";

	public String getPhoneticByWord(String word) {
		// TODO Auto-generated method stub
		String phonetic = null;
		String sql = "SELECT phonetic FROM " + tableName + " WHERE word = '" + word + "'";
		try {
			phonetic = jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return phonetic;
	}

}
