package com.openshift.notebook.web.controllers;

public class WebContextLoader extends GenericWebContextLoader {

	public WebContextLoader() {
		super("src/main/webapp/WEB-INF/", false);
	}

}
