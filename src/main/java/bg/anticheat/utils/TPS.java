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


package bg.anticheat.utils;

public class TPS
  implements Runnable
{
  public static int TICK_COUNT= 0;
  public static long[] TICKS= new long[600];
  public static long LAST_TICK= 0L;
 
  public static double getTPS()
  {
    return getTPS(100);
  }
 
  public static double getTPS(int ticks)
  {
    if (TICK_COUNT< ticks) {
      return 20.0D;
    }
    int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
    long elapsed = System.currentTimeMillis() - TICKS[target];
 
    return ticks / (elapsed / 1000.0D);
  }
 
  public static long getElapsed(int tickID)
  {
    if (TICK_COUNT- tickID >= TICKS.length)
    {
    }
 
    long time = TICKS[(tickID % TICKS.length)];
    return System.currentTimeMillis() - time;
  }
 
  @Override
public void run()
  {
    TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();
 
    TICK_COUNT+= 1;
  }
}