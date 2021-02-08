package de.darknes;

import de.darknes.Darknes;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordEventHandlers.Builder;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {

	private boolean running = true;
	private long created = 0;
	
	public void start() {
		
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
				update("Loading...", "");
			}
		}).build();
		
		DiscordRPC.discordInitialize("798451222310420530", handlers, true);
		
		new Thread("RPC Callback") {
			public void run() {
				while (running) {
					DiscordRPC.discordRunCallbacks();
				}	
			}
		}.start();
	}
	
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}
	
	public void update(String linefirst, String linesecond) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(linesecond);
		b.setBigImage("512x512", Darknes.NAME + " " + Darknes.VERSION + " " + "by " + Darknes.AUTHORS);
		b.setSmallImage("mc", "in Minecraft 1.8.8");
		b.setDetails(linefirst);
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
	}
}
