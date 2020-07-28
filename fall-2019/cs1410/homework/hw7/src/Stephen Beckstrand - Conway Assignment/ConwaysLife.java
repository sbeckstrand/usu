// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// Define primary driving class used to call on our simulation class, and render our patterns.
public class ConwaysLife {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {

            // Create our terminal window and set its parameters
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            TerminalSize size = screen.getTerminalSize();


            screen.startScreen();
            screen.setCursorPosition(null);

            // Loop through our four different pattern's and render them
            for (int i = 0; i < 4; i++) {
                LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());
                if (i == 0) {
                    simulation.insertPattern(new PatternBlock(), 40, 10);
                } else if (i == 1) {
                    simulation.insertPattern(new PatternBlinker(), 40, 10);
                } else if (i == 2) {
                    simulation.insertPattern(new PatternGlider(), 40, 10);
                } else {
                    simulation.insertPattern(new PatternAcorn(), 40, 10);
                }

                for (int j = 0; j < 50; j++) {
                    render(simulation, screen, graphics);   // Render the current state of the simulation
                    Thread.yield();                         // Let the JVM have some time to update other things
                    Thread.sleep(100);                // Sleep for a bit to make for a nicer paced animation
                    simulation.update();                    // Tell the simulation to update
                }

            }


            screen.stopScreen();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    // Define render method
    public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics) {
        screen.clear();

        for (int i = 1; i < simulation.getSizeX(); i++) {
            for (int j = 1; j < simulation.getSizeY(); j++) {
                if (simulation.getCell(i, j) == true) {
                    graphics.setCharacter(i, j, '▢');
                }
            }
        }


        try {
            screen.refresh();
        } catch (Exception ex) {
        }
    }
}
