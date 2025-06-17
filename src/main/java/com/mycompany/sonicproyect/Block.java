package com.mycompany.sonicproyect;

import java.awt.image.BufferedImage;

public class Block {

    BufferedImage buffer;
    int scale = 16;
    int x0;
    int y0;
    int color;
    String type;
    Boolean visible = true;
    private int originalColor;  // Nuevo campo para almacenar el color original

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void drawPixel(int x, int y, int color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color);
        }
    }

    public void drawSprite(int x0, int y0, int blockColor, String type) {
        int leafColor = blockColor;
        this.x0 = x0;
        this.y0 = y0;
        this.color = blockColor;

      
        if ("hoja".equals(type)) {
            this.originalColor = blockColor;  
        }

        int[][] sprite = {
            {1, 1},
            {1, 1},
        };

        if (visible) {
            for (int y = 0; y < sprite.length; y++) {
                for (int x = 0; x < sprite[y].length; x++) {
                    int color = leafColor;
                    for (int dy = 0; dy < scale; dy++) {
                        for (int dx = 0; dx < scale; dx++) {
                            drawPixel(x0 + (x * scale) + dx, y0 + (y * scale) + dy, color); 
                        }
                    }
                }
            }
        }
    }

 
    public int getOriginalColor() {
        return originalColor;
    }

   
    public void setOriginalColor(int originalColor) {
        this.originalColor = originalColor;
    }
}
