/*
 * AutoWalls by jkush321 is licensed under the
 * Creative Commons Attribution-NonCommercial 3.0 Unported License
 * 
 * You are fully allowed to modify the source code for your own network
 * of servers, but you may not distribute the modified code outside of
 * your servers.
 * 
 * AutoWalls was originally a personal project that was standalone for
 * my own private server, and it slowly accumulated into a giant plugin.
 * 
 * AutoWalls is for dedicated servers that are willing to run just Walls.
 * 
 * The license requires attribution and you have to give credit to jkush321
 * no matter how many changes were made to the code. In some clearly stated
 * way everyone who goes on the server must be able to easily see and be aware
 * of the fact that this code originated from jkush321 and was modified by
 * you or your team.
 * 
 * For more information visit http://bit.ly/AutoWalls
 * 
 */

package com.jkush321.autowalls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Heartbeat implements Runnable {
	public void run()
	{
		while (true)
		{
			try{
				//trololol idk if this will work.
				URL url = new URL("http://infacraft.net/autowalls/hb.php?port=" + "25565&online=1000&max=1&teamsize=100&version=<b>LazleCraft.net</b>");
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
				String s = "";
				String response = "";
				while ((s=br.readLine()) != null)
				{
					response+=s;
				}
				
				if (!response.trim().equals("Done.")) System.out.println("Abnormal: Heartbeat returned " + response);
				
				Thread.sleep(60000);
				
			}catch (Exception e) { System.out.println("Heartbeat error!"); e.printStackTrace(); }
		}
	}
}
