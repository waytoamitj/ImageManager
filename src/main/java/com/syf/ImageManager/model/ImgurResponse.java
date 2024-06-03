package com.syf.ImageManager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgurResponse {

	private int status;
	private boolean success;
	
	@JsonProperty("data")
	private Image data;

	// Getters and Setters
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Image getData() {
		return data;
	}
	public void setData(Image data) {
		this.data = data;
	}
	
}
