#ifndef SUMOBOY_H_
#define SUMOBOY_H_

#include "Arduino.h"
#include "GOFi2cOLED.h"
#include <Wire.h>

class SumoBoyMotor 
{
public:
	SumoBoyMotor();
	void init();
	void run(int type, int direction, int speed);

private:
	int leftspeed;
	int rightspeed;
};

class SumoBoySharp
{
public:
	SumoBoySharp();
	void init();
	bool read(int type);
	int readAllVal();
private:
	bool value[5];
};

class SumoBoySwitch
{
public:
	SumoBoySwitch();
	void init();
	bool read(int type);
	int readVal();
private:
	bool value[4];
};

class SumoBoyLine
{
public:
	SumoBoyLine();
	void init();
	int readAnalog(int type);
	bool readBool(int type, int threshold);
	int readAll(int threshold);
private :
	int value[3];
};

class SumoBoyLed
{
public:
	SumoBoyLed();
	void init();
	void set(int type, bool state);
private:
	bool states[4];
};

class SumoBoy 
{
public:
	SumoBoyMotor motor;
	SumoBoySharp sharp;
	SumoBoyLine lineSensor;
	SumoBoyLed led;
	SumoBoySwitch switches;

	SumoBoy();
	void init();
};
extern GOFi2cOLED display;
#endif
