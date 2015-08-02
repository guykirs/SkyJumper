package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.GameObject;

public class BronzeCoins extends GameObject
{
    public static final float BRONZECOIN_WIDTH = 0.5f;
    public static final float BRONZECOIN_HEIGHT = 0.8f;
    public static final int COIN_SCORE = 10;

    float stateTime;
    public BronzeCoins(float x, float y)
    {
        super(x, y, BRONZECOIN_WIDTH, BRONZECOIN_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime)
    {
        stateTime += deltaTime;
    }
}
