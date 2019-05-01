package ross_jeffrey;

/*************************************************************************//**
 * @file PlantBtn.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the PlantBtn class, which extends Button and implements observer. This
 * manages all the plant buttons in the view.
 *
 *****************************************************************************/

import javafx.scene.control.Button;
import java.util.Observable;
import java.util.Observer;

public class PlantBtn extends Button implements Observer {
    private int ival;
    private int jval;
    final String noneStyle = "-fx-background-color: #876746; -fx-border-color: #684d32;";
    final String lowStyle = "-fx-background-color: #9ba552; -fx-border-color: #7c8443;";
    final String midStyle = "-fx-background-color: #73a552; -fx-border-color: #64823e;";
    final String highStyle = "-fx-background-color: #54a552; -fx-border-color: #458943;";

    /*********************************************************************//**
     * @name getI
     * @par Description:
     * returns the "i" value of the button in reference to the grid. (x coordinate)
     *
     * @returns x coordinate
     * *************************************************************************/
    public int getI(){
        return ival;
    }

    /*********************************************************************//**
     * @name getJ
     * @par Description:
     * returns the "j" value of the button in reference to the grid. (y coordinate)
     *
     * @returns y coordinate
     * *************************************************************************/
    public int getJ(){
        return jval;
    }

    /*********************************************************************//**
     * @name PlantBtn
     * @par Description:
     * The constructor for the PlantBtn class. sets the i and j values as provided
     *
     * param[in] i - the x coordinate
     * param[in] j - the y coordinate
     *
     * @returns nothing
     * *************************************************************************/
    public PlantBtn(int i, int j){
        ival = i;
        jval = j;
        setStyle(noneStyle);
    }

    /*********************************************************************//**
     * @name update
     * @par Description:
     * The observer call to update whenever the observable tile is updated.
     * Updates the view of the tile, sets brown when the tile is empty, yellow
     * when below or at 10 health, light green when at or below 20 health, and
     * green when above 20 health.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public void update(Observable o, Object arg) {
        Tile tile = (Tile)o;
        double health = (double)arg;
        setText(tile.getText());
        if(health == -1){
            setStyle(noneStyle);
        }
        else if(health <= 10){
            setStyle(lowStyle);
        }
        else if(health <= 20){
            setStyle(midStyle);
        }
        else{
            setStyle(highStyle);
        }
    }
}
