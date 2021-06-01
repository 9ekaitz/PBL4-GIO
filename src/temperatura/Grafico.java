package temperatura;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Grafico extends JPanel {


    public Grafico() {
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawWidth(200);
        chartPanel.setMaximumDrawHeight(150);
        //chartPanel.setSize(new Dimension(50,50));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 15));
        chartPanel.setBackground(Color.white);

        this.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {

        String series2 = "Temp";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(0, series2, "1");
        dataset.addValue(10, series2, "2");
        dataset.addValue(20, series2, "3");
        dataset.addValue(30, series2, "4");

        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createLineChart(
                "", // Chart title
                "Date", // X-Axis Label
                "Temp", // Y-Axis Label
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis().setLabelFont(new Font("Arial",Font.PLAIN,10));
        plot.getRangeAxis().setLabelFont(new Font("Arial",Font.PLAIN,10));

        return chart;
    }
}
