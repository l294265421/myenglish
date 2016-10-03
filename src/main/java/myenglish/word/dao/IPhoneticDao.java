package myenglish.word.dao;

public interface IPhoneticDao {
	public String getPhoneticByWord(String word);
	public void saveWordPhonetic(String word, String phonetic);
}
