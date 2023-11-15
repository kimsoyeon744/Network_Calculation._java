//Calculation program(Server)

package server_calc;

import java.io.*;
import java.net.*;

public class server_calc {
   public static void main(String[] args) {
      ServerSocket ss = null;
      try {
         ss = new ServerSocket(1234);//serverPort
         
         
         for(;;) {
        	 System.out.println("Waiting for connecting......");
            //connected with client
            Socket cS = ss.accept(); 
            System.out.println("client " + cS.getInetAddress());
           
            new Thread(new exchange(cS)).start();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (ss != null) {
            try {
               ss.close();
            } 
            catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }
}


class exchange implements Runnable {
	   private Socket s;

	   public exchange(Socket s) {
	      this.s = s;
	   }

 
public void run() {
	   //create buffered stream input and output	   
	
   try {
      BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
      BufferedWriter output = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
      
      for (;;) {
         String MESSAGE = input.readLine();
         
         //if you enter bye, connection is lost
         if ("bye".equals(MESSAGE)) {
            System.out.println("client out(disconnect)");
            break;
         } 
         else if (MESSAGE == null  || MESSAGE.trim().isEmpty()) {
                output.write("no Messege");
                output.flush();// send everything in buffer

         } else {
            calculation net_calc = new calculation(MESSAGE);
            String result = net_calc.calculate();
            output.write(result + "\n");
            output.flush();
         }
      }
   }
catch (IOException e) {
      e.printStackTrace();
   } finally {
      try {
         s.close();
      } catch (IOException e) {
         System.out.println("Error closing the socket");
         e.printStackTrace();
      }
   }
}
}
   
  
   class calculation {
	    private int operand1, operand2;
	    private String operator;
	    double result=0.0;
	    String err;
	    calculation(String MESSAGE) {
	        String[] msg = MESSAGE.split(" ");
	        if (msg.length == 3) {
	            try {
	                operand1 = Integer.parseInt(msg[1]);
	                operand2 = Integer.parseInt(msg[2]);
	            } 
	            //error check
	            catch (NumberFormatException e) {
	                operator = "Wrong Input";
	            }
	            operator = msg[0].toUpperCase();
	        } else {
	            operator = "INCORRECT";
	        }
	        }
	    
	    
	    //calculator method
	    //Confirm the input and execute the four arithmetic operations.
	    String calculate() {
	    	if ("INCORRECT".equals(operator)) {
	        }
	        else if("Wrong input".equals(operator)) {
	           return "Please check input format";
	        }
	    	
	    	String add = "ADD", sub = "SUB", mul = "MUL", div = "DIV";
	    	
	    	
	    	if(operator.equals(add)) {
	    		return String.valueOf(operand1 + operand2);
	    	}
	    	else if(operator.equals(sub)) {
	    		return String.valueOf(operand1 - operand2);
	    	}
	    	else if(operator.equals(mul))
	    		return String.valueOf(operand1 * operand2);
	    	else if(operator.equals(div)){
	    		//Calculator doesn't work when operand2 is 0
	    		 if(operand2 !=0) {
	                 double result = operand1 / operand2;
	                 return String.valueOf(result);
	                }else {
	                	return "Incorrect: divided by zero";
	                }
	    	}
	    	else
	    	  return "Wrong input";
	    	}
  }

    


