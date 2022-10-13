package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Plotter {


    /**
     * @return HashMap<String, ArrayList<Integer>>
     *
     * This method parses all of the Logger files and extracts
     * information into a Hashmap of ArrayLists.
     *
     * Information taken is:
     *  - the round number
     *  - the number of creatures left
     *  - the number of treasures found
     *  - the character damage
     */
    private HashMap<String, ArrayList<Integer>> readLoggerFiles() {
        ArrayList<Integer> rounds = new ArrayList<Integer>();
        ArrayList<Integer> numCreatures = new ArrayList<Integer>();
        ArrayList<Integer> numTreasuresFound = new ArrayList<Integer>();
        ArrayList<Integer> characterHealth = new ArrayList<Integer>();

        File dir = new File("Logger-files");
        File[] loggerFiles = dir.listFiles();
        Arrays.sort(loggerFiles);
        for (File loggerFile: loggerFiles) {
            JSONParser parser = new JSONParser();
            try {
                Object obj
                    = parser.parse(new FileReader(loggerFile.toString()));
                JSONObject jsonObject = (JSONObject) obj;

                String roundStr = String.valueOf(jsonObject.get("Turn"));
                rounds.add(Integer.parseInt(roundStr));

                JSONObject jsonCreatures
                    = (JSONObject) jsonObject.get("Creatures");
                String remainingStr = String.valueOf(jsonCreatures.get("Remaining"));
                numCreatures.add(Integer.parseInt(remainingStr));

                JSONObject jsonCharacter
                    = (JSONObject) jsonObject.get("Character");
                ArrayList<Object> treasureFound =
                    (ArrayList<Object>) jsonCharacter.get("Treasure");
                numTreasuresFound.add(treasureFound.size());

                String healthStr = String.valueOf(jsonCharacter.get("Health"));
                characterHealth.add(Integer.parseInt(healthStr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ArrayList<Integer>> lineGraphData
            = new HashMap<String, ArrayList<Integer>>();
        lineGraphData.put("rounds", rounds);
        lineGraphData.put("numCreatures", numCreatures);
        lineGraphData.put("numTreasuresFound", numTreasuresFound);
        lineGraphData.put("characterHealth", characterHealth);

        return lineGraphData;
    }


    /**
     * This method saves a chart using the package XChart
     * for data scraped from the Logger files.
     *
     * Help with using XChart from Knowm "XChart Example Code":
     * https://knowm.org/open-source/xchart/xchart-example-code/
     * @throws IOException
     */
    public void plotLineGraph() throws IOException {
        HashMap<String, ArrayList<Integer>> lineGraphData = readLoggerFiles();

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800)
            .height(600)
            .title("Java Line Graph")
            .xAxisTitle("Rounds")
            .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNE);

        // Series
        ArrayList<Integer> xData = lineGraphData.get("rounds");
        chart.addSeries("Creatures Remaining", xData,
            lineGraphData.get("numCreatures"));
        chart.addSeries("Treasures Found", xData,
            lineGraphData.get("numTreasuresFound"));
        chart.addSeries("Adventurer Health", xData,
            lineGraphData.get("characterHealth"));

        // Show it
        new SwingWrapper<XYChart>(chart).displayChart();

        // Save it
        BitmapEncoder.saveBitmap(chart, "GameLineGraph", BitmapFormat.PNG);
    }
}
