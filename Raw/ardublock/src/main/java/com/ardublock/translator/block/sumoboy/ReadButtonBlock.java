package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ReadButtonBlock extends TranslatorBlock
{
    
    public ReadButtonBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
                translator.addSetupCommand("pinMode(12,INPUT);\n");
                translator.addSetupCommand("digitalWrite(12,HIGH);\n");
		String ret ="!digitalRead(12)";
		return codePrefix + ret + codeSuffix;
	}
    
}
