#include <ArduinoJson.h>
#include <WiFi.h>
#include <HTTPClient.h>
// 릴레이 핀 선언
int Relaypin1 = 16;                 // IN1
int Relaypin2 = 17;                 // IN2
int Relaypin3 = 25;                 // IN3
int Relaypin4 = 26;                 // IN4

const char* ssid = "Hotspot"; 
const char* password =  "12121212";

String result = "";

void setup() {
  pinMode(Relaypin1, OUTPUT);        // 릴레이 제어 1번핀을 출력으로 설정
  pinMode(Relaypin2, OUTPUT);        // 릴레이 제어 2번핀을 출력으로 설정
  pinMode(Relaypin3, OUTPUT);        // 릴레이 제어 3번핀을 출력으로 설정
  pinMode(Relaypin4, OUTPUT);        // 릴레이 제어 4번핀을 출력으로 설정

  Serial.begin(9600);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");

}

void loop() {

  if ((WiFi.status() == WL_CONNECTED)) { //Check the current connection status

    HTTPClient http;

    http.begin("http://192.168.0.121:8080/GitTest/Realy_write");

    //     http.begin("http://220.71.97.131:8081/IoT/Exam01");
    //     http.begin("http://project-student.ddns.net/nayeho/Exam01"); //Specify the URL
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

      if(relay1 == "1") digitalWrite(Relaypin1, LOW); // 릴레이 설정부분
      else digitalWrite(Relaypin1, HIGH);
      
      if(relay2 == "1") digitalWrite(Relaypin2, LOW); 
      else digitalWrite(Relaypin2, HIGH);
      
      if(relay3 == "1") digitalWrite(Relaypin3, LOW); 
      else digitalWrite(Relaypin3, HIGH);
      
      if(relay4 == "1") digitalWrite(Relaypin4, LOW); 
      else digitalWrite(Relaypin4, HIGH);



    }
    else {
      Serial.println("Error on HTTP request");
    }

    http.end(); //Free the resources
  }
  delay(1000);
}
