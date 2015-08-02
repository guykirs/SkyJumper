package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.GameObject;

public class GoldCoins extends GameObject
{
    public static final float GOLDCOIN_WIDTH = 0.5f;
    public static final float GOLDCOIN_HEIGHT = 0.8f;
    public static final int COIN_SCORE = 10;

    float stateTime;
    public GoldCoins(float x, float y)
    {
        super(x, y, GOLDCOIN_WIDTH, GOLDCOIN_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime)
    {
        stateTime += deltaTime;
    }
}
