package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CaseBlock extends TranslatorBlock
{

	public CaseBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

		@Override
	public String toCode() throws SocketNullException,SubroutineNotDeclaredException 
        {
            String ret = "";
            
            TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
            
            ret=ret+"  case "+translatorBlock.toCode()+":\n";
            try { translatorBlock=this.getRequiredTranslatorBlockAtSocket(1); } catch(Exception e) {}
            while (translatorBlock!=null) 
            {
                ret = ret + translatorBlock.toCode();
                translatorBlock = translatorBlock.nextTranslatorBlock();
            }
                
            ret=ret+"break;";
            
            return ret;
        }
}
