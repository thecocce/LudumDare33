package lando.systems.ld33.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import lando.systems.ld33.Config;
import lando.systems.ld33.LudumDare33;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Config.width, Config.height);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new LudumDare33();
        }
}