package jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends LevelScene {
    // game objects
    // entities, components, systems as a scene comes on, someone has to manage
    // each Scene.
    private boolean changingScene = false;

    // how long it takes to change scene
    private float timeToChangeScene = 3.0f;
    public LevelEditorScene() {

    }
    @Override
    public void update(float dt) {

        System.out.println(1.0f / dt  + "fps");

        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }

        // if keep decreasing.
        if (changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            // decrease that puppy.
            Window.get().decreaseColors(dt*2.0f, dt*2.0f, dt*2.0f, dt*2.0f);

        } else if (changingScene) {
            Window.changeScene(1);
//            this causes the currentscene to have no update function
            // this wont fire.
            // change to level editor
        }
    }
}

