/**
 * 
 */
package myenglish.word.dao.impl;

import myenglish.word.dao.IMeaningsDao;

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
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private String tableName = "meanings";
	/* (non-Javadoc)
	 * @see myenglish.dao.IMeaningsDao#getMeaningsByWord(java.lang.String)
	 */
	public String getMeaningsByWord(String word) {
		// TODO Auto-generated method stub
		String meaning = null;
		String sql = "SELECT meaning from " + tableName + " WHERE word = '" + word + "'";
		try {
			meaning = jdbcTemplate.queryForObject(sql, String.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return meaning;
	}

}
