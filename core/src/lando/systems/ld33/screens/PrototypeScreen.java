package lando.systems.ld33.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import lando.systems.ld33.Config;
import lando.systems.ld33.LudumDare33;
import lando.systems.ld33.utils.Assets;

/**
 * Brian Ploeckelman created on 8/22/2015.
 */
public class PrototypeScreen extends LDScreen {

    private static final float BLUE_SKY_R        = 107f / 255f;
    private static final float BLUE_SKY_G        = 140f / 255f;
    private static final float BLUE_SKY_B        = 255f / 255f;
    private static final float MAP_UNIT_SCALE    = 1f / 16f;
    private static final int   SCREEN_TILES_WIDE = 20;
    private static final int   SCREEN_TILES_HIGH = 15;

    OrthographicCamera         uiCamera;
    FrameBuffer                sceneFrameBuffer;
    TextureRegion              sceneRegion;
    TiledMap                   testMap;
    TiledMap                   mapLevel1;
    OrthogonalTiledMapRenderer mapRenderer;
    Array<Rectangle>           tileRects;
    Pool<Rectangle>            rectPool;

    public PrototypeScreen(LudumDare33 game) {
        super(game);

        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, Config.width, Config.height);
        sceneFrameBuffer = new FrameBuffer(Format.RGBA8888, Config.width, Config.height, false);
        sceneRegion = new TextureRegion(sceneFrameBuffer.getColorBufferTexture());
        sceneRegion.flip(false, true);

        final TmxMapLoader mapLoader = new TmxMapLoader();
        testMap = mapLoader.load("maps/mario-test.tmx");
        mapLevel1 = mapLoader.load("maps/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(testMap, MAP_UNIT_SCALE);
//        mapRenderer = new OrthogonalTiledMapRenderer(mapLevel1, MAP_UNIT_SCALE);

        // Show a tile portion of the map
        camera.setToOrtho(false, SCREEN_TILES_WIDE, SCREEN_TILES_HIGH);
        camera.update();

        tileRects = new Array<Rectangle>();
        rectPool = Pools.get(Rectangle.class);

        Gdx.gl.glClearColor(BLUE_SKY_R, BLUE_SKY_G, BLUE_SKY_B, 1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        sceneFrameBuffer.begin();
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            mapRenderer.setView(camera);
            mapRenderer.render();

            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            batch.draw(Assets.testTexture, 0, 0, 1, 1);
            batch.end();
        }
        sceneFrameBuffer.end();

        // TODO: add default screen shader
        batch.setShader(null);
        batch.begin();
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.setProjectionMatrix(uiCamera.combined);
            batch.draw(sceneRegion, 0, 0);
            batch.end();
        }
    }

    // ------------------------------------------------------------------------
    // Accessors
    // ------------------------------------------------------------------------



    // ------------------------------------------------------------------------
    // Private Implementation
    // ------------------------------------------------------------------------

    @Override
    protected void enableInput() {
        final InputMultiplexer mux = new InputMultiplexer();
        // TODO: add any other input processors here as needed
        Gdx.input.setInputProcessor(mux);
    }
@Override
    protected void disableInput() {
        Gdx.input.setInputProcessor(null);
    }

}
