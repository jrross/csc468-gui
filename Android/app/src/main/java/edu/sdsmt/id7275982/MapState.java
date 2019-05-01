/*************************************************************************//**
 * @file MapState.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds the state machine to take care of the different rooms.
 *
 *****************************************************************************/

package edu.sdsmt.id7275982;

enum State{GREY, RED, BLUE, END}

public class MapState {
    private State currentState;
    private boolean blueVisited = false;

    /*********************************************************************//**
     * @name gameWon
     * @par Description:
     * will tell whether or not the game state is END
     *
     * @returns true if state is end, false otherwise
     * *************************************************************************/
    public boolean gameWon(){
        if(currentState == State.END && blueVisited == true)
            return true;
        else
            return false;
    }

    /*********************************************************************//**
     * @name getColor
     * @par Description:
     * gets the hexadecimal color value for the specified room.
     *
     * @returns color as an int.
     * *************************************************************************/
    //REBENITSCH: ROOM
    public int getColor(){
        switch(currentState) {
            case GREY:
                return 0xff808080;
            case RED:
                return 0xffff0000;
            case BLUE:
                return 0xff0000ff;
            default:
                return 0xff808080;
        }
    }

    /*********************************************************************//**
     * @name getRoom
     * @par Description:
     * Returns the room as an int.
     *
     * @returns state as an int
     * *************************************************************************/
    public int getRoom(){
        return currentState.ordinal();
    }

    /*********************************************************************//**
     * @name getVisited
     * @par Description:
     * Whether or not the blue room has been visited
     *
     * @returns true if blue has been visited, false if not
     * *************************************************************************/
    public boolean getVisited(){
        return blueVisited;
    }

    /*********************************************************************//**
     * @name leftEdge
     * @par Description:
     * function to be called to move the state machine one room to the left. In
     * the case where the room is blue, room will stay blue. If entering the blue
     * state, will indicate the blue room has been visited.
     *
     * @returns nothing
     * *************************************************************************/
    public void leftEdge(){
        switch(currentState) {
            case GREY:
                currentState = State.BLUE;
                blueVisited = true;
                break;
            case RED:
                currentState = State.GREY;
                break;
        }
    }

    /*********************************************************************//**
     * @name MapState
     * @par Description:
     * Constructor, will set state to GREY.
     *
     * @returns nothing
     * *************************************************************************/
    public MapState(){
        currentState = State.GREY;
    }

    /*********************************************************************//**
     * @name reset
     * @par Description:
     * resets state to default, will put in grey room, and disable the blue
     * indicator.
     *
     * @returns nothing
     * *************************************************************************/
    public void reset(){
        currentState = State.GREY;
        blueVisited = false;
    }

    /*********************************************************************//**
     * @name rightEdge
     * @par Description:
     * function to move the state one room to the right. If the state is red,
     * but has not visited blue, will stay in red. If state is red and has visited
     * blue, will move to the end state.
     *
     * @returns nothing
     * *************************************************************************/
    public void rightEdge(){
        switch(currentState) {
            case GREY:
                currentState = State.RED;
                break;
            case BLUE:
                currentState = State.GREY;
                break;
                //REBENITSCH: EXIT
            case RED:
                if(blueVisited){
                    currentState = State.END;
                }
        }
    }

    /*********************************************************************//**
     * @name setRoom
     * @par Description:
     * sets the room to a specified state according to an int.
     *
     * param[in] room - room to set as an int
     *
     * @returns nothing
     * *************************************************************************/
    public void setRoom(int room){
        currentState = State.values()[room];
    }

    /*********************************************************************//**
     * @name setVisited
     * @par Description:
     * function to set the blue room indicator
     *
     * @returns nothing
     * *************************************************************************/
    public void setVisited(){
        blueVisited = true;
    }
}
