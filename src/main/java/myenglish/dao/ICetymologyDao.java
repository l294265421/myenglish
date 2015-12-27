package myenglish.dao;

import myenglish.entity.Cetymology;

public interface ICetymologyDao {
	public Cetymology getCetymologyByWord(String word);
	public int insertCetymology(Cetymology cetymology);
	public int updateCetymology(Cetymology cetymology);

}
