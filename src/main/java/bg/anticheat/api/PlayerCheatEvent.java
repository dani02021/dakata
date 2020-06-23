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


package bg.anticheat.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import bg.anticheat.utils.Ping;
import bg.anticheat.utils.TPS;

public class PlayerCheatEvent extends Event implements Cancellable {
	 
    private Player player;
    private CheatType cheatType;
    private boolean cancelled;
    private boolean kicked;
    private static HandlerList handlers = new HandlerList();
    
    public static String spigotId = "%%__USER__%%";
 
    public PlayerCheatEvent(Player player, CheatType cheatType, boolean kicked) {
        this.player = player;
        this.cheatType = cheatType;
        this.cancelled = false;
        this.kicked = kicked;
    }
 
    public Player getPlayer() {
        return this.player;
    }
    
    public int getPing()
    {
    	try {
			return Ping.getPlayerPing(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
    }
    
    public double getTPS()
    {
    	return TPS.getTPS();
    }
 
    public CheatType getCheatType() {
        return this.cheatType;
    }
 
    public void setCheatType(CheatType cheatType) {
        this.cheatType = cheatType;
    }
    
    public short getViolation_WORKINPROGRESS() {
        return 1;
    }
    
    public void setViolation_WORKINPROGRESS(short violation) {
        //
    }
    
    public void exempt_WORKINPROGRESS(CheatType cheatType) {
    	
    }
    
    public void unexempt_WORKINPROGRESS(CheatType cheatType) {
    	
    }
 
    @Override
	public boolean isCancelled() {
        return this.cancelled;
    }
 
    @Override
	public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    public boolean isKicked() {
        return this.kicked;
    }
 
    @Override
	public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
		return handlers;
	}
}