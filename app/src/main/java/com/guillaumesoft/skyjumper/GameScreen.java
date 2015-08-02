package com.guillaumesoft.skyjumper;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.FPSCounter;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;
import com.guillaumesoft.skyjumper.World.WorldListener;

public class GameScreen extends GLScreen
{
    static final int GAME_READY = 0;    
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;
  
    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;    
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;    
    Rectangle pauseBounds;
    Rectangle resumeBounds;
    Rectangle quitBounds;
    int lastScore;
    String scoreString;    
    FPSCounter fpsCounter;
    
    public GameScreen(Game game)
	{
        super(game);

        state      = GAME_READY;
        guiCam     = new Camera2D(glGraphics, 1920, 1080);
        touchPoint = new Vector2();
        batcher    = new SpriteBatcher(glGraphics, 1000);

        worldListener = new WorldListener()
        {
            @Override
            public void jump() {            
                Assets.playSound(Assets.jumpSound);
            }

            @Override
            public void highJump() {
                Assets.playSound(Assets.highJumpSound);
            }

            @Override
            public void hit() {
                Assets.playSound(Assets.hitSound);
            }

            @Override
            public void coin() {
                Assets.playSound(Assets.coinSound);
            }

            @Override
            public void gem() { Assets.playSound(Assets.gemCollected); }

            @Override
            public void goldcoin() { Assets.playSound(Assets.gemCollected); }

            @Override
            public void silvercoin() { Assets.playSound(Assets.gemCollected); }

            @Override
            public void bronzecoin() { Assets.playSound(Assets.gemCollected); }
        };

        world        = new World(worldListener);
        renderer     = new WorldRenderer(glGraphics, batcher, world);
        pauseBounds  = new Rectangle(1920 - 250, 1080 - 100, 64, 64);
        resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
        quitBounds   = new Rectangle(160 - 96, 240 - 36, 192, 36);
        lastScore    = 0;
        scoreString  = "";
        fpsCounter   = new FPSCounter();
    }

	@Override
	public void update(float deltaTime)
    {
	    if(deltaTime > 0.1f)
	        deltaTime = 0.1f;
	    
	    switch(state)
        {
            case GAME_READY:
                updateReady();
	            break;
	        case GAME_RUNNING:
	            updateRunning(deltaTime);
	            break;
	        case GAME_PAUSED:
	            updatePaused();
	            break;
	        case GAME_LEVEL_END:
	            updateLevelEnd();
	            break;
	        case GAME_OVER:
	            updateGameOver();
	            break;
	    }
	}
	
	private void updateReady()
    {
	    if(game.getInput().getTouchEvents().size() > 0)
        {
	        state = GAME_RUNNING;
	    }
	}
	
	private void updateRunning(float deltaTime)
    {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++)
        {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	        
	        if(OverlapTester.pointInRectangle(pauseBounds, touchPoint))
            {
	            Assets.playSound(Assets.clickSound);
	            state = GAME_PAUSED;
	            return;
	        }            
	    }
	    
	    world.update(deltaTime, game.getInput().getAccelX());

	    if(world.score != lastScore)
        {
	        lastScore = world.score;
	        scoreString = "" + lastScore;
	    }

	    if(world.state == World.WORLD_STATE_NEXT_LEVEL)
        {
	        state = GAME_LEVEL_END;        
	    }

	    if(world.state == World.WORLD_STATE_GAME_OVER)
        {
	        state = GAME_OVER;

	        if(lastScore >= Settings.highscores[4]) 
	            scoreString = "new highscore: " + lastScore;
	        else
	            scoreString = "" + lastScore;

	        Settings.addScore(lastScore);
	        Settings.save(game.getFileIO());
	    }
	}
	
	private void updatePaused()
    {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();

	    for(int i = 0; i < len; i++)
        {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	        
	        if(OverlapTester.pointInRectangle(resumeBounds, touchPoint))
            {
	            Assets.playSound(Assets.clickSound);
	            state = GAME_RUNNING;
	            return;
	        }
	        
	        if(OverlapTester.pointInRectangle(quitBounds, touchPoint))
            {
	            Assets.playSound(Assets.clickSound);
	            game.setScreen(new MainMenuScreen(game));
	            return;
	        
	        }
	    }
	}
	
	private void updateLevelEnd()
    {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++)
        {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;

	        world       = new World(worldListener);
	        renderer    = new WorldRenderer(glGraphics, batcher, world);
	        world.score = lastScore;
	        state       = GAME_READY;
	    }
	}
	
	private void updateGameOver()
    {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

	    int len = touchEvents.size();

	    for(int i = 0; i < len; i++)
        {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;

	        game.setScreen(new MainMenuScreen(game));
	    }
	}

	@Override
	public void present(float deltaTime)
    {
	    GL10 gl = glGraphics.getGL();
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    
	    renderer.render();
	    
	    guiCam.setViewportAndMatrices();
	    gl.glEnable(GL10.GL_BLEND);
	    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        switch(state)
        {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }

	    gl.glDisable(GL10.GL_BLEND);
	    fpsCounter.logFrame();
	}
	
	private void presentReady()
	{
        batcher.beginBatch(Assets.items);

	       batcher.drawSprite(1920 / 2, 1080 / 2, 192, 32, Assets.ready);

        batcher.endBatch();
	}
	
	private void presentRunning()
    {
        batcher.beginBatch(Assets.BlackFont);

           Assets.blackfont.drawText(batcher, "score: " + scoreString, 200, 1080 - 100, 20, 20);

        batcher.endBatch();

        batcher.beginBatch(Assets.items);

	       batcher.drawSprite(1920 - 200, 1080 - 100, 64, 64, Assets.pause);
	       //Assets.font.drawText(batcher, scoreString, 200, 1080 - 100, 20, 20);

        batcher.endBatch();
	}
	
	private void presentPaused()
    {
        batcher.beginBatch(Assets.BlackFont);

           Assets.blackfont.drawText(batcher, "score: " + scoreString, 200, 1080 - 100, 20, 20);

        batcher.endBatch();

        batcher.beginBatch(Assets.items);

	       batcher.drawSprite(1920 / 2, 1080 / 2, 192, 96, Assets.pauseMenu);
	       //Assets.font.drawText(batcher, scoreString, 200, 1080 - 100, 20, 20);

        batcher.endBatch();
	}
	
	private void presentLevelEnd()
    {
	    String topText = "the princess is ...";
	    String bottomText = "in another castle!";
	    float topWidth = Assets.font.glyphWidth * topText.length();
	    float bottomWidth = Assets.font.glyphWidth * bottomText.length();

        batcher.beginBatch(Assets.items);
	    //Assets.font.drawText(batcher, topText, 160 - topWidth / 2, 480 - 40);
	    //Assets.font.drawText(batcher, bottomText, 160 - bottomWidth / 2, 40);
        batcher.endBatch();
	}
	
	private void presentGameOver()
    {
		batcher.beginBatch(Assets.items);

		   batcher.drawSprite(1920 / 2, 1080 / 2, 160, 96, Assets.gameOver);

		batcher.endBatch();

		float scoreWidth = Assets.blackfont.glyphWidth * scoreString.length();

		batcher.beginBatch(Assets.BlackFont);

		   Assets.blackfont.drawText(batcher, "Score: " + scoreString, 200 + scoreWidth, 1080 - 100, 20, 20);

		batcher.endBatch();
	}

    @Override
    public void pause()
    {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() {        
    }

    @Override
    public void dispose() {       
    }
}
