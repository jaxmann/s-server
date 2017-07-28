package builder;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Transfers {

	public static void main(String[] args) {

		/*String[] tms = {"Atlanta_United_FC","Chicago_Fire_Soccer_Club","Columbus_Crew_SC","D.C._United", "Montreal_Impact","New_England_Revolution",
				"New_York_City_FC","New_York_Red_Bulls","Orlando_City_SC","Philadelphia_Union","Toronto_FC",
				"Colorado_Rapids","FC_Dallas","Houston_Dynamo","LA_Galaxy","Minnesota_United_FC","Portland_Timbers",
				"Real_Salt_Lake","San_Jose_Earthquakes","Seattle_Sounders_FC","Sporting_Kansas_City","Vancouver_Whitecaps_FC"
		};*/

		/*for (String s : tms) {
			start(s);
		}*/

		start();

		//start("Orlando_City_SC");

	}

	public static void start() {

		String transferURL = "http://www.espnfc.us/transfers?year=2017";

		HashMap<String,String[]> xferredPlayers = new HashMap<String, String[]>();

		try {
			Document document = Jsoup.connect(transferURL).followRedirects(true).get(); 
			Elements players = document.select("div.transfer-module");  

			for (Element thisPlayer: players) {
				String[] p = new String[2];
				String player = thisPlayer.select("div.transfer-module-header > h4 > a").text();
				p[0] = thisPlayer.select("div.transfer-module-content > div.transfer-graphic > a.previous").text();
				p[1] = thisPlayer.select("div.transfer-module-content > div.transfer-graphic > a.new").text();
				xferredPlayers.put(player, p);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//transferred players (most recent page) loaded into hashmap ^, now compare to existing set of players and update as necessary (below)

		try {
			BufferedReader reader = new BufferedReader (new FileReader("regenerate-players//fullTable.csv"));
			String line;
 

			while ((line = reader.readLine()) != null) {
				String[] arr = line.split(",");
				String name = arr[2].trim();
				byte pFull[] = name.getBytes(UTF_8);
				String fullName = new String(pFull, UTF_8);
//				for (int i=0; i<fullName.length();i++) {
//					if ((int)fullName.charAt(i)>300) {
//						System.out.println((int)fullName.charAt(i));
//						System.out.println(fullName.charAt(i));
//					}
//				}
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		


	}

}
