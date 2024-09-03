package project.compiler.assemblyInstructions;

import project.compiler.nodes.*;

import java.util.List;

public class LoopAssemblyGenerator {

    public static String ArrayAssignGenerator(Node ast){

        LoopArrNode loopArrNode = (LoopArrNode) ast;
        List<ExpressionNode> elements = loopArrNode.getArrayAssignmentNode().getElements();
        ExpressionNode iterator = loopArrNode.getPrinter();
        String arrayVar = loopArrNode.getArrayAssignmentNode().getArrayName();
        StringBuilder assemblyCode = new StringBuilder();
        assemblyCode.append("section .data\n");
        assemblyCode.append(arrayVar).append(" dd ");
        for(int i=0; i < elements.size();i++){
            assemblyCode.append(((LiteralNode) elements.get(i)).getValue());
            if (i < elements.size() - 1) {
                assemblyCode.append(", ");
            }
        }
        assemblyCode.append("\n");
        assemblyCode.append("msg db '%d', 10, 0\n");
        assemblyCode.append("section .bss\n");
        assemblyCode.append("iterator resd 1\n");

        assemblyCode.append("section .text\n");
        assemblyCode.append("global _start\n");

        assemblyCode.append("_start:\n");
        assemblyCode.append("mov ecx, 5\n");
        assemblyCode.append("mov esi, ").append(arrayVar).append("\n");

        assemblyCode.append("loop_start:\n");
        assemblyCode.append("mov eax, [esi]\n");
        assemblyCode.append("mov [iterator], eax\n");

        assemblyCode.append("push eax\n");
        assemblyCode.append("push msg\n");
        assemblyCode.append("call print\n");
        assemblyCode.append("add esp, 8\n");

        assemblyCode.append("add esi, 4\n");
        assemblyCode.append("loop loop_start\n");

        assemblyCode.append("mov eax, 1\n");
        assemblyCode.append("xor ebx, ebx\n");
        assemblyCode.append("int 0x80\n");

        assemblyCode.append("print:\n");
        assemblyCode.append("mov eax, 4\n");
        assemblyCode.append("mov ebx, 1\n");
        assemblyCode.append("mov ecx, [esp+4]\n");
        assemblyCode.append("mov edx, 10\n");
        assemblyCode.append("int 0x80\n");
        assemblyCode.append("ret\n");

        return assemblyCode.toString();
    }
}
