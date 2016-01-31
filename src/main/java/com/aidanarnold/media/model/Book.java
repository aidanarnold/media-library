package com.aidanarnold.media.model;

/**
 * POJO representing a Book entity
 * @author aidanarnold
 * 
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NotEmpty
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="is_read")
	private Boolean isRead;
	
	public Book() {
		
	}

	public Book(String title){
		this.title = title;
	}
	
	public Book(String title, String author, Boolean isRead) {
		this.title = title;
		this.author = author;
		this.isRead = isRead;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", isRead=" + isRead + "]";
	}
	
}
