package stud.ntnu.codeCompiler.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import stud.ntnu.codeCompiler.model.Code;
import stud.ntnu.codeCompiler.model.Result;
import stud.ntnu.codeCompiler.services.CodeCompilerService;

@org.springframework.web.bind.annotation.RestController
public class CodeCompilerController {
  @CrossOrigin(origins = "http://localhost:5173")
  @PostMapping("/compileAndRun")
  public Result compileAndRunCode(@RequestBody Code code) {
    System.out.println("Called");
    //Run and compile code
    return CodeCompilerService.compileAndRunCode(code.getCode());
  }

}
