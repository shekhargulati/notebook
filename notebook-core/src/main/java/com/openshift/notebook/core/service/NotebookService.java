package com.openshift.notebook.core.service;

import com.openshift.notebook.core.domain.Notebook;

public interface NotebookService {

	public Notebook createNewNotebook(Notebook notebook);

	public Notebook findNotebook(String id);

	public long numberOfNotebooks();

	public Notebook updateNotebook(Notebook notebook);

	public void deleteNotebook(Notebook notebook);

}
