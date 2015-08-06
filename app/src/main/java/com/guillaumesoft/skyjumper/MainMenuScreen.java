package com.guillaumesoft.skyjumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen
{
    ////////////////////////////////////////////////
    // CLASS VARAIBLES
    Camera2D      guiCam;
    SpriteBatcher batcher;
    Rectangle     soundBounds;
    Rectangle     playBounds;
    Rectangle     highscoresBounds;
    Rectangle     creditBounds;
    Rectangle     helpBounds;
    Rectangle     exitBounds;
    Vector2       touchPoint;

    public MainMenuScreen(Game game)
    {
        super(game);
        guiCam           = new Camera2D(glGraphics, 1920, 1080);
        batcher          = new SpriteBatcher(glGraphics, 100);
        soundBounds      = new Rectangle(128, 128, 64, 64);
        playBounds       = new Rectangle(1920 /2, 1080 /2, 300, 36);
        highscoresBounds = new Rectangle(1920 /2, 1080 /2 - 50, 300, 36);
        creditBounds     = new Rectangle(1920 /2, 1080 /2 - 50 - 50, 300, 36);
        helpBounds       = new Rectangle(1920 /2, 1080 /2 - 50 - 50 - 50, 300, 36);

        exitBounds       = new Rectangle(1920 /2, 1080 /2 - 50 - 50 - 50 - 50, 300, 36);
        touchPoint       = new Vector2();
    }       

    @Override
    public void update(float deltaTime)
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++)
        {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP)
            {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(OverlapTester.pointInRectangle(playBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new GameScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(highscoresBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HighscoresScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(creditBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new GameCreditScreen(game));
                    return;
                }


                if(OverlapTester.pointInRectangle(helpBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HelpScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(exitBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    ScreenManager.GetGameInstances().finish();
                    return;
                }


                if(OverlapTester.pointInRectangle(soundBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;

                    if(Settings.soundEnabled) 
                        Assets.music.play();
                    else
                        Assets.music.pause();
                }

                if(OverlapTester.pointInRectangle(exitBounds, touchPoint))
                {
                    Assets.playSound(Assets.clickSound);
                    ScreenManager.game.finish();
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime)
    {
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
           batcher.beginBatch(Assets.background);

              batcher.drawSprite(guiCam.position.x, guiCam.position.y, 1920, 1080, Assets.backgroundRegion);

           batcher.endBatch();
        
           gl.glEnable(GL10.GL_BLEND);
           gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

              batcher.beginBatch(Assets.BlackFont);

                 // PRINT THE GAME NAME ON THE SCREEN
                 Assets.blackfont.drawText(batcher, "Sky Jumper", 1920 / 2 - 300, 800, 45.0f, 45.0f);

              batcher.endBatch();

              batcher.beginBatch(Assets.items);

                 batcher.drawSprite(guiCam.position.x, guiCam.position.y -100, 300, 300, Assets.mainMenu);

              batcher.endBatch();

              batcher.beginBatch(Assets.tiles);

                 batcher.drawSprite(122, 132, 64, 64, Settings.soundEnabled ? Assets.soundOn : Assets.soundOff);

              batcher.endBatch();
        
           gl.glDisable(GL10.GL_BLEND);
    }
    
    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {        
    }       

    @Override
    public void dispose() {        
    }
}
