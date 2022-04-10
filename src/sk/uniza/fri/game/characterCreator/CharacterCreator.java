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
import java.io.FileWriter;
import java.io.IOException;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class CharacterCreator extends JPanel {

    public CharacterCreator (int width, int height, Window window) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(false);

        //this.setLayout(new GridLayout());

        //JTextField textField = new JTextField(20);

        //head choosing
        Button headPrev = new Button("<");
        headPrev.setFocusable(false);
        this.add(headPrev);

        try {
            BufferedImage headImg = ImageIO.read(new File("sprites/head/head_1.png"));
            JLabel headLable = new JLabel(new ImageIcon(headImg));
            this.add(headLable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button headNext = new Button(">");
        headNext.setFocusable(false);
        this.add(headNext);

        //save button
        Button save = new Button("Save Character");
        save.setFocusable(false);
        save.addActionListener(a -> {
            window.startGameFrame();
            try {
                FileWriter newSave = new FileWriter("saves/test.frisave");
                newSave.write("test");
                newSave.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //this.add(textField);
        this.add(save);
    }
}
