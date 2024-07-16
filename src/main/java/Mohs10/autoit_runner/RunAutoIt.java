package Mohs10.autoit_runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunAutoIt {
    public static void main(String[] args) {
        try {
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
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("AutoIt script exited with code: " + exitCode);

            if (exitCode != 0) {
                System.err.println("AutoIt script encountered an error.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
