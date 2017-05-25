package com.anacris.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import GameView.FlyChicken;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width =  FlyChicken.WIDTH;
		config.height = FlyChicken.HEIGHT;
		config.title = FlyChicken.TITLE;
		new LwjglApplication(FlyChicken.getInstance(), config);

		FlyChicken.getInstance().setPrefs(Gdx.app.getPreferences("com.anacris.game"));
	}
}
