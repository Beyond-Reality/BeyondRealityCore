package com.mcbeyondreality.beyondrealitycore.transformer;

import com.mcbeyondreality.beyondrealitycore.BeyondRealityCore;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.Opcodes.*;

public class BRClassTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String obfName, String transformedName, byte[] basicClass) {
		
		if (transformedName.equals("net.minecraft.block.BlockLeavesBase") && BeyondRealityCore.fastLeafDecay) {
			
			return patchLeaveClass(basicClass);
		}
		return basicClass;
	}

	private byte[] patchLeaveClass(byte[] basicClass) {
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		
		String methodName = MCPNames.method("func_149695_a");
		String worldClass = "net/minecraft/world/World";
		String leaveClass = "net/minecraft/block/BlockLeavesBase";
		String blockClass = "net/minecraft/block/Block";
		
		MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, methodName, "(L" + worldClass + ";IIIL" + blockClass + ";)V", null, null);
        mv.visitCode();

		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(81, l0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(ILOAD, 3);
		mv.visitVarInsn(ILOAD, 4);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, "com/mcbeyondreality/beyondrealitycore/handlers/CoreHandler", "handleLeaveDecay", "(L" + worldClass + ";IIIL" + blockClass + ";)V");
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLineNumber(82, l1);
		mv.visitInsn(RETURN);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitLocalVariable("this", "Lcom/mcbeyondreality/beyondrealitycore/transformer/BRClassTransformer;", null, l0, l2, 0);
		mv.visitLocalVariable("p_149695_1_", "L" + worldClass + ";", null, l0, l2, 1);
		mv.visitLocalVariable("p_149695_2_", "I", null, l0, l2, 2);
		mv.visitLocalVariable("p_149695_3_", "I", null, l0, l2, 3);
		mv.visitLocalVariable("p_149695_4_", "I", null, l0, l2, 4);
		mv.visitLocalVariable("p_149695_5_", "L" + blockClass + ";", null, l0, l2, 5);
		mv.visitMaxs(5, 6);
		mv.visitEnd();

		return writer.toByteArray();
	}
}
