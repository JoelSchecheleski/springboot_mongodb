package com.uppertools.workshop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.uppertools.workshop.dto.AuthorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "posts")
public class Post implements Serializable {
	private static final long SerialVersionUID = 1L;

	@Id
	private String id;
	private Date date;
	private String title;
	private String body;

	private AuthorDTO author;

	public Post() {
	}

	public Post(String id, Date date, String title, String body, AuthorDTO author) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return this.author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Post post = (Post) o;
		return Objects.equals(this.id, post.id);
	}

	@Override public int hashCode() {
		return Objects.hash(this.id);
	}


}
