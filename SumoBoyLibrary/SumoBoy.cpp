#include "SumoBoy.h"
#include "GOFi2cOLED.h"
#include "Arduino.h"

#define LEFTSPEEDMOTORPIN 5
#define RIGHTSPEEDMOTORPIN 9
#define LEFTDIRMOTORPIN 11
#define RIGHTDIRMOTORPIN 10
#define SHARP0PIN A0
#define SHARP1PIN A1
#define SHARP2PIN 4
//#define SHARP3PIN 2
//#define SHARP4PIN 3
#define LEFTLINESENSPIN A4
#define RIGHTLINESENSPIN A3
#define BACKLINESENSPIN A5
#define LEDAPIN 13
#define LED1PIN 12
#define LED2PIN 6
#define LED3PIN 17
#define DIP3PIN 14
#define DIP0PIN 7
#define DIP1PIN 15
#define DIP2PIN 16

SumoBoySwitch::SumoBoySwitch()
{
	for (int i = 0; i < 4; i++)
	{
		this->value[i] = false;
	}
}

void SumoBoySwitch::init()
{
	pinMode(DIP0PIN, INPUT);
	pinMode(DIP1PIN, INPUT);
	pinMode(DIP2PIN, INPUT);
	pinMode(DIP3PIN, INPUT);
	digitalWrite(DIP0PIN, HIGH);
	digitalWrite(DIP1PIN, HIGH);
	digitalWrite(DIP2PIN, HIGH);
	digitalWrite(DIP3PIN, HIGH);
}

bool SumoBoySwitch::read(int type)
{
	switch (type)
	{
	case 0: value[0] = !digitalRead(DIP0PIN); break;
	case 1: value[1] = !digitalRead(DIP1PIN); break;
	case 2: value[2] = !digitalRead(DIP2PIN); break;
	case 3: value[3] = !digitalRead(DIP3PIN); break;
	default:break;
	}
	return value[type];
}
int SumoBoySwitch::readVal()
{
	int ret=0;

	value[0] = digitalRead(DIP0PIN);
	value[1] = digitalRead(DIP1PIN);
	value[2] = digitalRead(DIP2PIN);
	value[3] = digitalRead(DIP3PIN);
	if (value[0])ret += 1;
	if (value[1])ret += 2;
	if (value[2])ret += 4;
	if (value[3])ret += 8;

	return ret;
}

SumoBoyLed::SumoBoyLed()
{
	for (int i = 0; i < 4; i++)
	{
		this->states[i] = false;
	}
}

void SumoBoyLed::init()
{
	pinMode(LED1PIN, OUTPUT);
	pinMode(LED2PIN, OUTPUT);
	pinMode(LED3PIN, OUTPUT);
	pinMode(LEDAPIN, OUTPUT);
}

void SumoBoyLed::set(int type, bool state)
{
	switch (type)
	{
	case 0: digitalWrite(LEDAPIN,state); break;
	case 1: digitalWrite(LED1PIN, state); break;
	case 2: digitalWrite(LED2PIN, state); break;
	case 3: digitalWrite(LED3PIN, state); break;
	default:break;
	}
	this->states[type] = state;
}

SumoBoyLine::SumoBoyLine()
{
	for (int i = 0; i < 3; i++)
	{
		this->value[i] = 1023;
	}
}
void SumoBoyLine::init()
{
	pinMode(LEFTLINESENSPIN, INPUT);
	pinMode(RIGHTLINESENSPIN, INPUT);
	pinMode(BACKLINESENSPIN, INPUT);
}

int SumoBoyLine::readAnalog(int type)
{
	switch (type)
	{
	case 0: value[type] = analogRead(LEFTLINESENSPIN); break;
	case 1: value[type] = analogRead(RIGHTLINESENSPIN); break;
	case 2: value[type] = analogRead(BACKLINESENSPIN); break;
	default:break;
	}
	return value[type];
}

bool SumoBoyLine::readBool(int type,int threshold)
{
	switch (type)
	{
	case 0: value[type] = analogRead(LEFTLINESENSPIN); break;
	case 1: value[type] = analogRead(RIGHTLINESENSPIN); break;
	case 2: value[type] = analogRead(BACKLINESENSPIN); break;
	default:break;
	}
	return (value[type]<threshold);
}

int SumoBoyLine::readAll(int threshold)
{
	int ret = 0;
	 
	if ( analogRead(LEFTLINESENSPIN) < threshold ) ret += 1;
	if ( analogRead(RIGHTLINESENSPIN) < threshold ) ret += 2;
	if ( analogRead(BACKLINESENSPIN) < threshold ) ret += 4;

	return ret;
}

SumoBoyMotor::SumoBoyMotor()
{
	leftspeed = 0;
	rightspeed = 0;
}

void SumoBoyMotor::init()
{
	pinMode(LEFTDIRMOTORPIN, OUTPUT);
	pinMode(RIGHTDIRMOTORPIN, OUTPUT);
	pinMode(LEFTSPEEDMOTORPIN, OUTPUT);
	pinMode(RIGHTSPEEDMOTORPIN, OUTPUT);
	this->run(0, 0, 0);
	this->run(1, 0, 0);
}

void SumoBoyMotor::run(int type, int direction, int speed)
{
	if (type == 0)
	{
		digitalWrite(LEFTDIRMOTORPIN, direction);
		analogWrite(LEFTSPEEDMOTORPIN, speed);
		this->leftspeed = speed;
	}
	else if( type == 1)
	{
		digitalWrite(RIGHTDIRMOTORPIN, direction);
		analogWrite(RIGHTSPEEDMOTORPIN, speed);
		this->rightspeed = speed;
	}
	else if (type == 2)
	{
		digitalWrite(LEFTDIRMOTORPIN, direction);
		analogWrite(LEFTSPEEDMOTORPIN, speed);
		this->leftspeed = speed;
		digitalWrite(RIGHTDIRMOTORPIN, direction);
		analogWrite(RIGHTSPEEDMOTORPIN, speed);
		this->rightspeed = speed;
	}
}

SumoBoySharp::SumoBoySharp()
{
	for (int i = 0; i < 5; i++)
	{
		this->value[i] = false;
	}
}

void SumoBoySharp::init()
{
	pinMode(SHARP0PIN, INPUT);
	pinMode(SHARP1PIN, INPUT);
	pinMode(SHARP2PIN, INPUT);
	//pinMode(SHARP3PIN, INPUT);
	//pinMode(SHARP4PIN, INPUT);
}

bool SumoBoySharp::read(int type)
{
	switch (type)
	{
		case 0: value[0] = digitalRead(SHARP0PIN); break;
		case 1: value[1] = digitalRead(SHARP1PIN); break;
		case 2: value[2] = digitalRead(SHARP2PIN); break;
		//case 3: value[3] = digitalRead(SHARP3PIN); break;
		//case 4: value[4] = digitalRead(SHARP4PIN); break;
		default:break;
	}
	return !value[type];
}

int SumoBoySharp::readAllVal()
{
	int ret = 0;

	if (!digitalRead(SHARP0PIN))ret += 8;
	if( !digitalRead(SHARP1PIN))ret += 16;
	if (!digitalRead(SHARP2PIN))ret += 32;
	//if (!digitalRead(SHARP3PIN))ret += 64;
	//if (!digitalRead(SHARP4PIN))ret += 128;

	return ret;
}


SumoBoy::SumoBoy()
{

}

void SumoBoy::init()
{
	motor.init();
	sharp.init();
	lineSensor.init();
	led.init();
	switches.init();
}
GOFi2cOLED display;