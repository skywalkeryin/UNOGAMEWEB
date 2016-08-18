package WS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Dependent
@ServerEndpoint("/player")
public class PlayerList {

	@OnOpen
	public void onOpen(Session session, @PathParam("topic") String topic) {
		//Treat this as HttpSession
		Map<String, Object> sessObject = session.getUserProperties();
		sessObject.put("topic", topic);	
                System.out.println("\ttopic: "+topic);
	}
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println(">> close connection: " + session.getId());
		System.out.println("\t close reason: " + reason.getReasonPhrase());
	}

	@OnMessage
	public void onMessage(Session session, String msg) {
//                String name=(String)session.getUserProperties().get("name");
		InputStream is = new ByteArrayInputStream(msg.getBytes());
		JsonReader reader = Json.createReader(is);
		JsonObject data = reader.readObject();
//		String topic = data.getString("topic");
               
                String newMessage = Json.createObjectBuilder()
				.add("aplayer", data.getString("player"))
				.build()
				.toString();
                
                     for(Session s: session.getOpenSessions())          
                              try{
                                  s.getBasicRemote().sendText(newMessage);
                              }catch (IOException ex){
                                  ex.printStackTrace();                
                              }
                       
		System.out.println("\toutgoing: " + newMessage);

	}
	
}
