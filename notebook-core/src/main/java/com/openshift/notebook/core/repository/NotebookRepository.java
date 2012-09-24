package com.openshift.notebook.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openshift.notebook.core.domain.Notebook;

@Repository
public interface NotebookRepository extends
		PagingAndSortingRepository<Notebook, String> {
	

}
