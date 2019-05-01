package ross_jeffrey;

/*************************************************************************//**
 * @file ModelView.java
 *
 * @author Jeff Ross
 *
 * @details
 * Maintains the GardenView class, inherited from GridPane
 *
 *****************************************************************************/

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/*********************************************************************//**
 * @name GardenView
 * @par Description:
 * Constructor for the GardenView class. Sets the GardenModel to the garden
 * passed in, and creates a new grid of specified size. The garden is filled
 * with new tiles initialized with empty plants, and the grid is filled with
 * PlantBtns that observe these tiles.
 *
 * param[in] gard - the GardenModel this GardenView corresponds to
 * param[in] size - the size of the grid to be created.
 *
 * @returns nothing
 * *************************************************************************/
public class GardenView extends GridPane {
    PlantBtn[][] grid;
    GardenModel garden;
    public void setGarden(GardenModel gard, int size)
    {
        garden = gard;
        grid = new PlantBtn[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                PlantBtn plant = new PlantBtn(i, j);
                plant.setText("None");
                plant.setMinSize(650 / size, 500 / size);
                plant.setMaxSize(650 / size, 500 / size);
                this.add(plant, i, j);
                plant.addEventFilter(MouseEvent.MOUSE_CLICKED, new Controller.GridButtons());
                Tile tile = new Tile();
                tile.addObserver(plant);
                garden.setTile(tile, i, j);
                grid[i][j] = plant;
            }
        }
    }
}
