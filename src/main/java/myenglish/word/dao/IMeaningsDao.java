package myenglish.word.dao;

public interface IMeaningsDao {
	public String getMeaningsByWord(String word);
	public void saveWordMeanings(String word, String meaning);
}
