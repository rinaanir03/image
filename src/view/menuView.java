package view;

import java.awt.*;


import controller.Application;
import model.Button;

public class menuView {

    public static boolean click = false;

    public Button [] menuButtons = {new Button(0, 215, 250, 45,"Linear"), new Button(0, 270, 250, 45, "Pow"), new Button(0, 325, 250, 45, "Blend"),
			new Button(0, 380, 250, 45, "Other"), new Button(0, 455, 250, 45, "Exit")};

    public void draw(Graphics g) throws FontFormatException {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(155, 225, 185));
        g2d.fillRect(0, 0, Application.WIDTH, Application.HEIGHT);


        Font font = new Font("Serif",Font.BOLD,40);
        g2d.setFont(font);
        g2d.setColor(new Color(45, 93, 67));
        g2d.drawString("IMAGE", 120, 130);
        g2d.drawString("PROCESSING", 60, 170);

        for (Button i :menuButtons) {
            i.setSizeF(35);
            i.draw(g2d);
        }

        g2d.setColor(new Color(232, 241, 236));
        g2d.fillRect(0, 440, 400, 1);

    }
}
