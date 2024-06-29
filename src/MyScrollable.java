import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MyScrollable extends JComponent implements Scrollable {
    public static final int VP_WIDTH = 200;
    private static final int ROW_COUNT = 40;

    public MyScrollable(String name) {
        super.setName(name);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        Component[] comps = getComponents();
        if (comps.length > 0) {
            int height = ROW_COUNT * comps[0].getPreferredSize().height;
            return new Dimension(VP_WIDTH, height);
        }

        return super.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return VP_WIDTH;
        }
        Component[] comps = getComponents();
        if (comps.length > 0) {
            return comps[0].getHeight() * (3 * ROW_COUNT / 4);
        }

        return getSize().height / 3;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return VP_WIDTH;
        }
        Component[] comps = getComponents();
        if (comps.length > 0) {
            return comps[0].getHeight();
        }
        return getSize().height / 3;
    }

}

