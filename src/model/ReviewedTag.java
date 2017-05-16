/**
 * 
 */
package model;

import java.util.HashMap;

/**
 * object of processed review
 *hold id: string and hashmap of category:string and tag:string
 *tag can be only 1,-1,0 
 */
public class ReviewedTag {
	private String id;
	private HashMap <String, String> tags;
	/**
	 * 
	 */
	public ReviewedTag(String id, HashMap <String, String> tags) {
		setId(id);
		setTags(tags);
	}
	public String getId() {
		return id;
	}
	private void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tags
	 */
	public HashMap <String, String> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	private void setTags(HashMap <String, String> newTags) {
		this.tags = new HashMap <String, String>();
		this.tags.putAll(newTags);
	}

}
