package myenglish.word.service;

public interface IMeaningsService {
	public String getMeaningsByWord(String word);
	public void saveWordMeanings(String word, String meaning);
}
