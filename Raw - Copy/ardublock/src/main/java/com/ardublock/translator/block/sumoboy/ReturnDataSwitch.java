package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ReturnDataSwitch extends TranslatorBlock
{
    public ReturnDataSwitch(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
            String ret="1";

            translator.addHeaderFile("SumoBoy.h");		
            translator.addDefinitionCommand("SumoBoy sumoboy;");
            translator.addSetupCommand("sumoboy.init();");
            
            if(label.equals("DIP0"))
            {
                ret = "0";
            }
            else if(label.equals("DIP1"))
            {
                ret = "1";
            }
            else if(label.equals("DIP2"))
            {
                ret = "2";
            }
            else if(label.equals("DIP3"))
            {
                ret = "3";
            }
            
            
            return codePrefix + ret + codeSuffix;
        }
    
}
