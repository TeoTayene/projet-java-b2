package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadPanel extends JPanel implements ActionListener, MouseListener {
    private static final int NORMAL_SIZE = 50;
    private static final int LARGE_SIZE = 250;
    private static final int ANIMATION_DURATION = 1000;
    private static final int FRAME_RATE = 30;

    private JLabel likeLabel;
    private int newSize = NORMAL_SIZE;
    private Timer enlargeTimer;
    private Timer shrinkTimer;
    private boolean isAnimating = false;

    public ThreadPanel() {
        setLayout(new BorderLayout());

        ImageIcon likeIcon = new ImageIcon("src/main/assets/heart.png");
        likeIcon.setImage(likeIcon.getImage().getScaledInstance(NORMAL_SIZE, NORMAL_SIZE, Image.SCALE_DEFAULT));
        likeLabel = new JLabel(likeIcon);

        add(likeLabel, BorderLayout.CENTER);

        likeLabel.addMouseListener(this);
    }

    private void animateHeart() {
        enlargeTimer = new Timer(1000 / FRAME_RATE, this);
        shrinkTimer = new Timer(1000 / FRAME_RATE, this);

        enlargeTimer.setInitialDelay(0);
        enlargeTimer.start();
    }

    private void updateHeartSize(int size) {
        ImageIcon resizedIcon = new ImageIcon("src/main/assets/heart.png");
        resizedIcon.setImage(resizedIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        likeLabel.setIcon(resizedIcon);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enlargeTimer) {
            int elapsed = ((Timer) e.getSource()).getInitialDelay();
            float progress = (float) elapsed / ANIMATION_DURATION;
            newSize = (int) (NORMAL_SIZE + (LARGE_SIZE - NORMAL_SIZE) * progress);
            if (newSize > LARGE_SIZE) newSize = LARGE_SIZE;

            updateHeartSize(newSize);

            if (elapsed >= ANIMATION_DURATION) {
                enlargeTimer.stop();
                shrinkTimer.setInitialDelay(0);
                shrinkTimer.start();
            } else {
                enlargeTimer.setInitialDelay(elapsed + 1000 / FRAME_RATE);
            }
        } else if (e.getSource() == shrinkTimer) {
            int elapsed = ((Timer) e.getSource()).getInitialDelay();
            float progress = (float) elapsed / ANIMATION_DURATION;
            newSize = (int) (LARGE_SIZE - (LARGE_SIZE - NORMAL_SIZE) * progress);
            if (newSize < NORMAL_SIZE) newSize = NORMAL_SIZE;

            updateHeartSize(newSize);

            if (elapsed >= ANIMATION_DURATION) {
                shrinkTimer.stop();
                isAnimating = false;
            } else {
                shrinkTimer.setInitialDelay(elapsed + 1000 / FRAME_RATE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int iconWidth = likeLabel.getIcon().getIconWidth();
        int iconHeight = likeLabel.getIcon().getIconHeight();

        int iconX = (likeLabel.getWidth() - iconWidth) / 2;
        int iconY = (likeLabel.getHeight() - iconHeight) / 2;

        int clickX = e.getX();
        int clickY = e.getY();

        if (clickX >= iconX && clickX <= iconX + iconWidth && clickY >= iconY && clickY <= iconY + iconHeight) {
//            System.out.println("clicker");
            if (!isAnimating) {
                isAnimating = true;
                animateHeart();
            }
        }
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
