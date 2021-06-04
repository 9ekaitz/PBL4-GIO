#include "gpio.h"
#include "bascula.h"

bool scaleState = false;

int BASCULA_Read(HX711* H)
{
    int count = 0;
    bool isNegative = false;

    GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_RESET);

    while (GPIO_ReadPin(H->DOUT_PinType, H->DOUT_PinNumber));
    
    for (int i = 0; i < 24; i++)
    {
        GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_SET);
        count = (count << 1) ;

        GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_RESET);

        if (GPIO_ReadPin(H->DOUT_PinType, H->DOUT_PinNumber))
        {
            if (i == 0) isNegative = true;
            count++;
        }
    }

    if (isNegative) count = (count ^ 0xFF000000);

    GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_SET);
    GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_RESET);


    for (int j = 0; j < H->mode; j++)
    {
        GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_SET);
        GPIO_WritePin(H->PD_SCK_PinType, H->PD_SCK_PinNumber,Value_RESET);
    }
    
    return count;
}

int BASCULA_AvgRead(HX711* H, int times)
{

    int sum = 0;

    for (int i = 0; i < times; i++)
    {
        sum += BASCULA_Read(H);
    }
    
    return (sum/times);
}

void BASCULA_Tare(HX711* H, int times)
{
    H->offset = BASCULA_AvgRead(H, times);
}

int BASCULA_GetValue(HX711* H)
{
    return  (BASCULA_Read(H) - (H->offset));
}

int BASCULA_GetConversion(int value)
{
	return (value * 148)/ GET_VALUE;
}


void GPIO_EnableInterruptWKUP(void)
{
	RCC->APB2ENR |= (1 << 14);
	SYSCFG->EXTICR[0] &=~(0x0F);
	EXTI->RTSR |= (0x01);
	EXTI->IMR |= (0x01);
	NVIC->ISER[0] |=  (0x01 << 6);
}

void EXTI0_IRQHandler(void)
{
	if(EXTI->PR & (1 << 0))
	{
		scaleState = true;
		EXTI->PR = (1 << 0);
	}
}

uint32_t BASCULA_GetState(void)
{
	return scaleState;
}

void BASCULA_SetState(bool state)
{
	scaleState = state;
}

