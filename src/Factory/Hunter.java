package Factory;

import javax.swing.*;

public class Hunter implements GridComponent {
    private final String hunterMark = "[HX]";
    private final ImageIcon hunterIcon = new ImageIcon("src/IconImages/HunterIconImage.png");
    @Override
    public String getCharMark() {
        return this.hunterMark;
    }
    @Override
    public Icon getIcon() {
        return this.hunterIcon;
    }
}
