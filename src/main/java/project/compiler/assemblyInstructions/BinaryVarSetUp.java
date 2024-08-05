package project.compiler.assemblyInstructions;

import project.compiler.nodes.BinaryAssignmentNode;
import project.compiler.nodes.ExpressionNode;
import project.compiler.nodes.LiteralNode;
import project.compiler.nodes.Node;

public class BinaryVarSetUp {

    public static String binaryVarSetUp(Node ast){
        BinaryAssignmentNode binaryAssignmentNode = (BinaryAssignmentNode) ast;
        ExpressionNode left = binaryAssignmentNode.getValue();
        ExpressionNode right = binaryAssignmentNode.getSecondValue();
        String operation = binaryAssignmentNode.getOperation();
        String variableName = binaryAssignmentNode.getVariableName();

        StringBuilder assemblyCode = new StringBuilder();

        assemblyCode.append("section .data\n");
        assemblyCode.append("result dd 0\n");
        assemblyCode.append("buffer db 'Result: ', 0\n");
        assemblyCode.append("buffer_len equ $ - buffer\n");
        assemblyCode.append("num_str db '0000000000', 0\n");
        assemblyCode.append("num_len equ 10\n");
        assemblyCode.append(variableName).append(" dd ").append(((LiteralNode) left).getValue()).append("\n");
        assemblyCode.append("section .text\n");
        assemblyCode.append("global _start\n");
        assemblyCode.append("_start:\n");
        assemblyCode.append("    mov eax, [").append(variableName).append("]\n");

        switch (operation) {
            case "+":
                assemblyCode.append("    add eax, ").append(((LiteralNode) right).getValue()).append("\n");
                break;
            case "-":
                assemblyCode.append("    sub eax, ").append(((LiteralNode) right).getValue()).append("\n");
                break;
            case "*":
                assemblyCode.append("    imul eax, ").append(((LiteralNode) right).getValue()).append("\n");
                break;
            case "/":
                assemblyCode.append("    xor edx, edx\n");
                assemblyCode.append("    mov ebx, ").append(((LiteralNode) right).getValue()).append("\n");
                assemblyCode.append("    idiv ebx\n");
                break;
        }

        assemblyCode.append("    mov [result], eax\n");
        assemblyCode.append("    mov ecx, num_len\n");
        assemblyCode.append("    mov esi, result\n");
        assemblyCode.append("    mov eax, [esi]\n");
        assemblyCode.append("    mov ebx, 10\n");
        assemblyCode.append("convert_loop:\n");
        assemblyCode.append("    xor edx, edx\n");
        assemblyCode.append("    div ebx\n");
        assemblyCode.append("    add dl, '0'\n");
        assemblyCode.append("    dec ecx\n");
        assemblyCode.append("    mov [num_str + ecx], dl\n");
        assemblyCode.append("    test eax, eax\n");
        assemblyCode.append("    jnz convert_loop\n");
        assemblyCode.append("    mov eax, 4\n");
        assemblyCode.append("    mov ebx, 1\n");
        assemblyCode.append("    mov edx, num_len\n");
        assemblyCode.append("    mov ecx, num_str\n");
        assemblyCode.append("    int 0x80\n");
        assemblyCode.append("    mov eax, 1\n");
        assemblyCode.append("    xor ebx, ebx\n");
        assemblyCode.append("    int 0x80\n");

        return assemblyCode.toString();
    }

}
