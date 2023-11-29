package software.ulpgc.kata3;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;

import static org.jfree.chart.ChartFactory.createHistogram;

public class JFreeChartHistogramDisplay extends JPanel implements HistogramDisplay {
    @Override
    public void show(String title, String xaxis, String yaxis, Histogram dataset) {
        JFreeChart histogram = createHistogram(title, xaxis, yaxis, datasetWith(dataset), PlotOrientation.VERTICAL,
                false, false, false);
        add(new ChartPanel(histogram));
    }

    private HistogramDataset datasetWith(Histogram dataset) {
        HistogramDataset dataset1 = new HistogramDataset();
        dataset1.addSeries("s", dataset.values(), dataset.bins());
        return dataset1;
    }
}
