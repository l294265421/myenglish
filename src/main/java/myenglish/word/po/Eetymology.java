package myenglish.word.po;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Eetymology {
	private String word;
    private String eetymology;
    
    public Eetymology() {
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
