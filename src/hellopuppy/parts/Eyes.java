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

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

/**
 *
 * @author Johannes C. Schneider
 */
public class Eyes {

    final GraphicsLCD lcd;
    final int width, height, border;
    final int rectWidth, rectHeight, eyeRadius;

    public Eyes() {
        lcd = BrickFinder.getDefault().getGraphicsLCD();
        width = lcd.getWidth(); // 128
        height = lcd.getHeight(); // 178
        border = width / 16;
        rectWidth = (width - 3 * border) / 2;
        rectHeight = (height - 2 * border);
        eyeRadius = Math.round(0.5f * rectWidth / 2.0f);

    }

    // width
    // ************************************
    // *........b(order)..................*
    // *.-b-*********.-b-.***********.-b-.*
    // *....*.......*.....*.........*.....*
    // *....*.......*.....*.........*.....*
    // *....*.......*.....*.........*.....*
    // *....*...*...*.....*....*....*.....*..height
    // *....*.*****.*.....*..*****..*.....*
    // *....*.*****.*.....*..*****..*.....*
    // *....*...*...*.....*....*....*.....*
    // *....*.......*.....*.........*.....*
    // *....*.......*.....*.........*.....*
    // *....*.......*.....*.........*.....*
    // *....*********.....***********.....*
    // *........b..............b..........*
    // ************************************
    private void initScreenWithRectangles() {
        lcd.clear();
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.drawRect(border, border, rectWidth, rectHeight);
        lcd.drawRect(border + rectWidth + border, border, rectWidth, rectHeight);
    }

    private void initScreenWithSmallRectangles() {
        lcd.clear();
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.drawRect(border + rectWidth / 4, border + rectHeight / 4, rectWidth / 2, rectHeight / 2);
        lcd.drawRect(border + rectWidth + border + rectWidth / 4, border + rectHeight / 4, rectWidth / 2,
                rectHeight / 2);
    }

    private void drawCenteredEyes() {
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.fillArc(border + rectWidth / 2 - eyeRadius / 2, height / 2 - eyeRadius / 2, eyeRadius, eyeRadius, 0, 360);
        lcd.fillArc(border + rectWidth + border + rectWidth / 2 - eyeRadius / 2, height / 2 - eyeRadius / 2, eyeRadius,
                eyeRadius, 0, 360);
    }

    private void drawSmallCenteredEyes() {
        int smallEyeRadius = this.eyeRadius / 2;
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.fillArc(border + rectWidth / 2 - smallEyeRadius / 2, height / 2 - smallEyeRadius / 2, smallEyeRadius,
                smallEyeRadius, 0, 360);
        lcd.fillArc(border + rectWidth + border + rectWidth / 2 - smallEyeRadius / 2, height / 2 - smallEyeRadius / 2,
                smallEyeRadius, smallEyeRadius, 0, 360);
    }

    private void lookUpperLeft() {
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.fillArc(border + eyeRadius + 1 - eyeRadius / 2, border + eyeRadius + 1 - eyeRadius / 2, eyeRadius,
                eyeRadius, 0, 360);
        lcd.fillArc(border + rectWidth + border + eyeRadius + 1 - eyeRadius / 2, border + eyeRadius + 1 - eyeRadius / 2,
                eyeRadius, eyeRadius, 0, 360);
    }

    private void lookUpperRight() {
        lcd.setColor(GraphicsLCD.BLACK);
        lcd.fillArc(border + rectWidth - 1 - eyeRadius, border + eyeRadius + 1 - eyeRadius / 2, eyeRadius,
                eyeRadius, 0, 360);
        lcd.fillArc(border + rectWidth + border + rectWidth - 1 - eyeRadius, border + eyeRadius + 1 - eyeRadius / 2,
                eyeRadius, eyeRadius, 0, 360);
    }

    public void lookAround() {
        initScreenWithRectangles();
        lookUpperLeft();
        Delay.msDelay(500);
        initScreenWithRectangles();
        drawCenteredEyes();
        Delay.msDelay(1000);
        initScreenWithRectangles();
        lookUpperRight();
        Delay.msDelay(500);
        initScreenWithRectangles();
        drawCenteredEyes();
    }

    public void lookWithSmallEyes() {
        initScreenWithRectangles();
        drawSmallCenteredEyes();
    }

    public void lookWithNormalEyes() {
        initScreenWithRectangles();
        drawCenteredEyes();
    }
    
    public void lookBetweenSleepAndNormal() {
        initScreenWithSmallRectangles();
        drawSmallCenteredEyes();
    }

    public void lookSleeping() {
        initScreenWithSmallRectangles();
    }

    public void endSleepingLook() {
        initScreenWithRectangles();
        lookWithNormalEyes();
    }

}
