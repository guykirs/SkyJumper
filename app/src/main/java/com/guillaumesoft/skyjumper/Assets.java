package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.gl.Animation;
import com.badlogic.androidgames.framework.gl.Font;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets
{
    public static Texture background;
    public static TextureRegion backgroundRegion;
    
    public static Texture items;        
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion arrow;
    public static TextureRegion pause;    
    public static TextureRegion spring;
    public static TextureRegion castle;
    public static Animation coinAnim;
    public static Animation bobJump;
    public static Animation bobFall;
    public static TextureRegion bobHit;
    public static Animation squirrelFly;
    public static TextureRegion platform;
    public static Animation brakingPlatform;    
    public static Font font;

    // FONT COLLORS
    public static Texture BlackFont;
    public static Font blackfont;

    public static Texture RedFont;
    public static Font redfont;

    public static Texture BlueFont;
    public static Font bluefont;

    // CREDIT SCREEN
    public static Texture creditScreen;
    public static TextureRegion creditScreenRegion;

    public static Texture tiles;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion gem;
    public static Animation goldcoins;
    public static Animation silvercoins;
    public static Animation bronzecoins;
    public static TextureRegion buttonA;

    public static Sound exitReached;
    public static Sound gemCollected;
    public static Sound monsterkilled;
    public static Sound playerfall;
    public static Sound powerup;
    public static Sound playerKilledSound;
    public static Music drum;
    
    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;
    
    public static void load(GLGame game)
    {
        background          = new Texture(game, "background.png");
        backgroundRegion    = new TextureRegion(background, 0, 0, 320, 480);

        creditScreen        = new Texture(game, "creditScreen.png");
        creditScreenRegion  = new TextureRegion(creditScreen , 0, 0, 512, 512);

        tiles               = new Texture(game, "tiles.png");
        pause               = new TextureRegion(tiles,   0,  0, 64, 64);
        soundOff            = new TextureRegion(tiles,  64,  0, 64, 64);
        soundOn             = new TextureRegion(tiles, 128,  0, 64, 64);
        gem                 = new TextureRegion(tiles,   0, 64, 64, 64);
        buttonA             = new TextureRegion(tiles, 350,  0, 64, 64);
        pause               = new TextureRegion(tiles,  64, 64, 64, 64);

        goldcoins         = new Animation(0.2f, new TextureRegion(tiles, 0, 150, 32, 32), new TextureRegion(tiles, 32, 150, 32, 32),
                new TextureRegion(tiles, 64, 150, 32, 32), new TextureRegion(tiles, 96, 150, 32, 32), new TextureRegion(tiles, 128, 150, 32, 32),
                new TextureRegion(tiles,160, 150, 32, 32), new TextureRegion(tiles,192, 150, 32, 32), new TextureRegion(tiles, 224, 150, 32, 32));

        silvercoins         = new Animation(0.2f, new TextureRegion(tiles, 0, 190, 32, 32), new TextureRegion(tiles, 32, 190, 32, 32),
                new TextureRegion(tiles, 64, 190, 32, 32), new TextureRegion(tiles, 96, 190, 32, 32), new TextureRegion(tiles, 128, 190, 32, 32),
                new TextureRegion(tiles,160, 190, 32, 32), new TextureRegion(tiles,192, 190, 32, 32), new TextureRegion(tiles, 224, 190, 32, 32));

        bronzecoins        = new Animation(0.2f, new TextureRegion(tiles, 0, 240, 32, 32), new TextureRegion(tiles, 32, 240, 32, 32),
                new TextureRegion(tiles, 64, 240, 32, 32), new TextureRegion(tiles, 96, 240, 32, 32), new TextureRegion(tiles, 128, 240, 32, 32),
                new TextureRegion(tiles,160, 240, 32, 32), new TextureRegion(tiles,192, 240, 32, 32), new TextureRegion(tiles, 224, 240, 32, 32));

        items = new Texture(game, "items.png");        
        mainMenu = new TextureRegion(items, 0, 224, 300, 210);

        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        ready     = new TextureRegion(items, 320, 224, 192, 32);
        gameOver  = new TextureRegion(items, 352, 256, 160, 96);
        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
        logo = new TextureRegion(items, 0, 352, 274, 142);
        arrow = new TextureRegion(items, 0, 64, 64, 64);
        //pause = new TextureRegion(items, 64, 64, 64, 64);
        
        spring = new TextureRegion(items, 128, 0, 32, 32);
        castle = new TextureRegion(items, 128, 64, 64, 64);

        coinAnim = new Animation(0.2f,                                 
                                 new TextureRegion(items, 128, 32, 32, 32),
                                 new TextureRegion(items, 160, 32, 32, 32),
                                 new TextureRegion(items, 192, 32, 32, 32),
                                 new TextureRegion(items, 160, 32, 32, 32));
        bobJump = new Animation(0.2f,
                                new TextureRegion(items, 0, 128, 32, 32),
                                new TextureRegion(items, 32, 128, 32, 32));
        bobFall = new Animation(0.2f,
                                new TextureRegion(items, 64, 128, 32, 32),
                                new TextureRegion(items, 96, 128, 32, 32));
        bobHit = new TextureRegion(items, 128, 128, 32, 32);
        squirrelFly = new Animation(0.2f, 
                                    new TextureRegion(items, 0, 160, 32, 32),
                                    new TextureRegion(items, 32, 160, 32, 32));
        platform = new TextureRegion(items, 64, 160, 64, 16);
        brakingPlatform = new Animation(0.2f,
                                     new TextureRegion(items, 64, 160, 64, 16),
                                     new TextureRegion(items, 64, 176, 64, 16),
                                     new TextureRegion(items, 64, 192, 64, 16),
                                     new TextureRegion(items, 64, 208, 64, 16));
        
        font      = new Font(items, 224, 0, 16, 16, 20);

        BlackFont = new Texture(game, "blackFont.png");
        blackfont = new Font(BlackFont, 224, 0, 16, 16, 20);

        RedFont = new Texture(game, "redFont.png");
        redfont = new Font(RedFont, 224, 0, 16, 16, 20);

        BlueFont  = new Texture(game, "blueFont.png");
        bluefont  = new Font(BlueFont, 224, 0, 16, 16, 20);
        
        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);

        if(Settings.soundEnabled)
            music.play();

        jumpSound     = game.getAudio().newSound("jump.ogg");
        highJumpSound = game.getAudio().newSound("highjump.ogg");
        hitSound      = game.getAudio().newSound("hit.ogg");
        coinSound     = game.getAudio().newSound("coin.ogg");
        clickSound    = game.getAudio().newSound("click.ogg");
        gemCollected  = game.getAudio().newSound("GemCollected.ogg");
    }       
    
    public static void reload()
    {
        background.reload();
        items.reload();
        BlackFont.reload();
        RedFont.reload();
        BlueFont.reload();
        tiles.reload();

        if(Settings.soundEnabled)
            music.play();
    }
    
    public static void playSound(Sound sound)
    {
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
