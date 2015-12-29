package myenglish.word.service;

import java.util.List;

import myenglish.word.entity.Eetymology;

public interface IEetymologyService {
	public List<Eetymology> getEetymologyByWord(String word);
}
