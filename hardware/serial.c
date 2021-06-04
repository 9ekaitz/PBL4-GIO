#include "serial.h"
#include "gpio.h"

uint8_t* buffer;
volatile uint8_t readBuffer;

void SERIAL_WriteCR1(USART_TypeDef* usartx, USARTCR1_Type position, STATE_Value state)
{
	if(!state) 	usartx->CR1 &=~(1 << position);
	else usartx->CR1 |= ( 1 << position);
}

void SERIAL_SetStopMode(USART_TypeDef* usartx, STOPMode_CR2 mode)
{
	usartx->CR2 &= ~(3 << 12);
	usartx->CR2 |= (mode << 12);
}

void NVIC_enableInterrupt(uint32_t position)
{
	NVIC->ISER[position < 32 ? 0 : 1] |= (1 << (position - 32));
}

void SERIAL_SetBaudRate(USART_TypeDef* usartx, uint32_t mantissa, uint32_t fraction)
{
	usartx->BRR &=~(0xFFF << 4);
	usartx->BRR |= (mantissa << 4);
	
	usartx->BRR &=~(0xF << 0);
	usartx->BRR |= (fraction << 0);	
}

uint32_t SERIAL_GetUsartTXState(USART_TypeDef* usartx)
{ 
	return (usartx->SR  & USART_SR_TXE);
}

uint32_t SERIAL_GetUsartRXState(USART_TypeDef* usartx)
{ 
	return (usartx->SR  & USART_SR_RXNE);
}

void SERIAL_WriteToUart(USART_TypeDef* usartx, uint8_t* buffer)
{
	usartx->DR |= *buffer;
}

uint8_t SERIAL_GetReadValue(USART_TypeDef* usartx)
{
	return usartx->DR;
}

/*---- Others ----*/


void SERIAL_init(void)
{
	ENABLE_ClockUsartxEN(USART_EN_3);
	
	SERIAL_WriteCR1(USART3,USART_CR1_USART, Value_SET);
	SERIAL_WriteCR1(USART3,USART_CR1_TR, Value_SET);
	SERIAL_WriteCR1(USART3,USART_CR1_RC, Value_SET);
	SERIAL_WriteCR1(USART3,USART_CR1_WR, Value_SET);
	SERIAL_WriteCR1(USART3,USART_CR1_RXINTER, Value_SET);	
	
	
	SERIAL_SetBaudRate(USART3,0x8, 0xB);
	SERIAL_SetStopMode(USART3,STOPMode_ONE);
	
	NVIC_enableInterrupt(EXTI_USART3);
}


void USART3_IRQHandler(void)
{
	
	SERIAL_WriteInterrupt(USART3);
	SERIAL_ReadInterrupt(USART3);
}


void SERIAL_Write(USART_TypeDef* usartx, uint8_t* pMsg)
{
		if (*pMsg != 0){
		buffer = pMsg;
		SERIAL_WriteCR1(usartx,USART_CR1_TXINTER, Value_SET);
	}
}

void SERIAL_WriteInterrupt(USART_TypeDef* usartx)
{
	if(SERIAL_GetUsartTXState(USART3))
	{
		SERIAL_WriteToUart(usartx,buffer);
		buffer++;
		if((*buffer) == 0) SERIAL_WriteCR1(usartx,USART_CR1_TXINTER, Value_RESET);
	}
}

void SERIAL_ReadInterrupt(USART_TypeDef* usartx)
{
	if(SERIAL_GetUsartRXState(usartx))
	{
		readBuffer = SERIAL_GetReadValue(usartx);
		SERIAL_WriteCR1(USART3,USART_CR1_RXINTER, Value_RESET);
	}
}

