#include <SPI.h>
#include <MFRC522.h>
#include <ArduinoJson.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <Tone32.h>

#define BUZZER_PIN 16
#define BUZZER_CHANNEL 0
#define RST_PIN   22                            // reset핀은 22번으로 설정
#define SS_PIN    21                           // SS핀은 21번으로 설정
// SS핀은 데이터를 주고받는 역할의 핀( SS = Slave Selector )
const char* ssid = "Hotspot"; // wifi 이름
const char* password =  "12121212"; // wifi 패스워드
WiFiClient client;
MFRC522 mfrc(SS_PIN, RST_PIN);                 // MFR522를 이용하기 위해 mfrc객체를 생성해 줍니다.

void setup() {
  Serial.begin(115200);                         // 시리얼 통신, 속도는 9600
  SPI.begin();                                // SPI 초기화                                             // (SPI : 하나의 마스터와 다수의 SLAVE(종속적인 역활)간의 통신 방식)
  mfrc.PCD_Init();

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
}

void loop() {

  if ( !mfrc.PICC_IsNewCardPresent() || !mfrc.PICC_ReadCardSerial() ) {
    // 태그 접촉이 되지 않았을때 또는 ID가 읽혀지지 않았을때
    delay(500);                                // 0.5초 딜레이
    return;                                    // return
  }
  tone(BUZZER_PIN, NOTE_B4, 300, BUZZER_CHANNEL);
  noTone(BUZZER_PIN, BUZZER_CHANNEL);
  Serial.print("Card UID:");                  // 태그의 ID출력
  String uid = "";
  for (byte i = 0; i < 4; i++) {    // 태그의 ID출력하는 반복문.태그의 ID사이즈(4)까지
    uid += mfrc.uid.uidByte[i];
    Serial.print(mfrc.uid.uidByte[i]);        // mfrc.uid.uidByte[0] ~ mfrc.uid.uidByte[3]까지 출력
    Serial.print(" ");                        // id 사이의 간격 출력
  }
  Serial.println(uid);
  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status

    HTTPClient http;

//    http.begin("주소/arduino_2/Servlet");  //Specify destination for HTTP request
    http.begin("주소/GitTest/Rfid_read");  //Specify destination for HTTP request
    http.addHeader("Content-Type",  "application/x-www-form-urlencoded");   //Specify content-type header,  Json형식의 타입이다.

    String httpRequestData = "uid="+uid;  // 
    Serial.println(httpRequestData); //시리얼 모니터
    int httpResponseCode = http.POST(httpRequestData);   //Send the actual POST request

    if (httpResponseCode > 0) { // 잘 전송되었으면

      String response = http.getString();                       //Get the response to the request

      Serial.println(httpResponseCode);   //Print return code
      Serial.println(response);           //Print request answer

    } else {

      Serial.print("Error on sending POST: ");
      Serial.println(httpResponseCode);

    }

    http.end();  //Free resources

  } else {

    Serial.println("Error in WiFi connection");
  }
  delay(1000);  //Send a request every 10 seconds
}
