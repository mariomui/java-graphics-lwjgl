package jade;

public abstract class Scene {
    // game objects
    // entities, components, systems as a scene comes on, someone has to manage
    // each Scene.
    public Scene() {

    }
    public abstract void update(float dt);
}
