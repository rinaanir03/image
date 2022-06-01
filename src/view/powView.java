package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Application;
import model.Button;
import model.powModel;

public class powView {

    public static boolean click_p = false;

    public Button [] powButtons = {new Button(0, 50, 255, 25,""), new Button(5, 160, 200, 40,"Pow"),
            new Button(180, 220, 200, 40, "Contrast"), new Button(5, 280, 200, 40, "RGB plot"),
            new Button(260, 410, 200, 20, "")};

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(162, 218, 184));
        g2d.fillRect(0, 0, Application.WIDTH, Application.HEIGHT);

        Font f = new Font("Serif",Font.BOLD, 25);
        g2d.setFont(f);
        g2d.setColor(new Color(46, 86, 64));
        g2d.drawString("CHOOSE IMAGE", 10, 45);
        g2d.drawString("MENU", 285, 400);


        for (Button i :powButtons) {
            i.setSizeF(35);
            i.draw(g2d);
        }

        if (!powModel.getPicturePath().isEmpty()) {
            Font font = new Font("Serif",Font.BOLD, 20);
            g2d.setFont(font);
            g2d.setColor(new Color(221,238,229));
            g2d.drawString("Okej", 270, 70);
        }


    }
}