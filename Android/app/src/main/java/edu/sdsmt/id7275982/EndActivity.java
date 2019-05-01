/*************************************************************************//**
 * @file EndActivity.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds the functions to handle the end game activity.
 *
 *****************************************************************************/

package edu.sdsmt.id7275982;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private String score;

    /*********************************************************************//**
     * @name onCreate
     * @par Description:
     * Overrided function called when the activity is created. Sets to end
     * activity xml, and sets up the button and sets the score.
     *
     * param[in] savedInstanceState - holds the score to be displayed
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        score = intent.getStringExtra("edu.sdsmt.id7275982.message");
        TextView text = findViewById(R.id.endScore);
        text.setText(score);
        Button setBtn = findViewById(R.id.again);
        setBtn.setText(R.string.play_again);
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
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getString("score");
        TextView text = findViewById(R.id.endScore);
        text.setText(score);
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
        savedInstanceState.putString("score", score);
    }

    /*********************************************************************//**
     * @name setButton
     * @par Description:
     * sets up the "play again" button, such that when it is clicked, will
     * finish the current activity and return back to the main activity.
     *
     * @returns nothing
     * *************************************************************************/
    private void setButton(){
        Button button = (Button) findViewById(R.id.again);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}