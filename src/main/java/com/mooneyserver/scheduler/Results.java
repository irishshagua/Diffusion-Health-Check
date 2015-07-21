package com.mooneyserver.scheduler;

import java.util.Date;


public class Results {
	
	private Date connectionAttempted;
	private Date connectionEstablished;
	
	private Date subscription;
	private Date lastItlReceived;
	
	public Date getConnectionAttempted() {
		return connectionAttempted;
	}
	public void setConnectionAttempted(Date connectionAttempted) {
		this.connectionAttempted = connectionAttempted;
	}
	
	public Date getConnectionEstablished() {
		return connectionEstablished;
	}
	public void setConnectionEstablished(Date connectionEstablished) {
		this.connectionEstablished = connectionEstablished;
	}
	
	public Date getSubscription() {
		return subscription;
	}
	public void setSubscription(Date subscription) {
		this.subscription = subscription;
	}
	
	public Date getLastItlReceived() {
		return lastItlReceived;
	}
	public void setLastItlReceived(Date lastItlReceived) {
		this.lastItlReceived = lastItlReceived;
	}
	
	@Override
	public String toString() {
		return "Connection Time: " + (connectionEstablished.getTime() - connectionAttempted.getTime()) +
				"\tLongest ITL: " + (lastItlReceived.getTime() - subscription.getTime());
	}
}
