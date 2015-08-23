package lando.systems.ld33.utils;

import com.badlogic.gdx.utils.Array;
import lando.systems.ld33.LudumDare33;
import lando.systems.ld33.World;
import lando.systems.ld33.screens.LDScreen;

/**
 * Brian Ploeckelman created on 8/22/2015.
 */
public class StoryManager {

    final LudumDare33 game;

    Integer            currentChapter;
    World.Phase        currentPhase;
    LDScreen           currentScreen;
    int                storyIndex;
    Array<StoryObject> storySequence;

    public StoryManager(LudumDare33 game) {
        this.game = game;

        initializeStorySequence();
    }

    private void initializeStorySequence() {
        storySequence = new Array<StoryObject>();

        storySequence.add(new StoryObject(this, 1, null));
        storySequence.add(new StoryObject(this, null, World.Phase.DAY_ONE));
        storySequence.add(new StoryObject(this, null, World.Phase.HEADING_HOME));
        storySequence.add(new StoryObject(this, null, World.Phase.MEET_THE_WIFE));
        storySequence.add(new StoryObject(this, null, World.Phase.LEAVING_HOME));
        storySequence.add(new StoryObject(this, null, World.Phase.DAY_ONE));
        storySequence.add(new StoryObject(this, null, World.Phase.HEADING_HOME));
        storySequence.add(new StoryObject(this, null, World.Phase.EMPTY_HOUSE));
        storySequence.add(new StoryObject(this, null, World.Phase.LEAVING_HOME));
        storySequence.add(new StoryObject(this, 2, null));
        storySequence.add(new StoryObject(this, null, World.Phase.DAY_ONE));
        storySequence.add(new StoryObject(this, null, World.Phase.HEADING_HOME));

        storyIndex = 0;
        storySequence.get(storyIndex).doTransition();
    }

    public void update(float delta) {
        if (!currentScreen.isDone()) return;
        if (storyIndex + 1 >= storySequence.size) {
            storyIndex = -1;
        }
        storySequence.get(++storyIndex).doTransition();
    }

}
