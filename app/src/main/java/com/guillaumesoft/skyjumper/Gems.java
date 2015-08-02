package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.GameObject;

public class Gems extends GameObject
{
    public static final float GEM_WIDTH = 0.5f;
    public static final float GEM_HEIGHT = 0.8f;
    public static final int GEM_SCORE = 10;

    float stateTime;
    public Gems(float x, float y)
    {
        super(x, y, GEM_WIDTH, GEM_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime)
    {
        stateTime += deltaTime;
    }
}
