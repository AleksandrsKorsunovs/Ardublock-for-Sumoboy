
package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LedSetBlock extends TranslatorBlock
{
    
    public LedSetBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		translator.addHeaderFile("SumoBoy.h");		
		translator.addDefinitionCommand("SumoBoy sumoboy;");
		translator.addSetupCommand("sumoboy.init();");
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String type = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String state = translatorBlock.toCode();
		
		String ret = "sumoboy.led.set(" + type + "," + state + ");\n";
		return  ret;			
	}
    
}
