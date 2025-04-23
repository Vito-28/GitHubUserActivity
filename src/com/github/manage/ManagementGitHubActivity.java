package com.github.manage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ManagementGitHubActivity {
	
	public void manageMessageEvents() {
	    try {
	    	String userName = inputUserName();
	        if (getResponseCode(userName) != 200) {
	            throw new RuntimeException("HttpResponseCode: " + getResponseCode(userName));
	        } else {
	            printMessagesEvents(getEventsArray(getInline(userName)));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public String inputUserName() {
		Scanner sc = new Scanner(System.in);
	    System.out.print("github-activity ");
	    String userName = sc.next();
	    sc.close();
	    
	    return userName;
	}
	
	private URL getURL(String userName) throws Exception {
	    return new URL("https://api.github.com/users/" + userName + "/events");
	}

	private int getResponseCode(String userName) throws Exception {
	    HttpURLConnection conn = (HttpURLConnection) getURL(userName).openConnection();
	    conn.setRequestMethod("GET");
	    conn.connect();
	    return conn.getResponseCode();
	}

	private String getInline(String userName) throws Exception {
	    String inline = "";
	    Scanner scanner = new Scanner(getURL(userName).openStream());
	    while (scanner.hasNext()) {
	        inline += scanner.nextLine();
	    }
	    scanner.close();
	    return inline;
	}

	private JSONArray getEventsArray(String inline) throws Exception {
	    JSONParser parser = new JSONParser();
	    return (JSONArray) parser.parse(inline);
	}
	
	private void printMessagesEvents(JSONArray eventsArray) {
		System.out.println("Output: ");
        for (Object obj : eventsArray) {
            JSONObject event = (JSONObject) obj;
            String type = (String) event.get("type");

            if ("PushEvent".equals(type)) {
                JSONObject payload = (JSONObject) event.get("payload");
                JSONArray commits = (JSONArray) payload.get("commits");

                for (Object c : commits) {
                    JSONObject commit = (JSONObject) c;
                    System.out.println("- " + commit.get("message"));
                }
            }
        }
	}
	
}
