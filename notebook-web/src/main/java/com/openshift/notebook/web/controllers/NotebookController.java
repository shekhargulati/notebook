package com.openshift.notebook.web.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openshift.notebook.core.domain.Notebook;
import com.openshift.notebook.core.service.NotebookService;

@Controller
@RequestMapping("/notebooks")
public class NotebookController {
	
	@Inject
	NotebookService notebookService;
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Notebook notebook = Notebook.fromJsonToNotebook(json);
        notebookService.createNewNotebook(notebook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    } 

}
