#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <HttpClient.h>
int Relaypin1 = 16;                 // IN1 - 릴레이
int Relaypin2 = 17;                 // IN2
int Relaypin3 = 25;                 // IN3
int Relaypin4 = 26;                 // IN4
const char* ssid = "Hotspot";    //wifi 아이디
const char* password =  "12121212";    // wifi 비번
String result = "";
WiFiClient client;

void setup() {
  pinMode(Relaypin1, OUTPUT);        // 릴레이 제어 1번핀을 출력으로 설정
  pinMode(Relaypin2, OUTPUT);        // 릴레이 제어 2번핀을 출력으로 설정
  pinMode(Relaypin3, OUTPUT);        // 릴레이 제어 3번핀을 출력으로 설정
  pinMode(Relaypin4, OUTPUT);        // 릴레이 제어 4번핀을 출력으로 설정
  
  Serial.begin(9600);
  WiFi.begin(ssid, password);   // 와이파이 접속
  while (WiFi.status() != WL_CONNECTED) { //Check for the connection
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
}

void loop() {

  if (Serial.available()) { //시리얼 입력이 들어왔을때
    String ab = Serial.readStringUntil('\n'); // 받은 문자열을 ab에 넣는다.
    Serial.println("ab : " + ab);
    char str[50];
    const char *pch = ab.c_str();
    strcpy(str, pch);

    if (pch[0] == 's') { // 시작 문장 1번째 문자가 s일 때만 웹서버로 송신 -> 통신이 꼬여서 가끔 잘못된 값이 들어올 수 있다.
      String* nums = sub_string(ab); // 전류값 문자열 받음
//      for (int i = 0; i < 4; i++) Serial.println(nums[i]);
      if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
        HTTPClient http;
        http.begin("http://220.71.97.131:8081/GitTest/Elect_read");  //Specify destination for HTTP request
        http.addHeader("Content-Type",  "application/x-www-form-urlencoded");   //Specify content-type header,  Json형식의 타입이다.

        String httpRequestData = "value1="+nums[0]+"&value2="+nums[1]+"&value3="+nums[2]+"&value4="+nums[3];  //
        Serial.println(httpRequestData); //
        int httpResponseCode = http.POST(httpRequestData);   //Send the actual POST request

        if (httpResponseCode > 0) { // 잘 전송되었으면
          String response = http.getString();                       //Get the response to the request
          Serial.println(httpResponseCode);   //Print return code
          Serial.println(response);           //Print request answer

        } else { // 전송이 실패할 경우
          Serial.print("Error on sending POST: ");
          Serial.println(httpResponseCode);
        }
        http.end();  //Free resources

      } else {
        Serial.println("Error in WiFi connection");
      }
      delay(500);  //Send a request every 10 seconds
    }
  }
  if ((WiFi.status() == WL_CONNECTED)) { //Check the current connection status

    HTTPClient http;

    http.begin("http://웹서버주소/GitTest/Realy_write");

    int httpCode = http.GET();                                        //Make the request

    if (httpCode > 0) { //Check for the returning code

      Serial.println(httpCode);
      result = http.getString();
      Serial.println(result);

      DynamicJsonBuffer jsonBuffer;
      JsonObject& root = jsonBuffer.parseObject(result);
      String relay1 = root["relay1"];
      String relay2 = root["relay2"];
      String relay3 = root["relay3"];
      String relay4 = root["relay4"];

      Serial.println("relay1 : " + relay1 );
      Serial.println("relay2 : " + relay2 );
      Serial.println("relay3 : " + relay3 );
      Serial.println("relay4 : " + relay4 );

      if(relay1 == "1") digitalWrite(Relaypin1, HIGH); // 릴레이 설정부분
      else digitalWrite(Relaypin1, LOW);
      
      if(relay2 == "1") digitalWrite(Relaypin2, HIGH); 
      else digitalWrite(Relaypin2, LOW);
      
      if(relay3 == "1") digitalWrite(Relaypin3, HIGH); 
      else digitalWrite(Relaypin3, LOW);
      
      if(relay4 == "1") digitalWrite(Relaypin4, HIGH); 
      else digitalWrite(Relaypin4, LOW);

    }
    else {
      Serial.println("Error on HTTP request");
    }

    http.end(); //Free the resources
  }
  delay(1000);
}

String* sub_string(String input)
{
  static String arr[4];
  int first = input.indexOf(",");// 첫 번째 콤마 위치
  int second = input.indexOf(",", first + 1); // 두 번째 콤마 위치
  int third = input.indexOf(",", second + 1);
  int length = input.length(); // 문자열 길이

  String str1 = input.substring(1, first);
  arr[0] = str1;
  String str2 = input.substring(first + 1, second);
  arr[1] = str2;
  String str3 = input.substring(second + 1, third);
  arr[2] = str3;
  String str4 = input.substring(third + 1, length - 1); 
  arr[3] = str4;

  return arr;
}
