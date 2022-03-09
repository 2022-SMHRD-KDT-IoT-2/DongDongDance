#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
#include <HttpClient.h>

const char* ssid = "wift 아이디";    //wifi 아이디
const char* password =  "wifi 비번";    // wifi 비번
const char* serverName = "웹서버주소"; // 웹서버주소

IPAddress hostIp(192, 168, 0, 121);  //웹서버의 ip 주소
int SERVER_PORT = 8080;  // 웹서버 포트 번호
WiFiClient client;

void setup() {

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
//    Serial.println("ab : " + ab);
    char str[50];
    const char *pch = ab.c_str();
    strcpy(str, pch);

    if (pch[0] == 's') { // 시작 문장 1번째 문자가 s일 때만 웹서버로 송신 -> 통신이 꼬여서 가끔 잘못된 값이 들어올 수 있다.
      String* nums = sub_string(ab); // 전류값 문자열 받음
//      for (int i = 0; i < 4; i++) Serial.println(nums[i]);
      if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
        HTTPClient http;
        http.begin("http://192.168.0.121:8080/GitTest/Elect_read");  //Specify destination for HTTP request
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
      delay(1000);  //Send a request every 10 seconds
    }
  }
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
