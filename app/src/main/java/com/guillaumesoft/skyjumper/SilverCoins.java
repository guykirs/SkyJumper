package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.GameObject;

public class SilverCoins extends GameObject
{
    public static final float SILVERCOIN_WIDTH = 0.5f;
    public static final float SILVERCOIN_HEIGHT = 0.8f;
    public static final int COIN_SCORE = 10;

    float stateTime;
    public SilverCoins(float x, float y)
    {
        super(x, y, SILVERCOIN_WIDTH, SILVERCOIN_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime)
    {
        stateTime += deltaTime;
    }
}
