#include "teclado.h"
#include "gpio.h"

const char Keypad_Button_Values[4][3] = {
																		{'1','2','3'},
																		{'4','5','6'},
																		{'7','8','9'},
																		{'*','0','#'}
															  };

void Set_Keypad_Row(uint32_t Row)
{
	GPIO_WritePin(GPIOE,PIN_ROW1, Value_SET);
	GPIO_WritePin(GPIOE,PIN_ROW2, Value_SET);
	GPIO_WritePin(GPIOE,PIN_ROW3, Value_SET);
	GPIO_WritePin(GPIOE,PIN_ROW4, Value_SET);
	
	switch(Row)
	{
		case 1: GPIO_WritePin(GPIOE,PIN_ROW1, Value_RESET); break;
		case 2: GPIO_WritePin(GPIOE,PIN_ROW2, Value_RESET); break;
		case 3: GPIO_WritePin(GPIOE,PIN_ROW3, Value_RESET); break;
		case 4: GPIO_WritePin(GPIOE,PIN_ROW4, Value_RESET); break;
	}	
}																

char Check_Keypad_Column(uint32_t Row)
{
	if(!GPIO_ReadPin(GPIOE,PIN_COL1)) {
		while(!GPIO_ReadPin(GPIOE,PIN_COL1));
		return Keypad_Button_Values[Row-1][0];
	}
	
		if(!GPIO_ReadPin(GPIOE,PIN_COL2)) {
		while(!GPIO_ReadPin(GPIOE,PIN_COL2));
		return Keypad_Button_Values[Row-1][1];
	}
	
		if(!GPIO_ReadPin(GPIOE,PIN_COL3)) {
		while(!GPIO_ReadPin(GPIOE,PIN_COL3));
		return Keypad_Button_Values[Row-1][2];
	}
	
	return KEYPAD_NOT_PRESSED;
}

char TECLADO_Read(void)
{
	char check;
	/*Set ROW_1 LOW and scan all the columns*/
	Set_Keypad_Row(1);
	check = Check_Keypad_Column(1);
  if(check) return check;
			
	/*Set ROW_2 LOW and scan all the columns*/
	Set_Keypad_Row(2);
	check = Check_Keypad_Column(2);
	if(check) return check;
	
	/*Set ROW_3 LOW and scan all the columns*/
	Set_Keypad_Row(3);
	check = Check_Keypad_Column(3);
	if(check) return check;
	
	/*Set ROW_4 LOW and scan all the columns*/
	Set_Keypad_Row(4);
	check = Check_Keypad_Column(4);
	if(check) return check;

	/*Key not pressed */
	return KEYPAD_NOT_PRESSED;
}



