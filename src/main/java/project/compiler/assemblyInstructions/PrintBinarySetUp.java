package project.compiler.assemblyInstructions;

import project.compiler.nodes.*;

public class PrintBinarySetUp {

    public static String printVarBinarySetUp(Node ast){
        BinaryAssignNode binaryAssignNode = (BinaryAssignNode) ast;
        String totalVar = binaryAssignNode.getVariableName();
        String operation = binaryAssignNode.getOperation();
        VariableAssignmentNode assignmentNode = binaryAssignNode.getVariableOne();
        VariableAssignmentNode assignmentNode1 = binaryAssignNode.getVariableTwo();
        ExpressionNode value1 = assignmentNode.getExpression();
        ExpressionNode value2 = assignmentNode1.getExpression();
        int valueNum = Integer.parseInt(((LiteralNode) value1).getValue());
        int valueNumTwo = Integer.parseInt(((LiteralNode) value2).getValue());
        StringBuilder assemblyCode = new StringBuilder();

        assemblyCode.append("section .data\n");
        assemblyCode.append("a dd ").append(valueNum).append("\n");
        assemblyCode.append("b dd ").append(valueNumTwo).append("\n");
        assemblyCode.append("c dd 0\n");
        assemblyCode.append("buffer db 'Result: ', 0\n");
        assemblyCode.append("buffer_len equ $ - buffer\n");
        assemblyCode.append("num_str db '0000000000', 0\n");
        assemblyCode.append("num_len equ 10\n");

        assemblyCode.append("section .text\n");
        assemblyCode.append("global _start\n");
        assemblyCode.append("_start:\n");

        assemblyCode.append("    mov eax, [a]\n");
        assemblyCode.append("    mov ebx, [b]\n");

        switch (operation) {
            case "+":
                assemblyCode.append("    add eax, ebx\n");
                break;
            case "-":
                assemblyCode.append("    sub eax, ebx\n");
                break;
            case "*":
                assemblyCode.append("    imul eax, ebx\n");
                break;
            case "/":
                assemblyCode.append("    xor edx, edx\n");
                assemblyCode.append("    idiv ebx\n");
                break;
        }

        assemblyCode.append("    mov [c], eax\n");
        assemblyCode.append("    mov ecx, num_len\n");
        assemblyCode.append("    mov esi, c\n");
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
