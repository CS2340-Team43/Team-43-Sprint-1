package com.example.demo_2340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_2340.CollisionObserver.CollisionManager;
import com.example.demo_2340.DecoratorPowerUp.HealthPowerUp;
import com.example.demo_2340.DecoratorPowerUp.PowerUp;
import com.example.demo_2340.DecoratorPowerUp.SlowPowerUp;
import com.example.demo_2340.DecoratorPowerUp.SpeedPowerUp;
import com.example.demo_2340.Enemies_Implementation.Enemies;
import com.example.demo_2340.Enemies_Implementation.EnemiesFactory;
import com.example.demo_2340.Player_Movement.MoveDown;
import com.example.demo_2340.Player_Movement.MoveLeft;
import com.example.demo_2340.Player_Movement.MoveRight;
import com.example.demo_2340.Player_Movement.MoveUp;
import com.example.demo_2340.Player_Movement.MovementStrategyPattern;
public class GameScreen2 extends AppCompatActivity {

    private Player player;
    private Enemies spriteEnemy; // Update the type to Enemies
    private Enemies heavyEnemy;
    private ImageView playerImageView;
    private ImageView enemyImageView1;
    private ImageView enemyImageView2;
    private ImageView healthPowerUpImageView;
    private ImageView speedPowerUpImageView;
    private ImageView attackCooldownPowerUpImageView;
    private boolean gameOverFlag = false; // Add this flag
    private boolean moveButtonPressed = false;
    // flags to check which enemy attacked
    private boolean heavyHit = false;
    private boolean spriteHit = false;
    private final Handler clockHandler = new Handler(Looper.myLooper()); //Activity Loop for screen

    private boolean isAttackOnCooldown = false; // Flag to check if the attack is on cooldown
    private boolean isAttackActive = false; // Flag to check if the attack is currently active
    //Activity Loop for screen
    private final Handler attackCooldownHandler = new Handler(Looper.myLooper());

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Move the player to its initial position
            movePlayer(0, 0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen2);

        // Get active elements
        playerImageView = findViewById(R.id.playerImageView);
        enemyImageView1 = findViewById(R.id.enemyImageView1);
        enemyImageView2 = findViewById(R.id.enemyImageView2);

        healthPowerUpImageView = findViewById(R.id.healthPowerUpImageView);
        speedPowerUpImageView = findViewById(R.id.speedPowerUpImageView);
        attackCooldownPowerUpImageView = findViewById(R.id.attackCooldownPowerUpImageView);

        // Create Enemies
        spriteEnemy = EnemiesFactory.buildEnemies("Sprite");
        heavyEnemy = EnemiesFactory.buildEnemies("Heavy");
        createEnemies();

        // Create Player
        player = Player.getInstance();
        player.setHealth(100);
        createPlayer();

        inheritProperties();
        setdPADController();
        ScoreTimer.start();
        createExit();
        startClockLoop();

