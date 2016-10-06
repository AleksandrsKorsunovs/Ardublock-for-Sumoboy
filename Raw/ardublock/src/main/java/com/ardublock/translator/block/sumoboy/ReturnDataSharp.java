package com.ardublock.translator.block.sumoboy;


import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
 
public class ReturnDataSharp extends TranslatorBlock
{
    public ReturnDataSharp(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
            String ret="0";

            translator.addHeaderFile("SumoBoy.h");		
            translator.addDefinitionCommand("SumoBoy sumoboy;");
            translator.addSetupCommand("sumoboy.init();");
            
            if(label.equals("left"))
            {
                ret = "0";
            }
            else if(label.equals("front-left"))
            {
                ret = "1";
            }
            else if(label.equals("front"))
            {
                ret = "2";
            }
            else if(label.equals("front-right"))
            {
                ret = "3";
            }
            else if(label.equals("right"))
            {
                ret = "4";
            }
            
            return codePrefix + ret + codeSuffix;
        }
}
