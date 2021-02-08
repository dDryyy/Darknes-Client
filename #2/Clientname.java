package de.darknes;

import org.lwjgl.opengl.Display;

public class Darknes {
    public static String NAME = "Darknes";
    public static String VERSION = "v1.5";
    public static String AUTHORS = "Lndry#3616";
    public static final Darknes INSTANCE = new Darknes();
    public DiscordRP discordRP = new DiscordRP();

    public Darknes() {
    }

    public static void nameChange() {
        Display.setTitle(NAME + " " + VERSION);
    }

    public static final Darknes getInstance() {
        return INSTANCE;
    }

    public void init() {
        this.discordRP.start();
    }

    public void shutdown() {
        this.discordRP.shutdown();
    }
}
