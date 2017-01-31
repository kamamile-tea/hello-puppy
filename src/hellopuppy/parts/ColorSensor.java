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

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

/**
 *
 * @author Johannes C. Schneider
 */
public class ColorSensor {

    private final EV3ColorSensor ev3ColorSensor;
    private final SampleProvider colorCodeProvider;

    public ColorSensor() {
        ev3ColorSensor = new EV3ColorSensor(SensorPort.S4);
        colorCodeProvider = ev3ColorSensor.getColorIDMode();
    }

    public int getCurrentColor() {
        float[] sample = new float[1];
        colorCodeProvider.fetchSample(sample, 0);
        return (int) sample[0];
    }
    
}
