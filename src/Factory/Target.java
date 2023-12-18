package Factory;

import javax.swing.*;

public class Target implements GridComponent {
    private final String targetMark = "[TG]";
    private final ImageIcon targetIcon = new ImageIcon("src/IconImages/TargetIconImage.png");
    @Override
    public String getCharMark() {
        return this.targetMark;
    }
    @Override
    public Icon getIcon() {
        return this.targetIcon;
    }
}
