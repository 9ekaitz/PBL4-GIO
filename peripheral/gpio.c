#include "gpio.h"

/*CLOCK Configuration*/

void ENABLE_ClockGpioxEN(GPIOX_EN gpioxEN)
{
	RCC->AHB1ENR |= (1 << gpioxEN);
}

void ENABLE_ClockUsartxEN(USARTX_EN usartxEN)
{
	RCC->APB1ENR |= (1 << usartxEN);
}


void ENABLE_ClockAdcEN(ADCX_EN adcxEN)
{
	RCC->APB2ENR |= (1 << adcxEN);
}


/*GPIO Configuration*/

void GPIO_SetPinMode(GPIO_TypeDef* gpiox, uint32_t pinN, GPIOMode_Type mode)
{		
	gpiox->MODER &= ~(3 << (pinN *2));
	gpiox->MODER |= (mode << (pinN *2));
}


void GPIO_SetPinAF(GPIO_TypeDef * gpio, uint32_t pin, uint32_t AF)
{
	gpio->AFR[pin<8 ? 0 :1] &= ~(0xF << ((pin % 8)*4));
	gpio->AFR[pin<8 ? 0 :1] |= (AF << ((pin % 8)*4));
}

void GPIO_SetPinPull(GPIO_TypeDef * gpiox, uint32_t pinN, GPIOPull_Type pull)
{
	gpiox->PUPDR &=~(3 << (pinN * 2));
	gpiox->PUPDR |=( pull<< (pinN * 2));
}


void GPIO_SetPinSpeed(GPIO_TypeDef * gpiox, uint32_t pinN, GPIOSpeed_Type speed)
{
	gpiox->OSPEEDR &=~(3 << (pinN * 2));
	gpiox->OSPEEDR |= (speed << (pinN *2));
}


void GPIO_SetPinValue(GPIO_TypeDef* gpiox, uint32_t pinN, STATE_Value value)
{
	if(!value) gpiox->ODR &=~ (0x01 << pinN);
	else gpiox->ODR |= (0x01 << pinN);
}

uint32_t GPIO_getPinValue(GPIO_TypeDef * gpiox, uint32_t pinN)
{
	return (gpiox->IDR & (0x01<<pinN));
}


void GPIO_WritePin(GPIO_TypeDef* gpiox, uint32_t pinN, STATE_Value state)
{	
	if(!state) 
	{
		gpiox->BSRR = (1 << (pinN + 16));
	}	else {
		gpiox->BSRR = (1 << pinN);
	}
}

uint32_t GPIO_ReadPin(GPIO_TypeDef * gpio, uint32_t pin)
{
	return (gpio->IDR & (0x01<<pin));
}

/*---- Others ----*/

void GPIO_SerialInit()
{
	ENABLE_ClockGpioxEN(GPIO_EN_D);
	
	GPIO_SetPinMode(GPIOD,PIN_8, GPIO_Mode_AF);
	GPIO_SetPinMode(GPIOD,PIN_9, GPIO_Mode_AF);
	
	GPIO_SetPinAF(GPIOD, PIN_8, 7);
	GPIO_SetPinAF(GPIOD, PIN_9, 7);
}

void GPIO_TecladoInit(void)
{
	ENABLE_ClockGpioxEN(GPIO_EN_E);
		
	GPIO_SetPinMode(GPIOE, PIN_ROW1, GPIO_Mode_OUT);
	GPIO_SetPinPull(GPIOE,PIN_ROW1,GPIO_Pull_NOUPDOWN);
	GPIO_SetPinMode(GPIOE, PIN_ROW2, GPIO_Mode_OUT);
	GPIO_SetPinPull(GPIOE,PIN_ROW2,GPIO_Pull_NOUPDOWN);
	GPIO_SetPinMode(GPIOE, PIN_ROW3, GPIO_Mode_OUT);
	GPIO_SetPinPull(GPIOE,PIN_ROW3,GPIO_Pull_NOUPDOWN);
	GPIO_SetPinMode(GPIOE, PIN_ROW4, GPIO_Mode_OUT);
	GPIO_SetPinPull(GPIOE,PIN_ROW4,GPIO_Pull_NOUPDOWN);

	
	GPIO_WritePin(GPIOE,PIN_ROW1, Value_RESET);
	GPIO_WritePin(GPIOE,PIN_ROW2, Value_RESET);
	GPIO_WritePin(GPIOE,PIN_ROW3, Value_RESET);
	GPIO_WritePin(GPIOE,PIN_ROW4, Value_RESET);
		
	
	GPIO_SetPinMode(GPIOE, PIN_COL1, GPIO_Mode_IN);
	GPIO_SetPinPull(GPIOE,PIN_COL1,GPIO_Pull_UP);
	GPIO_SetPinMode(GPIOE, PIN_COL2, GPIO_Mode_IN);
	GPIO_SetPinPull(GPIOE,PIN_COL2,GPIO_Pull_UP);	
	GPIO_SetPinMode(GPIOE, PIN_COL3, GPIO_Mode_IN);
	GPIO_SetPinPull(GPIOE,PIN_COL3,GPIO_Pull_UP);
}

void GPIO_BasculaInit(void)
{
	ENABLE_ClockGpioxEN(GPIO_EN_F);
	ENABLE_ClockGpioxEN(GPIO_EN_A);

	GPIO_SetPinMode(GPIOF, PIN_DOUT, GPIO_Mode_IN);
	GPIO_SetPinMode(GPIOF, PIN_SCK, GPIO_Mode_OUT);	
	
	GPIO_SetPinMode(GPIOA, PIN_0, GPIO_Mode_IN);
	
}

void GPIO_TemperaturaInit(void)
{
	ENABLE_ClockGpioxEN(GPIO_EN_B);
	
	GPIO_SetPinMode(GPIOB,PIN_0, GPIO_Mode_AN);	
}
