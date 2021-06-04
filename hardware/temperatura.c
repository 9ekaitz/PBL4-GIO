#include "temperatura.h"
#include "gpio.h"


void TEMPERATURA_Init(void)
{
	ENABLE_ClockAdcEN(ADC_EN_1);
	ADC_ConfigSingle(ADC1);	
	
}




void ADC_ConfigSingle(ADC_TypeDef* adcx)
{
	adcx->CR1 &=~(3 << 24);
	
	adcx->CR2 &=~(1 << 11);
	
	adcx->SQR1 &=~(0x0F << 20);
	
	adcx->SQR3 &=~(0x1F);
	adcx->SQR3 |= (0x08);
		
	adcx->SMPR1 &= ~ (0x07);
	adcx->SMPR1 |= (0x01);

	adcx->CR2 |= ADC_CR2_ADON;
}

void ADC_SetConversion(ADC_TypeDef* adcx)
{
	adcx->CR2 |= (1 << 30);
}


uint32_t TEMPERATURA_GetTemperature(void)
{
	uint32_t temp;	
	temp = TEMPERATURA_GetValue();
	temp *= (5000/4096);
	temp /= 10;
	
	return temp;
}

/*---- Others ----*/

uint32_t TEMPERATURA_GetValue(void)
{
	ADC1->CR2 |= ADC_CR2_SWSTART;
	while((ADC1->SR & (1 << 1)) == 0);
	return ADC1->DR;
}



