package ross_jeffrey;

/*************************************************************************//**
 * @file Flower.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Flower class, inherited from Plant.
 *
 *****************************************************************************/

public class Flower extends Plant{
    private double health;
    private double waterNeeds;
    private String name;
    private double moisture;

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
     * @name Flower
     * @par Description:
     * Constructor for Flower. Sets health to 20, amount of water needed to 10,
     * current moisture to 0, and the name of the plant to "Flower"
     *
     * @returns nothing
     * *************************************************************************/
    public Flower(){
        health = 20.0;
        waterNeeds = 10.0;
        moisture = 0.0;
        name = "Flower";
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