package lando.systems.ld33.entities.items;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;
import lando.systems.ld33.LudumDare33;
import lando.systems.ld33.World;
import lando.systems.ld33.accessors.RectangleAccessor;
import lando.systems.ld33.utils.Assets;

/**
 * Brian Ploeckelman created on 8/22/2015.
 */
public class FireflowerItem extends ItemEntity {


    public FireflowerItem(World w, float px, float py) {
        super(w, px, py);
        type = ItemType.FIREFLOWER;
        walkingAnimation = smashedAnimation = jumpingAnimation = standingAnimation  = Assets.fireFlowerAnimation;
        stateTime = 0f;

        Tween.to(bounds, RectangleAccessor.Y, 0.5f)
                .target(py + 1f)
                .ease(Linear.INOUT)
                .start(LudumDare33.tween);
    }

    @Override
    public void update(float delta) {
    }

}
