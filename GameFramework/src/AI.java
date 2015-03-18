
public interface AI{
	
	public void moveTowards(PolygonModel target, int speed, int degrees);

	public void turnTowards(PolygonModel target, int degrees);

	public void shootAt(PolygonModel target, int degrees);

	public void pointGunAt(PolygonModel target, int degrees);	
}
