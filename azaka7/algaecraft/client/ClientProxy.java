package azaka7.algaecraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import azaka7.algaecraft.common.container.TileEntityAirCompressor;
import azaka7.algaecraft.common.container.TileEntityCage;
import azaka7.algaecraft.common.container.TileEntityRendererAirCompressor;
import azaka7.algaecraft.common.container.TileEntityRendererLobsterCage;
import azaka7.algaecraft.common.entity.EntityFish;
import azaka7.algaecraft.common.entity.EntityLobster;
import azaka7.algaecraft.common.entity.EntitySquidMeaty;
import azaka7.algaecraft.common.entity.ModelFish;
import azaka7.algaecraft.common.entity.ModelLobster;
import azaka7.algaecraft.common.entity.RenderFish;
import azaka7.algaecraft.common.entity.RenderLobster;
import azaka7.algaecraft.common.entity.RenderSquidAlt;
import azaka7.fogClarityAPI.FogClarityAPI;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public static KeyBindHandler keyBindings = new KeyBindHandler();
	public static ClientProxy instance = new ClientProxy();
	
	public static enum EnumDefinedRenderer{
		ALGAE,
		SEAWEED,
		SPONGE,
		CORAL,
		NONE;
	}
	
	public void registerRenderInformation(){
		registerRenders();
	}
	
	public void registerRenders(){
		try{
			System.out.println("[AlgaeCraft] Checking for FogClarity API");
			if(Loader.instance().isModLoaded("fogclarityAPI")){
				if(AlgaeCraftMain.usesFogClarityAPI){
					System.out.println("[AlgaeCraft] Attempting to register with Fog Clarity API...");
					FogHandler.register();
					System.out.println("[AlgaeCraft] Registered with Fog Clarity API.");
				}
				else{
					System.out.println("[AlgaeCraft] Ignoring.");
				}
			}
			else{
				if(AlgaeCraftMain.usesFogClarityAPI){
					System.out.println("[AlgaeCraft] AlgaeCraft has attempted to use the FogClarityAPI and failed.");
					System.out.println("[AlgaeCraft] No instance of FogClarityAPI was found.");
				}
				else{
					System.out.println("[AlgaeCraft] Fog Clarity API is not installed. Ignoring.");
				}
			}
		//if(AlgaeCraftMain.usesFogClarityAPI){
		//	FogHandler.register();
		//}
		}catch(Exception e){
			System.out.println("[AlgaeCraft] AlgaeCraft has attempted to use the FogClarityAPI and failed.");
			System.out.println("[AlgaeCraft] No instance of FogClarityAPI was found.");
		};
		
		RenderingRegistry.registerBlockHandler(new RenderBlockSimpleHandler(AlgaeCraftMain.algaeModelID,false,EnumDefinedRenderer.ALGAE));
		RenderingRegistry.registerBlockHandler(new RenderBlockSimpleHandler(AlgaeCraftMain.seaweedModelID,false,EnumDefinedRenderer.SEAWEED));
		RenderingRegistry.registerBlockHandler(new RenderBlockSimpleHandler(AlgaeCraftMain.coralModelID,false,EnumDefinedRenderer.CORAL));
		RenderingRegistry.registerBlockHandler(new RenderBlockSimpleHandler(AlgaeCraftMain.spongeModelID,false,EnumDefinedRenderer.SPONGE));
		
		KeyBindingRegistry.registerKeyBinding(keyBindings);
		RenderingRegistry.registerEntityRenderingHandler(EntitySquidMeaty.class, new RenderSquidAlt(new ModelSquid(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLobster.class, new RenderLobster(new ModelLobster(), 0.8F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFish.class, new RenderFish(new ModelFish(), 0.5F));
		ClientRegistry.registerTileEntity(TileEntityCage.class, "lobsterCageRender", (TileEntitySpecialRenderer) new TileEntityRendererLobsterCage());
		ClientRegistry.registerTileEntity(TileEntityAirCompressor.class, "blockAirCompressorRender", (TileEntitySpecialRenderer) new TileEntityRendererAirCompressor());
	}
	
	public void registerTickRegistry(){
		super.registerTickRegistry();
		TickRegistry.registerTickHandler(new RenderTickHandler(Side.CLIENT), Side.CLIENT);
		//TickRegistry.registerScheduledTickHandler(this, Side.CLIENT);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderWorldBlock(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block, int modelID)
    {
		if(modelID == AlgaeCraftMain.algaeModelID){
			this.renderAlgaeInWorld(renderer, world, x, y, z, block);
		}
		if(modelID == AlgaeCraftMain.spongeModelID){
			this.renderSpongeInWorld(renderer, world, x, y, z, block);
		}
		if(modelID == AlgaeCraftMain.coralModelID){
			this.renderCoralInWorld(renderer, world, x, y, z, block);
		}
		if(modelID == AlgaeCraftMain.seaweedModelID){
			this.renderBlockCrops(renderer, world, block, x, y, z);
		}
		return true;
	}
	
	public boolean renderBlockCrops(RenderBlocks renderer, IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4)
    {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
			tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			//this.renderBlockFluids(renderer, blockAccess, Block.waterStill, par2, par3, par4);
			renderer.renderBlockCropsImpl(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
		//}
		if(AlgaeCraftMain.specialCoralRender){
			this.renderBlockFluids(renderer, blockAccess, Block.waterStill, par2, par3, par4);
		}
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	private boolean renderCoralInWorld(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
		//var1.renderBlockFluids(Block.waterStill, var3, var4, var5);
		if(var2.getBlockMetadata(var3, var4, var5) == 6){
			var6.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
			var1.renderStandardBlock(var6, var3, var4, var5);
		}
		else if(var2.getBlockMetadata(var3, var4, var5) == 2){
			var6.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
			var1.renderStandardBlock(var6, var3, var4, var5);
		}
		else{
			var6.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
			this.renderCrossedSquares(var6, var3, var4, var5, var2);
		}
		if(AlgaeCraftMain.specialCoralRender){
		return var1.renderBlockFluids(Block.waterStill, var3, var4, var5);
		}else{return true;}
    }
	
	@SideOnly(Side.CLIENT)
    public boolean renderCrossedSquares(Block par1Block, int par2, int par3, int par4, IBlockAccess blockAccess)
    {
		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
        float f = 1.0F;
        int l = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
        float f1 = (float)(l >> 16 & 255) / 255.0F;
        float f2 = (float)(l >> 8 & 255) / 255.0F;
        float f3 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        double d0 = (double)par2;
        double d1 = (double)par3;
        double d2 = (double)par4;

        this.drawCrossedSquares(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), d0, d1, d2, 1.0F);
        return true;
    }
	@SideOnly(Side.CLIENT)
    public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9)
    {
		Tessellator tessellator = Tessellator.instance;
        Icon icon = par1Block.getIcon(0, par2);
        if (icon==null){return;}
        double d3 = (double)icon.getMinU();
        double d4 = (double)icon.getMinV();
        double d5 = (double)icon.getMaxU();
        double d6 = (double)icon.getMaxV();
        double d7 = 0.45D * (double)par9;
        double d8 = par3 + 0.5D - d7;
        double d9 = par3 + 0.5D + d7;
        double d10 = par7 + 0.5D - d7;
        double d11 = par7 + 0.5D + d7;
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d10, d5, d4);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double)par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double)par9, d11, d5, d4);
    }
	@SideOnly(Side.CLIENT)
	private boolean renderAlgaeInWorld(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
		var6.setBlockBounds(0.0F, 0.0F-0.125F, 0.0F, 1.0F, 0.0F-0.0625F, 1.0F);
		var1.renderStandardBlock(var6, var3, var4, var5);
		float var7 = 0.5F;
        float var8 = 0.015625F;
		var6.setBlockBounds(0.5F - var7, 0.0F-0.125F, 0.5F - var7, 0.5F + var7, var8, 0.5F + var7);
        return true;
    }
	@SideOnly(Side.CLIENT)
	private boolean renderSpongeInWorld(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		if(var7 == 0){
			var6.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
			var1.renderStandardBlock(var6, var3, var4, var5);
		}
		else if(var7 == 1){
			var6.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
			var1.renderStandardBlock(var6, var3, var4, var5);
		}
		else if(var7 == 2){
			var6.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.75F, 0.875F);
			var1.renderStandardBlock(var6, var3, var4, var5);
		}
		if(AlgaeCraftMain.specialCoralRender){
		return var1.renderBlockFluids(Block.waterStill, var3, var4, var5);
		}else{return true;}
    }
	
	public boolean renderBlockFluids(RenderBlocks renderer, IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4)
    {
        Tessellator tessellator = Tessellator.instance;
        int l = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;
        boolean flag = par1Block.shouldSideBeRendered(blockAccess, par2, par3 + 1, par4, 1);
        boolean flag1 = par1Block.shouldSideBeRendered(blockAccess, par2, par3 - 1, par4, 0);
        boolean[] aboolean = new boolean[] {par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 - 1, 2), par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 + 1, 3), par1Block.shouldSideBeRendered(blockAccess, par2 - 1, par3, par4, 4), par1Block.shouldSideBeRendered(blockAccess, par2 + 1, par3, par4, 5)};

        if (!flag && !flag1 && !aboolean[0] && !aboolean[1] && !aboolean[2] && !aboolean[3])
        {
            return false;
        }
        else
        {
            boolean flag2 = false;
            float f3 = 0.5F;
            float f4 = 1.0F;
            float f5 = 0.8F;
            float f6 = 0.6F;
            double d0 = 0.0D;
            double d1 = 1.0D;
            Material material = par1Block.blockMaterial;
            int i1 = blockAccess.getBlockMetadata(par2, par3, par4);
            double d2 = (double)renderer.getFluidHeight(par2, par3, par4, material);
            double d3 = (double)renderer.getFluidHeight(par2, par3, par4 + 1, material);
            double d4 = (double)renderer.getFluidHeight(par2 + 1, par3, par4 + 1, material);
            double d5 = (double)renderer.getFluidHeight(par2 + 1, par3, par4, material);
            double d6 = 0.0010000000474974513D;
            float f7;
            float f8;
            float f9;
            
            //
           //flag= false;
            //flag1=false;
            
            //
            /*
            if (renderer.renderAllFaces || flag)
            {
                flag2 = true;
                Icon icon = renderer.getBlockIconFromSideAndMetadata(par1Block, 1, i1);
                float f10 = (float)BlockFluid.getFlowDirection(renderer.blockAccess, par2, par3, par4, material);

                if (f10 > -999.0F)
                {
                    icon = renderer.getBlockIconFromSideAndMetadata(par1Block, 2, i1);
                }

                d2 -= d6;
                d3 -= d6;
                d4 -= d6;
                d5 -= d6;
                double d7;
                double d8;
                double d9;
                double d10;
                double d11;
                double d12;
                double d13;
                double d14;

                if (f10 < -999.0F)
                {
                    d8 = (double)icon.getInterpolatedU(0.0D);
                    d12 = (double)icon.getInterpolatedV(0.0D);
                    d7 = d8;
                    d11 = (double)icon.getInterpolatedV(16.0D);
                    d10 = (double)icon.getInterpolatedU(16.0D);
                    d14 = d11;
                    d9 = d10;
                    d13 = d12;
                }
                else
                {
                    f9 = MathHelper.sin(f10) * 0.25F;
                    f8 = MathHelper.cos(f10) * 0.25F;
                    f7 = 8.0F;
                    d8 = (double)icon.getInterpolatedU((double)(8.0F + (-f8 - f9) * 16.0F));
                    d12 = (double)icon.getInterpolatedV((double)(8.0F + (-f8 + f9) * 16.0F));
                    d7 = (double)icon.getInterpolatedU((double)(8.0F + (-f8 + f9) * 16.0F));
                    d11 = (double)icon.getInterpolatedV((double)(8.0F + (f8 + f9) * 16.0F));
                    d10 = (double)icon.getInterpolatedU((double)(8.0F + (f8 + f9) * 16.0F));
                    d14 = (double)icon.getInterpolatedV((double)(8.0F + (f8 - f9) * 16.0F));
                    d9 = (double)icon.getInterpolatedU((double)(8.0F + (f8 - f9) * 16.0F));
                    d13 = (double)icon.getInterpolatedV((double)(8.0F + (-f8 - f9) * 16.0F));
                }

                tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4));
                f9 = 1.0F;
                tessellator.setColorOpaque_F(f4 * f9 * f, f4 * f9 * f1, f4 * f9 * f2);
                tessellator.addVertexWithUV((double)(par2 + 0), (double)par3 + d2, (double)(par4 + 0), d8, d12);
                tessellator.addVertexWithUV((double)(par2 + 0), (double)par3 + d3, (double)(par4 + 1), d7, d11);
                tessellator.addVertexWithUV((double)(par2 + 1), (double)par3 + d4, (double)(par4 + 1), d10, d14);
                tessellator.addVertexWithUV((double)(par2 + 1), (double)par3 + d5, (double)(par4 + 0), d9, d13);
            }

            if (renderer.renderAllFaces || flag1)
            {
                tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3 - 1, par4));
                float f11 = 1.0F;
                tessellator.setColorOpaque_F(f3 * f11, f3 * f11, f3 * f11);
                renderer.renderFaceYNeg(par1Block, (double)par2, (double)par3 + d6, (double)par4, renderer.getBlockIconFromSide(par1Block, 0));
                flag2 = true;
            }*/

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = par2;
                int l1 = par4;

                if (j1 == 0)
                {
                    l1 = par4 - 1;
                }

                if (j1 == 1)
                {
                    ++l1;
                }

                if (j1 == 2)
                {
                    k1 = par2 - 1;
                }

                if (j1 == 3)
                {
                    ++k1;
                }

                Icon icon1 = renderer.getBlockIconFromSideAndMetadata(par1Block, j1 + 2, i1);

                if (renderer.renderAllFaces || aboolean[j1])
                {
                    double d15;
                    double d16;
                    double d17;
                    double d18;
                    double d19;
                    double d20;

                    if (j1 == 0)
                    {
                        d15 = d2;
                        d17 = d5;
                        d16 = (double)par2;
                        d18 = (double)(par2 + 1);
                        d19 = (double)par4 + d6;
                        d20 = (double)par4 + d6;
                    }
                    else if (j1 == 1)
                    {
                        d15 = d4;
                        d17 = d3;
                        d16 = (double)(par2 + 1);
                        d18 = (double)par2;
                        d19 = (double)(par4 + 1) - d6;
                        d20 = (double)(par4 + 1) - d6;
                    }
                    else if (j1 == 2)
                    {
                        d15 = d3;
                        d17 = d2;
                        d16 = (double)par2 + d6;
                        d18 = (double)par2 + d6;
                        d19 = (double)(par4 + 1);
                        d20 = (double)par4;
                    }
                    else
                    {
                        d15 = d5;
                        d17 = d4;
                        d16 = (double)(par2 + 1) - d6;
                        d18 = (double)(par2 + 1) - d6;
                        d19 = (double)par4;
                        d20 = (double)(par4 + 1);
                    }

                    flag2 = true;
                    float f12 = icon1.getInterpolatedU(0.0D);
                    f9 = icon1.getInterpolatedU(8.0D);
                    f8 = icon1.getInterpolatedV((1.0D - d15) * 16.0D * 0.5D);
                    f7 = icon1.getInterpolatedV((1.0D - d17) * 16.0D * 0.5D);
                    float f13 = icon1.getInterpolatedV(8.0D);
                    tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(renderer.blockAccess, k1, par3, l1));
                    float f14 = 1.0F;

                    if (j1 < 2)
                    {
                        f14 *= f5;
                    }
                    else
                    {
                        f14 *= f6;
                    }

                    tessellator.setColorOpaque_F(f4 * f14 * f, f4 * f14 * f1, f4 * f14 * f2);
                    //tessellator.setColorOpaque_F(1, 1, 1);
                    tessellator.addVertexWithUV(d16, (double)par3 + d15, d19, (double)f12, (double)f8);
                    tessellator.addVertexWithUV(d18, (double)par3 + d17, d20, (double)f9, (double)f7);
                    tessellator.addVertexWithUV(d18, (double)(par3 + 0), d20, (double)f9, (double)f13);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d19, (double)f12, (double)f13);
                }
            }

            renderer.renderMinY = d0;
            renderer.renderMaxY = d1;
            return flag2;
        }
    }
}
