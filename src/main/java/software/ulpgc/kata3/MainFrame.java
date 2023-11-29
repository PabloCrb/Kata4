package software.ulpgc.kata3;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JFreeChartHistogramDisplay display;

    public MainFrame() throws HeadlessException {
        this.setTitle("Histogram viewer");
        this.setSize(1200, 900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createHistogramDisplay());
    }

    private Component createHistogramDisplay() {
        this.display = new JFreeChartHistogramDisplay();
        return display;
    }

    public JFreeChartHistogramDisplay getDisplay() {
        return display;
    }
}
