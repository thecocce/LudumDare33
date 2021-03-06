package lando.systems.ld33.utils;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import lando.systems.ld33.LudumDare33;

import java.util.HashMap;

/**
 * Author: Ian McNamara <ian.mcnamara@wisc.edu>
 * Teaching and Research Application Development
 * Copyright 2015 Board of Regents of the University of Wisconsin System
 */
public class SoundManager{

    public enum SoundOptions {
        COIN_GET,
        GLASS_JAR_BREAK,
        GOOMBA_2ND_CHANCE,
        GOOMBA_JUMP,
        GOOMBA_MUSHROOM_GET,
        GOOMBA_SQUASH,
        MARIO_DEATH,
        MARIO_JUMP,
        MARIO_RECLAIMATION,
        MUSHROOM_GET,
        MUSHROOM_REVEAL,
        PIPE_TRAVEL,
        SPIKE_STAB
    }
    public enum MusicOptions {
        DNUORGREDNU,
        MARIO_MAJOR,
        MARIO_MAJOR_BK,
        MARIO_MINOR,
        METRIOD_BK,
        ZELDA_BK
    }
    private enum MusicPieces {
        DNUORGREDNU,
        MARIO_MAJOR_INTRO,
        MARIO_MAJOR_LOOP,
        MARIO_MAJOR_LOOP_BK,
        MARIO_MINOR_INTRO,
        MARIO_MINOR_LOOP,
        METROID_LOOP_BK,
        ZELDA_MYSTERIOUS_LOOP_BK
    }

    private HashMap<SoundOptions, Sound> soundMap = new HashMap<SoundOptions, Sound>();
    private HashMap<MusicPieces, Sound> musicMap = new HashMap<MusicPieces, Sound>();

    // -----------------------------------------------------------------------------------------------------------------

