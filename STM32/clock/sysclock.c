#include "..\include.h"

volatile uint32_t ticks=0;
volatile uint32_t ticksOld=0;

uint32_t getSysClockFrequency(void)
{
  return 16000000U;
}

void initSysTick(uint32_t ms)
{
  if(ms)
  {
    SysTick->LOAD  = (uint32_t)(getSysClockFrequency()/1000*ms - 1UL); 
	  SysTick->VAL   = 0UL;
    SysTick->CTRL  |= 0x05; //enable, AHB clock
    SysTick->CTRL  |= 0x00000002; // int enable
  }
}

void waitSysTick(void)
{
  while(ticksOld==ticks);
  ticksOld++;
}

uint32_t getTime(void)
{
  return ticks;
}

void SysTick_Handler(void)
{
  ticks++;
}
