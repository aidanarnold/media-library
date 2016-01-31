package com.aidanarnold.media.model;

/**
 * POJO representing a Game entity
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
@Table(name="GAME")
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NotEmpty
	@Column(name="title")
	private String title;
	
	@Column(name="platform")
	private String platform;
	
	@Column(name="is_played")
	private Boolean isPlayed;
	
	public Game() {
		
	}

	public Game(String title){
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
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Boolean getIsPlayed() {
		return isPlayed;
	}

	public void setIsPlayed(Boolean isPlayed) {
		this.isPlayed = isPlayed;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", platform=" + platform
				+ ", isPlayed=" + isPlayed + "]";
	}
	
}
