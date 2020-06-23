/*<DakataAntiCheat>
Copyright C 2020 Author: dani02

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
at your option any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/


package bg.anticheat.modules.other;

import java.util.List;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;

import bg.anticheat.dakata.Main;

public class HealthTag {
	public static void a()
	{
		Main.protocolManager.addPacketListener(
				  new PacketAdapter(Main.getThisPlugin(), ListenerPriority.NORMAL, 
				          PacketType.Play.Server.ENTITY_METADATA) {
				    @Override
				    public void onPacketSending(PacketEvent event) {
				    	if(event.getPlayer().hasPermission("Dakata.Bypass.HealthTag"))
				    		return;
			            Player observer = event.getPlayer();
			            StructureModifier<Entity> entityModifer = event.getPacket().getEntityModifier(observer.getWorld());
			            Entity entity = entityModifer.read(0);
			            if ((entity != null) && (observer != entity) && ((entity instanceof LivingEntity)) && 
			              ((!(entity instanceof EnderDragon)) || (!(entity instanceof Wither))) && (
			              (entity.getPassenger() == null) || (entity.getPassenger() != observer)))
			            {
			              event.setPacket(event.getPacket().deepClone());
			              StructureModifier<List<WrappedWatchableObject>> watcher = event.getPacket()
			                .getWatchableCollectionModifier();
			              for (WrappedWatchableObject watch : watcher.read(0)) {
			            	  if(Main.is114ro1()) {
									// 1.14 is kinda strange, first check if the value is float, then if its 0-20 range, then set it to 0.5
									if ((watch.getIndex() == 8) && watch.getValue() instanceof  Float && ((Float)watch.getValue()) > 0 && ((Float)watch.getValue()) < 20 && (((Float) watch.getValue()).floatValue() > 0.5F))
										watch.setValue(0.5F);
							  } else if(Main.is110ro1() || Main.is111ro1() || Main.is112ro1() || Main.is113ro1()) {
					                if ((watch.getIndex() == 7) && 
							                  (((Float)watch.getValue()).floatValue() > 0.5F)) {
							                  watch.setValue(
							                    0.5F);
							                }
			            	  } else {
			                if ((watch.getIndex() == 6) && 
			                  (((Float)watch.getValue()).floatValue() > 0.5F)) {
			                  watch.setValue(
			                    0.5F);
			                }}
			              }
			            }
				    }
				});
	}
}
