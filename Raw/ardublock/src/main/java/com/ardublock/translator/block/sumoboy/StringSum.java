
package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class StringSum extends TranslatorBlock
{
    
    public StringSum(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String str1 = translatorBlock.toCode();
                translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String str2 = translatorBlock.toCode();
		
		String ret = str1+"+"+str2;
		return  codePrefix + ret + codeSuffix;             
	}
    
}
