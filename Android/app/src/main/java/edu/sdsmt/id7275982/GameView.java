/*************************************************************************//**
 * @file GameView.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the view which displays the rooms and movement of the shape.
 *
 *****************************************************************************/

package edu.sdsmt.id7275982;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.os.SystemClock;
import android.view.ViewTreeObserver;

public class GameView extends View {
    private MapState map;
    private Paint paint;
    private long lastTime = 0;
    private float x;
    private float y;
    private float xpct = -1;
    private float touchx;
    private int width;
    private int height;
    private boolean right = false;
    private boolean left = false;
    private boolean hasStopped = true;
    private MainActivity activity;
    private int shape = 1;

    /*********************************************************************//**
     * @name GameView
     * @par Description:
     * Constructor for whenever there is only Context
     *
     * param[in] context - the context
     *
     * @returns nothing
     * *************************************************************************/
    public GameView(Context context) {
        super(context);
        init(context);
    }

    /*********************************************************************//**
     * @name GameView
     * @par Description:
     * Constructor for whenever there is Context and AttributeSet
     *
     * param[in] context - the context
     * param[in] attrs - the attribute set
     *
     * @returns nothing
     * *************************************************************************/
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    /*********************************************************************//**
     * @name GameView
     * @par Description:
     * Constructor for whenever there is Context, AttributeSet and Style
     *
     * param[in] context - the context
     * param[in] attrs - the attribute set
     * param[in] defStyleAttr - the style
     *
     * @returns nothing
     * *************************************************************************/
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /*********************************************************************//**
     * @name getDirection
     * @par Description:
     * Gives the direction the box is moving, represented by an integer, where
     * 0 is not moving, left is 1, and right is 2
     *
     * @returns the direction as an int
     * *************************************************************************/
    public int getDirection(){
        if(!left && !right)
            return 0;
        else if(left)
            return 1;
        else
            return 2;
    }

    /*********************************************************************//**
     * @name getRoom
     * @par Description:
     * gets the room the state machine is currently in as an integer,
     *
     *
     * @returns the room as an int.
     * *************************************************************************/
    public int getRoom(){
        return map.getRoom();
    }

    /*********************************************************************//**
     * @name GameView
     * @par Description:
     * gets the current selected shape as an int, where 1 is square, 2 is circle
     *
     *
     * @returns shape as an int
     * *************************************************************************/
    public int getShape(){
        return shape;
    }

    /*********************************************************************//**
     * @name getStopped
     * @par Description:
     * indicates whether or not the shape has stopped on the screen at all
     *
     * @returns true if has stopped, false if has stopped
     * *************************************************************************/
    public boolean getStopped(){
        return hasStopped;
    }

    /*********************************************************************//**
     * @name getVisited
     * @par Description:
     * Indicates whether or not the shape has visited the blue room
     *
     * @returns True if has visited, false if hasn't
     * *************************************************************************/
    public boolean getVisited(){
        return map.getVisited();
    }

    /*********************************************************************//**
     * @name getX
     * @par Description:
     * gets the x location of the shape as a percentage of the width
     *
     * @returns X percent coordinate as a float
     * *************************************************************************/
    public float getXpercent(){ return x / width;}

