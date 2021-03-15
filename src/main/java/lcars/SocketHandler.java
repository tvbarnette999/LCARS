package lcars;

import org.eclipse.jetty.websocket.api.CloseException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@WebSocket
public class SocketHandler {
    static final Logger LOG = LoggerFactory.getLogger(SocketHandler.class);
    static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());


    @OnWebSocketConnect
    public void connected(Session session) {
        sessions.add(session);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }


    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        if (message.equals("ping")) {
            session.getRemote().sendString("pong");
        }
    }

    public void broadcast(String message) {
        sessions.forEach(x -> {
            try {
                x.getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @OnWebSocketError
    public void onError(Session session, Throwable t) {
        if (t instanceof CloseException) {
            // ignore CloseExceptions
        } else {
            LOG.error("Websocket Error: {}", t.getMessage());
        }
    }


}
