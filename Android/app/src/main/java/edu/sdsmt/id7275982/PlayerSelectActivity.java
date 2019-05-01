/*************************************************************************//**
 * @file PlayerSelectActivity.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds the functions to handle the player select activity.
 *
 *****************************************************************************/

package edu.sdsmt.id7275982;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayerSelectActivity extends AppCompatActivity {
    private int shape;

    /*********************************************************************//**
     * @name endFunction
     * @par Description:
     * Function called when a shape has been selected, adds the type of shape to
     * the intent, and returns to the main activity, finishing this activity.
     *
     * @returns nothing
     * *************************************************************************/
    private void endFunction() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("shape" , shape);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    /*********************************************************************//**
     * @name onCreate
     * @par Description:
     * Overrided function called when the activity is created. Sets to player
     * select activity xml, and sets up the square and circle buttons
     *
     * param[in] savedInstanceState - used to call onCreate in super class
     *
     * @returns nothing
     * *************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerselect);
        Button setCircle = findViewById(R.id.circle);
        setCircle.setText(R.string.circle);
        Button setSquare = findViewById(R.id.square);
        setSquare.setText(R.string.square);
        setSquareButton();
        setCircleButton();
    }

    /*********************************************************************//**
     * @name setCircleButton
     * @par Description:
     * Sets up the circle button, such that when clicked will notify the shape
     * is a circle, and exit the activity.
     *
     * @returns nothing
     * *************************************************************************/
    private void setCircleButton(){
        Button button = (Button) findViewById(R.id.circle);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shape = 2;
                endFunction();
            }
        });
    }

    /*********************************************************************//**
     * @name setSquareButton
     * @par Description:
     * Sets up the square button, such that when clicked will notify the shape
     * is a square, and exit the activity.
     *
     * @returns nothing
     * *************************************************************************/
    private void setSquareButton(){
        Button button = (Button) findViewById(R.id.square);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shape = 1;
                endFunction();
            }
        });
    }
}
