import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.util.List;

public class Graf extends JFrame {
    public Graf(String Title , String chartTitle, List<String> count) {
        super(Title);
        JFreeChart bar = ChartFactory.createBarChart(
                chartTitle, "Регион",
                "Количество", createDataset(count),
                PlotOrientation.HORIZONTAL,
                false, true, false);
        ChartPanel chartPanel = new ChartPanel(bar);
        chartPanel.setPreferredSize(new java.awt.Dimension(800 , 600 ));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<String> count) {
        final String c = "";
        int var;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < count.size(); i++){
            String[] s = count.get(i).split(" -- ");
            var = Integer.parseInt(s[1]);
            dataset.addValue(var, c, s[0]);
        }
        return dataset;
    }
}