    /*********************************************************************//**
     * @name init
     * @par Description:
     * Function called from constructors to set up the view. Initializes variables
     * and then waits for the view to be created on the screen. Once the information
     * is populated, will set the width and height of the object to be half of the
     * view area.
     *
     * param[in] context - the context
     *
     * @returns nothing
     * *************************************************************************/
    public void init(Context context){
        map = new MapState();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(map.getColor());

        ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        final GameView view = this;
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    width = view.getWidth();
                    height = view.getHeight();
                    //if came from another screen, set percent of width
                    if(xpct == -1)
                        x = width / 2;
                    else{
                        x = xpct * width;
                    }
                    y = height / 2;
                }
            });
        }
    }

    /*********************************************************************//**
     * @name onDraw
     * @par Description:
     * Function to handle drawing the view. If either the variable left or right
     * are set, the object will be moved a certain distance in the corresponding
     * direction. When moving to a new "room" a variable hasStopped will be set
     * to false, which will allow the shape to keep moving, but once it hits the
     * center it will set left and right to false and hasStopped to true, meaning
     * that it will not stop again. Additionally, moving to a new room will also
     * change the color of the background according to the color in the state
     * machine. This state machine will be updated upon hitting the right or left
     * edge.
     *
     * param[in] canvas - the canvas to draw on
     *
     * @returns nothing
     * *************************************************************************/
    public void onDraw(Canvas canvas){
        float size = width * 0.10f;
        if(!hasStopped && x < (width/2 + 10) && x > (width/2 - 10))
        {
            setCenter();
            hasStopped = true;
        }

        if(x > width) {
            x = 0;
            map.rightEdge();
            lastTime = 0;
            if(map.gameWon()){
                activity.gameOver();
                setCenter();
            }
            hasStopped = false;
        }

        if(x < 0){
            x = width;
            map.leftEdge();
            lastTime = 0;
            hasStopped = false;
        }

        paint.setColor(map.getColor());
        canvas.drawRect(0, 0, width, height, paint);
        paint.setColor(0xff000000);
        if(shape == 1)
            canvas.drawRect(x - (size/2), y - (size/2), x + (size/2), y + (size/2), paint);
        if(shape == 2)
            canvas.drawCircle(x, y, size/2, paint);
        long time = SystemClock.uptimeMillis();
        if (lastTime == 0) {
            lastTime = time;
        }
        float delta = (time - lastTime) * 0.001f;
        lastTime = time;
        if(right) {
            x += (width / 2) * delta;
        }
        if(left) {
            x -= (width / 2) * delta;
        }
        this.invalidate();
    }

    /*********************************************************************//**
     * @name onTouchEvent
     * @par Description:
     * function called when the user touches the screen. When first pressed, will
     * save the location of the x coordinate. When released, if the x is greater
     * than the original x, will trigger right movement and disable left movement,
     * if the x is less than the original, will trigger left movement and disable
     * right movement.
     *
     * param[in] event- the touch event
     *
     * @returns result of the superclass touch event (True / False)
     * *************************************************************************/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                touchx = event.getX();
                return true;
            case MotionEvent.ACTION_UP:
                if(event.getX() > touchx) {
                    right = true;
                    left = false;
                }
                else {
                    left = true;
                    right = false;
                }
                this.invalidate();

        }
        performClick();
        return super.onTouchEvent(event);
    }

    /*********************************************************************//**
     * @name performClick
     * @par Description:
     * calls the superclass performClick
     *
     * @returns true
     * *************************************************************************/
    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    /*********************************************************************//**
     * @name reset
     * @par Description:
     * Resets the view and state machine back to default values.
     *
     * @returns nothing
     * *************************************************************************/
    public void reset(){
        left = false;
        right = false;
        hasStopped = true;
        x = width / 2;
        map.reset();
    }

    /*********************************************************************//**
     * @name setActivity
     * @par Description:
     * links activity variable to the activity which holds the view
     *
     * param[in] act - the activity holding the view
     *
     * @returns nothing
     * *************************************************************************/
    public void setActivity(MainActivity act){
        activity = act;
    }

    /*********************************************************************//**
     * @name setCenter
     * @par Description:
     * resets the shape to the center, and disables moving
     *
     * @returns nothing
     * *************************************************************************/
    public void setCenter(){
        x = width/2;
        left = false;
        right = false;
    }

    /*********************************************************************//**
     * @name setDirection
     * @par Description:
     * sets the object to move in a specified direction where 1 is left and 2
     * is right.
     *
     * param[in] dir - direction to move as an int
     *
     * @returns nothing
     * *************************************************************************/
    public void setDirection(int dir){
        if(dir == 1)
            left = true;
        if(dir == 2)
            right = true;
    }

    /*********************************************************************//**
     * @name setHasStopped
     * @par Description:
     * sets whether or not the shape has stopped in the current room yet.
     *
     * param[in] set - boolean of if it has stopped
     *
     * @returns nothing
     * *************************************************************************/
    public void setHasStopped(boolean set){
        hasStopped = set;
    }

    /*********************************************************************//**
     * @name setRoom
     * @par Description:
     * Sets which room the shape is currently in
     *
     * param[in] room - which room to set to, specified by an int
     *
     * @returns nothing
     * *************************************************************************/
    public void setRoom(int room){
        map.setRoom(room);
    }

    /*********************************************************************//**
     * @name setShape
     * @par Description:
     * sets which shape to be drawn, 1 is square, 2 is circle
     *
     * param[in] shp - the shape to set as an int
     *
     * @returns nothing
     * *************************************************************************/
    public void setShape(int shp){
        shape = shp;
    }

    /*********************************************************************//**
     * @name setVisited
     * @par Description:
     * sets the view to specify that the blue room has been visited
     *
     * @returns nothing
     * *************************************************************************/
    public void setVisited(){
        map.setVisited();
    }

    /*********************************************************************//**
     * @name setXpercent
     * @par Description:
     * Saves the percent of the screen for the initial x value to take, will
     * put the shape at that percent of the view on first draw.
     *
     * param[in] temppct - percent X location of the shape
     *
     * @returns nothing
     * *************************************************************************/
    public void setXpercent(float temppct){
        xpct = temppct;
        this.invalidate();
    }
}
