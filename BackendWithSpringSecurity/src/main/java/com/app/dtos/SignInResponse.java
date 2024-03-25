package com.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInResponse {
	@JsonProperty(value = "JWT_TOKEN")
	private String jwt;

	@JsonProperty(value = "message")
	private String mesg;

	public SignInResponse() {
		super();
	}

	public SignInResponse(String jwt, String mesg) {
		super();
		this.jwt = jwt;

		this.mesg = mesg;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

}
