package com.aidanarnold.media.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * POJO representing a Movie
 * @author aidanarnold
 * 
 */
@Entity
@Table(name="MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@NotEmpty
	@Column(name="title")
	private String title;

	@Column(name="year")
	private String year;

	@Column(name="watched")
	private Boolean isWatched;

	public Movie() {

	}

	public Movie(String title){
		this.title = title;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getWatched() {
		return isWatched;
	}

	public void setWatched(Boolean watched) {
		isWatched = watched;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", title='" + title + '\'' +
				", year='" + year + '\'' +
				", isWatched=" + isWatched +
				'}';
	}
}
