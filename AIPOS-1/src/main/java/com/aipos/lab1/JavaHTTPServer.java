package com.aipos.lab1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.StringTokenizer;

public class JavaHTTPServer implements Runnable {
    private static final Logger logger = LogManager.getLogger(JavaHTTPServer.class);

    private Socket connect;

    private BufferedReader in;
    private PrintWriter out;
    private BufferedOutputStream dataOut;

    JavaHTTPServer(Socket connect) {
        this.connect = connect;
        try {
            connect.setKeepAlive(true);
            connect.setSoTimeout(3000);
        } catch (SocketException e) {
            System.exit(0);
        }

    }

    @Override
    public void run() {
      
        try {
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            String input = in.readLine();
            logger.log(Level.INFO, "Input is:\n" + input);
            StringTokenizer parse;
            try {
                parse = new StringTokenizer(input);
            } catch (NullPointerException e){
                return;
            }

            String method = parse.nextToken().toUpperCase();
            logger.log(Level.INFO, "Request method: " + method);

        } catch (IOException ioe) {
            logger.log(Level.ERROR, "Server error : " + ioe);
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close();
            } catch (Exception e) {
                logger.log(Level.ERROR, "Error closing stream : " + e.getMessage());
            }
            logger.log(Level.INFO, "Connection closed");
        }
    }


    private void createResponse(HTTPCodes code, ContentType content, int fileLength, byte[] fileData) throws IOException {
        out.println("HTTP/1.1 " + code.getCode() + " " + code.getDescription());
        out.println("Server: Java HTTP Server");
        out.println("Date: " + new Date());
        out.println("Content-type: " + content.getText());
        out.println("Content-length: " + fileLength);
        out.println("Access-Control-Allow-Origin: " + "localhost");
        out.println("Access-Control-Allow-Methods: " + "GET, POST, DELETE, PUT");
        out.println();
        out.flush();
        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
        logger.log(Level.INFO, "type " + content.getExtension() + " size " + fileLength);
        try {
            Thread.sleep(fileLength / 100);
        } catch (InterruptedException e) {

        }
        logger.log(Level.INFO, "Creating header of response with code " + code.getCode());
    }
}