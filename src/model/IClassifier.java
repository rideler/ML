/**
 * 
 */
package model;

/**
 * interface IClassifier
 */
public interface IClassifier {

	/**
	 * classify Attribute 
	 * @param text the text we want to classify.
	 * @return the result of the classification of the attribute.
	 */
	public String classifyAttribute(String text);
	
	/**
	 * get the name of the attribute.
	 * @return the name of the attribute.
	 */
	public String getAttrName();
}
