package com.example.demo_2340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_2340.CollisionObserver.CollisionManager;
import com.example.demo_2340.Enemies_Implementation.Enemies;
import com.example.demo_2340.Enemies_Implementation.EnemiesFactory;
import com.example.demo_2340.Enemies_Implementation.Heavy1;
import com.example.demo_2340.Enemies_Implementation.Heavy2;
import com.example.demo_2340.Enemies_Implementation.Heavy3;
import com.example.demo_2340.Enemies_Implementation.Sprite;
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
    private boolean gameOverFlag = false; // Add this flag
    private boolean moveButtonPressed = false;
    private final Handler clockHandler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen2);

        //Get active elements
        playerImageView = findViewById(R.id.playerImageView);
        enemyImageView1 = findViewById(R.id.enemyImageView1);
        enemyImageView2 = findViewById(R.id.enemyImageView2);

        spriteEnemy = new Heavy3();
        heavyEnemy = new Heavy2();
        createEnemies();

        player = Player.getInstance();
        createPlayer();

        inheritProperties();
        setDPADController();
        ScoreTimer.start();
        moveEnemySprite();
        moveEnemyHeavy();
        createExit();
        startClockLoop();
    }

    private void startClockLoop() {
        // task to be executed by the clock loop
        Runnable clockRunnable = new Runnable() {
            @Override
            public void run() {
                if (!gameOverFlag) {
                    // Update the score
                    updateScore();

                    // Move enemies
                    moveEnemySprite();
                    moveEnemyHeavy();
                    checkGameOver();

                    // Schedule the next execution of the clock loop
                    clockHandler.postDelayed(this, 100);
                }// Adjust the delay as needed
            }
        };

        // Start the clock loop
        clockHandler.post(clockRunnable);
    }

    private void updateScore() {
        // Update the score TextView
        TextView livescoreTextView = findViewById(R.id.livescoreTextView);
        livescoreTextView.setText("Score: " + ScoreTimer.getInterval());

        // Update the health TextView
        ProgressBar playerHealthProgressBar = findViewById(R.id.playerHealthProgressBar);
        playerHealthProgressBar.setProgress(player.getHealth());
    }

    private boolean handleTouch(MotionEvent event, int deltaX, int deltaY) {
        ImageView playerImageView = findViewById(R.id.playerImageView);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveButtonPressed = true;
                movePlayer(deltaX, deltaY);
                break;
            case MotionEvent.ACTION_UP:
                moveButtonPressed = false;
                break;
        }
        return true;
    }

    private void movePlayer(int deltaX, int deltaY) {
        ImageView playerImageView = findViewById(R.id.playerImageView);
        int newX = player.getxPosition() + deltaX;
        int newY = player.getyPosition() + deltaY;

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (newX >= 0 && newX <= rootView.getWidth() - playerImageView.getWidth()) {
            player.setxPosition(newX);
            playerImageView.setX((float)newX);
        }

        if (newY >= 0 && newY <= rootView.getHeight() - playerImageView.getHeight()) {
            player.setyPosition(newY);
            playerImageView.setY((float)newY);
        }

        RelativeLayout nextScreenLayout = findViewById(R.id.nextScreenLayout);
        if (CollisionManager.isViewOverlapping(playerImageView, nextScreenLayout)) {
            moveToNextScreen();
        }

        rootView.invalidate();
    }
    private void moveEnemySprite() {
        ImageView enemyImageView1 = findViewById(R.id.enemyImageView1);

        double newX = spriteEnemy.move();
        double newY = spriteEnemy.getyPosition(); // No need to move in the y-direction

        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (newX >= 0 && newX <= rootView.getWidth() - enemyImageView1.getWidth()) {
            enemyImageView1.setX((float) newX);
            spriteEnemy.setxPosition(newX);
        }

        if (newY >= 0 && newY <= rootView.getHeight() - enemyImageView1.getHeight()) {
            enemyImageView1.setY((float) newY);
            spriteEnemy.setyPosition(newY);
        }
        checkCollisions();
    }


    private void moveEnemyHeavy() {
        ImageView enemyImageView2 = findViewById(R.id.enemyImageView2);

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

    private void setDPADController() {
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
    }

    private void createPlayer() {
        ImageView playerImageView = findViewById(R.id.playerImageView);
        int initialX = (getResources().getDisplayMetrics().widthPixels - playerImageView.getWidth()) / 2;
        int initialY = (getResources().getDisplayMetrics().heightPixels - playerImageView.getHeight()) / 2;
        player.setxPosition(initialX);
        player.setyPosition(initialY);
        playerImageView.setX(initialX);
        playerImageView.setY(initialY);
    }

    private void createEnemies() {
        //Sprite
        spriteEnemy = new Sprite();
        ImageView enemyImageView1 = findViewById(R.id.enemyImageView1);
        spriteEnemy.setInitialPosition(enemyImageView1.getX(), (getResources().getDisplayMetrics().heightPixels - playerImageView.getHeight()) / 5);

        //Heavy1
        heavyEnemy = new Heavy1();
        ImageView enemyImageView2 = findViewById(R.id.enemyImageView2);
        heavyEnemy.setInitialPosition(enemyImageView2.getX(), (getResources().getDisplayMetrics().heightPixels - playerImageView.getHeight()) / 2);
    }

    private void moveToNextScreen() {
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
        finish();
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
        CollisionManager.checkCollisions(player, spriteEnemy, heavyEnemy, playerImageView, enemyImageView1, enemyImageView2);
    }

    private void checkGameOver() {
        if (player.getHealth() <= 0 && !gameOverFlag) {
            gameOverFlag = true; // Set the flag to true to avoid calling the game over screen multiple times
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
