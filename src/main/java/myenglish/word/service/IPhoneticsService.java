package myenglish.word.service;

public interface IPhoneticsService {
	public String getPhoneticByWord(String word);
	public void saveWordPhonetic(String word, String phonetic);
}
