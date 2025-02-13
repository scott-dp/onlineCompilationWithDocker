package stud.ntnu.codeCompiler.services;

import stud.ntnu.codeCompiler.model.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CodeCompilerService {
  public static Result compileAndRunCode(String code) {
    Result result = new Result("");
    String[] dockerCommand = {"docker", "run", "--rm", "-i", "g++", "sh", "-c",
        "cat > main.cpp && g++ main.cpp -o main && ./main"};

    try {

      // Start Docker process
      Process process = Runtime.getRuntime().exec(dockerCommand);

      // Write C++ code to Docker's input stream
      try (OutputStream outputStream = process.getOutputStream()) {
        outputStream.write(code.getBytes());
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      System.out.println("got result");

      String line;
      while ((line = reader.readLine()) != null) {
        result.appendToResult(line);
      }

      while ((line = errorReader.readLine()) != null) {
        result.appendToResult(line);
      }

      int exitCode = process.waitFor();
      result.appendToResult("Process exited with code: " + exitCode);
      System.out.println("1");
    }catch(Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
