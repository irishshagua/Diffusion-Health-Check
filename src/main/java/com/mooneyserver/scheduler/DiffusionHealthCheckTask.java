package com.mooneyserver.scheduler;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pushtechnology.diffusion.api.client.ExternalClientConnection;
import com.pushtechnology.diffusion.api.connection.ConnectionFactory;
import com.pushtechnology.diffusion.api.connection.ServerDetails;

public class DiffusionHealthCheckTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiffusionHealthCheckTask.class);

	public void performTask() throws Exception {		
		Results results = new Results();
		ServerDetails serverDetails = createServerDetails("https://realtime.paddypower.com/diffusion");
		ExternalClientConnection clientConnection = new ExternalClientConnection(new MyListener(results), serverDetails);
		
		results.setConnectionAttempted(new Date());
		clientConnection.connect();
		
		results.setSubscription(new Date());
		clientConnection.subscribe("PaddyPower/Sportsbook/Sports/49/Competitions/289/Events/.*");		
		
		synchronized (this) {
			wait(10_000);
		}
		
		clientConnection.close();		
		LOGGER.info("Results: {}", results);
		
		// https://docs.appdynamics.com/display/PRO14S/Standalone+Machine+Agent+HTTP+Listener
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:83429/machineagent/metrics?name=Custom Metrics|Test|My Metric&value=42&type=current");
		target.request().get();
	}
	
	private ServerDetails createServerDetails(final String url) throws Exception {
        final ServerDetails serverDetails = ConnectionFactory.createServerDetails(url);
        return serverDetails;
    }
}
