package screens.panel;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class GraphicScreen extends JPanel {


	private static final long serialVersionUID = 1L;

	public GraphicScreen() {
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawWidth(550);
        chartPanel.setMaximumDrawHeight(500);
        chartPanel.setBackground(Color.WHITE);
        
        this.setOpaque(false);
        this.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {

        String series2 = "Temperatura";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(10, series2, "1");
        dataset.addValue(12, series2, "2");
        dataset.addValue(11, series2, "3");
        dataset.addValue(11, series2, "4");
        dataset.addValue(13, series2, "5");
        dataset.addValue(13, series2, "6");
        dataset.addValue(13, series2, "7");
        dataset.addValue(14, series2, "8");
        dataset.addValue(15, series2, "9");
        dataset.addValue(13, series2, "10");
        dataset.addValue(13, series2, "11");
        dataset.addValue(14, series2, "12");
        dataset.addValue(15, series2, "13");
        dataset.addValue(16, series2, "14");
        dataset.addValue(17, series2, "15");
        dataset.addValue(17, series2, "16");
        dataset.addValue(17, series2, "17");
        dataset.addValue(16, series2, "18");
        dataset.addValue(16, series2, "19");
        dataset.addValue(15, series2, "20");
        dataset.addValue(15, series2, "21");
        dataset.addValue(13, series2, "22");
        dataset.addValue(13, series2, "23");
        dataset.addValue(12, series2, "24");

        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createLineChart(
                "Temperatura de conservaci�n", // Chart title
                "Tiempo (Hora)", // X-Axis Label
                "Temperatura (�C)", // Y-Axis Label
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis().setLabelFont(new Font("Arial",Font.PLAIN,8));
        plot.getRangeAxis().setLabelFont(new Font("Arial",Font.PLAIN,8));

        return chart;
    }
}
