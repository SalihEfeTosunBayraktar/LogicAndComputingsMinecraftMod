/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package logicandcomputings.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import logicandcomputings.client.particle.WireparticleParticle;

@EventBusSubscriber(Dist.CLIENT)
public class LogicandcomputingsModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(LogicandcomputingsModParticleTypes.WIREPARTICLE.get(), WireparticleParticle::provider);
	}
}