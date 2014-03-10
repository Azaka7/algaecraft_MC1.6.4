package azaka7.algaecraft.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StringTranslate;

public class TabAlgaeCraft extends CreativeTabs {

	public TabAlgaeCraft() {
		super(CreativeTabs.getNextID(),"algaecraft");
	}
	
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
    {
        return "AlgaeCraft Mod";
    }
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return Item.itemsList[Block.sponge.blockID];
    }

}
