package azaka7.algaecraft.common.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.block.BlockFilter;

public class ItemBlockMetadata extends ItemBlock {
	
	private String name;
	private String[] nameListPre = new String[4];
	private String[] nameListPost = new String[4];
	
	public ItemBlockMetadata(int par1, String[] par2, String[] par3, String par4) {
		super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(AlgaeCraftMain.modTab);
        name = par4;
        for(int i = 0; i < 4; i++){
        	try{
        		nameListPre[i] = par2[i];
        	}
        	catch(Exception e){
        		nameListPre[i] = "";
        	}
        	try{
        		nameListPost[i] = par3[i];
        	}
        	catch(Exception e){
        		nameListPost[i] = "";
        	}
        }
	}
	
	public int getMetadata(int par1)
    {
        return par1;
    }
	
	private String getNameFromMetadata(int i){
    	return nameListPre[i&3]+name+nameListPost[i&3];
    }
    
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
    	return this.getNameFromMetadata(par1ItemStack.getItemDamage());
    }

}