package me.justahuman.pasturecrashfix

import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager
import com.cobblemon.mod.common.net.messages.client.storage.pc.ClosePCPacket
import com.cobblemon.mod.common.net.messages.server.storage.pc.MovePCPokemonToPartyPacket
import net.fabricmc.api.ModInitializer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

object PastureCrashFix : ModInitializer {
	override fun onInitialize() {}
	fun handlePacket(
		packet: MovePCPokemonToPartyPacket,
		player: ServerPlayerEntity,
		ci: CallbackInfo
	) {
		val pc = PCLinkManager.getPC(player) ?: return run { ClosePCPacket(null).sendToPlayer(player) }
		val pokemon = pc[packet.pcPosition] ?: return
		if (pokemon.uuid != packet.pokemonID) {
			return
		}

		if (pokemon.tetheringId != null) {
			player.sendMessage(Text.literal("You must take the pokemon out of the pasture first!").formatted(Formatting.RED))
			ci.cancel();
		}
	}
}