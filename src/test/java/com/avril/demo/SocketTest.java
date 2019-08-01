package com.avril.demo;

import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

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
		stompClient.connect("ws://localhost:8080/", sessionHandler);
		 
		new Scanner(System.in).nextLine(); // Don't close immediately.
	}
}
