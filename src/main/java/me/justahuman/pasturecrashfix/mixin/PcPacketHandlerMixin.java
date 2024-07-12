package me.justahuman.pasturecrashfix.mixin;

import com.cobblemon.mod.common.net.messages.server.storage.pc.MovePCPokemonToPartyPacket;
import com.cobblemon.mod.common.net.serverhandling.storage.pc.MovePCPokemonToPartyHandler;
import me.justahuman.pasturecrashfix.PastureCrashFix;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovePCPokemonToPartyHandler.class)
public class PcPacketHandlerMixin {
	@Inject(at = @At("HEAD"), method = "handle(Lcom/cobblemon/mod/common/net/messages/server/storage/pc/MovePCPokemonToPartyPacket;Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/server/network/ServerPlayerEntity;)V", cancellable = true)
	private void handle(MovePCPokemonToPartyPacket packet, MinecraftServer server, ServerPlayerEntity player, CallbackInfo ci) {
		PastureCrashFix.INSTANCE.handlePacket(packet, player, ci);
	}
}