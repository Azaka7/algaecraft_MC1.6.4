package azaka7.algaecraft.common;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerSCUBAMovementHandler {
	
	//0=helm 1=chest 2=legs 3=feet
	private static float[] setOfGearHorizAdd = new float[4];
	private static float[] setOfGearVertAdd = new float[4];
	
	private static float[] setOfGearHorizMult = new float[]{1,1,1,1};
	private static float[] setOfGearVertMult = new float[]{1,1,1,1};
	
	public PlayerSCUBAMovementHandler(){
	}
	
	public static void updatePlayerMovement(EntityPlayer player){
		player.motionX += getOverallHorizTotal();
		player.motionX *= getOverallHorizMult();
		player.motionZ += getOverallHorizTotal();
		player.motionZ *= getOverallHorizMult();
		
		player.motionY += getOverallVertTotal();
		player.motionY *= getOverallVertMult();
	}
	
	public static void updateVertAdd(float value, int location){
		setOfGearVertAdd[location] = value;
	}
	
	public static void updateHorizAdd(int value, int location){
		setOfGearHorizAdd[location] = value;
	}
	
	public static void updateVertMult(float value, int location){
		setOfGearVertMult[location] = value;
	}
	
	public static void updateHorizMult(float value, int location){
		setOfGearHorizMult[location] = value;
	}
	
	public static float getVertAddForPiece(int location){
		return setOfGearVertAdd[location];
	}
	
	public static float getHorizAddForPiece(int location){
		return setOfGearHorizAdd[location];
	}
	
	public static float getOverallVertMult(){
		return setOfGearVertMult[0]*setOfGearVertMult[1]*setOfGearVertMult[2]*setOfGearVertMult[3];
	}
	
	public static float getOverallHorizMult(){
		return setOfGearHorizMult[0]*setOfGearHorizMult[1]*setOfGearHorizMult[2]*setOfGearHorizMult[3];
	}
	
	public static float getOverallVertTotal(){
		return (setOfGearVertAdd[0]+setOfGearVertAdd[1]+setOfGearVertAdd[2]+setOfGearVertAdd[3]);
	}
	
	public static float getOverallHorizTotal(){
		return (setOfGearHorizAdd[0]+setOfGearHorizAdd[1]+setOfGearHorizAdd[2]+setOfGearHorizAdd[3]);
	}

}
