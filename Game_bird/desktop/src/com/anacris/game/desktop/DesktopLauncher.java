package com.anacris.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import GameView.ViewMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width =  ViewMain.WIDTH;
		config.height = ViewMain.HEIGHT;
		config.title = ViewMain.TITLE;
		new LwjglApplication(new ViewMain(), config);
	}
}
