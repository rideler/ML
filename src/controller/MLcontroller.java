/**
 * 
 */
package controller;

import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONObject;

import model.JsonParser;
import model.MLmodel;
import view.Gateway;

/**
 * class Presenter that implements Observer will coordinate between a model and a view
 *
 */
public class MLcontroller implements Observer {

	private MLmodel model;
	private Gateway view;
	
	/**
	 * Ctor for Presenter
	 * @param model the model the Presenter will work with
	 * @param view the view the Presenter will work with
	 */
	public MLcontroller(MLmodel model,  Gateway view) {
		setModel(model);
		setView(view);
		model.addObserver(this);
		view.addObserver(this);
	}
	/**
	 * get the model the presenter work with
	 * @return the model
	 */
	public MLmodel getModel() {
		return model;
	}
	/**
	 * set the model the presenter work with
	 * @param model
	 */
	public void setModel(MLmodel model) {
		this.model = model;
	}
	/**
	 * get the view the presenter work with
	 * @return the view
	 */
	public Gateway getView() {
		return view;
	}
	/**
	 * set the view the presenter work with
	 * @param view
	 */
	public void setView(Gateway view) {
		this.view = view;
	}

	/**
	 * @Override for Observer method update
	 */
	@Override
	public void update(Observable obs, Object obj) {
		Object[] args = (Object[])obj;
		String cmd = "";
		try {
			cmd = (String)args[0];
		} catch (Exception e) {
			view.error("wrong argumetns");
		}
		if (obs == model)
		{
			switch(cmd)
			{
			case "taggedJson":
				if (JsonParser.objIsJason(args[1])){
					view.sender(args);
				}
				else{
					view.error("param not Json");
				}
				break;	
			case "error": 
				String err = "";
				try {
					err = (String)args[1];
				} catch (Exception e) {
					view.error("unknown error from model");
				}
				view.error(err);
				break;
			default:
				view.error("no such command");
				break;
					
			}
			
		}
		if (obs == view)
		{
			switch(cmd)
			{
			case "classify": 
				if (!JsonParser.objIsJason(args[1])){
					view.error("param not Json");
				}
				else{
					JSONArray jsonArray = JsonParser.jsonToArray((JSONObject)args[1]);
					if (jsonArray.length() != 0){
						args[1] = jsonArray;
						model.classifyReview(args);
					}
					else{
						view.error("Json not reviewes format");
					}
				}
				break;
			case "error": 
				String err = "";
				try {
					err = (String)args[1];
				} catch (Exception e) {
					view.error("unknown error from view");
				}
				view.error(err);
				break;
			default:
				view.error("no such command");
				break;
			}

		}

	}


	
}
