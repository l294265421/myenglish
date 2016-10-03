package myenglish.word.dao;

import java.util.List;

import myenglish.word.po.Eetymology;

public interface IEetymologyDao {
	public List<Eetymology> getEetymologyByWord(String word);
	public void saveEetymology(Eetymology eetymology);
}
