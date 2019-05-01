package ross_jeffrey;

/*************************************************************************//**
 * @file Layout.java
 *
 * @author Jeff Ross
 *
 * @details
 * Holds the layout of all the buttons and interface items in the program. This
 * is the main part of the View in the MVC.
 *
 *****************************************************************************/

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Layout {
    private Controller controller;
    private GardenView grid;
    private ChoiceBox choicebox;
    private TextField text;
    private Label day;
    private Label died;
    private Label filled;
    private Button fert1;
    private Button fert2;

    /*********************************************************************//**
     * @name enableFertilize
     * @par Description:
     * Enables both fertilize buttons
     *
     * @returns nothing
     * *************************************************************************/
    public void enableFertilize(){
        fert1.setDisable(false);
        fert2.setDisable(false);
    }

    /*********************************************************************//**
     * @name getGrid
     * @par Description:
     * returns the GardenView
     *
     * @returns the GardenView
     * *************************************************************************/
    public GardenView getGrid() {
        return grid;
    }

    /*********************************************************************//**
     * @name getUpdateSize
     * @par Description:
     * Gets the value currently contained in the update choicebox
     *
     * @returns update size
     * *************************************************************************/
    public int getUpdateSize(){
        return Integer.valueOf(choicebox.getSelectionModel().getSelectedItem().toString());
    }

    /*********************************************************************//**
     * @name getWaterAmount
     * @par Description:
     * gets the value inside the water text box
     *
     * @returns water amount
     * *************************************************************************/
    public double getWaterAmount(){
        String string = text.getText();
        double value = Double.valueOf(string);
        return value;
    }

    //this make the contents of the window
    /*********************************************************************//**
     * @name makeContents
     * @par Description:
     * Initializes all of the contents in the window. Additionally creates a new
     * GardenView and Controller.
     *
     * param[in] root - the Group to make the contents for
     * param[in] width - the size of the width of the window
     * param[in] height - the size of the height of the window
     *
     * @returns nothing
     * *************************************************************************/
    public void makeContents(Group root, int width, int height) {
        //all views are Nodes, and they are in a tree structure
        ObservableList<Node> list = root.getChildren();

        GridPane screen = new GridPane();
        screen.setPrefWidth(width);
        screen.setPrefHeight(height);

        grid = new GardenView();
        controller = new Controller(this);

        BorderPane right = new BorderPane();

        Label waterAmt = new Label("Water Amount");
        text = new TextField();
        text.setMaxWidth(130);
        Button water = new Button("Water All");
        water.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.WaterButton());
        fert1 = new Button("Fertilize");
        fert1.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.FertilizeButton());
        fert2 = new Button("Fertilize");
        fert2.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.FertilizeButton());
        VBox vbox1 = new VBox(waterAmt, text, water, fert1, fert2);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setMinWidth(150);

        right.setBottom(vbox1);

        Button tree = new Button("Tree");
        tree.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.TreeButton());
        Button flower = new Button("Flower");
        flower.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.FlowerButton());
        Button shrubbery = new Button("Shrubbery");
        shrubbery.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.ShrubberyButton());
        Button vegetable = new Button("Vegetable");
        vegetable.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.VegetableButton());
        Button remove = new Button("Remove");
        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.RemoveButton());
        VBox vbox2 = new VBox(tree, flower, shrubbery, vegetable, remove);
        vbox2.setAlignment(Pos.CENTER);
        right.setTop(vbox2);

        BorderPane top = new BorderPane();

        Label resize = new Label("Resize: ");
        choicebox = new ChoiceBox();
        choicebox.getItems().addAll("3", "4", "5", "6");
        choicebox.getSelectionModel().selectFirst();
        Button update = new Button("Update");
        update.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.UpdateButton());
        HBox hbox = new HBox(resize, choicebox, update);
        hbox.setAlignment(Pos.CENTER);
        top.setRight(hbox);

        day = new Label("Day: 0");
        filled = new Label("Filled: 0");
        died = new Label("Died: 0");
        VBox vbox3 = new VBox(day, filled, died);
        vbox3.setAlignment(Pos.CENTER);
        top.setCenter(vbox3);

        Button newDay = new Button("New Day");
        newDay.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.NewDayButton());
        VBox vbox4 = new VBox(newDay);
        vbox4.setAlignment(Pos.CENTER);
        top.setLeft(vbox4);
        top.setMinHeight(100);

        screen.add(grid, 0, 1, 2, 2);
        screen.add(top,0,0,3,1);
        screen.add(right,2,1,1,2);

        list.add(screen);
    }

    /*********************************************************************//**
     * @name setDay
     * @par Description:
     * Sets the day field at the top of the screen
     *
     * param[in] dayCount - the current day count to set
     *
     * @returns nothing
     * *************************************************************************/
    public void setDay(int dayCount){
        day.setText("Day: " + String.valueOf(dayCount));
    }

    /*********************************************************************//**
     * @name setDied
     * @par Description:
     * Sets the died field at the top of the screen
     *
     * param[in] deadCount - the current number of dead plants to set
     *
     * @returns nothing
     * *************************************************************************/
    public void setDied(int deadCount){
        died.setText("Died: " + String.valueOf(deadCount));
    }

    /*********************************************************************//**
     * @name setFilled
     * @par Description:
     * Sets the filled field at the top of the screen
     *
     * param[in] filledCount - the number of filled tiles to set
     *
     * @returns nothing
     * *************************************************************************/
    public void setFilled(int filledCount) {
        filled.setText("Filled: " + String.valueOf(filledCount));
    }
}
