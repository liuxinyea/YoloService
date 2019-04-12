package com.example.demo.webSocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MyWebSocket extends WebSocketServer {
    final List<String> a=new ArrayList<>();
    public MyWebSocket( int port ) throws UnknownHostException {
        super( new InetSocketAddress( port ) );
    }
    public MyWebSocket( InetSocketAddress address ) {
        super( address );
    }
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast( "new connection: " + clientHandshake.getResourceDescriptor()+1); //This method sends a message to all clients connected
        System.out.println( webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        broadcast( webSocket + " has left the room!" );
        System.out.println( webSocket + " has left the room!" );
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println( webSocket.getRemoteSocketAddress() + ": " + s );
    }
    public static void main( String[] args ) throws InterruptedException , IOException {
        int port = 8888; // 843 flash policy port
        try {
            port = Integer.parseInt( args[ 0 ] );
        } catch ( Exception ex ) {
        }
        MyWebSocket s = new MyWebSocket( port );
        s.start();
        System.out.println( "ChatServer started on port: " + s.getPort() );
        s.broadcast("ChatServer==================================>" );
        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.broadcast( in );
            if( in.equals( "exit" ) ) {
                s.stop(1000);
                break;
            }
        }
    }
    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

}
