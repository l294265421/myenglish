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
public class EetymologyDaoImpl implements IEetymologyDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Eetymology> getEetymologyByWord(String word) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("word", word);
//		return sqlSession.selectList("myenglish.word.IEetymologyDao.getEetymologyByWord", params);
		List<Eetymology> result = new LinkedList<Eetymology>();
		
		String databaseHome = "berkelydb";
		
		String indexDatabase = "eetymologyIndex";
		BerkelyDbCRUD berkelyDbCRUD = new BerkelyDbCRUD();
		berkelyDbCRUD.init(databaseHome, indexDatabase);
		
		String children = berkelyDbCRUD.get(word);
		if (StringUtils.isBlank(children)) {
			return result;
		}
		
		String dataDatabase = "eetymologyData";
		BerkelyDbCRUD berkelyDbCRUD2 = new BerkelyDbCRUD();
		berkelyDbCRUD2.init(databaseHome, dataDatabase);
		for(String child : children.split("#")){
			Eetymology eetymology = new Eetymology();
			eetymology.setWord(child);
			eetymology.setEetymology(berkelyDbCRUD2.get(child));
			result.add(eetymology);
		}
		return result; 
	}

	@Override
	public void saveEetymology(Eetymology eetymology) {
		// TODO Auto-generated method stub
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("eetymology", eetymology);
		sqlSession.insert("myenglish.word.IEetymologyDao.saveEetymology", parameter);
	}

}