        // Move the player after creating it
        movePlayer(1, 1);
    }

    private void startClockLoop() {
        // task to be executed by the clock loop
        Runnable clockRunnable = new Runnable() {
            @Override
            public void run() {
                if (!gameOverFlag) {
                    // Update the score
                    updateScore();
                    int amount =  5;
                    // Move enemies
                    moveEnemySprite(4 * amount, 3 * amount);
                    moveEnemyHeavy();
                    checkGameOver();

                    // Schedule the next execution of the clock loop
                    clockHandler.postDelayed(this, 100);
                } // Adjust the delay as needed
            }
        };

        // Start the clock loop
        clockHandler.post(clockRunnable);
    }

    private void updateScore() {
        // Update the score TextView
        TextView livescoreTextView = findViewById(R.id.livescoreTextView);
        // if heavy or sprite attack game score is affected accordingly
        if (heavyHit) {
            double s = ScoreTimer.getInterval() - 10;
            livescoreTextView.setText("Score: " + s);
        }
        if (spriteHit) {
            double s = ScoreTimer.getInterval() - 5;
            livescoreTextView.setText("Score: " + s);
        }
        livescoreTextView.setText("Score: " + ScoreTimer.getInterval());

        // Update the health TextView
        ProgressBar playerHealthProgressBar = findViewById(R.id.playerHealthProgressBar);
        playerHealthProgressBar.setProgress(player.getHealth());
    }

    private boolean handleTouch(MotionEvent event, double deltaX, double deltaY) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            moveButtonPressed = true;
            movePlayer(deltaX * player.getSpeed(), deltaY * player.getSpeed());
        } else if (action == MotionEvent.ACTION_UP) {
            moveButtonPressed = false;
        }
        return true;
    }
    private void movePlayer(double deltaX, double deltaY) {
        double newX = player.getxPosition() + deltaX;
        double newY = player.getyPosition() + deltaY;

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (newX >= 0 && newX <= rootView.getWidth() - playerImageView.getWidth()) {
            player.setxPosition(newX);
            playerImageView.setX((float) newX);
        }
        FrameLayout fr = findViewById(R.id.playerInfoView);
        if (newY >= fr.getHeight() && newY <= rootView.getHeight() - playerImageView.getHeight()) {
            player.setyPosition(newY);
            playerImageView.setY((float) newY);
        }

        RelativeLayout nextScreenLayout = findViewById(R.id.nextScreenLayout);
        if (CollisionManager.isViewOverlapping(playerImageView, nextScreenLayout)) {
            moveToNextScreen();
        }

        rootView.invalidate();
    }
    private void moveEnemySprite(double deltaX, double deltaY) {
        FrameLayout fr = findViewById(R.id.playerInfoView);

        double newX = spriteEnemy.getxPosition();
        double newY = spriteEnemy.getyPosition();

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (newX > rootView.getWidth() - enemyImageView1.getWidth() || newX < 0) {
            deltaX *= -1; // Reverse the x-axis movement direction
        }

        if (newY > rootView.getHeight() - enemyImageView1.getHeight() || newY < 0) {
            deltaY *= -1; // Reverse the y-axis movement direction
        }

        newX = spriteEnemy.getxPosition() - deltaX;
        newY = spriteEnemy.getyPosition() + deltaY;

        // Update the sprite's position
        spriteEnemy.setxPosition(newX);
        spriteEnemy.setyPosition(newY);

        // Update the view's position
        enemyImageView1.setX((float) newX);
        enemyImageView1.setY((float) newY);

        checkCollisions();
        /**
         if (newX >= 0 && newX <= rootView.getWidth() - enemyImageView1.getWidth()) {
         enemyImageView1.setX((float) newX);
         spriteEnemy.setxPosition(newX);
         }

         if (newY > fr.getHeight() && newY <= rootView.getHeight() - enemyImageView1.getHeight()) {
         enemyImageView1.setY((float) newY);
         spriteEnemy.setyPosition(newY);
         }
         **/
    }


    private void moveEnemyHeavy() {
        double newX = heavyEnemy.move();
        double newY = heavyEnemy.getyPosition(); // No need to move in the y-direction

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (newX >= 0 && newX <= rootView.getWidth() - enemyImageView2.getWidth()) {
            enemyImageView2.setX((float) newX);
            heavyEnemy.setxPosition(newX);
        }

        if (newY >= 0 && newY <= rootView.getHeight() - enemyImageView2.getHeight()) {
            enemyImageView2.setY((float) newY);
            heavyEnemy.setyPosition(newY);
        }
        checkCollisions();
    }

    private void setdPADController() {
        Button buttonUp = findViewById(R.id.buttonUp);
        Button buttonDown = findViewById(R.id.buttonDown);
        Button buttonLeft = findViewById(R.id.buttonLeft);
        Button buttonRight = findViewById(R.id.buttonRight);
        int amount = 40; // how much to move character
        MovementStrategyPattern up = new MoveUp();
        buttonUp.setOnTouchListener((v, event) -> handleTouch(event, 0, up.move(amount)));
        MovementStrategyPattern down = new MoveDown();
        buttonDown.setOnTouchListener((v, event) -> handleTouch(event, 0, down.move(amount)));
        MovementStrategyPattern left = new MoveLeft();
        buttonLeft.setOnTouchListener((v, event) -> handleTouch(event, left.move(amount), 0));
        MovementStrategyPattern right = new MoveRight();
        buttonRight.setOnTouchListener((v, event) -> handleTouch(event, right.move(amount), 0));
        Button attackButton = findViewById(R.id.buttonAttack);
        attackButton.setOnClickListener(v -> {
            if (!isAttackOnCooldown && !isAttackActive) {
                startAttack();
            }
        });
    }

    private void startAttack() {
        isAttackActive = true; // Set the flag to indicate that the attack is active

        // Increase player image size for 10 seconds
        playerImageView.setScaleX(3f);
        playerImageView.setScaleY(3f);

        // Start a timer to revert the player image size after 10 seconds
        new Handler().postDelayed(() -> {
            playerImageView.setScaleX(1.0f);
            playerImageView.setScaleY(1.0f);
            // Reset the flag to indicate that the attack is no longer active
            isAttackActive = false;
        }, 10000);

        // Start a cooldown timer for 30 seconds
        startAttackCooldown();
    }

    private void startAttackCooldown() {
        isAttackOnCooldown = true; // Set the flag to indicate that the attack is on cooldown

        // Start a timer to reset the attack cooldown after 30 seconds
        attackCooldownHandler.postDelayed(() -> {
            // Reset the flag to indicate that the attack is no longer on cooldown
            isAttackOnCooldown = false;
        }, 30000);
    }

    private void createPlayer() {
        int initialX = (getResources().getDisplayMetrics().widthPixels
                - playerImageView.getWidth()) / 2;
        int initialY = (getResources().getDisplayMetrics().heightPixels
                - playerImageView.getHeight()) / 2;
        player.setxPosition(initialX);
        player.setyPosition(initialY);
        playerImageView.setX(initialX);
        playerImageView.setY(initialY);
    }

    private void createEnemies() {
        //Sprite
        spriteEnemy.setInitialPosition(900,
                (getResources().getDisplayMetrics().heightPixels
                        - playerImageView.getHeight()) / 3);

        //Heavy1
        heavyEnemy.setInitialPosition(enemyImageView2.getX(),
                (getResources().getDisplayMetrics().heightPixels
                        - playerImageView.getHeight()) / 4);
    }

    private void moveToNextScreen() {
        // Retrieve necessary data
        Intent previousIntent = getIntent();
        String difficulty = previousIntent.getStringExtra("difficulty");
        String playerName = previousIntent.getStringExtra("playerName");
        int liveScore = previousIntent.getIntExtra("livescore", ScoreTimer.getInterval());

        // Start the next activity
        Intent intent = new Intent(GameScreen2.this, GameScreen3.class);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("playerName", playerName);
        intent.putExtra("livescore", liveScore);
        startActivity(intent);
    }

    private void inheritProperties() {
        Intent previousIntent = getIntent();
        String difficulty = previousIntent.getStringExtra("difficulty");
        TextView gameDifficultyTextView = findViewById(R.id.gameDifficultyTextView);
        gameDifficultyTextView.setText("Difficulty: " + difficulty);

        String playerName = previousIntent.getStringExtra("playerName");
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        playerNameTextView.setText(playerName);

        int liveScore = previousIntent.getIntExtra("livescore", ScoreTimer.getInterval());
        TextView livescoreTextView = findViewById(R.id.livescoreTextView);
        livescoreTextView.setText("Score: " + liveScore);
    }

    private void createExit() {
        RelativeLayout nextScreenLayout = findViewById(R.id.nextScreenLayout);
        nextScreenLayout.setOnClickListener(v -> moveToNextScreen());
    }

    private void checkCollisions() {
        // Check collisions and delete enemies if the attack is active
        if (isAttackActive) {
            // Check and handle collisions for attacking enemies
            CollisionManager.checkAttackingCollisions(player, spriteEnemy, heavyEnemy,
                    playerImageView, enemyImageView1, enemyImageView2);
            checkPowerUpCollisions();
        } else {
            // Check collisions as usual
            CollisionManager.checkCollisions(player, spriteEnemy, heavyEnemy,
                    playerImageView, enemyImageView1, enemyImageView2);
            checkPowerUpCollisions();
        }
    }

    private void checkPowerUpCollisions() {
        // Check if player collides with power-ups and apply their effects
        if (CollisionManager.isViewOverlapping(playerImageView, healthPowerUpImageView)) {
            applyPowerUp(new HealthPowerUp());
        } else if (CollisionManager.isViewOverlapping(playerImageView, speedPowerUpImageView)) {
            applyPowerUp(new SpeedPowerUp());
        } else if (CollisionManager.isViewOverlapping(playerImageView,
                attackCooldownPowerUpImageView)) {
            applyPowerUp(new SlowPowerUp());
        }
    }

    private void applyPowerUp(PowerUp powerUp) {
        powerUp.powerUpHero(player);
    }

    private void checkGameOver() {
        if (player.getHealth() <= 0 && !gameOverFlag) {
            gameOverFlag = true; // Set the flag to true to avoid calling
            // the game over screen multiple times
            // Player's health is zero, show game over screen
            showGameOverScreen();
        }
    }

    private void showGameOverScreen() {
        // You can start a new activity for the game over screen
        Intent gameOverIntent = new Intent(GameScreen2.this, GameOverActivity.class);
        startActivity(gameOverIntent);
        clockHandler.removeCallbacksAndMessages(null);
        finish();
    }
}
