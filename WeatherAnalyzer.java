import java.util.*;

class WeatherData {
    private double temperature;
    private double humidity;

    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

public class WeatherAnalyzer {
    public static void main(String[] args) {
        // Sample weather data
        List<WeatherData> weatherDataList = Arrays.asList(
            new WeatherData(15.0, 50.0),
            new WeatherData(5.0, 70.0),
            new WeatherData(25.0, 40.0),
            new WeatherData(-5.0, 80.0),
            new WeatherData(10.0, 60.0),
            new WeatherData(20.0, 45.0)
        );

        // Define temperature ranges
        double[] temperatureRanges = { Double.NEGATIVE_INFINITY, 0, 10, 20, 30, Double.POSITIVE_INFINITY };

        // Initialize map to store count of days and total humidity for each temperature range
        Map<String, Integer> daysCountByRange = new LinkedHashMap<>();
        Map<String, Double> totalHumidityByRange = new LinkedHashMap<>();

        // Initialize counts and total humidity to zero for each temperature range
        for (int i = 0; i < temperatureRanges.length - 1; i++) {
            daysCountByRange.put(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]), 0);
            totalHumidityByRange.put(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]), 0.0);
        }

        // Iterate through weather data and update counts and total humidity
        for (WeatherData weatherData : weatherDataList) {
            double temperature = weatherData.getTemperature();
            double humidity = weatherData.getHumidity();

            for (int i = 0; i < temperatureRanges.length - 1; i++) {
                if (temperature >= temperatureRanges[i] && temperature < temperatureRanges[i + 1]) {
                    int count = daysCountByRange.get(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]));
                    daysCountByRange.put(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]), count + 1);

                    double totalHumidity = totalHumidityByRange.get(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]));
                    totalHumidityByRange.put(String.format("%.0f-%.0f°C", temperatureRanges[i], temperatureRanges[i + 1]), totalHumidity + humidity);
                    break;
                }
            }
        }

        // Calculate average humidity for each temperature range and display results
        System.out.println("Temperature Range   | Days Count | Avg Humidity");
        System.out.println("---------------------------------------------");
        for (Map.Entry<String, Integer> entry : daysCountByRange.entrySet()) {
            String range = entry.getKey();
            int daysCount = entry.getValue();
            double totalHumidity = totalHumidityByRange.get(range);
            double avgHumidity = daysCount == 0 ? 0 : totalHumidity / daysCount;
            System.out.printf("%-20s | %-10d | %.2f%%\n", range, daysCount, avgHumidity);
        }
    }
}
