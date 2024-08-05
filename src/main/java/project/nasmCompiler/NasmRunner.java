package project.nasmCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NasmRunner {
    public static void nasmController() {
        String asmFile = "src/main/resources/assembly.asm";
        String objectFile = "src/main/resources/assembly.o";
        String executable = "src/main/resources/assembly";

        try {

            if (!Files.exists(Paths.get(asmFile))) {
                System.out.println("Assembly file not found: " + asmFile);
                return;
            }


            Process nasmProcess = new ProcessBuilder("nasm", "-felf64", asmFile, "-o", objectFile).start();
            nasmProcess.waitFor();
            printProcessOutput(nasmProcess);

            if (nasmProcess.exitValue() != 0) {
                System.out.println("NASM process failed.");
                return;
            }


            Process ldProcess = new ProcessBuilder("ld", objectFile, "-o", executable).start();
            ldProcess.waitFor();
            printProcessOutput(ldProcess);

            if (ldProcess.exitValue() != 0) {
                System.out.println("LD process failed.");
                return;
            }


            Process execProcess = new ProcessBuilder("./" + executable).start();
            execProcess.waitFor();
            printProcessOutput(execProcess);

            if (execProcess.exitValue() != 0) {
                System.out.println("Execution process failed.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error during NASM execution: " + e.getMessage());
        }
    }

    private static void printProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}