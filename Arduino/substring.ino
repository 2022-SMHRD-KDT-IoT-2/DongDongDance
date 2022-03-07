void setup(){
 Serial.begin(9600);
}

void loop(){
  float a = 3.14;
  float b = 1.33;
  float c = 4.62;
  float d = 8.31;
  String ab = (String)a+","+(String)b+","+(String)c+","+(String)d;
//  Serial.print(ab); // uart 통신
  delay(3000);
  
  String* nums = sub_string(ab);
  for(int i=0; i<4; i++) printf("%s ",nums[i]);
}
// 함수로 사용.. UNO에서 사용할 예정 , 미완
String* sub_string(String input)
{
  static String arr[4];
  int first = input.indexOf(",");// 첫 번째 콤마 위치
  int second = input.indexOf(",", first + 1); // 두 번째 콤마 위치
  int third = input.indexOf(",", second + 1);
  int length = input.length(); // 문자열 길이

  String str1 = input.substring(0, first);
  arr[0]=str1;
  String str2 = input.substring(first + 1, second);
  arr[1]=str2;
  String str3 = input.substring(second + 1, third);
  arr[2]=str3;
  String str4 = input.substring(third + 1, length);
  arr[3]=str4;
  Serial.println(str1);
  Serial.println(str2);
  Serial.println(str3);
  Serial.println(str4);
  
  return arr;
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

