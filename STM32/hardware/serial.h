#ifndef SERIAL_H
#define SERIAL_H

#include "..\include.h"

/*---- Type ----*/
typedef enum USARTCR1_Type {
	USART_CR1_USART = 13,
	USART_CR1_TR = 3, // Transmitter
	USART_CR1_RC = 2, // Receiver
	USART_CR1_WR = 12, // Word Lenght
	USART_CR1_RXINTER = 5, // RX Interrupt
	USART_CR1_TXINTER = 7 // TX Interrupt
	
} USARTCR1_Type;



typedef enum STOPMode_CR2 {
	STOPMode_ONE = 0x00, 
	STOPMode_HALF = 0x01,
	STOPMode_TWO = 0x02,
	STOPMode_ONEHALF = 0x03
}STOPMode_CR2;


/*---- Header ----*/

void SERIAL_ConfigureCR1(USART_TypeDef* usartx, USARTCR1_Type position, STATE_Value state);
void SERIAL_SetStopMode(USART_TypeDef* usartx, STOPMode_CR2 mode);
void SERIAL_SetBaudRate(USART_TypeDef* usartx, uint32_t baud);
void SERIAL_usardiv(float a, uint32_t* c, uint32_t* d);
void SERIAL_init(USART_TypeDef* usartx, uint32_t baud);
void SERIAL_SendWord(USART_TypeDef*usartx, uint8_t* str);
void SERIAL_SendCharacter(USART_TypeDef* usartx, uint8_t c);
void SERIAL_WriteInterrupt(USART_TypeDef* usartx);
void SERIAL_Write(USART_TypeDef* usartx, uint8_t* pMsg);
void SERIAL_SetTXInterrupt(USART_TypeDef* usartx, STATE_Value state);
void SERIAL_SetRXInterrupt(USART_TypeDef* usartx, STATE_Value state);
void SERIAL_ReadInterrupt(USART_TypeDef* usartx);
void SERIAL_SetReceiveCharacter(uint8_t c);
uint8_t SERIAL_GetReceiveCharacter(void);


#endif
