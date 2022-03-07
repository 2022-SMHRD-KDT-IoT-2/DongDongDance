void setup() { // 데이터 받는 쪽( esp-32 )
  // put your setup code here, to run once:
  Serial.begin(9600);
}
String input = "";
void loop() {
  // put your main code here, to run repeatedly:
  if (Serial.available())
  {
    input = Serial.readStringUntil('\n');
    Serial.print(input);

    int first = input.indexOf(",");// 첫 번째 콤마 위치
    int second = input.indexOf(",", first + 1); // 두 번째 콤마 위치
    int third = input.indexOf(",", second + 1);
    int length = input.length(); // 문자열 길이

    String str1 = input.substring(0, first); 
    String str2 = input.substring(first + 1, second); 
    String str3 = input.substring(second + 1, third); 
    String str4 = input.substring(third + 1, length); 
    
    Serial.println(str1);
    Serial.println(str2);
    Serial.println(str3);
    Serial.println(str4);

  }
}

void setup(){ // 데이터 보내는 쪽( UNO )
 Serial.begin(9600);
}

void loop(){
  float a = 3.14;
  float b = 1.33;
  float c = 4.62;
  float d = 8.31;
  String ab = (String)a+","+(String)b+","+(String)c+","+(String)d;
  Serial.print(ab); // uart 통신
  delay(3000);
}

