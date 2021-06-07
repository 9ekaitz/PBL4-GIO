#include "serial.h"
#include "gpio.h"

uint8_t receive;

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
	SERIAL_ReadInterrupt(USART3);
}

void SERIAL_ReadInterrupt(USART_TypeDef* usartx)
{
	uint8_t tmp = 0;
	while (USART2->SR & USART_SR_RXNE);
	tmp = usartx->DR;
	SERIAL_SetReceiveCharacter(usartx,tmp);
}



void SERIAL_SendCharacter(USART_TypeDef* usartx, uint8_t c)
{
	usartx->DR = c;
	while(!(usartx->SR & USART_SR_TC));
}

void SERIAL_SendWord(USART_TypeDef* usartx, uint8_t* str)
{
	while(*str) SERIAL_SendCharacter(usartx, *str++);
}


void SERIAL_SetReceiveCharacter(USART_TypeDef* usartx, uint8_t c)
{
	if(c != 0) receive = c;
}


void SERIAL_ResetReceive(void)
{
	receive = 1;
}

uint8_t SERIAL_GetReceiveCharacter(USART_TypeDef* usartx)
{
	return receive;
}

