package azaka7.algaecraft.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import azaka7.algaecraft.common.AlgaeCraftMain;

public class BiomeGenOceanDeep extends BiomeGenBase {

	public BiomeGenOceanDeep(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = (byte) AlgaeCraftMain.blockSediment.blockID;
		this.fillerBlock = (byte) Block.blockClay.blockID;
		this.theBiomeDecorator.dirtGen = new WorldGenMinable(AlgaeCraftMain.blockSediment.blockID, 32);
		this.theBiomeDecorator.sandGen = new WorldGenSandDeep(7, AlgaeCraftMain.blockSediment.blockID);
        this.theBiomeDecorator.gravelAsSandGen = new WorldGenSandDeep(6, AlgaeCraftMain.blockSediment.blockID);
        this.theBiomeDecorator.goldGen = new WorldGenMinable(Block.oreGold.blockID, 12);
	}
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        int k = 4 + par2Random.nextInt(5);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = par3 + par2Random.nextInt(16);
            j1 = par2Random.nextInt(16) + 4;
            int k1 = par4 + par2Random.nextInt(16);
            int l1 = par1World.getBlockId(i1, j1, k1);

            if (l1 == Block.stone.blockID)
            {
                par1World.setBlock(i1, j1, k1, Block.oreEmerald.blockID, 0, 2);
            }
        }
        k = 4 + par2Random.nextInt(3);
        l=0;
        i1=0;
        j1=0;

        for (l = 0; l < k; ++l)
        {
            i1 = par3 + par2Random.nextInt(16);
            j1 = par2Random.nextInt(4) + 9;
            int k1 = par4 + par2Random.nextInt(16);
            int l1 = par1World.getBlockId(i1, j1, k1);

            if (l1 == Block.stone.blockID)
            {
                par1World.setBlock(i1, j1, k1, Block.oreDiamond.blockID, 0, 2);
            }
        }
        k = 2 + par2Random.nextInt(3);
        l=0;
        i1=0;
        j1=0;

        for (l = 0; l < k; ++l)
        {
            i1 = par3 + par2Random.nextInt(16);
            j1 = par2Random.nextInt(4) + 1;
            int k1 = par4 + par2Random.nextInt(16);
            int l1 = par1World.getBlockId(i1, j1, k1);

            if (l1 == Block.stone.blockID)
            {
                par1World.setBlock(i1, j1, k1, Block.lavaMoving.blockID, 0, 2);
            }
        }
    }

}
