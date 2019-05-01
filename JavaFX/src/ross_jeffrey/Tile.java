package ross_jeffrey;

/*************************************************************************//**
 * @file Tile.java
 *
 * @author Jeff Ross
 *
 * @details
 * Maintains the Tile class, which inherits from Observable. Acts as a holder
 * for the plants in the grid, and is observed by PlantBtns.
 *
 *****************************************************************************/

import java.util.Observable;

public class Tile extends Observable {
    Plant plant;

    /*********************************************************************//**
     * @name fertilize
     * @par Description:
     * Fertilizes the plant, and returns the boolean that results from the
     * operation.
     *
     * @returns result of fertilization (true / false)
     * *************************************************************************/
    public Boolean fertilize(){
        Boolean temp = plant.fertilize();
        triggerUpdate();
        return temp;
    }

    /*********************************************************************//**
     * @name getText
     * @par Description:
     * gets the text of the plant
     *
     * @returns text of the plant
     * *************************************************************************/
    public String getText(){
        return plant.getText();
    }

    /*********************************************************************//**
     * @name isEmpty
     * @par Description:
     * Returns whether or not the plant is empty
     *
     * @returns if the plant is empty
     * *************************************************************************/
    public Boolean isEmpty(){
        return plant instanceof EmptyPlant;
    }

    /*********************************************************************//**
     * @name newDay
     * @par Description:
     * starts a new day in the plant, and sets the plant accordingly.
     *
     * @returns if the plant has died
     * *************************************************************************/
    public Boolean newDay(){
        plant.newDay();
        Boolean dead = false;
        if(plant.getHealth() <= 0 && !isEmpty()){
            plant = new EmptyPlant();
            dead = true;
        }
        triggerUpdate();
        return dead;
    }

    /*********************************************************************//**
     * @name setEmpty
     * @par Description:
     * sets the plant to an empty plant
     *
     * @returns nothing
     * *************************************************************************/
    public void setEmpty(){
        EmptyPlant empty = new EmptyPlant();
        plant = empty;
        triggerUpdate();
    }

    /*********************************************************************//**
     * @name setFlower
     * @par Description:
     * sets the plant to a flower
     *
     * @returns nothing
     * *************************************************************************/
    public void setFlower(){
        Flower flower = new Flower();
        plant = flower;
        triggerUpdate();
    }

    /*********************************************************************//**
     * @name setShrubbery
     * @par Description:
     * sets the plant to a shrubbery
     *
     * @returns nothing
     * *************************************************************************/
    public void setShrubbery(){
        Shrubbery shrubbery = new Shrubbery();
        plant = shrubbery;
        triggerUpdate();
    }

    /*********************************************************************//**
     * @name setTree
     * @par Description:
     * sets the plant to a tree
     *
     * @returns nothing
     * *************************************************************************/
    public void setTree(){
        Tree tree = new Tree();
        plant = tree;
        triggerUpdate();
    }

    /*********************************************************************//**
     * @name setVegetable
     * @par Description:
     * sets the plant to a vegetable
     *
     * @returns nothing
     * *************************************************************************/
    public void setVegetable(){
        Vegetable vegetable = new Vegetable();
        plant = vegetable;
        triggerUpdate();
    }

    /*********************************************************************//**
     * @name Tile
     * @par Description:
     * Constructor for the Tile class, initializing a new empty plant
     *
     * @returns nothing
     * *************************************************************************/
    public Tile(){
        plant = new EmptyPlant();
    }

    /*********************************************************************//**
     * @name triggerUpdate
     * @par Description:
     * Triggers an update to the PlantBtn observing the tile, sending the
     * current health.
     *
     * @returns nothing
     * *************************************************************************/
    public void triggerUpdate(){
        setChanged();
        notifyObservers(plant.getHealth());
    }

    /*********************************************************************//**
     * @name water
     * @par Description:
     * Waters the plant with the given amount
     *
     * param[in] amount - the amount to water the plant with
     *
     * @returns nothing
     * *************************************************************************/
    public void water(double amount){
        plant.water(amount);
        triggerUpdate();
    }
}
