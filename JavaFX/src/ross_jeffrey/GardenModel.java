package ross_jeffrey;

/*************************************************************************//**
 * @file GardenModel.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Garden Model class. Handles the collection of tiles of PlantBtn
 * and serves as the main component of the Model.
 *
 *****************************************************************************/

public class GardenModel {
    private int dayCount;
    private int deadCount;
    private int filledCount;
    private Tile[][] grid;
    private int size;

    /*********************************************************************//**
     * @name fertilize
     * @par Description:
     * Fertilizes the specified tile and returns the result of the operation.
     *
     * param[in] i - the x coordinate of the tile to fertilize
     * param[in] j = the y coordinate of the tile to fertilize
     *
     * @returns Boolean indicating whether or not the tile was fertilized
     * *************************************************************************/
    public Boolean fertilize(int i, int j){
        return grid[i][j].fertilize();
    }

    /*********************************************************************//**
     * @name GardenModel
     * @par Description:
     * Constructor for GardenModel, sets day, dead, and filled to 0, and makes a
     * new grid of tiles of specified size.
     *
     * param[in] dim - dimensions of the square grid
     *
     * @returns nothing
     * *************************************************************************/
    public GardenModel(int dim){
        grid = new Tile[dim][dim];
        dayCount = 0;
        deadCount = 0;
        filledCount = 0;
        size = dim;
    }

    /*********************************************************************//**
     * @name getDayCount
     * @par Description:
     * Gets the current day count
     *
     * @returns current day count
     * *************************************************************************/
    public int getDayCount(){ return dayCount; }

    /*********************************************************************//**
     * @name getDeadCount
     * @par Description:
     * Gets the current dead plant count
     *
     * @returns current dead count
     * *************************************************************************/
    public int getDeadCount(){
        return deadCount;
    }

    /*********************************************************************//**
     * @name getFilledCount
     * @par Description:
     * Gets the number of tiles currently occupied by nonempty plants
     *
     * @returns number of filled tiles
     * *************************************************************************/
    public int getFilledCount(){
        return filledCount;
    }

    /*********************************************************************//**
     * @name newDay
     * @par Description:
     * increments day count and starts a new day for each tile, changing dead and
     * filled counts where necessary
     *
     * @returns nothing
     * *************************************************************************/
    public void newDay(){
        dayCount += 1;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j].newDay()){
                    deadCount += 1;
                    filledCount -= 1;
                }
            }
        }
    }

    /*********************************************************************//**
     * @name setEmpty
     * @par Description:
     * sets a specific tile to an empty tile
     *
     * param[in] i - x coordinate of the tile to be set
     * param[in] j - y coordinate of the tile to be set
     *
     * @returns nothing
     * *************************************************************************/
    public void setEmpty(int i, int j){
        if(!grid[i][j].isEmpty()){
            grid[i][j].setEmpty();
            filledCount -= 1;
        }
    }

    /*********************************************************************//**
     * @name setFlower
     * @par Description:
     * sets a specific tile to a flower
     *
     * param[in] i - x coordinate of the tile to be set
     * param[in] j - y coordinate of the tile to be set
     *
     * @returns nothing
     * *************************************************************************/
    public void setFlower(int i, int j){
        grid[i][j].setFlower();
        filledCount += 1;
    }

    /*********************************************************************//**
     * @name setShrubbery
     * @par Description:
     * sets a specific tile to a shrubbery
     *
     * param[in] i - x coordinate of the tile to be set
     * param[in] j - y coordinate of the tile to be set
     *
     * @returns nothing
     * *************************************************************************/
    public void setShrubbery(int i, int j){
        grid[i][j].setShrubbery();
        filledCount += 1;
    }

    /*********************************************************************//**
     * @name setTile
     * @par Description:
     * sets a specific tile to a premade tile
     *
     * param[in] tile - the tile to be set as
     * param[in] i - x coordinate of the tile to be set
     * param[in] j - y coordinate of the tile to be set
     *
     * @returns nothing
     * *************************************************************************/
    public void setTile(Tile tile, int i, int j){
        grid[i][j] = tile;
    }

    public void setTree(int i, int j){
        grid[i][j].setTree();
        filledCount += 1;
    }

    /*********************************************************************//**
     * @name setVegetable
     * @par Description:
     * sets a specific tile to a vegetable
     *
     * param[in] i - x coordinate of the tile to be set
     * param[in] j - y coordinate of the tile to be set
     *
     * @returns nothing
     * *************************************************************************/
    public void setVegetable(int i, int j){
        grid[i][j].setVegetable();
        filledCount += 1;
    }

    /*********************************************************************//**
     * @name waterAll
     * @par Description:
     * waters every tile in a grid by a given amount
     *
     * param[in] amount - amount to water the tile
     *
     * @returns nothing
     * *************************************************************************/
    public void waterAll(double amount){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j].water(amount);
            }
        }
    }
}
