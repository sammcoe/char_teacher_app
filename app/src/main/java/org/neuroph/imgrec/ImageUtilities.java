/**
 * Copyright 2010 Neuroph Project http://neuroph.sourceforge.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neuroph.imgrec;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neuroph.imgrec.image.Image;

/**
 * Contains various utility methods used for OCR.
 * 
 * @author Ivana Jovicic, Vladimir Kolarevic, Marko Ivanovic, Zoran Sevarac
 */
public class ImageUtilities {



    public static Image resizeImage(Image image, int width, int height) {
        return image.resize(width, height);
    }    


    /**
     * Creates and returns image from the given text.
     * @param text input text
     * @param font text font
     * @return image with input text
     */
//    public static BufferedImage createImageFromText(String text, Font font) {
//        //You may want to change these setting, or make them parameters
//        boolean isAntiAliased = true;
//        boolean usesFractionalMetrics = false;
//        FontRenderContext frc = new FontRenderContext(null, isAntiAliased, usesFractionalMetrics);
//        TextLayout layout = new TextLayout(text, font, frc);
//        Rectangle2D bounds = layout.getBounds();
//        int w = (int) Math.ceil(bounds.getWidth());
//        int h = (int) Math.ceil(bounds.getHeight()) + 2;
//        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); //for example;
//        Graphics2D g = image.createGraphics();
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, w, h);
//        g.setColor(Color.BLACK);
//        g.setFont(font);
//        Object antiAliased = isAntiAliased
//                ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
//        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAliased);
//        Object fractionalMetrics = usesFractionalMetrics
//                ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
//        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics);
//        g.drawString(text, (float) -bounds.getX(), (float) -bounds.getY());
//        g.dispose();
//
//        return image;
//    }
    
//    public static Bitmap createImageFromText(String text) {
//		TextView txtView = new TextView(null);
//		txtView.setBackgroundColor(Color.WHITE);
//		txtView.setTextColor(Color.BLACK);
//		txtView.setText(text);
//		txtView.setWidth(LayoutParams.WRAP_CONTENT);
//		txtView.setHeight(LayoutParams.WRAP_CONTENT);
//        Bitmap image = Bitmap.createBitmap(txtView.getWidth(), txtView.getHeight(), Bitmap.Config.ALPHA_8);
//        Canvas canvas = new Canvas(image);
//        txtView.draw(canvas);
//        
//        return image;
//    }



}
