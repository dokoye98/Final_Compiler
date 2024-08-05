package project.compiler.assemblyInstructions;

import project.compiler.nodes.ExpressionNode;
import project.compiler.nodes.LiteralNode;
import project.compiler.nodes.Node;
import project.compiler.nodes.PrintVariableNode;

public class PrintVariableSetUp {

    public static String printVariableAssemble(Node ast){

        PrintVariableNode printNode = (PrintVariableNode) ast;
        String variableName = printNode.getVariableName();
        ExpressionNode value = printNode.getVariableValue();

        String variableValue = ((LiteralNode) value).getValue();

        return "section .data\n" +
                variableName + " db '" + variableValue + "', 0\n" +
                "\n" +
                "section .bss\n" +
                ";\n" +
                "\n" +
                "section .text\n" +
                "global _start\n" +
                "_start:\n" +
                "    ; variable into eax\n" +
                "    mov eax, " + variableName + "\n" +
                "    ; \n" +
                "    push eax\n" +
                "    ;  print function\n" +
                "    call print\n" +
                "    ; \n" +
                "    add esp, 4\n" +
                "    ; Exit the program\n" +
                "    mov eax, 1          ; System call number for exit\n" +
                "    xor ebx, ebx        ; Exit code 0\n" +
                "    int 0x80            ; Make the system call\n" +
                "\n" +
                "print:\n" +
                "    mov edx, " + variableValue.length() + "       ; Length of the string\n" +
                "    mov ecx, [esp+4]    ; Pointer to the string on the stack\n" +
                "    mov ebx, 1          ; File descriptor (stdout)\n" +
                "    mov eax, 4          ; System call number for sys_write\n" +
                "    int 0x80            ; Make the system call\n" +
                "    ret                 ; Return from the function\n";
    }
}
