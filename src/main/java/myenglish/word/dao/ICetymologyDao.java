package myenglish.word.dao;

import myenglish.word.po.Cetymology;

public interface ICetymologyDao {
	public Cetymology getCetymologyByWord(String word);
	public int insertCetymology(Cetymology cetymology);
	public int updateCetymology(Cetymology cetymology);

}
