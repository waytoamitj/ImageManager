package com.syf.ImageManager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("deletehash")
	private String deleteHash;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("size")
	private String size;
	
	@JsonProperty("link")
	private String link;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id")
//	private User user;

	// Getters and Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteHash() {
		return deleteHash;
	}

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}


}
