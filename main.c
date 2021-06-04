#include "gpio.h"
#include "serial.h"
#include "teclado.h"
#include "bascula.h"
#include "temperatura.h"
#include "sysclock.h"


int main(void)
{

	/*-- BEGIN SERIAL --*/
	SERIAL_init();
	GPIO_SerialInit();
	/*-- END SERIAL --*/
	
		/*-- BEGIN KEYPAD --*/
	GPIO_TecladoInit();
	char letter[2] = "\0";
	/*-- END KEYPAD --*/
	
		/*-- BEGIN WEIGHT --*/
	GPIO_BasculaInit();
	GPIO_EnableInterruptWKUP();
	HX711 sensor;
	sensor.PD_SCK_PinType = GPIOF;
	sensor.PD_SCK_PinNumber = PIN_SCK;	 
	sensor.DOUT_PinType = GPIOF;
	sensor.DOUT_PinNumber = PIN_DOUT;	 
	sensor.mode = 0;
	int weight = -1;
	char weightP[8];
	BASCULA_Tare(&sensor, 50);
	
	/*-- END WEIGHT --*/	
	
	/*-- BEGIN TEMPERATURE --*/
	GPIO_TemperaturaInit();
	TEMPERATURA_Init();
	initSysTick(1);
	
	uint32_t time = 0;
	uint32_t temp;
	char base[8];
  char grade[2] = "t-";
  char temperature[8] = "";
	
	/*-- END TEMPERATURE --*/
	
	
	/*-- PROGRAM --*/
	
	while(1)
	{
		letter[0] = TECLADO_Read();
		
		if(getTime() - time > 6000)
		{
			temp = TEMPERATURA_GetTemperature();
			strcpy(temperature, grade);
			sprintf(base, "%zu", temp);
			strcat(temperature,base);
			SERIAL_Write(USART3, (uint8_t*) temperature);

			time = getTime();
		}
			
		if(letter[0] != KEYPAD_NOT_PRESSED)
		{
			SERIAL_Write(USART3,(uint8_t*) letter);
		}
		
		weight = BASCULA_GetConversion(BASCULA_GetValue(&sensor));
		
		if(BASCULA_GetState())
		{
			
			sprintf(weightP,"%d", weight);
			
			SERIAL_Write(USART3, (uint8_t*) weightP);
			BASCULA_SetState(false);
		}		
	}
	

}
