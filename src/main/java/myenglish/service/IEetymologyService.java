package myenglish.service;

import java.util.List;

import myenglish.entity.Eetymology;

public interface IEetymologyService {
	public List<Eetymology> getEetymologyByWord(String word);
}
