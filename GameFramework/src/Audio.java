import java.applet.*;
import java.net.URL;

public class Audio {
	
	URL url;
	
	AudioClip shoot1AC;
	
	public Audio(GameFramework game){
		url = game.getDocumentBase();
		
		shoot1AC = game.getAudioClip(url, "audio/shoot1.au");
		
		
	}

}
