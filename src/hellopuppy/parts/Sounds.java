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

import java.io.File;
import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;

/**
 *
 * @author Johannes C. Schneider
 */
public class Sounds {

    private final Audio audio;
    private static final File BARK_FILE = new File("./puppy_bark.wav");
    private static final File PEE_FILE = new File("./puppy_pee.wav");
    private static final File STRETCH_FILE = new File("./puppy_stretch.wav");
    
    public Sounds() {
        audio = BrickFinder.getDefault().getAudio();
    }
    
    public void bark() {
        audio.playSample(BARK_FILE, 100);
    }
    
    public void pee() {
        audio.playSample(PEE_FILE, 100);
    }
    
    public void stretch() {
        audio.playSample(STRETCH_FILE, 80);
    }
}
