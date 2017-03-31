
package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SetCursorPx extends TranslatorBlock
{
    
    public SetCursorPx(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		
		translator.addHeaderFile("SumoBoy.h");		
		translator.addSetupCommand("display.init(0x3C);");
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String x = translatorBlock.toCode();
                translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String y = translatorBlock.toCode();
		
		String ret = "display.setCursor("+x+","+y+");\n";
		return  codePrefix + ret + codeSuffix;             
	}
    
}
