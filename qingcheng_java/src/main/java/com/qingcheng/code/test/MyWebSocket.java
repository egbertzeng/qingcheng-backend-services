package com.qingcheng.code.test;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by liguohua on 2017/5/8.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    private static CopyOnWriteArraySet<Session> onlinePeople = new CopyOnWriteArraySet<Session>();
    @OnOpen
    public void onOpen(Session client) {
        onlinePeople.add(client);
        System.out.println("有新链接加入!当前在线人数为" + onlinePeople.size());
    }

    @OnMessage
    public void onMessage(String message, Session speaker) throws IOException {
        System.out.println("来自客户端" + speaker + "的消息:" + message);
        // 群发消息
        for (Session lisienter : onlinePeople) {
            lisienter.getBasicRemote().sendText("from" + speaker.getId() + "  to" + lisienter.getId() + " :" + message);
        }
    }

    @OnClose
    public void onClose(Session client) {
        onlinePeople.remove(client);
        System.out.println("有一链接关闭!当前在线人数为" + onlinePeople.size());
    }
}