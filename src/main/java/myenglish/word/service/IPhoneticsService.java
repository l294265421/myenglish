package myenglish.word.service;

public interface IPhoneticsService {
	public String getPhoneticByWord(String word);
	public String getPhoneticFromInternet(String word);
	public String getPhoneticFromHtml(String html);
	public void saveWordPhonetic(String word, String phonetic);
}
