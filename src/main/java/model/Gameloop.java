package model;

public class Gameloop {
    private BlockyGame game; // TODO: Document game loop responsibilities
    private BlockRandomizer blockRandomizer; // TODO: Implement and use block randomizer
    private ScoringSystem scoringSystem; // TODO: Implement and use scoring system

    public Gameloop(BlockyGame game, ScoringSystem scoringSystem) {
        this.game = game;
        this.scoringSystem = scoringSystem;
    }

    public void tick() {
        game.tick(); // TODO: Implement tick logic (advance game state)
        if (game.isGameOver()) {
            // TODO: Handle game over (stop loop, show message, etc.)
        }
    }

}

