package com.mycompany.sonicproyect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;

public class Minecraft extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    Flashlight flashlight = new Flashlight();
    private Clip audioClip;
    private static final int BLOCK_SIZE = 32;
    private BufferedImage buffer;
    private int woodCount = 0;
    private int leafCount = 0;
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Integer> leafColors = new ArrayList<>();
    private Random random = new Random();
    private Random colorRandom = new Random();
    private String selectedBlockType = "";
    private int mouseX = 400;
    private int mouseY = 400;

    
     private void playMusicBackground(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.start();
            audioClip.loop(60);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    
    public Minecraft() {
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        initializeBackground();
        generateTrees(60);
        startLeafColorAnimation(); 
        playMusicBackground("/home/arky/NetBeansProjects/SonicProject/src/main/java/com/mycompany/sonicproyect/aria.wav");
    }

    private void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
   

    private void initializeBackground() {
        for (int x = 0; x < buffer.getWidth(); x++) {
            for (int y = 0; y < buffer.getHeight(); y++) {
                buffer.setRGB(x, y, 0x256E00);
            }
        }
    }

    private void generateTrees(int quantity) {
        for (int i = 0; i < quantity; i++) {
            int posX = random.nextInt(buffer.getWidth() / BLOCK_SIZE) * BLOCK_SIZE;
            int posY = random.nextInt(buffer.getHeight() / BLOCK_SIZE) * BLOCK_SIZE;
            drawTree(posX, posY, colorRandom.nextInt(0x1000000));
        }
    }

    public void drawTree(int posX, int posY, int treeColor) {
        Block wood1 = new Block();
        wood1.setBuffer(buffer);
        wood1.drawSprite(posX, posY + BLOCK_SIZE, 0x491300, "madera");

        Block wood2 = new Block();
        wood2.setBuffer(buffer);
        wood2.drawSprite(posX, posY + 2 * BLOCK_SIZE, 0x491300, "madera");

        Block leaf1 = new Block();
        leaf1.setBuffer(buffer);
        leaf1.setType("hoja");
        leaf1.drawSprite(posX, posY, treeColor, "hojas");

        Block leaf2 = new Block();
        leaf2.setBuffer(buffer);
        leaf2.setType("hoja");
        leaf2.drawSprite(posX - BLOCK_SIZE, posY, treeColor, "hojas");

        Block leaf3 = new Block();
        leaf3.setBuffer(buffer);
        leaf3.setType("hoja");
        leaf3.drawSprite(posX + BLOCK_SIZE, posY, treeColor, "hojas");

        Block leaf4 = new Block();
        leaf4.setBuffer(buffer);
        leaf4.setType("hoja");
        leaf4.drawSprite(posX, posY - BLOCK_SIZE, treeColor, "hojas");

        blocks.add(wood1);
        blocks.add(wood2);
        blocks.add(leaf1);
        blocks.add(leaf2);
        blocks.add(leaf3);
        blocks.add(leaf4);
    }

  
    private void startLeafColorAnimation() {
        Thread animationThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);  
                    changeLeafColors();
                    repaint();  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.start();
    }

    private void changeLeafColors() {
        for (Block b : blocks) {
            if ("hoja".equals(b.getType())) {
                int newColor = colorRandom.nextInt(0x1000000); 
                b.setColor(newColor);  
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        initializeBackground();

        for (Block b : blocks) {
            b.drawSprite(b.getX0(), b.getY0(), b.getColor(), "Base");
        }

        BufferedImage mask = flashlight.createMask(mouseX, mouseY, buffer.getWidth(), buffer.getHeight());
        for (int x = 0; x < buffer.getWidth(); x++) {
            for (int y = 0; y < buffer.getHeight(); y++) {
                if (mask.getRGB(x, y) == Color.BLACK.getRGB()) {
                    buffer.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        g.drawImage(buffer, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Minecraft");
        Minecraft panel = new Minecraft();
        window.add(panel);
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int blockX = (e.getX() / BLOCK_SIZE) * BLOCK_SIZE;
        int blockY = (e.getY() / BLOCK_SIZE) * BLOCK_SIZE;

        if (e.getButton() == MouseEvent.BUTTON1) {
            for (Block b : blocks) {
                if (b.getX0() == blockX && b.getY0() == blockY && b.getVisible()) {
                    if (b.getColor() == 0x491300) {
                        playMusic("/home/arky/NetBeansProjects/SonicProject/src/main/java/com/mycompany/sonicproyect/wood.wav");
                        woodCount++;
                        selectedBlockType = "madera";
                        System.out.println("Wood broken. You now have " + woodCount);
                    } else if ("hoja".equals(b.getType())) {
                        playMusic("/home/arky/NetBeansProjects/SonicProject/src/main/java/com/mycompany/sonicproyect/grass.wav");
                        leafCount++;
                        leafColors.add(b.getColor());
                        selectedBlockType = "hoja";
                        System.out.println("Leaves broken. You now have " + leafCount);
                    }
                    b.setVisible(false);
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if ("madera".equals(selectedBlockType) && woodCount > 0) {
                Block newBlock = new Block();
                newBlock.setBuffer(buffer);
                newBlock.setType("madera");
                newBlock.drawSprite(blockX, blockY, 0x491300, "madera");
                blocks.add(newBlock);
                playMusic("/home/arky/NetBeansProjects/SonicProject/src/main/java/com/mycompany/sonicproyect/place.wav");
                woodCount--;
                System.out.println("Wood block placed. Remaining wood: " + woodCount);
            } else if ("hoja".equals(selectedBlockType) && leafCount > 0 && !leafColors.isEmpty()) {
                Block newBlock = new Block();
                newBlock.setBuffer(buffer);
                newBlock.setType("hoja");
                int leafColor = leafColors.remove(leafColors.size() - 1);
                newBlock.drawSprite(blockX, blockY, leafColor, "hojas");
                blocks.add(newBlock);
                playMusic("/home/arky/NetBeansProjects/SonicProject/src/main/java/com/mycompany/sonicproyect/grassplace.wav");
                leafCount--;
                System.out.println("Leaf block placed. Remaining leaves: " + leafCount);
            } else {
                System.out.println("You don't have enough materials to place a block.");
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1 && leafCount > 0) {
            selectedBlockType = "hoja";
            System.out.println("Leaf block selected. Available: " + leafCount);
        } else if (e.getKeyCode() == KeyEvent.VK_2 && woodCount > 0) {
            selectedBlockType = "madera";
            System.out.println("Wood block selected. Available: " + woodCount);
        } else {
            System.out.println("Not enough materials to select this block.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
