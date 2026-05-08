/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import logicandcomputings.LogicandcomputingsMod;

public class LogicandcomputingsModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, LogicandcomputingsMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WIREPARTICLE = REGISTRY.register("wireparticle", () -> new SimpleParticleType(true));
}