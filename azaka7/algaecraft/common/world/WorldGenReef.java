package azaka7.algaecraft.common.world;

import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.entity.EntityFish;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReef extends WorldGenerator {
	
	ItemStack[] genList;
	int type;
	
	public WorldGenReef(ItemStack[] blocks, int reefMaterial){
		genList = blocks;
		type = reefMaterial;
	}
	
public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		
		int xRadius = var2.nextInt(3)+4;
		int zRadius = var2.nextInt(3)+4;
		if ((var1.getBlockId(var3, var4, var5) == Block.dirt.blockID || var1.getBlockId(var3, var4, var5) == Block.sand.blockID)&&var1.getBlockMaterial(var3, var4+1, var5)==Material.water){
			for(int x = -1*xRadius; x <= xRadius; x++){
				for(int z = -1*zRadius; z <= zRadius; z++){
					if(var2.nextInt(21) != 0 && !(Math.abs(x) == xRadius && Math.abs(z) == zRadius)){
						ItemStack block = stone();
						var1.setBlock(var3+x, var4-1, var5+z, block.itemID, block.getItemDamage(), 2);
					}
					else if(var1.getBlockId(var3+x, var4-1, var5+z)==0){
						var1.setBlock(var3+x, var4-1, var5+z, Block.waterStill.blockID, 0, 2);
					}
				}
			}
			for(int x = -1*(xRadius-1); x <= (xRadius-1); x++){
				for(int z = -1*(zRadius-1); z <= (zRadius-1); z++){
					if(var2.nextInt(5) != 0){
						ItemStack block = stone();
						var1.setBlock(var3+x, var4-2, var5+z, block.itemID, block.getItemDamage(), 2);
					}
					else if(var1.getBlockId(var3+x, var4-2, var5+z)!=Block.waterStill.blockID){
						var1.setBlock(var3+x, var4-2, var5+z, Block.waterStill.blockID, 0, 2);
					}
				}
			}
			for(int x = -1*xRadius; x <= xRadius; x++){
				for(int z = -1*zRadius; z <= zRadius; z++){
					if(var2.nextInt(3)==0){
						ItemStack block = stone();
						var1.setBlock(var3+x, var4, var5+z, block.itemID, block.getItemDamage(), 2);
					}
					else if(var2.nextInt(4)!=0){
						ItemStack item = randBlock();
						if(Block.blocksList[item.itemID].canPlaceBlockAt(var1, var3+x, var4, var5+z)){
							var1.setBlock(var3+x, var4, var5+z, item.itemID, item.getItemDamage(), 2);
						}
						else if(var1.getBlockId(var3+x, var4, var5+z)!=Block.waterStill.blockID){
							var1.setBlock(var3+x, var4, var5+z, Block.waterStill.blockID,0,2);
							if(var1.rand.nextBoolean()){
								//this.spawnFish(var3+x, var4, var5+z, var1);
								}
						}
					}
					else if(var1.getBlockId(var3+x, var4, var5+z)!=Block.waterStill.blockID){
						var1.setBlock(var3+x, var4, var5+z, Block.waterStill.blockID,0,2);
						if(var1.rand.nextBoolean()){
							//this.spawnFish(var3+x, var4, var5+z, var1);
							}
					}
				}
			}
			for(int x = -1*xRadius; x <= xRadius; x++){
				for(int z = -1*zRadius; z <= zRadius; z++){
					if(var2.nextInt(4)!=0){
						ItemStack item = randBlock();
						if(Block.blocksList[item.itemID].canPlaceBlockAt(var1, var3+x, var4+1, var5+z)){
							var1.setBlock(var3+x, var4+1, var5+z, item.itemID, item.getItemDamage(),2);
						}
						else if(var1.getBlockId(var3+x, var4+1, var5+z)!=Block.waterStill.blockID){
							var1.setBlock(var3+x, var4+1, var5+z, Block.waterStill.blockID,0,2);
							if(var1.rand.nextBoolean()){
							//this.spawnFish(var3+x, var4+1, var5+z, var1);
							}
						}
					}
					else if(var1.getBlockId(var3+x, var4+1, var5+z)!=Block.waterStill.blockID){
						var1.setBlock(var3+x, var4+1, var5+z, Block.waterStill.blockID,0,2);
						if(var1.rand.nextBoolean()){
						//this.spawnFish(var3+x, var4+1, var5+z, var1);
						}
					}
				}
			}
			for(int x = -1*(xRadius-1); x <= (xRadius-1); x++){
				for(int z = -1*(zRadius-1); z <= (zRadius-1); z++){
					if(var2.nextInt(4)==0){
						ItemStack item = randBlock();
						if(Block.blocksList[item.itemID].canPlaceBlockAt(var1, var3+x, var4+2, var5+z)){
							var1.setBlock(var3+x, var4+2, var5+z, item.itemID, item.getItemDamage(),2);
						}
						else if(var1.getBlockId(var3+x, var4+2, var5+z)!=Block.waterStill.blockID){
							var1.setBlock(var3+x, var4+2, var5+z, Block.waterStill.blockID,0,2);
							if(var1.rand.nextBoolean()){
								//this.spawnFish(var3+x, var4+2, var5+z, var1);
								}
						}
					}
					else if(var1.getBlockId(var3+x, var4+2, var5+z)!=Block.waterStill.blockID){
						var1.setBlock(var3+x, var4+2, var5+z, Block.waterStill.blockID,0,2);
						if(var1.rand.nextBoolean()){
							//this.spawnFish(var3+x, var4+2, var5+z, var1);
							}
					}
				}
			}
			for(int x = -1*(xRadius-1); x <= (xRadius-1); x++){
				for(int z = -1*(zRadius-1); z <= (zRadius-1); z++){
					if(var1.getBlockId(var3+x, var4+3, var5+z)!=Block.waterStill.blockID){
						var1.setBlock(var3+x, var4+3, var5+z, Block.waterStill.blockID,0,2);
					}
				}
			}
		}
		return false;
	}
	
	private ItemStack stone(){
		switch(type){
		case 1: return this.decideSandstone();
		case 2: return this.decideLimestone();
		default: return decideStone();
		}
		/*
		Random rand = new Random();
		if(!type){
			if (rand.nextInt(10) >= 4){
				return Block.cobblestone.blockID;
			}
			else if(rand.nextInt(2)==0){
				return AlgaeCraftMain.canReefHaveMossyCobble ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID;
			}
			else{
				return Block.stone.blockID;
			}
		}
		else{
			if (rand.nextInt(10) >= 4){
				return Block.sandStone.blockID;
			}
			else if(rand.nextInt(2)==0){
				return Block.sandStone.blockID;
			}
			else{
				return Block.sand.blockID;
			}
		}*/
	}
	
	private ItemStack decideStone(){
		Random rand = new Random();
		if(rand.nextInt(10) == 0){
			return new ItemStack(Block.stone);
		}
		if(rand.nextInt(4)==0){
			return new ItemStack(Block.gravel);
		}
		if(AlgaeCraftMain.canReefHaveMossyCobble && rand.nextInt(6)==0){
			return new ItemStack(Block.cobblestoneMossy);
		}
		return new ItemStack(Block.cobblestone);
	}
	
	private ItemStack decideSandstone(){
		Random rand = new Random();
		if(rand.nextInt(10) == 0){
			return new ItemStack(Block.sandStone,1,2);
		}
		if(rand.nextInt(4)==0){
			return new ItemStack(Block.sand);
		}
		if(AlgaeCraftMain.canReefHaveMossyCobble && rand.nextInt(6)==0){
			return new ItemStack(Block.sandStone,1,1);
		}
		return new ItemStack(Block.sandStone,1,0);
	}
	
	private ItemStack decideLimestone(){
		Random rand = new Random();
		if(rand.nextInt(10) == 0){
			return new ItemStack(AlgaeCraftMain.blockLimestone,1,3);
		}
		/*if(rand.nextInt(4)==0){
			return new ItemStack(Block.sand);
		}*/
		if(AlgaeCraftMain.canReefHaveMossyCobble && rand.nextInt(6)==0){
			return new ItemStack(AlgaeCraftMain.blockLimestone,1,2);
		}
		return new ItemStack(AlgaeCraftMain.blockLimestone,1,0);
	}
	
	private ItemStack randBlock(){
		Random rand = new Random();
		return genList[rand.nextInt(genList.length)];
	}
	
	/*private void spawnFish(int x, int y, int z, World world){
		EntityFish entity = new EntityFish(world);
		entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        entity.rotationYawHead = entity.rotationYaw;
        entity.renderYawOffset = entity.rotationYaw;
        entity.onSpawnWithEgg((EntityLivingData)null);
        world.spawnEntityInWorld(entity);
        entity.playLivingSound();
	}*/

}
