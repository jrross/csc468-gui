package ross_jeffrey;

/*************************************************************************//**
 * @file Main.java
 *
 * @section M001
 *
 * @author Jeff Ross
 *
 * @date 2/11/2019
 *
 * @par Course:
 *         CSC 468
 *
 * @par Language Version: Java 8
 *
 * @details
 * This program is a simulation for a garden. Each tile can be one of four
 * different plants, each plant has its own starting health and moisture
 * requirements. Days can be advanced and will decrease plant health by 5, and
 * soil moisture by 10. If after the soil is decreased the moisture is below
 * the required moisture for the plant, it will lose an additional 10 health.
 * Any plant that falls below 0 health will be removed from the garden
 * automatically. All plants in the garden can be watered with a specific amount
 * indicated in the text box in the bottom, and up to two tiles can be fertilzed
 * each day to increase health by 10. The grid can be resized to have any where
 * from a 3x3 to 6x6 grid.
 *
 * Observer Pattern:
 *      Observer notified: Tile.java 157
 *      Observer attached: GardenView.java 45
 *      Observer update: PlantBtn.java 76
 *
 * Extensions:
 *      9c: Vegetable.java; Shrubbery.java
 *          This can be tested the same as other plants, as it is functionally
 *          equivalent. Water requirements are 15 for shrubbery, and 30 for
 *          vegetable.
 *
 *      6/7: PlantBtn.java 76
 *          This changes the tiles based on their condition, defaulting them to
 *          brown when the tile is empty. The tile will be green when above 20
 *          health, light green when below or at 20 health, and yellow when
 *          below or at 10 health. This can be properly tested with various
 *          scenarios where these health values are achieved. Test to make sure
 *          tiles return to brown when using the remove button and when they die.

 *****************************************************************************/

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    /*********************************************************************//**
     * @name main
     * @par Description:
     * starts the program
     *
     * param[in] args - arguments being passed in
     *
     * @returns nothing
     * *************************************************************************/
    public static void main(String[] args) {
        launch(args);
    }

    /*********************************************************************//**
     * @name Start
     * @par Description:
     * Creates the window within the stage as passed in, with default 600x800.
     * Then calls the rest of the program to build everything and run.
     *
     * param[in] PrimaryStage - the Stage to create the window in
     *
     * @returns nothing
     * *************************************************************************/
    public void start(Stage primaryStage) {
        //group is an area that can hold Nodes (non resizable)
        Group root = new Group();

        //Scene is the contents of the window
        //stage is the entire window
        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Plant Simulator");

        Layout layout = new Layout();
        layout.makeContents(root, WIDTH, HEIGHT);

        //make visible
        primaryStage.show();
    }
}
