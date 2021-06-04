#ifndef SYSC_H
#define SYSC_H

#include <stm32f4xx.h>


#define MINUTE (uint32_t) 60000

void initSysTick(uint32_t ms);
uint32_t getSysClockFrequency(void);
void waitSysTick(void);
uint32_t getTime(void);
#define HwClockCount (SysTick->VAL)

#endif
