package com.ardublock.translator.block.sumoboy;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SwitchBlock extends TranslatorBlock
{

	public SwitchBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
                String ret = "switch ((int) ";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + " )\n{\n";
		int i = 1;
                while (true)
                {
                    translatorBlock =null;
                    try { translatorBlock=this.getRequiredTranslatorBlockAtSocket(i); } catch(Exception e) {}
                    if (translatorBlock==null) {
                        break;
                    }
                    ret=ret+translatorBlock.toCode();
                    i++;
                }
                
                i++;
                translatorBlock=null;
                try { translatorBlock=this.getRequiredTranslatorBlockAtSocket(i); } catch(Exception e) {}

                if (translatorBlock!=null) 
                {
                    ret=ret+"  default:\n";
                }
                while (translatorBlock!=null) 
                {
                    ret = ret + translatorBlock.toCode();
                    translatorBlock = translatorBlock.nextTranslatorBlock();
                }
                ret=ret+"}\n";
		return ret;
	}
}
