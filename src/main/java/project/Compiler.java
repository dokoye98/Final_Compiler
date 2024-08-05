package project;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import project.compiler.assemblyInstructions.AssemblyGenerator;
import project.compiler.intercode.TACVisual;
import project.compiler.intercode.TACgenerator;
import project.compiler.lexer.Lexicon;
import project.compiler.nodes.Node;
import project.compiler.semantic.SemanticTree;
import project.compiler.syntaxtree.Parser;
import project.compiler.tokens.Token;
import project.nasmCompiler.NasmRunner;
import project.vm.codegen.Instruction;
import project.vm.codegen.VM;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Compiler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready to receive input:");
        System.out.flush();
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break; // Stop processing if "exit" is received
            }
            Lexicon lexer = new Lexicon(inputLine);
            lexer.splitter();
            Stream<Token> tokensStep1 = lexer.getTokens().stream();
            tokensStep1.forEach(t-> {
                System.out.println(t);
                System.out.flush();
            });
            //System.out.println("checker");
            List<Token> tokens = lexer.getTokens();

            Parser parser = new Parser(tokens);
           try{
               Node ast = parser.parse();
               parser.printAST(ast);
               SemanticTree.analyzeSemantic(ast);
               TACVisual tacVisual = new TACVisual();
               TACgenerator tacGenerator = new TACgenerator();

               List<String> tac = tacVisual.generateTAC(ast);
               for(String process: tac){
                   System.out.println(process);
               }
              List<Instruction> instructions = tacGenerator.generateCode(ast);
               VM vm = new VM(instructions);
               vm.execute();
                vm.printState();
                if(AssemblyGenerator.assemble(ast) != null) {
                    System.out.println("Assembly Code here:\n");
                    System.out.println(AssemblyGenerator.assemble(ast));


                }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }


        }

        System.out.println("Finished processing input.");
        System.out.flush();
        scanner.close();
    }
   private static void assemblyWriter(String assemblyCode){
        File file = new File("src/main/resources/assembly.asm");
        try(FileWriter writer = new FileWriter(file)){
            writer.write(assemblyCode);
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.err.println("Retry later file: " + e.getMessage());
        }
    }
}
