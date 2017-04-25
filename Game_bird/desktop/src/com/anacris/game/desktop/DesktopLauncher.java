package com.anacris.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anacris.game.GameBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameBird.WIDTH;
		config.height = GameBird.HEIGHT;
		config.title = GameBird.TITLE;
		new LwjglApplication(new GameBird(), config);
	}
}
