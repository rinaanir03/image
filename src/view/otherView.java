package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Application;
import model.Button;
import model.otherModel;

public class otherView {

    public static boolean click_o = false;


    public Button [] otherButtons = {new Button(0, 50, 255, 25,""), new Button(10, 130, 200, 50,"Box"),
            new Button(10, 190, 200, 50,"Gauss"), new Button(10, 250, 200, 50,"Sharpen"),
            new Button(10, 310, 200, 50,"Max"),
            new Button(10, 370, 200, 50,"Mediana"),
            new Button(10, 430, 200, 50,"Min"),
            new Button(260, 500, 200, 20, "")};

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(155, 225, 185));
        g2d.fillRect(0, 0, Application.WIDTH, Application.HEIGHT);

        Font f = new Font("Serif",Font.BOLD, 26);
        g2d.setFont(f);
        g2d.setColor(new Color(45, 93, 67));
        g2d.drawString("CHOOSE IMAGE", 10, 45);
        g2d.drawString("MENU", 280, 498);
        g2d.drawString("CHOOSE MODE", 15, 120);

        for (Button i :otherButtons) {
            i.setSizeF(35);
            i.draw(g2d);
        }


    }
}