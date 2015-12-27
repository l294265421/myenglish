package myenglish.service;

import myenglish.entity.Cetymology;

public interface ICetymologyService {
	public Cetymology getCetymologyByWord(String word);
	public int insertCetymology(Cetymology cetymology);
	public int updateCetymology(Cetymology cetymology);
}
