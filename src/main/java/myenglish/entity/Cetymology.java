package myenglish.entity;

public class Cetymology {
	private String word;
	private String parent;
	private String cetymology;
	
	public Cetymology() {
		// TODO Auto-generated constructor stub
	}

	public Cetymology(String word, String parent, String cetymology) {
		super();
		this.word = word;
		this.parent = parent;
		this.cetymology = cetymology;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCetymology() {
		return cetymology;
	}

	public void setCetymology(String cetymology) {
		this.cetymology = cetymology;
	}

	@Override
	public String toString() {
		return "Cetymology [word=" + word + ", parent=" + parent
				+ ", cetymology=" + cetymology + "]";
	}
	
	

}
