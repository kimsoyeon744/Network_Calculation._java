# Network_Calculation._java


목적: Java를 사용하여 인터넷 계산기 프로그램 개발

Requirements 
•	< 요구조건 >:
- Java 소켓 API를 사용하여 클라이언트 및 서버 프로그램 모두 생성
-	4가지 기본 사칙연산 수행
-	서버에서 오는 답은 정답이나 에러 메시지여야 함
-	서버는 여러 클라이언트를 처리할 수 있어야 함
-	서버 정보는 text file에 저장돼있음 (파일 읽어서 정보 얻어야 함, 파일 없으면 default 정보로 처리)

#Introduction
In the realm of computer science, understanding how to create applications that can efficiently handle multiple clients is crucial. 
This tutorial focuses on building a basic calculator that can Add, Subtract, Multiply or Divide two numbers. The server accepts
calculations from multiple clients and responds with the results. To achieve this, we’ll utilize Java’s multithreading capabilities and socket programming.

First, we understand the basics of java socket programming. Java Socket is used to communicate between two different JREs. 
Java socket can be connection-oriented or connection-less. In java, we have a package called “java.net”. In this package, 
we have two classes Socket Class and Server Class. Those classes are used to create connection-oriented or connection-less programs.
In this article, We will see how the java server will perform the basic operations and send back the result to the java client.


=Java Client
First, we create and write client-side socket code. In client socket program must know two information

-IP Address of Server and,
-Port Number

=Java Server
In this Java Server, we are performing simple calculations example addition, subtraction, etc. In this Code 
first, we receive input from the client-side. when the result is ready java server sends it back to the result client
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Conclusion
By combining multithreading and socket programming in Java, we’ve built a multi-client calculator server that 
can efficiently handle multiple client connections concurrently. This project provides valuable insights into building 
scalable and responsive network applications. As you delve deeper into the realm of computer science, remember that
mastering concurrent programming is a crucial skill for designing high-performance systems.



=========================================================================================================================================================================================

Explanation of my Code
#client_calc.java / server_calc.java 

PROTOCOL
 	
•	server_calc class
1. 서버소켓 생성
2. calculation 클래스 내부에는 calculation이라는 메서드에서 3가지 매게 변수 사용 (operand1, operand2, operator)
3. Switch 문 : 계산기의 핵심 논리는 `operator` 매개변수를 기반으로 하는 스위치 문을 사용하여 구현됩니다. 연산자에 따라 다른 산술 연산이 수행됩니다.
4. 산술 연산 : 
   - `ADD`: `operand1`과 `operand2`를 더한다.
   - `SUB`: `operand1`에서 `operand2`를 뺀다.
   - `MUL: `operand1`과 `operand2`를 곱한다.
   - `DIV`: `operand1`을 `operand2`로 나누지만 오류를 방지하기 위해 0으로 나누었는지 확인한다.
5.오류 처리 : 코드는 나누기 또는 모듈로를 수행하기 전에 `operand2`가 0인지 확인하여 0으로 나누기를 처리합니다. `operand2`가 0이면 오류 메시지를 인쇄합니다.

•	client_calc class
1. "server_info.txt" 읽고 서버 정보 가져오기 (txt없으면 default값을 사용)
2. 클라이언트 소켓 생성, 계산기 기본 틀 생성
3. bye를 치면 연결 종료
