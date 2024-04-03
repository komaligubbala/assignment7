import java.util.*;

class MedicalTestResult {
    private String patientName;
    private double resultValue;

    public MedicalTestResult(String patientName, double resultValue) {
        this.patientName = patientName;
        this.resultValue = resultValue;
    }

    public double getResultValue() {
        return resultValue;
    }
}

public class MedicalTestAnalyzer {
    public static void main(String[] args) {
        List<MedicalTestResult> testResults = Arrays.asList(
            new MedicalTestResult("Patient1", 75.0),
            new MedicalTestResult("Patient2", 90.0),
            new MedicalTestResult("Patient3", 110.0),
            new MedicalTestResult("Patient4", 60.0),
            new MedicalTestResult("Patient5", 100.0),
            new MedicalTestResult("Patient6", 85.0),
            new MedicalTestResult("Patient7", 120.0)
        );

        double[] resultRanges = { 0, 100, 150 };
        String[] categories = { "Normal", "Borderline", "High" };

        Map<String, Integer> patientCountByCategory = new LinkedHashMap<>();
        Map<String, Double> totalResultValueByCategory = new LinkedHashMap<>();

        for (String category : categories) {
            patientCountByCategory.put(category, 0);
            totalResultValueByCategory.put(category, 0.0);
        }

        for (MedicalTestResult testResult : testResults) {
            double resultValue = testResult.getResultValue();
            String category = determineCategory(resultValue, resultRanges, categories);

            int count = patientCountByCategory.get(category);
            patientCountByCategory.put(category, count + 1);

            double totalValue = totalResultValueByCategory.get(category);
            totalResultValueByCategory.put(category, totalValue + resultValue);
        }

        System.out.println("Category   | Patients Count | Average Value");
        for (String category : categories) {
            int patientsCount = patientCountByCategory.get(category);
            double totalValue = totalResultValueByCategory.get(category);
            double averageValue = patientsCount == 0 ? 0 : totalValue / patientsCount;
            System.out.printf("%-10s | %-14d | %.2f\n", category, patientsCount, averageValue);
        }
    }

    private static String determineCategory(double resultValue, double[] ranges, String[] categories) {
        for (int i = 0; i < ranges.length - 1; i++) {
            if (resultValue >= ranges[i] && resultValue < ranges[i + 1]) {
                return categories[i];
            }
        }
        return "Unknown";
    }
}
