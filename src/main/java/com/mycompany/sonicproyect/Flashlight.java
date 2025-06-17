package com.mycompany.sonicproyect;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Flashlight {

    public BufferedImage createMask(int cx, int cy, int width, int height) {
        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int radius = 200;
        int radiusSq = radius * radius;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int dx = x - cx;
                int dy = y - cy;
                if (dx * dx + dy * dy > radiusSq) {
                    mask.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    mask.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return mask;
    }
}
