package Mohs10.autoit_runner;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class RunAutoIt {
    public static void main(String[] args) {
        FileWriter htmlReport = null;
        try {
            // Initialize HTML report
            //htmlReport = new FileWriter("C:\\Users\\DELL\\Downloads\\test_reportisafe.html");
            htmlReport = new FileWriter("report/InstaSafeReport.html");
            htmlReport.write("<html><head><title>AutoIt Test Report</title></head><body>");
            htmlReport.write("<h1>AutoIt Script Test Execution Report</h1>");
            htmlReport.write("<table border='1'><tr><th>Test Step</th><th>Output</th><th>Status</th></tr>");

            // Path to the AutoIt executable and script
            String autoItExecutable = "C:\\Users\\DELL\\Downloads\\Insta_Safe_Open.exe";
            String autoItScript = "C:\\Users\\DELL\\Downloads\\Insta_Safe_Connect.bat";  // Update this path to your script location

            // Build the process
            ProcessBuilder processBuilder = new ProcessBuilder(autoItExecutable, autoItScript);
            processBuilder.redirectErrorStream(true); // Merge stdout and stderr

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the AutoIt script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String status = line.contains("ERROR") ? "FAIL" : "PASS";
                htmlReport.write("<tr><td>" + line + "</td><td>" + line + "</td><td>" + status + "</td></tr>");
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("AutoIt script exited with code: " + exitCode);

            if (exitCode != 0) {
                System.err.println("AutoIt script encountered an error.");
            }

            // Finalize HTML report
            htmlReport.write("</table></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (htmlReport != null) {
                try {
                    htmlReport.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
