package gene.tank;

public class Lookup {
	
	public final static double sin[] = generateSine();
	
	public final static double cos[] = generateCosine();
	
	public static double[] generateSine(){
		double[] sin = new double[360];
		
		for (int i = 0; i < 360; i++){
			sin[i] = Math.sin(i * Math.PI/180);
		}
		
		return sin;
	}
	
	public static double[] generateCosine(){
		double[] cos = new double[360];
		
		for (int i = 0; i < 360; i++){
			cos[i] = Math.cos(i * Math.PI/180);
		}
		
		return cos;
	}
}
