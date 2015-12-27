package myenglish.entity;

public class Eetymology {
	private String word;
    private String eetymology;
    
    public Eetymology() {
		// TODO Auto-generated constructor stub
	}

	public Eetymology(String word, String eetymology) {
		super();
		this.word = word;
		this.eetymology = eetymology;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getEetymology() {
		return eetymology;
	}

	public void setEetymology(String eetymology) {
		this.eetymology = eetymology;
	}
}
