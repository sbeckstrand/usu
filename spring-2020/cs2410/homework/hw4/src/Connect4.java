import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Connect4 {

    // Constants to set columns, rows and size of each tile
    int TILE_SIZE;
    final int COLUMNS = 7;
    final int ROWS = 6;

    // Booleans to track turns, if game is over and if the current animation is over.
    boolean redMove = true;
    boolean won = false;
    boolean noAnimation = true;

    //JavaFX Objects
    Shape grid;
    List<Rectangle> columnOverlay;
    Text status = new Text();
    TextFlow statusPane = new TextFlow(this.status);
    Disc[][] discGrid = new Disc[this.COLUMNS][this.ROWS];
    ArrayList<Disc> winningDiscs = new ArrayList<Disc>();
    Pane discPane = new Pane();

    // Lastly, variable to track how many moves have been taken to determine if all slots have been filled.
    int moves = 0;






    // Constructor of our Connect 4 games. It builds the grid of tiles, the Column overlay to select to column to add a Disc as well as the Text displayed at the top showing the current turn
    public Connect4(int tileSize) {
        this.TILE_SIZE = tileSize;

        status.setText("Current Turn: Red");
        status.setFill(Color.WHITE);
        status.setFont(Font.font ("Verdana", 25));
        statusPane.setStyle("-fx-background-color: blue;");
        statusPane.setTextAlignment(TextAlignment.CENTER);

        this.grid = new Rectangle((this.COLUMNS + 1) * TILE_SIZE, (this.ROWS + 1) * TILE_SIZE);
        for (int y = 0; y < this.ROWS; y++) {
            for (int x = 0; x < this.COLUMNS; x++) {
                Circle tile = new Circle(TILE_SIZE / 2);
                tile.setCenterX(TILE_SIZE / 2);
                tile.setCenterY(TILE_SIZE / 2);
                tile.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                tile.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);

                grid = Shape.subtract(grid, tile);
                this.columnOverlay = makeColumns();


            }
        }
        grid.setFill(Color.BLUE);

    }

    // Method used to build the columns that lay over the grid. These columns have hover and click event listeners that are used to indicate which column you are selecting and to play a disc.
    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < this.COLUMNS; x++) {
            Rectangle select = new Rectangle(this.TILE_SIZE, (this.ROWS + 1) * this.TILE_SIZE);
            select.setTranslateX(x * (this.TILE_SIZE + 5) + this.TILE_SIZE / 4);
            select.setFill(Color.TRANSPARENT);

            select.setOnMouseEntered(e -> {
                if (!won) {
                    select.setFill(Color.rgb(255, 255, 255, 0.3));
                }

            });
            select.setOnMouseExited(e -> select.setFill(Color.TRANSPARENT));

            final int column = x;

            select.setOnMouseClicked(e -> {
                if (!won) {
                    if (noAnimation) {
                        moves += 1;
                        this.noAnimation = false;
                        placeDisc(new Disc(redMove, this.TILE_SIZE), column);
                        if (!redMove) {
                            setStatus("Current Turn: Red");
                        } else {
                            status.setText("Current Turn: Yellow");
                        }
                    }
                }
            });


            list.add(select);
        }

        return list;
    }


    // Method used to place a disc in our two dimensional array tracking positions of discs and displaying them in our pane.
    private void placeDisc(Disc disc, int column) {
        int row = this.ROWS -1;
        do {
            if (!getDisc(column, row).isPresent()) {
                break;
            }
            row -= 1;
        } while (row >= 0);

        if (row < 0) return;

        this.discGrid[column][row] = disc;
        this.discPane.getChildren().add(disc);
        disc.setTranslateX(column * (this.TILE_SIZE + 5) + this.TILE_SIZE / 4);

        int currentRow = row;
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (this.TILE_SIZE + 5) + this.TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            if (gameEnded(column, currentRow)) {
                gameOver();
            }
            redMove = !redMove;
            this.noAnimation = true;
        });

        animation.play();
    }

    // Method used to check if a game ending event has occurred at the a turn.
    private boolean gameEnded(int column, int row) {
        if (moves == ROWS * COLUMNS) {
            return true;
        }
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3).mapToObj(r -> new Point2D(column, r)).collect(Collectors.toList());
        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3).mapToObj(c -> new Point2D(c, row)).collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row -3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0, 6).mapToObj(i -> topLeft.add(i, i)).collect(Collectors.toList());

        Point2D bottomLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6).mapToObj(i -> bottomLeft.add(i, -i)).collect(Collectors.toList());

        return checkRange(vertical) || checkRange(horizontal) || checkRange(diagonal1) || checkRange(diagonal2);
    }

    // Helper method for gameEnded. This will check a range of discs. If 4 of the same color are in a row, it will confirm the range is a winning range.
    private boolean checkRange(List<Point2D> points) {
        int chain = 0;

        for (Point2D p: points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Disc disc = getDisc(column, row).orElse(new Disc(!redMove, this.TILE_SIZE));

            if (disc.red == redMove) {
                winningDiscs.add(disc);
                chain++;
                if (chain == 4) {
                    won = true;
                    return true;
                }
            } else {
                winningDiscs.clear();
                chain = 0;

            }
        }
        return false;
    }

    // Method to update the status showing the game is over and cause the winning tiles to blink.
    private void gameOver() {
        if (moves == ROWS * COLUMNS) {
            setStatus("No Winner. Board is full!");
        }
        setStatus("Winner: " + (redMove ? "RED" : "YELLOW"));
        Paint original = winningDiscs.get(0).getFill();


        for (Disc disc: winningDiscs) {
            disc.setStrokeWidth(5);
            disc.setStroke(original);
        }

        Timer timer = new Timer(500, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Paint fill;
                if (winningDiscs.get(0).getFill() == original) {
                    fill = Color.WHITE;

                } else {
                    fill = original;
                }

                for (Disc disc: winningDiscs) {
                    disc.setFill(fill);
                }
            }
        });

        timer.start();
    }

    // Getter Method to return Column Overlay
    public List<Rectangle> getColumnOverlay() {
        return this.columnOverlay;
    }

    // Getter Method to get the disc at a position in our two dimensional array.
    private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= this.COLUMNS || row < 0 || row >= this.ROWS) {
            return Optional.empty();
        }

        return Optional.ofNullable(discGrid[column][row]);
    }

    // Getter Method to get our Panes and objects so they can be added to our overall border pane and eventually to our stage.
    public Pane getDiscPane() {
        return this.discPane;
    }

    public TextFlow getStatus() {
        return this.statusPane;
    }


    public Shape getGrid() {
        return this.grid;
    }

    // Setter method to update the current status (Such as who's turn it is, who won, etc).
    private void setStatus(String message) {
        status.setText(message);
    }

}

