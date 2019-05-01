/*************************************************************************//**
 * @file Main.java
 *
 * @section M001
 *
 * @author Jeff Ross
 *
 * @date 4/22/2019
 *
 * @par Course:
 *         CSC 468
 *
 * @par Language Version: Java 8
 *                        Android API 21
 *
 * @details
 * This program is a simple choose your own adventure game consisting of three possible
 * rooms. Swiping left or right will move the shape in the corresponding direciton. upon
 * reaching the edge it will move to a new room. The only win condition is to have visited
 * the blue room, and exit the right side of the red room. Your score is based on the number
 * of seconds it has taken to reach the end condition. The game will remain the same and
 * continue working after being turned sideways. The player can choose between two shapes
 * of a square or a circle to move left and right. Upon reaching the end  of the game the
 * player will be shown their score and prompted to play again, resetting the game, but not
 * their chosen shape.
 *
 *
 * Extensions:
 *      1h: This is implemented such that it appears to come in as if the tiles
 *          were connected, in example, if the shape were to exit on the left of
 *          one tile, it would enter on the right of the next tile. For the blue
 *          and red squares, it acts as though it keeps going further into the
 *          blue or red, meaning it will exit on one side, and enter on the opposite
 *          side. For the end condition, no animation will play and it will reset
 *          to the center of a gray square before displaying the end screen, this
 *          is intentional. Once entering a square, the shape will continue until it
 *          hits the center of the area, where it will stop. The shape will only stop
 *          one per square, even if the direction is changed using the other extension,
 *          and will continue moving through the center thereafter. This extension can be
 *          tested by making sure all of the conditions as stated above hold true and
 *          act as expected.
 *
 *
 *      2e: This has been implemented such that you can change the direction of the
 *          shape at any time. The main testing to do for this is to make sure it
 *          can switch from left to right, and from right to left.

 *****************************************************************************/

package edu.sdsmt.id7275982;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private Timer timer;

    /*********************************************************************//**
     * @name gameOver
     * @par Description:
     * function to be called when the end state is reached. Gives control over to
     * the End Activity, passing the score.
     *
     * @returns nothing
     * *************************************************************************/
    public void gameOver(){
        Intent intent = new Intent(this, EndActivity.class);
        String message = "Score: " + Integer.toString(score);
        intent.putExtra("edu.sdsmt.id7275982.message", message);
        startActivityForResult(intent,2);

    }

    /*********************************************************************//**
     * @name onActivityResult
     * @par Description:
     * Function to be called when returning back from another activity.
     *
     * param[in] savedInstanceState - holds the saved variables
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            int shape = data.getIntExtra("shape",0); //many type options
            GameView gview = findViewById(R.id.view);
            gview.setShape(shape);
        }
        if(requestCode == 2){
            GameView gview = findViewById(R.id.view);
            gview.reset();
            score = 0;
        }
    }

    /*********************************************************************//**
     * @name onCreate
     * @par Description:
     * Overrided function called when the activity is created. Sets to main
     * activity xml, and assigns itself to the view inside that activity and
     * sets up the button and timer.
     *
     * param[in] savedInstanceState - used to call onCreate in superclass
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameView gview = findViewById(R.id.view);
        TextView setName = findViewById(R.id.name);
        setName.setText(R.string.ross);
        TextView setScore = findViewById(R.id.score);
        setScore.setText(R.string.score);
        Button setBtn = findViewById(R.id.select);
        setBtn.setText(R.string.choose_player);
        gview.setActivity(this);
        setTimer();
        setButton();
    }

    /*********************************************************************//**
     * @name onRestoreInstanceState
     * @par Description:
     * function called when returning from reloading. Takes all the
     * variables assigned and resets them to how they were before leaving.
     *
     * param[in] savedInstanceState - holds the saved variables
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        score = savedInstanceState.getInt("score");
        super.onRestoreInstanceState(savedInstanceState);
        GameView gview = findViewById(R.id.view);
        gview.setRoom(savedInstanceState.getInt("room"));
        gview.setXpercent(savedInstanceState.getFloat("x"));
        gview.setDirection(savedInstanceState.getInt("direction"));
        gview.setShape(savedInstanceState.getInt("shape"));
        gview.setHasStopped(savedInstanceState.getBoolean("stopped"));
        if(savedInstanceState.getBoolean("visited")){
            gview.setVisited();
        }
    }

    /*********************************************************************//**
     * @name onSaveInstanceState
     * @par Description:
     * Function that is called before reloading. Saves all the
     * information needed into the savedInstanceState so that what is currently
     * being displayed on the screen can be recreated once returned.
     *
     * param[in] savedInstanceState - what is used to save information before
     *                                sending off.
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        GameView gview = findViewById(R.id.view);
        savedInstanceState.putBoolean("visited", gview.getVisited());
        savedInstanceState.putInt("room", gview.getRoom());
        savedInstanceState.putInt("score", score);
        savedInstanceState.putFloat("x", gview.getXpercent());
        savedInstanceState.putInt("shape", gview.getShape());
        savedInstanceState.putInt("direction", gview.getDirection());
        savedInstanceState.putBoolean("stopped", gview.getStopped());
    }

    /*********************************************************************//**
     * @name setButton
     * @par Description:
     * sets up the "choose player" button, such that when it is clicked, will
     * launch the player select activity.
     *
     * @returns nothing
     * *************************************************************************/
    private void setButton(){
        Button button = (Button) findViewById(R.id.select);
        final MainActivity act = this;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(act, PlayerSelectActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    /*********************************************************************//**
     * @name setTimer
     * @par Description:
     * sets the timer to increment the score. Increments score by 1 every
     * second.
     *
     * @returns nothing
     * *************************************************************************/
    private void setTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        score++;
                        TextView text = findViewById(R.id.score);
                        text.setText(getString(R.string.score_filled, score));
                    }
                });

            }
        },0, 1000);
    }

}
