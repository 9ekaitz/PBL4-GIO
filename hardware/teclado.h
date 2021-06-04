#ifndef TECLADO_H
#define TECLADO_H

#include <stm32f407xx.h>

void Set_Keypad_Row(uint32_t Row);
char Check_Keypad_Column(uint32_t Row);
char TECLADO_Read(void);

#endif
