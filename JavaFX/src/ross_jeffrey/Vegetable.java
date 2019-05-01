package ross_jeffrey;

/*************************************************************************//**
 * @file Vegetable.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Vegetable class, inherited from Plant.
 *
 *****************************************************************************/

public class Vegetable extends Plant {
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
     * @name Vegetable
     * @par Description:
     * Constructor for Vegetable. Sets health to 10, amount of water needed to 30,
     * current moisture to 0, and the name of the plant to "Vegetable"
     *
     * @returns nothing
     * *************************************************************************/
    public Vegetable(){
        health = 10.0;
        waterNeeds = 30.0;
        moisture = 0.0;
        name = "Vegetable";
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
