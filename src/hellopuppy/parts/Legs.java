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

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/**
 *
 * @author Johannes C. Schneider
 */
public class Legs {

    private final EV3LargeRegulatedMotor leftLeg;
    private final EV3LargeRegulatedMotor rightLeg;

    public Legs() {
        leftLeg = new EV3LargeRegulatedMotor(MotorPort.A);
        rightLeg = new EV3LargeRegulatedMotor(MotorPort.D);
    }

    public void standUp() {
        leftLeg.setSpeed(600);
        rightLeg.setSpeed(600);
        leftLeg.rotateTo(-30, true);
        rightLeg.rotateTo(-30, true);
        Delay.msDelay(800);
        leftLeg.setSpeed(200);
        rightLeg.setSpeed(200);
        leftLeg.rotateTo(-60, true);
        rightLeg.rotateTo(-60, true);
    }

    public void sit() {
        leftLeg.setSpeed(30);
        rightLeg.setSpeed(30);
        leftLeg.rotateTo(0, true);
        rightLeg.rotateTo(0, true);
        Delay.msDelay(1000);
    }

    public void liftLeftLeg() {
        leftLeg.setSpeed(200);
        leftLeg.rotateTo(-180, false);
    }

    public void returnLeftLeg() {
        leftLeg.setSpeed(200);
        leftLeg.rotateTo(-60, true);
    }

    public void stretchLegs() {
        leftLeg.setSpeed(50);
        rightLeg.setSpeed(50);
        leftLeg.rotateTo(-150, true);
        rightLeg.rotateTo(-150, false);
    }

    public void moveLegsBackToStand() {
        leftLeg.setSpeed(50);
        rightLeg.setSpeed(50);
        leftLeg.rotateTo(-60, true);
        rightLeg.rotateTo(-60, false);
    }

    public void moveLegsInExcitement() {
        leftLeg.setSpeed(400);
        rightLeg.setSpeed(400);
        for (int i = 0; i < 3; ++i) {
            leftLeg.rotateTo(-45, true);
            rightLeg.rotateTo(-75, false);
            leftLeg.rotateTo(-75, true);
            rightLeg.rotateTo(-45, false);
        }
        moveLegsBackToStand();
    }

}
