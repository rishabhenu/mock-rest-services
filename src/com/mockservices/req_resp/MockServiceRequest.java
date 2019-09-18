package com.mockservices.req_resp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MockServiceRequest {

	private String name;
	private String body;
	private String path;
	private MockServiceRequestAdvancedOptions advancedOptions;
	private MockServiceRequestHeaders headers;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public MockServiceRequestAdvancedOptions getAdvancedOptions() {
		return advancedOptions;
	}
	public void setAdvancedOptions(MockServiceRequestAdvancedOptions advancedOptions) {
		this.advancedOptions = advancedOptions;
	}
	public MockServiceRequestHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(MockServiceRequestHeaders headers) {
		this.headers = headers;
	}
	
	@Override
	public String toString() {
		return "MockServiceRequest [name=" + name + ", body=" + body + ", path=" + path + ", advancedOptions="
				+ advancedOptions + ", headers=" + headers + "]";
	}
		
}
