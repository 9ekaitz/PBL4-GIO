#ifndef INCLUDE_H
#define INCLUDE_H

#include <stm32f407xx.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#include ".\peripheral\gpio.h"
#include ".\hardware\serial.h"
#include ".\hardware\teclado.h"
#include ".\hardware\bascula.h"
#include ".\hardware\temperatura.h"
#include ".\clock\sysclock.h"


#define BAUD_RATE (uint32_t) 115000


#endif
