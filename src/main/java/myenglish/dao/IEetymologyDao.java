package myenglish.dao;

import java.util.List;

import myenglish.entity.Eetymology;

public interface IEetymologyDao {
	public List<Eetymology> getEetymologyByWord(String word);

}