    public SoundManager() {
        load();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void load() {
        soundMap.put(SoundOptions.COIN_GET, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/coin-get.mp3")));
        soundMap.put(SoundOptions.GLASS_JAR_BREAK, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/glass-jar-break.mp3")));
        soundMap.put(SoundOptions.GOOMBA_2ND_CHANCE, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/goomba-2nd-chance.mp3")));
        soundMap.put(SoundOptions.GOOMBA_JUMP, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/goomba-jump.mp3")));
        soundMap.put(SoundOptions.GOOMBA_MUSHROOM_GET, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/goomba-mushroom-get.mp3")));
        soundMap.put(SoundOptions.GOOMBA_SQUASH, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/goomba-squash.mp3")));
        soundMap.put(SoundOptions.MARIO_DEATH, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/mario-death.mp3")));
        soundMap.put(SoundOptions.MARIO_JUMP, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/mario-jump.mp3")));
        soundMap.put(SoundOptions.MARIO_RECLAIMATION, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/mario-reclaimation.mp3")));
        soundMap.put(SoundOptions.MUSHROOM_GET, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/mushroom-get.mp3")));
        soundMap.put(SoundOptions.MUSHROOM_REVEAL, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/mushroom-reveal.mp3")));
        soundMap.put(SoundOptions.PIPE_TRAVEL, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/pipe-travel.mp3")));
        soundMap.put(SoundOptions.SPIKE_STAB, Gdx.audio.newSound(Gdx.files.internal("sounds/effects/spike-stab.mp3")));

        musicMap.put(MusicPieces.DNUORGREDNU, Gdx.audio.newSound(Gdx.files.internal("sounds/music/dnuorgrednu.mp3")));
        musicMap.put(MusicPieces.MARIO_MAJOR_INTRO, Gdx.audio.newSound(Gdx.files.internal("sounds/music/mario-major-intro.mp3")));
        musicMap.put(MusicPieces.MARIO_MAJOR_LOOP, Gdx.audio.newSound(Gdx.files.internal("sounds/music/mario-major-loop.mp3")));
        musicMap.put(MusicPieces.MARIO_MAJOR_LOOP_BK, Gdx.audio.newSound(Gdx.files.internal("sounds/music/mario-major-backwards.mp3")));
        musicMap.put(MusicPieces.MARIO_MINOR_INTRO, Gdx.audio.newSound(Gdx.files.internal("sounds/music/mario-minor-intro.mp3")));
        musicMap.put(MusicPieces.MARIO_MINOR_LOOP, Gdx.audio.newSound(Gdx.files.internal("sounds/music/mario-minor-loop.mp3")));
        musicMap.put(MusicPieces.METROID_LOOP_BK, Gdx.audio.newSound(Gdx.files.internal("sounds/music/metriod-tourian-loop-bk.mp3")));
        musicMap.put(MusicPieces.ZELDA_MYSTERIOUS_LOOP_BK, Gdx.audio.newSound(Gdx.files.internal("sounds/music/zelda-mysterious-loop-backwards.mp3")));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void dispose() {
        SoundOptions[] allSounds = SoundOptions.values();
        for (SoundOptions allSound : allSounds) {
            soundMap.get(allSound).dispose();
        }
        MusicPieces[] allMusicPieces = MusicPieces.values();
        for (MusicPieces musicPiece : allMusicPieces) {
            musicMap.get(musicPiece).dispose();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void playSound(SoundOptions soundOption) {
        //Gdx.app.log("DEBUG", "SoundManager.playSound | soundOption='" + String.valueOf(soundOption) + "'");
        soundMap.get(soundOption).play();
    }

    public void playSound3D(SoundOptions soundOption, Vector2 pos){
        //Gdx.app.log("DEBUG", "SoundManager.playSound3d | soundOption='" + String.valueOf(soundOption) + "'");
        float maxSoundDist = 20;
        float v = MathUtils.clamp(1 - (pos.len()/maxSoundDist), 0, 1);
        float pitch = 1;
        float pan = MathUtils.clamp((pos.x * 2) /maxSoundDist, -1, 1);
        soundMap.get(soundOption).play(v, pitch, pan);

    }

    private MusicOptions currentOption;
    private long currentLoopID;
    private Sound currentLoopSound;

    public void setMusicVolume(float level){
        if (currentLoopSound != null){
            currentLoopSound.setVolume(currentLoopID, level);
        }
    }

    public void playMusic(MusicOptions musicOption) {

//        Gdx.app.log("DEBUG", "SoundManager.playMusic | musicOption='" + String.valueOf(musicOption) + "'");

        currentOption = musicOption;
        // Kill any currently play loop
        if (currentLoopSound != null) {
            currentLoopSound.stop(currentLoopID);
        }

        switch (musicOption) {

            case DNUORGREDNU:
                currentLoopSound = musicMap.get(MusicPieces.DNUORGREDNU);
                currentLoopID = currentLoopSound.loop();
                break;

            case MARIO_MAJOR:
                musicMap.get(MusicPieces.MARIO_MAJOR_INTRO).play();
                Tween.call(new TweenCallback() {
                    @Override
                    public void onEvent(int i, BaseTween<?> baseTween) {
                        // Are we still in this case?
                        if (currentOption == MusicOptions.MARIO_MAJOR) {
                            currentLoopID = musicMap.get(MusicPieces.MARIO_MAJOR_LOOP).loop();
                            currentLoopSound = musicMap.get(MusicPieces.MARIO_MAJOR_LOOP);
                        }
                    }
                })
                        .delay(2.6f)
                        .start(LudumDare33.tween);
                break;

            case MARIO_MAJOR_BK:
                currentLoopSound = musicMap.get(MusicPieces.MARIO_MAJOR_LOOP_BK);
                currentLoopID = currentLoopSound.loop();
                break;

            case MARIO_MINOR:
                musicMap.get(MusicPieces.MARIO_MINOR_INTRO).play();
                Tween.call(new TweenCallback() {
                    @Override
                    public void onEvent(int i, BaseTween<?> baseTween) {
                        // Are we still in this case?
                        if (currentOption == MusicOptions.MARIO_MINOR) {
                            currentLoopID = musicMap.get(MusicPieces.MARIO_MINOR_LOOP).loop();
                            currentLoopSound = musicMap.get(MusicPieces.MARIO_MINOR_LOOP);
                        }
                    }
                })
                        .delay(3.2f)
                        .start(LudumDare33.tween);
                break;

            case METRIOD_BK:
                currentLoopSound = musicMap.get(MusicPieces.METROID_LOOP_BK);
                currentLoopID = currentLoopSound.loop();
                break;

            case ZELDA_BK:
                currentLoopSound = musicMap.get(MusicPieces.ZELDA_MYSTERIOUS_LOOP_BK);
                currentLoopID = currentLoopSound.loop();
                break;

            default:
//                Gdx.app.log("ERROR", "SoundManager.playMusic | Unrecognized music option.");

        }
    }

}
