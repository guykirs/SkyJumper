package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.impl.GLGame;

/**
 * Created by guyki on 7/31/2015.
 */
public abstract class ScreenManager
{
    ////////////////////////////////////////////////////////////////
    // GET THE GAME INSTANCE AND HOLD IT FOR OTHERE CLASSES
    public static GLGame GetGameInstances()
    {
        return game;
    }

    public static void SetGameInstances(GLGame value)
    {
        game = value;
    }
    // GET AND SET GAME INSTANCES
    public static GLGame game;

}
