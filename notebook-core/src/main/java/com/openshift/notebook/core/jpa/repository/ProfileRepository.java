package com.openshift.notebook.core.jpa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openshift.notebook.core.domain.Profile;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

	List<Profile> findAll();
}
