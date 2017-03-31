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
                translator.addSetupCommand("pinMode(A2,INPUT);\n");
                translator.addSetupCommand("digitalWrite(A2,HIGH);\n");
		String ret ="!digitalRead(A2)";
		return codePrefix + ret + codeSuffix;
	}
    
}
