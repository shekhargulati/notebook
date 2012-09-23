package com.openshift.notebook.core.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "notebooks")
public class Notebook {

	@Id
	private String id;

	@NotNull
	private String name;

	@NotNull
	@Size(max = 4000)
	private String description;

	@NotNull
	@DateTimeFormat(style = "M-")
	private Date created = new Date();

	@NotNull
	private String author;

	@NotNull
	@Size(max = 4)
	private String[] tags;

	@Size(max = 10)
	private Note[] notes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setNotes(Note[] notes) {
		this.notes = notes;
	}

	public Note[] getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		return "Notebook [id=" + id + ", name=" + name + ", author=" + author
				+ "]";
	}

}
