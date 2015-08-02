package com.guillaumesoft.skyjumper;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

///  OCTOBER 23, 2014
///  GUILLAUME SWOLFS
///  GUILLAUMESOFT
///  THIS CLASS DRAWS THE GAME CREDIT SCREEN
public class GameCreditScreen extends GLScreen
{
    ////////////////////////////////////////
    // CLASS VARAIBLES
    ////////////////////////////////////////
    private SpriteBatcher batcher;
    private Camera2D guiCam;
    Rectangle backBounds;
    Vector2 touchPoint;

    public GameCreditScreen(Game game)
    {
        super(game);

        batcher    = new SpriteBatcher(glGraphics, 100);
        guiCam     = new Camera2D(glGraphics, 1920, 1080);

        backBounds = new Rectangle(0, 0, 64, 64);
        touchPoint = new Vector2();
    }

    @Override
    public void update(float deltaTime)
    {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++)
        {
            Input.TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(OverlapTester.pointInRectangle(backBounds, touchPoint))
                {
                    // CALL ON THE MAIN MENU
                    //ScreenManager.STATE = ScreenManager.GAME_MAINMENU;
                    MainMenuScreen menu = new MainMenuScreen(game);
                    game.setScreen(menu);
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

           batcher.beginBatch(Assets.creditScreen);

              // SHOW THE CREDIT SCREEN TO THE USER
              batcher .drawSprite(guiCam.position.x,  guiCam.position.y, guiCam.frustumWidth, guiCam.frustumHeight, Assets.creditScreenRegion);

           batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);

           batcher.drawSprite(32, 32, 64, 64, Assets.arrow);

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);

    }

    @Override
    public void pause() {   }

    @Override
    public void resume(){  }

    @Override
    public void dispose() {  }
}
