
const int buttonPin = 2;
int lastButtonState = LOW;

long lastDebounceTime = 0;
long deltaDebouce = 50;

long lastPrintTime = 0;
long deltaPrintTime = 1000;

int count = 0;
boolean primed = false;

void setup(){
  pinMode(buttonPin, INPUT_PULLUP);
  Serial.begin(9600);
}

void loop(){
  //Serial.println(count);
  int reading = digitalRead(buttonPin);

  if(reading!=lastButtonState){
    lastDebounceTime = millis();
    lastButtonState = reading;
  }
  if(millis()-lastDebounceTime>=deltaDebouce){
    if(reading==lastButtonState && reading==LOW){
      if(primed == true){
        count++;
        primed = false;
      }
    }else if(reading==lastButtonState && reading==HIGH){
      primed = true;
    }
  }

  if(millis()-lastPrintTime>deltaPrintTime){
    Serial.write(count);
    lastPrintTime = millis();
  }
  
}

