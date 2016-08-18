package WS;

import java.io.IOException;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{nameid}")
public class EndPoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(">> new connection: " + session.getId());
        Map<String, Object> sessObject = session.getUserProperties();
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println(">> close connection: " + session.getId());
        System.out.println("\t close reason: " + reason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException {
        System.out.println(">> no of connection: " + session.getOpenSessions().size());
        for (Session s : session.getOpenSessions()) {
            s.getBasicRemote().sendText(msg);
        }

    }

}
