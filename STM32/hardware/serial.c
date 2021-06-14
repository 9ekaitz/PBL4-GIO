#include "..\include.h"

bool readState = false;

uint8_t* buffer;
uint8_t receive = 0;

void SERIAL_ConfigureCR1(USART_TypeDef* usartx, USARTCR1_Type position, STATE_Value state)
{
	if(!state) 	usartx->CR1 &=~(1 << position);
	else usartx->CR1 |= ( 1 << position);
}

void SERIAL_SetStopMode(USART_TypeDef* usartx, STOPMode_CR2 mode)
{
	usartx->CR2 &= ~(3 << 12);
	usartx->CR2 |= (mode << 12);
}


void SERIAL_SetBaudRate(USART_TypeDef* usartx, uint32_t baud)
{
	float usardiv;

	uint32_t mantissa, fraction;
	
	usardiv = (float) CLK_FREQ/(16*baud);	
	SERIAL_usardiv(usardiv,&mantissa, &fraction);

	usartx->BRR &=~ (0xFFF << 4);
	usartx->BRR |= ( mantissa << 4);
	
	usartx->BRR &=~(0xF << 0);
	usartx->BRR |= (fraction << 0);
}

void SERIAL_usardiv(float div, uint32_t* mantissa, uint32_t* fraction)
{
    *mantissa = (uint32_t) div;
    *fraction = round((div - *mantissa) * 16);
}


/*---- Others ----*/


void SERIAL_init(USART_TypeDef* usartx, uint32_t baud)
{
	ENABLE_ClockUsartxEN(USART_EN_3);
	
	SERIAL_ConfigureCR1(usartx,USART_CR1_USART, Value_SET);
	SERIAL_ConfigureCR1(usartx,USART_CR1_TR, Value_SET);
	SERIAL_ConfigureCR1(usartx,USART_CR1_RC, Value_SET);
	SERIAL_ConfigureCR1(usartx,USART_CR1_WR, Value_SET);
	SERIAL_SetRXInterrupt(usartx,Value_SET);
	
	
	SERIAL_SetBaudRate(usartx,baud);
	SERIAL_SetStopMode(usartx,STOPMode_ONE);
	
	NVIC_EnableInterrupt(EXTI_USART3);
}


void USART3_IRQHandler(void)
{
	
	if(USART3->SR & USART_SR_TXE) 
	{
		SERIAL_WriteInterrupt(USART3);
	}	
	if(USART3->SR & USART_SR_RXNE)
	{
		SERIAL_ReadInterrupt(USART3);
	}
	
}

/*TX*/
void SERIAL_SendWord(USART_TypeDef*usartx, uint8_t* str)
{	
	if(*str) {
		buffer = str;
		usartx->CR1 |= USART_SR_TXE;
	}
}

void SERIAL_SendCharacter(USART_TypeDef* usartx, uint8_t c)
{
	usartx->DR = c;
	while(!(usartx->SR & USART_SR_TC));
}


void SERIAL_WriteInterrupt(USART_TypeDef* usartx)
{
	SERIAL_SendCharacter(usartx,*buffer);
	buffer++;
	if((*buffer)== 0) SERIAL_SetTXInterrupt(usartx, Value_RESET);
}

void SERIAL_Write(USART_TypeDef* usartx, uint8_t* pMsg)
{
		if (*pMsg != 0){
		buffer = pMsg;
		SERIAL_SetTXInterrupt(usartx, Value_SET);
	}
}

void SERIAL_SetTXInterrupt(USART_TypeDef* usartx, STATE_Value state)
{
	if(!state) usartx->CR1 &=~ USART_SR_TXE;
	else usartx->CR1 |= USART_SR_TXE;
}

/*RX*/

void SERIAL_SetRXInterrupt(USART_TypeDef* usartx, STATE_Value state)
{
	if(!state) usartx->CR1 &=~ USART_SR_RXNE;
	else usartx->CR1 |= USART_SR_RXNE;
}

void SERIAL_ReadInterrupt(USART_TypeDef* usartx)
{
	uint8_t tmp = 0;
	tmp = usartx->DR;
	SERIAL_SetReceiveCharacter(tmp);
}

void SERIAL_SetReceiveCharacter(uint8_t c)
{
	if(c != 0) receive = c;
}

uint8_t SERIAL_GetReceiveCharacter(void)
{
	return receive;
}


