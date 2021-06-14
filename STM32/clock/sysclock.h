#ifndef SYSC_H
#define SYSC_H

#include "..\include.h"


#define MINUTE (uint32_t) 60000
#define MS (uint32_t) 1
#define CLK_FREQ (uint32_t) 16000000

void initSysTick(uint32_t ms);
uint32_t getSysClockFrequency(void);
void waitSysTick(void);
uint32_t getTime(void);
#define HwClockCount (SysTick->VAL)

#endif
