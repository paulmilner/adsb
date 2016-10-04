package com.jeannot.adsb.processor;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Try out the JSON API: http://www.adsbexchange.com/data
 * The "Querying Live Position Data" JSON API can give you a snapshot of what planes are in the air
 * (within a given distance of a lat/long etc.)
 *
 */
public class AdsbJsonApiTest {
	

	/**
	 * Get some JSON data and do something with it...
	 * @throws Exception
	 */
	@Test
	public void read_some_JSON_data_and_do_something_with_it() throws Exception {
		
		//Sometimes you need a proxy, depends where you are...
	    //System.setProperty("java.net.useSystemProxies", "true");
		//System.setProperty("http.proxyHost", "proxy.logica.com");
	    //System.setProperty("http.proxyPort", "80");	  	    
		
	    Client client = Client.create();

		//Lat/long example: 51.141261, 0.178647, 0-20km radius, including "short trail" data. See their documentation.
		WebResource webResource = client
		   .resource("http://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?lat=51.141261&lng=0.178647&fDstL=0&fDstU=20&trFmt=sa");

		//Needs a User-Agent when invoked from Java code...
		ClientResponse response = webResource.header("User-Agent","jersey-rest-client").accept("application/json").get(ClientResponse.class);
		
		//Do something with the JSON we get back...
		String json = response.getEntity(String.class);
		ObjectMapper om = new ObjectMapper();

		//TODO get the result into an array of FlightTrackingInfo objects...
		

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		System.out.println(json);		
		
	}

}