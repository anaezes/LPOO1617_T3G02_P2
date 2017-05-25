package com.anacris.game;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;

import GameLogic.Score;
import GameView.FlyChicken;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		initialize(FlyChicken.GetInstance(), config);

		FlyChicken.GetInstance().setPrefs(Gdx.app.getPreferences("com.anacris.game"));
	}
}
