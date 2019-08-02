package com.avril.demo;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.avril.model.ChatMessage;
import com.avril.model.ChatMessage.MessageType;

public class SocketTest {
//	public static void main(String[] args) {
//		String hostName = "127.0.0.1";
//		int portNumber = 8080;
//        try {
//            Socket echoSocket = new Socket(hostName, portNumber);
//            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader( new InputStreamReader(echoSocket.getInputStream()));
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            String userInput;
//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//                System.out.println("echo: " + in.readLine());
//	            }
//            echoSocket.close();
//		}catch(Exception e) {
//			System.err.println("fuck that shit");
//			e.printStackTrace();
//		}finally {
//			
//		}
//	}
	
	public static void main(String[] args) {
		WebSocketClient client = new StandardWebSocketClient();
		 
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		 
		StompSessionHandler sessionHandler = new MySessionHandler();
		try {
			StompSession sp = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();
			ChatMessage chat = new ChatMessage();
			chat.setType(MessageType.JOIN);
			chat.setSender("jbaez");
			sp.send("/app/chat.addUser", chat);
			
			String st = "";
			do {
				st = new Scanner(System.in).nextLine();			
				chat.setType(MessageType.CHAT);
				chat.setContent(st);
				sp.send("/app/chat.sendMessage", chat);
			}			
			while(!"x".equals(st));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
