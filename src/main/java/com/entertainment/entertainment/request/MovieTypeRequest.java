package com.entertainment.entertainment.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value= {"id"},allowGetters=true)
public class MovieTypeRequest {

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("type")
	@NotBlank
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
