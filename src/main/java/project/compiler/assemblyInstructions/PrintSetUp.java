package project.compiler.assemblyInstructions;

import project.compiler.nodes.ExpressionNode;
import project.compiler.nodes.LiteralNode;
import project.compiler.nodes.Node;
import project.compiler.nodes.PrintStatementNode;

public class PrintSetUp {
    public static String printAssembly(Node ast){
        PrintStatementNode printNode = (PrintStatementNode) ast;
        ExpressionNode value = printNode.getExpression();
//intellij is the best went from string builder to string to return this automatically
        return "section .data\n" +
                "message db  '" + ((LiteralNode) value).getValue() + "', 0;" +
                "\n" +
                "\n" +
                "section .bss\n" +
                ";\n" +
                "\n" +
                "section .text\n" +
                "global _start            ;\n" +
                "_start\n" +
                "_start:\n" +
                "    mov eax, message\n" +
                "    push eax\n" +
                "    call print\n" +
                "    add esp, 4\n" +
                "    mov eax, 1          ; exit call\n" +
                "    xor ebx, ebx        ; exit code 0\n" +
                "    int 0x80            ;  system call\n" +

                "print:\n" +
                "    mov edx, len        ; dynamic string len\n" +
                "    mov ecx, [esp+4]    ; \n" +
                "    mov ebx, 1          ; File descriptor (stdout)\n" +
                "    mov eax, 4          ;  call number -> sys_write\n" +
                "    int 0x80            ; system call\n" +
                "    ret                 ; Return from the function\n" +
                "section .data\n" +
                "len equ $ - message     ;length is automatically calculated";
    }
}
