#ifndef GPIO_H
#define GPIO_H

#include "..\include.h"

/*---- Type ----*/

typedef enum GPIOMode_Type {
	GPIO_Mode_IN   = 0x00, /*!< GPIO Input Mode */
  GPIO_Mode_OUT  = 0x01, /*!< GPIO Output Mode */
  GPIO_Mode_AF   = 0x02, /*!< GPIO Alternate function Mode */
  GPIO_Mode_AN   = 0x03  /*!< GPIO Analog Mode */
} GPIOMode_Type;

typedef enum GPIOPull_Type {
	GPIO_Pull_NOUPDOWN = 0x00, 
	GPIO_Pull_UP = 0x01,
	GPIO_Pull_DOWN = 0x02,
	GPIO_Pull_RESERVED = 0x03
	
}GPIOPull_Type;

typedef enum GPIOSpeed_Type {
	GPIO_Speed_LOW = 0x00,
	GPIO_Speed_MEDIUM = 0x01,
	GPIO_Speed_HIHG = 0x02,
	GPIO_Speed_VERYHIGH = 0x03
}GPIOSpeed_Type;

typedef enum STATE_Value {

	Value_OFF = 0, 
	Value_ON = 1,
	Value_RESET = 0,
	Value_SET = 1
	
} STATE_Value;

typedef enum GPIOX_EN {
	
	GPIO_EN_A = (uint32_t) 0,
	GPIO_EN_B = (uint32_t) 1,
	GPIO_EN_C = (uint32_t) 2,
	GPIO_EN_D = (uint32_t) 3,
	GPIO_EN_E = (uint32_t) 4,
	GPIO_EN_F = (uint32_t) 5,
	GPIO_EN_G = (uint32_t) 6,
	GPIO_EN_H = (uint32_t) 7,
	GPIO_EN_I = (uint32_t) 8	
} GPIOX_EN;

typedef enum USARTX_EN {
	
	USART_EN_2 = (uint32_t) 17,
	USART_EN_3 = (uint32_t) 18,
	USART_EN_4 = (uint32_t) 19,
	USART_EN_5 = (uint32_t) 20
} USARTX_EN;


typedef enum ADCX_EN {
	
	ADC_EN_1 = (uint32_t) 8,
	ADC_EN_2 = (uint32_t) 9,
	ADC_EN_3 = (uint32_t) 10
} ADCX_EN;


typedef enum GPIOXPort_PIN {

	PIN_0 = (uint32_t) 0,
	PIN_1 = (uint32_t) 1,
	PIN_2 = (uint32_t) 2,
	PIN_3 = (uint32_t) 3,
	PIN_4 = (uint32_t) 4,
	PIN_5 = (uint32_t) 5,
	PIN_6 = (uint32_t) 6,
	PIN_7 = (uint32_t) 7,
	PIN_8 = (uint32_t) 8,
	PIN_9 = (uint32_t) 9,
	PIN_10 = (uint32_t) 10,
	PIN_11 = (uint32_t) 11,
	PIN_12 = (uint32_t) 12,
	PIN_13 = (uint32_t) 13,
	PIN_14 = (uint32_t) 14,
	PIN_15 = (uint32_t) 15
} GPIOXPort_PIN;




/*---- Definition ----*/

	/*Serial -> Usart*/
			/** 
				Weight	STM32F4xx	 Description      I/O

					 TX			PD8			N/A           AF
					 RX			PD9			N/A           AF
			**/


#define EXTI_USART3 (uint32_t) 39

	/*Weight Sensor -> HX711*/
			/** 
				Weight	STM32F4xx	 Description      I/O

					 SCK			PF9			N/A           Output
					 DOUT			PF8			N/A           Input
			**/

#define PIN_DOUT PIN_8
#define PIN_SCK PIN_9

	/*Keypad*/
			/** 
				Keypad	STM32F4xx	 Description      I/O

					 R1			PE13			Row 1           Output
					 R2			PE12			Row 2           Output
					 R3			PE11			Row 3           Output
					 R4			PE10			Row 4           Output
					 
					 C1			PE9				Column 1        Input
					 C2			PE8				Column 2        Input
					 C3			PE7				Column 3        Input
			**/
#define PIN_ROW1 PIN_13
#define PIN_ROW2 PIN_12
#define PIN_ROW3 PIN_11
#define PIN_ROW4 PIN_10
#define PIN_COL1 PIN_9
#define PIN_COL2 PIN_8
#define PIN_COL3 PIN_7

#define NULL_CHARACTER '\0'
#define KEYPAD_NOT_PRESSED NULL_CHARACTER 

	/*Temperature -> LM35*/
			/** 
				Temperature	STM32F4xx	 Description      I/O

					   OUT			PB0					N/A          	 X

			**/


/*---- Header ----*/

void ENABLE_ClockGpioxEN(GPIOX_EN gpioxEN);
void ENABLE_ClockUsartxEN(USARTX_EN usartxEN);
void ENABLE_ClockAdcEN(ADCX_EN adcxEN);
	/*GPIO Configuration*/
void GPIO_SetPinMode(GPIO_TypeDef* gpiox, uint32_t pinN, GPIOMode_Type mode);
void GPIO_SetPinAF(GPIO_TypeDef * gpio, uint32_t pin, uint32_t AF);
void GPIO_SetPinPull(GPIO_TypeDef * gpiox, uint32_t pinN, GPIOPull_Type pull);
void GPIO_SetPinSpeed(GPIO_TypeDef * gpiox, uint32_t pinN, GPIOSpeed_Type speed);
void GPIO_SetPinValue(GPIO_TypeDef* gpiox, uint32_t pinN, STATE_Value value);
uint32_t GPIO_getPinValue(GPIO_TypeDef * gpiox, uint32_t pinN);
void GPIO_WritePin(GPIO_TypeDef* gpiox, uint32_t pinN, STATE_Value state);
uint32_t GPIO_ReadPin(GPIO_TypeDef * gpio, uint32_t pin);
void GPIO_toglePin(GPIO_TypeDef* gpiox, uint32_t pinN);
	/*Interrupts*/
void NVIC_EnableInterrupt(uint32_t position);
void GPIO_EnableInterruptWKUP(void);
	/*Others*/
void GPIO_SerialInit(void);
void GPIO_TecladoInit(void);
void GPIO_BasculaInit(void);
void GPIO_TemperaturaInit(void);
void GPIO_VentiladorLedInit(void);



#endif

