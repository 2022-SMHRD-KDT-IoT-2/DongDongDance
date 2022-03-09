// 데이터보내는쪽 UNO
const int SensorIn1 = A0;
const int SensorIn2 = A1;
const int SensorIn3 = A2;
const int SensorIn4 = A3;
int mVperAmp = 185; // use 185 for 5A Module and 66 for 30A Module

//double Voltage = 0;
double VRMS = 0;
double AmpsRMS = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {
  double Voltage1 = getVPP(SensorIn1);
  double Amps1 = amp(Voltage1); // 1번 콘센트 전류센서값
  
  double Voltage2 = getVPP(SensorIn2);
  double Amps2 = amp(Voltage2); // 2번 콘센트 전류센서값

  double Voltage3 = getVPP(SensorIn3);
  double Amps3 = amp(Voltage3); // 3번 콘센트 전류센서값

  double Voltage4 = getVPP(SensorIn4);
  double Amps4 = amp(Voltage4); // 4번 콘센트 전류센서값

  String st = "s"+(String)Amps1+","+(String)Amps2+","+(String)Amps3+","+(String)Amps4;
  Serial.println(st);
  
}

float amp(float Voltage) {
  double VRMS = (Voltage / 2.0) * 0.707;
  double AmpsRMS = (VRMS * 10000) / mVperAmp;
  AmpsRMS -= 0.559; // 노이즈값 제거
  return AmpsRMS;
}

float getVPP(int SensorIn)
{
  float result;

  int readValue;             //value read from the sensor
  int maxValue = 0;          // store max value here
  int minValue = 1024;          // store min value here

  uint32_t start_time = millis();
  while ((millis() - start_time) < 1000) //sample for 1 Sec
  {
    readValue = analogRead(SensorIn);
    // see if you have a new maxValue
    if (readValue > maxValue)
    {
      /*record the maximum sensor value*/
      maxValue = readValue;
    }
    if (readValue < minValue)
    {
      /*record the maximum sensor value*/
      minValue = readValue;
    }
  }

  // Subtract min from max
  result = ((maxValue - minValue) * 5.0) / 1024.0;

  return result;
}
