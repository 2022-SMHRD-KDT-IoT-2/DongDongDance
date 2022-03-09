#include <ArduinoJson.h>
#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "Hotspot"; // wifi 이름
const char* password =  "12121212"; // wifi 패스워드

String result = "";

void setup() {

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

    http.begin("웹주소");


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


    }
    else {
      Serial.println("Error on HTTP request");
    }

    http.end(); //Free the resources
  }
  delay(1000);
}
