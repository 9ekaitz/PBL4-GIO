#ifndef TEMPERATURA_H
#define TEMPERATURA_H

#include <stm32f407xx.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void TEMPERATURA_Init(void);
void GPIO_TemperaturaInit(void);
void ADC_ConfigSingle(ADC_TypeDef* adcx);
void ADC_SetConversion(ADC_TypeDef* adcx);
uint32_t TEMPERATURA_GetValue(void);

uint32_t TEMPERATURA_GetTemperature(void);

#endif
