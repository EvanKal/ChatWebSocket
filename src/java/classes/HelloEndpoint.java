/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.util.Set;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Los_e
 */
@ServerEndpoint("/hello")
public class HelloEndpoint {
    private SessionRegistry registry = SessionRegistry.getInstance();
    
    //Sti sunartisi auti mpainei otan apo o browser anoigei to JS arxeio kai dimiourgeitai to neo Websocket
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) throws IOException {
        registry.add(session);
        session.getBasicRemote().sendText("People in chat " + registry.getAll().size());
    }
 
    @OnMessage
    public void onMessage(Session sender, String message) throws IOException {
        Set<Session> sessions = registry.getAll();
        for(Session s : sessions) {
            try {
                s.getBasicRemote().sendText("User said: " + message);
            } catch (IOException iOException) {
            }
        }
    }
 
    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
