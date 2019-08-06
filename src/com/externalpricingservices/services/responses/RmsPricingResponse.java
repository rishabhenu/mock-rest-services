package com.externalpricingservices.services.responses;

public class RmsPricingResponse {
	
	private String priceType;
	private String currency;
	private String createdAt;
	private double value;
	
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "RmsPricingResponse [priceType=" + priceType + ", currency=" + currency + ", createdAt=" + createdAt
				+ ", value=" + value + "]";
	}
}
