package azaka7.algaecraft.common.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFilter extends Block {
	
	public String[] nameList = new String[]{"Ocean","Sporous","Fresh","Ender"};
	Icon[] iconList = new Icon[4];
	Icon bottom;
	Icon side;

	public BlockFilter(int par1) {
		super(par1, Material.iron);
		this.setTickRandomly(true);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < this.nameList.length; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:filter"+nameList[i]);
        }
    	bottom = par1IconRegister.registerIcon("azaka7:filterBottom");
    	side = par1IconRegister.registerIcon("azaka7:filterSide");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if(par1 == 1){
    	try{
        return iconList[par2];
    	}
    	catch(Exception e){
    		return iconList[0];
    	}
    	}
    	if(par1 == 0){
    		return bottom;
    	}
    	return side;
    }
    
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
    
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    public static boolean isBlockFiltered(World world, int x, int y, int z, int type){
    	boolean b2 = false;
    	for(int a = -4; a <= 4; a++){
    		for(int b = -4; b <= 4; b++){
    			for(int c = -4; c<=4; c++){
    				if(world.getBlockId(x+a,y+b,z+c) == AlgaeCraftMain.blockFilter.blockID && world.getBlockMetadata(x+a,y+b,z+c) == type){
    					b2=true;
    				}
    			}
    		}
    	}
    	return b2;
    }
    
    /*public static boolean isFiltered(int x, int y, int z, int type){
		boolean b = false;
    	switch(type){
    	case 0:
			b = false;
    		for(int i = 0;i<filterOceanList.size();i++){
    			int x2 = ((int[])(filterOceanList.get(i)))[0];
    			int y2 = ((int[])(filterOceanList.get(i)))[1];
    			int z2 = ((int[])(filterOceanList.get(i)))[2];
    			if(distance3d(x,y,z,x2,y2,z2) <= 5.0F){b=true;}
    		}
    		return b;
    	case 1:
			b = false;
    		for(int i = 0;i<filterSporeList.size();i++){
    			int x2 = ((int[])(filterSporeList.get(i)))[0];
    			int y2 = ((int[])(filterSporeList.get(i)))[1];
    			int z2 = ((int[])(filterSporeList.get(i)))[2];
    			if(distance3d(x,y,z,x2,y2,z2) <= 5.0F){b=true;}
    		}
    		return b;
    	case 2:
			b = false;
    		for(int i = 0;i<filterFreshList.size();i++){
    			int x2 = ((int[])(filterFreshList.get(i)))[0];
    			int y2 = ((int[])(filterFreshList.get(i)))[1];
    			int z2 = ((int[])(filterFreshList.get(i)))[2];
    			if(distance3d(x,y,z,x2,y2,z2) <= 5.0F){b=true;}
    		}
    		return b;
    	case 3:
			b = false;
    		for(int i = 0;i<filterEnderList.size();i++){
    			int x2 = ((int[])(filterEnderList.get(i)))[0];
    			int y2 = ((int[])(filterEnderList.get(i)))[1];
    			int z2 = ((int[])(filterEnderList.get(i)))[2];
    			if(distance3d(x,y,z,x2,y2,z2) <= 5.0F){b=true;}
    		}
    		return b;
    	default: return false;
    	}
    }*/
    
    public static float distance3d(int x1, int y1, int z1, int x2, int y2, int z2){
    	//System.out.println(Math.sqrt(Math.pow(Math.abs(x1-x2), 2)+Math.pow(Math.abs(y1-y2), 2)+Math.pow(Math.abs(z1-z2), 2)));
    	return (float) Math.sqrt(Math.pow(Math.abs(x1-x2), 2)+Math.pow(Math.abs(y1-y2), 2)+Math.pow(Math.abs(z1-z2), 2));
    }
    
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	/*
    	int type = par1World.getBlockMetadata(par2, par3, par4);
    	boolean b = false;
    	switch(type){
    	case 0: //if(!this.filterOceanList.containsKey(new int[]{par2,par3,par4}))
    		//{this.filterOceanList.put(0, new int[]{par2,par3,par4});}
    		b = false;
    		try{
    		for(int n = 0; n<this.filterOceanList.size();n++){
    			if(are3intArraysSame((int[]) filterOceanList.get(n),new int[]{par2,par3,par4})){
    				b = true;
    			}
    		}
    		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("DisplayTick failed");}
    		
    		if(!b){this.filterOceanList.put(0, new int[]{par2,par3,par4});}return;
    	case 1: 
    		b = false;
    		try{
    		for(int n = 0; n<this.filterSporeList.size();n++){
    			if(are3intArraysSame((int[]) filterSporeList.get(n),new int[]{par2,par3,par4})){
				b = true;
    			}
    		}
    		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("DisplayTick failed");}
    		
    		if(!b){this.filterSporeList.put(0, new int[]{par2,par3,par4});}return;
    	case 2:
    		b = false;
    		try{
    		for(int n = 0; n<this.filterFreshList.size();n++){
    			if(are3intArraysSame((int[]) filterFreshList.get(n),new int[]{par2,par3,par4})){
				b = true;
    			}
    		}
    		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("DisplayTick failed");}
    		
    		if(!b){this.filterFreshList.put(0, new int[]{par2,par3,par4});}return;
    	case 3:
    		b = false;
    		try{
    		for(int n = 0; n<this.filterEnderList.size();n++){
    			if(are3intArraysSame((int[]) filterEnderList.get(n),new int[]{par2,par3,par4})){
				b = true;
    			}
    		}
    		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("DisplayTick failed");}
    		if(!b){this.filterEnderList.put(0, new int[]{par2,par3,par4});}return;
    	default: return;
    	}*/
    }
    /*
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    	int type = (par1World.getBlockId(par2, par3, par4)&3);
    	switch(type){
    	case 0:
    		try{
        		for(int n = 0; n<this.filterOceanList.size();n++){
        			if(are3intArraysSame((int[]) filterOceanList.get(n),new int[]{par2,par3,par4})){
        				filterOceanList.remove(n);
        			}
        		}
        		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("breakBlock failed");}
    	case 1:
    		try{
        		for(int n = 0; n<this.filterSporeList.size();n++){
        			if(are3intArraysSame((int[]) filterSporeList.get(n),new int[]{par2,par3,par4})){
        				filterSporeList.remove(n);
        			}
        		}
        		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("breakBlock failed");}
    	case 2:
    		try{
        		for(int n = 0; n<this.filterFreshList.size();n++){
        			if(are3intArraysSame((int[]) filterFreshList.get(n),new int[]{par2,par3,par4})){
        				filterFreshList.remove(n);
        			}
        		}
        		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("breakBlock failed");}
    	case 3:
    		try{
        		for(int n = 0; n<this.filterEnderList.size();n++){
        			if(are3intArraysSame((int[]) filterEnderList.get(n),new int[]{par2,par3,par4})){
        				filterEnderList.remove(n);
        			}
        		}
        		}catch(java.lang.ArrayIndexOutOfBoundsException e){System.out.println("breakBlock failed");}
    	default: return;
    	}
    }
    
    private static boolean are3intArraysSame(int[] i1, int[] i2){
    	return (i1[0]==i2[0] && i1[1]==i2[1] && i1[3]==i2[3]);
    }*/

}
