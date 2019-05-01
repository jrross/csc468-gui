package ross_jeffrey;

/*************************************************************************//**
 * @file Plant.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds the functions for the abstract plant class
 *
 *****************************************************************************/

public class Plant {
    private double health;
    private double waterNeeds;
    private String name;
    private double moisture;

    /*********************************************************************//**
     * @name fertilize
     * @par Description:
     * Abstract function for fertilize, default returns false
     *
     * @returns false
     * *************************************************************************/
    public Boolean fertilize(){
        return false;
    }

    /*********************************************************************//**
     * @name getHealth
     * @par Description:
     * Abstract function for getHealth, returns health
     *
     * @returns false
     * *************************************************************************/
    public double getHealth(){
        return health;
    }

    /*********************************************************************//**
     * @name getText
     * @par Description:
     * Abstract function for getText, default returns "None"
     *
     * @returns "None"
     * *************************************************************************/
    public String getText(){
        return "None";
    }

    /*********************************************************************//**
     * @name newDay
     * @par Description:
     * Abstract function for newDay
     *
     * @returns nothing
     * *************************************************************************/
    public void newDay(){

    }

    /*********************************************************************//**
     * @name water
     * @par Description:
     * Abstract function for water.
     *
     * param[in] amount - the amount to water the plant with
     *
     * @returns nothing
     * *************************************************************************/
    public void water(double amount){

    }
}
