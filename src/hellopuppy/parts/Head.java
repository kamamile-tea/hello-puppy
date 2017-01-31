/*******************************************************************************
 * Copyright (C) 2016 Johannes C. Schneider
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package hellopuppy.parts;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 *
 * @author Johannes C. Schneider
 */
public class Head {
    
    private final EV3MediumRegulatedMotor head;

    public Head() {
        head = new EV3MediumRegulatedMotor(MotorPort.C);
        head.setSpeed(900);
    }
    
    public void moveUp() {
        head.rotate(-2500);
    }
    
    public void moveDown() {
        head.rotate(2500);
    }
    
}
