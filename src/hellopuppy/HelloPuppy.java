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
package hellopuppy;

import hellopuppy.parts.TouchSensor;
import hellopuppy.parts.Sounds;
import hellopuppy.parts.ColorSensor;
import hellopuppy.parts.Eyes;
import hellopuppy.parts.Head;
import hellopuppy.parts.Legs;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

/**
 *
 * @author Johannes C. Schneider
 */
public class HelloPuppy {

    public static void main(String[] args) {
        new HelloPuppy().run();
    }

    final Eyes eyes;
    final Legs legs;
    final Head head;
    final Sounds sounds;
    final TouchSensor touchSensor;
    final ColorSensor colorSensor;
    final LED led;

    public HelloPuppy() {
        eyes = new Eyes();
        legs = new Legs();
        head = new Head();
        sounds = new Sounds();
        touchSensor = new TouchSensor();
        colorSensor = new ColorSensor();
        led = BrickFinder.getDefault().getLED();
    }

    private final Runnable lookAroundAction = new Runnable() {
        @Override
        public void run() {
            eyes.lookAround();
        }
    };

    private final Runnable standUpAction = new Runnable() {
        @Override
        public void run() {
            eyes.lookBetweenSleepAndNormal();
            head.moveUp();
            eyes.lookWithNormalEyes();
            legs.standUp();
            led.setPattern(2);
            sounds.bark();
            led.setPattern(0);
        }
    };

    private final Runnable peeAction = new Runnable() {
        @Override
        public void run() {
            legs.liftLeftLeg();
            Delay.msDelay(400);
            sounds.pee();
            Delay.msDelay(400);
            legs.returnLeftLeg();
        }
    };

    private final Runnable stretchLegAction = new Runnable() {
        @Override
        public void run() {
            legs.stretchLegs();
            led.setPattern(2);
            sounds.stretch();
            led.setPattern(0);
            Delay.msDelay(300);
            legs.moveLegsBackToStand();
        }
    };

    private final Runnable excitedAction = new Runnable() {
        @Override
        public void run() {
            legs.moveLegsInExcitement();
            led.setPattern(2);
            sounds.bark();
            led.setPattern(0);
            legs.moveLegsInExcitement();
        }
    };

    private final Runnable sitDownAction = new Runnable() {
        @Override
        public void run() {
            legs.sit();
            eyes.lookBetweenSleepAndNormal();
            head.moveDown();
            eyes.lookSleeping();
        }
    };

    private enum State {
        SITTING, STANDING, STANDING_LOOKED_AROUND;
    }

    private static final long MAX_IDLE_TIME_IN_MS = 10000;

    public void run() {
        Sound.beepSequenceUp();
        eyes.lookSleeping();
        State currentState = State.SITTING;
        long busyTime = -1;
        while (!Button.ESCAPE.isDown()) {
            if (touchSensor.isPressed() && currentState == State.SITTING) {
                standUpAction.run();
                currentState = State.STANDING;
                busyTime = System.currentTimeMillis();
            }
            if (currentState == State.STANDING && System.currentTimeMillis() - busyTime > MAX_IDLE_TIME_IN_MS / 2) {
                lookAroundAction.run();
                currentState = State.STANDING_LOOKED_AROUND;
            }
            if (currentState == State.STANDING || currentState == State.STANDING_LOOKED_AROUND) {
                int color = colorSensor.getCurrentColor();
                if (Color.YELLOW == color) {
                    eyes.lookWithSmallEyes();
                    peeAction.run();
                    eyes.lookWithNormalEyes();
                    busyTime = System.currentTimeMillis();
                    currentState = State.STANDING;
                } else if (Color.RED == color) {
                    stretchLegAction.run();
                    busyTime = System.currentTimeMillis();
                    currentState = State.STANDING;
                } else if (Color.BLUE == color) {
                    excitedAction.run();
                    busyTime = System.currentTimeMillis();
                    currentState = State.STANDING;
                }
            }
            if ((currentState == State.STANDING || currentState == State.STANDING_LOOKED_AROUND)
                    && System.currentTimeMillis() - busyTime > MAX_IDLE_TIME_IN_MS) {
                sitDownAction.run();
                currentState = State.SITTING;
            }
        }

        // ensure sitting position before exiting
        if (currentState != State.SITTING) {
            sitDownAction.run();
        }
    }

}
