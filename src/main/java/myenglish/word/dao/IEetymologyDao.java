package myenglish.word.dao;

import java.util.List;

import myenglish.word.entity.Eetymology;

public interface IEetymologyDao {
	public List<Eetymology> getEetymologyByWord(String word);

}
