package ross_jeffrey;

/*************************************************************************//**
 * @file EmptyPlant.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the EmptyPlant class, inherited from Plant.
 *
 *****************************************************************************/

public class EmptyPlant extends Plant{

    /*********************************************************************//**
     * @name fertilize
     * @par Description:
     * returns false to indicate that the tile was not fertilized.
     *
     * @returns false
     * *************************************************************************/
    public Boolean fertilize(){
        return false;
    }

    /*********************************************************************//**
     * @name getHealth
     * @par Description:
     * returns -1.0 to indicate that the plant is empty
     *
     * @returns -1.0
     * *************************************************************************/
    public double getHealth(){
        return -1.0;
    }

    /*********************************************************************//**
     * @name getText
     * @par Description:
     * returns the string "None"
     *
     * @returns "None"
     * *************************************************************************/
    public String getText(){
        return "None";
    }
}
