#ifndef BASCULA_H
#define BASCULA_H

#include "..\include.h"

typedef struct HX711 {
	GPIO_TypeDef* PD_SCK_PinType;	//GPIOx
	uint16_t PD_SCK_PinNumber;		//GPIO_Pin
	GPIO_TypeDef* DOUT_PinType;		//GPIOx
	uint16_t DOUT_PinNumber;		//GPIO_Pin
	int offset;
	int mode;		// 0 Input channel A, gain=128
					// 1 Input channel B, gain=32
					// 2 Input channel A, gain=64
} HX711;

#define GET_VALUE (int) 340343

	/*Others*/
int BASCULA_Read(HX711* H);
int BASCULA_AvgRead(HX711* H, int times);
void BASCULA_Tare(HX711* H, int times);
int BASCULA_GetValue(HX711* H);
int BASCULA_GetConversion(int value);
uint32_t BASCULA_GetState(void);
void BASCULA_SetState(bool state);



#endif
