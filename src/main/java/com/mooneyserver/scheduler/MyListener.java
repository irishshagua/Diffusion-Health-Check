package com.mooneyserver.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pushtechnology.diffusion.api.Credentials;
import com.pushtechnology.diffusion.api.ServerConnection;
import com.pushtechnology.diffusion.api.ServerConnectionListener;
import com.pushtechnology.diffusion.api.message.TopicMessage;
import com.pushtechnology.diffusion.api.topic.TopicStatus;

public class MyListener implements ServerConnectionListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);
	
	private final Results results;
	
	public MyListener(Results results) {
		this.results = results;
	}

	@Override
	public void messageFromServer(ServerConnection arg0, TopicMessage arg1) {
		if (arg1.isTopicLoad()) {
			results.setLastItlReceived(new Date());
		}
	}

	@Override
	public void serverConnected(ServerConnection arg0) {
		results.setConnectionEstablished(new Date());
	}

	@Override
	public void serverDisconnected(ServerConnection arg0) {
		LOGGER.info("Server Disconnected {}", arg0);
	}

	@Override
	public void serverRejectedCredentials(ServerConnection arg0,
			Credentials arg1) {
		LOGGER.info("Server Credentials Rejected {}", arg1);
	}

	@Override
	public void serverTopicStatusChanged(ServerConnection arg0, String arg1,
			TopicStatus arg2) {
		LOGGER.info("Topic Status Change {}", arg2);
	}
}
