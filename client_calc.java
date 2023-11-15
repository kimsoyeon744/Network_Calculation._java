//Calculation program(Client)

package client_calc;

import java.io.*;
import java.net.*;
import java.util.*;

public class client_calc{
   public static void main(String[] args) throws Exception // 
   {
      Socket socket = null;
      Scanner sc = new Scanner(System.in);

      try {
    	  String info, IP = null, str_server_port = null;
          int port = 0;
          
             
       // read the server information from server_info.txt
          File server_path = new File("server_info.txt"); //dataset txt file
          
          boolean isExists = server_path.exists();
        //default : when txt file is no exist


          if (!isExists) {
             IP = "127.0.0.1"; //default IP
             port = 1234;	//default port
          } else {
        	  BufferedReader dataset_txt = new BufferedReader(new FileReader("server_info.txt"));
             
             while ((info = dataset_txt.readLine()) != null) {
                
                String[] server_info = info.split(" ");
                IP = server_info[0];
                str_server_port = server_info[1];
                port = Integer.parseInt(str_server_port);
             }
             dataset_txt.close();
          }
          
          
          //client socket
          
          BufferedReader dis = null;
          BufferedWriter dos = null;
          
          socket = new Socket(IP, port); 

          dis = new BufferedReader(new InputStreamReader(socket.getInputStream())); // create input stream
          dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // create output stream
          
          System.out.println("\n===========Calculator============================\n");
          System.out.println("if you enter 'bye', you can exit");

          while (true) {
             System.out.println("\nEquation form : operator operand1 operand2");
             System.out.println("Ex) ADD 1 1\n");
             System.out.println("Enter:");
             String inp = sc.nextLine(); 
             // if you enter 'bye' you can exit
             if (inp.equals("bye")) {
               break;
             }


             dos.write(inp + "\n"); // send the input to server
             dos.flush();
             String result = dis.readLine(); // get the answer from the server
             System.out.println("Server>> " + result);
          }
       }
       catch (Exception e) {
          System.out.println("cannot connect");
          e.printStackTrace();
       }
           sc.close(); 
           if(socket != null)
        	   socket.close();
           
      }
   }
