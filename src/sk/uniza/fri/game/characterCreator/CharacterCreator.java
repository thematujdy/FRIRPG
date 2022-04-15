package sk.uniza.fri.game.characterCreator;

import sk.uniza.fri.engine.window.Window;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class CharacterCreator extends JPanel {

    private int currHair;
    private final int hairCount;
    private final JLabel hairLabel;

    private int currHead;
    private final int headCount;
    private final JLabel headLabel;

    private int currBody;
    private final int bodyCount;
    private final JLabel bodyLabel;

    private int currLegs;
    private final int legsCount;
    private final JLabel legsLabel;

    private ArrayList<Byte> saveBytes;

    public CharacterCreator (int width, int height, Window window) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.saveBytes = new ArrayList<>();

        File hairDir = new File("sprites/hair");
        this.hairCount = Objects.requireNonNull(hairDir.listFiles()).length;
        this.currHair = 1;

        File headDir = new File("sprites/head");
        this.headCount = Objects.requireNonNull(headDir.listFiles()).length;
        this.currHead = 1;

        File bodyDir = new File("sprites/body");
        this.bodyCount = Objects.requireNonNull(bodyDir.listFiles()).length;
        this.currBody = 1;

        File legsDir = new File("sprites/legs");
        this.legsCount = Objects.requireNonNull(legsDir.listFiles()).length;
        this.currLegs = 1;

        //this.setLayout(new GridLayout());

        //JTextField textField = new JTextField(20);

        //menu button
        Button menuButton = new Button("Menu");
        menuButton.setFocusable(false);
        menuButton.addActionListener(a -> window.goToMenu());
        this.add(menuButton);

        /**
         * hair choosing
         */
        Button hairPrev = new Button("<");
        hairPrev.addActionListener(a -> {
            if ((this.currHair - 1) < 1) {
                this.currHair = this.hairCount;
            } else {
                this.currHair--;
            }
            this.changeHair();
        });
        hairPrev.setFocusable(false);
        this.add(hairPrev);

        this.hairLabel = new JLabel();
        this.changeHair();
        this.add(this.hairLabel);


        Button hairNext = new Button(">");
        hairNext.addActionListener(a -> {
            if ((this.currHair + 1) > this.hairCount) {
                this.currHair = 1;
            } else {
                this.currHair++;
            }
            this.changeHair();
        });
        hairNext.setFocusable(false);
        this.add(hairNext);

        /**
         * head choosing
         */
        Button headPrev = new Button("<");
        headPrev.addActionListener(a -> {
            if ((this.currHead - 1) < 1) {
                this.currHead = this.headCount;
            } else {
                this.currHead--;
            }
            this.changeHead();
        });
        headPrev.setFocusable(false);
        this.add(headPrev);

        this.headLabel = new JLabel();
        this.changeHead();
        this.add(this.headLabel);

        Button headNext = new Button(">");
        headNext.addActionListener(a -> {
            if ((this.currHead + 1) > this.headCount) {
                this.currHead = 1;
            } else {
                this.currHead++;
            }
            this.changeHead();
        });
        headNext.setFocusable(false);
        this.add(headNext);

        /**
         * body choosing
         */
        Button bodyPrev = new Button("<");
        bodyPrev.addActionListener(a -> {
            if ((this.currBody - 1) < 1) {
                this.currBody = this.bodyCount;
            } else {
                this.currBody--;
            }
            this.changeBody();
        });
        bodyPrev.setFocusable(false);
        this.add(bodyPrev);

        this.bodyLabel = new JLabel();
        this.changeBody();
        this.add(this.bodyLabel);

        Button bodyNext = new Button(">");
        bodyNext.addActionListener(a -> {
            if ((this.currBody + 1) > this.bodyCount) {
                this.currBody = 1;
            } else {
                this.currBody++;
            }
            this.changeBody();
        });
        bodyNext.setFocusable(false);
        this.add(bodyNext);

        /**
         * legs choosing
         */
        Button legsPrev = new Button("<");
        legsPrev.addActionListener(a -> {
            if ((this.currLegs - 1) < 1) {
                this.currLegs = this.legsCount;
            } else {
                this.currLegs--;
            }
            this.changeLegs();
        });
        legsPrev.setFocusable(false);
        this.add(legsPrev);

        this.legsLabel = new JLabel();
        this.changeLegs();
        this.add(this.legsLabel);

        Button legsNext = new Button(">");
        legsNext.addActionListener(a -> {
            if ((this.currLegs + 1) > this.legsCount) {
                this.currLegs = 1;
            } else {
                this.currLegs++;
            }
            this.changeLegs();
        });
        legsNext.setFocusable(false);
        this.add(legsNext);

        /**
         * save button
         */
        Button save = new Button("Save Character");
        save.setFocusable(false);
        save.addActionListener(a -> {
            File saveFile = new File("saves/test.frisave");
            Checksum generator = new CRC32();
            try (FileOutputStream outputStream = new FileOutputStream(saveFile)) {
                this.saveBytes.add((byte)this.currHair);
                this.saveBytes.add((byte)this.currHead);
                this.saveBytes.add((byte)this.currBody);
                this.saveBytes.add((byte)this.currLegs);
                generator.update(this.saveBytes.get(0));
                generator.update(this.saveBytes.get(1));
                generator.update(this.saveBytes.get(2));
                generator.update(this.saveBytes.get(3));
                byte[] bufferedBytes = ByteBuffer.allocate(4).putInt((int)(generator.getValue() % 65535)).array();
                outputStream.write(bufferedBytes , 0, bufferedBytes.length);
                for (Byte saveByte:this.saveBytes) {
                    outputStream.write(saveByte);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            window.startGameFrame(false);
        });

        //this.add(textField);
        this.add(save);
    }

    private void changeHair () {
        try {
            BufferedImage hair = ImageIO.read(new File("sprites/hair/hair_" + this.currHair + ".png"));
            this.hairLabel.setIcon(new ImageIcon(hair));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeHead () {
        try {
            BufferedImage head = ImageIO.read(new File("sprites/head/head_" + this.currHead + ".png"));
            this.headLabel.setIcon(new ImageIcon(head));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeBody() {
        try {
            BufferedImage body = ImageIO.read(new File("sprites/body/body_" + this.currBody + ".png"));
            this.bodyLabel.setIcon(new ImageIcon(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeLegs() {
        try {
            BufferedImage legs = ImageIO.read(new File("sprites/legs/legs_" + this.currLegs + ".png"));
            this.legsLabel.setIcon(new ImageIcon(legs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
