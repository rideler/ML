package model;
/**
 * Review object with structure of id: string and text: string
 * each review will hold unique id
 */
public class Review {

	private String id;
	private String text;
	/**
	 * CTOR that gets 2 strings
	 * @param id unique number in string
	 * @param text the content of the review
	 */
	public Review(String id, String text) {
		setId(id);
		setText(text);
	}
//getter and setters
	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

}
