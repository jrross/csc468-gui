package ross_jeffrey;

/*************************************************************************//**
 * @file Controller.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds all of the event handlers for the buttons.
 *
 *****************************************************************************/

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {
    private static Layout layout;
    private static GardenModel garden;
    private static GardenView gardenView;
    private static Button fertHold;
    private static boolean treeMode;
    private static boolean flowerMode;
    private static boolean shrubberyMode;
    private static boolean vegetableMode;
    private static boolean removeMode;
    private static boolean fertilizeMode;

    /*********************************************************************//**
     * @name FertilizeButton
     * @par Description:
     * Event handler for the fertilize button, resets all modes except fertilize
     * and sets the program to fertilize mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class FertilizeButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            resetModes();
            fertHold = (Button)event.getSource();
            fertilizeMode = true;
        }
    }

    /*********************************************************************//**
     * @name FlowerButton
     * @par Description:
     * Event handler for the flower button, resets all modes except fertilize
     * and sets the program to flower mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class FlowerButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            flowerMode = true;
        }
    }

    /*********************************************************************//**
     * @name GridButtons
     * @par Description:
     * Event handler for the PlantBtn class, behaves differently depending on
     * what mode the program is currently set to. If the mode is set to
     * tree, flower, shrubbery, vegetable it will set the button to the respective
     * class. If it is set to remove, it sets the button to an empty button, and if
     * the mode is set to fertilize, it will fertilize the button and disable the
     * selected fertilize button. After all of these, the filled count will be
     * updated.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class GridButtons implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            PlantBtn button = (PlantBtn)event.getSource();
            if(treeMode){
                garden.setTree(button.getI(),button.getJ());
            }
            if(flowerMode){
                garden.setFlower(button.getI(),button.getJ());
            }
            if(shrubberyMode){
                garden.setShrubbery(button.getI(),button.getJ());
            }
            if(vegetableMode){
                garden.setVegetable(button.getI(),button.getJ());
            }
            if(removeMode){
                garden.setEmpty(button.getI(),button.getJ());
            }
            if(fertilizeMode){
                if(garden.fertilize(button.getI(), button.getJ())) {
                    fertHold.setDisable(true);
                    fertilizeMode = false;
                }
            }
            layout.setFilled(garden.getFilledCount());
        }
    }

    /*********************************************************************//**
     * @name NewDayButton
     * @par Description:
     * Event handler for the new day button. Resets all modes except fertilize,
     * calls a new day in garden, and resets day, died, and filled indicators,
     * and reneables all fertilize buttons.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class NewDayButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            garden.newDay();
            layout.setDay(garden.getDayCount());
            layout.setDied(garden.getDeadCount());
            layout.setFilled(garden.getFilledCount());
            layout.enableFertilize();
        }
    }

    /*********************************************************************//**
     * @name RemoveButton
     * @par Description:
     * Event handler for the remove button, resets all modes except fertilize
     * and sets the program to remove mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class RemoveButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            removeMode = true;
        }
    }

    /*********************************************************************//**
     * @name ShrubberyButton
     * @par Description:
     * Event handler for the shrubbery button, resets all modes except fertilize
     * and sets the program to shrubbery mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class ShrubberyButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            shrubberyMode = true;
        }
    }

    /*********************************************************************//**
     * @name TreeButton
     * @par Description:
     * Event handler for the tree button, resets all modes except fertilize
     * and sets the program to tree mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class TreeButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            resetModes();
            treeMode = true;
        }
    }

    /*********************************************************************//**
     * @name UpdateButton
     * @par Description:
     * Event handler for the update button, resets all modes except fertilize
     * and crates a new GardenModel objects based on the size indicated in the
     * layout, and resets the GardenView object before resetting the day, died,
     * and filled indicators.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class UpdateButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            int size = layout.getUpdateSize();
            garden = new GardenModel(size);
            gardenView.getChildren().clear();
            gardenView.setGarden(garden, size);
            layout.setDay(garden.getDayCount());
            layout.setDied(garden.getDeadCount());
            layout.setFilled(garden.getFilledCount());
        }
    }

    /*********************************************************************//**
     * @name VegetableButton
     * @par Description:
     * Event handler for the vegetable button, resets all modes except fertilize
     * and sets the program to vegetable mode.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class VegetableButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            resetModes();
            vegetableMode = true;
        }
    }

    /*********************************************************************//**
     * @name WaterButton
     * @par Description:
     * Event handler for the water button, resets all modes except fertilize
     * and gets the water amount from layout, and waters the garden with that
     * amount.
     *
     * param[in] event - the event triggered
     *
     * @returns nothing
     * *************************************************************************/
    public static class WaterButton implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            resetModes();
            double waterAmt = layout.getWaterAmount();
            garden.waterAll(waterAmt);
        }
    }

    /*********************************************************************//**
     * @name Controller
     * @par Description:
     * Constructor for the controller. Creates a new GardenModel and GardenView
     * of size 3, and initializes all modes false
     *
     * param[in] lay - the layout the controller is being applied to
     *
     * @returns nothing
     * *************************************************************************/
    Controller(Layout lay){
        layout = lay;
        garden = new GardenModel(3);
        gardenView = lay.getGrid();
        gardenView.setGarden(garden, 3);
        treeMode = false;
        flowerMode = false;
        shrubberyMode = false;
        vegetableMode = false;
        removeMode = false;
        fertilizeMode = false;
    }

    /*********************************************************************//**
     * @name resetModes
     * @par Description:
     * Sets all modes except for fertilize button to false
     *
     * @returns nothing
     * *************************************************************************/
    public static void resetModes(){
        treeMode = false;
        flowerMode = false;
        shrubberyMode = false;
        vegetableMode = false;
        removeMode = false;
    }
}
