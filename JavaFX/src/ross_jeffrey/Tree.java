package ross_jeffrey;

/*************************************************************************//**
 * @file Tree.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Tree class, inherited from Plant.
 *
 *****************************************************************************/

public class Tree extends Plant{
    private double health;
    private double waterNeeds;
    private double moisture;
    private String name;

    /*********************************************************************//**
     * @name fertillize
     * @par Description:
     * Increases the health by 10, and returns true to indicate success.
     *
     * @returns true
     * *************************************************************************/
    public Boolean fertilize(){
        health += 10;
        return true;
    }

    /*********************************************************************//**
     * @name getHealth
     * @par Description:
     * gets the health of the plant.
     *
     * @returns the health value
     * *************************************************************************/
    public double getHealth(){
        return health;
    }

    /*********************************************************************//**
     * @name getText
     * @par Description:
     * Gets the text to be displayed on the button. Puts name, health, and soil
     * moisture into a string and returns it.
     *
     * @returns the text to display on the button
     * *************************************************************************/
    public String getText(){
        String string = name + "\nHealth: " + String.valueOf(health);
        string = string + "\nSoil Moisture: " + String.valueOf(moisture);
        return string;
    }

    /*********************************************************************//**
     * @name newDay
     * @par Description:
     * Decreases health by 5, and decreases moisture by 10. If moisture falls
     * below 0, it is set to 0. If, following this, the water moisture is below
     * the water requirement, health is further decremented by 10.
     *
     * @returns nothing
     * *************************************************************************/
    public void newDay(){
        health -= 5;
        if(moisture <= 10){
            moisture = 0;
        }
        else {
            moisture -= 10;
        }
        if(moisture < waterNeeds){
            health -= 10;
        }
    }

    /*********************************************************************//**
     * @name Tree
     * @par Description:
     * Constructor for Tree. Sets health to 50, amount of water needed to 20,
     * current moisture to 0, and the name of the plant to "Tree"
     *
     * @returns nothing
     * *************************************************************************/
    public Tree(){
        health = 50.0;
        waterNeeds = 20.0;
        moisture = 0.0;
        name = "Tree";
    }

    /*********************************************************************//**
     * @name water
     * @par Description:
     * Increases the moisture by the specified amount
     *
     * param[in] amount - the amount to increase moisture by
     *
     * @returns nothing
     * *************************************************************************/
    public void water(double amount){
        moisture += amount;
    }
}
