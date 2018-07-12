package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HexGrid extends GridPane {

    byte[] data;
    int rows;

    private String byteToHex(byte b) {
        //no two's complement
        int value = (b < 0) ? b + 256 : b;
        return String.format("%02x", value);
    }

    private void buildHexGrid() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(4);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        //top guides
        for (int x = 0; x < 16; x++) {
            String hexGuide = Integer.toHexString(x);
            Label guide = new Label(hexGuide);
            guide.setId("guide");
            this.add(guide, x+1, 0);
        }
        //left guides
        for (int y = 0; y < this.rows; y++) {
            String hexGuide = Integer.toHexString(y);
            Label guide = new Label(hexGuide);
            guide.setId("guide");
            this.add(guide, 0, y+1);
        }
        //main hex stuff
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < 16; x++) {
                //Label dummyHex = new Label(randomByteString());
                int i = x + y * 16;
                Label dummyHex = new Label(byteToHex(this.data[i]));
                if (50 < (x + y * 16) && (x + y * 16) < 79) { //dummy values, don't panic
                    dummyHex.setId("highlighted-hex");
                } else {
                    if (x % 2 == 0) {
                        dummyHex.setId("default-hex-even");
                    } else {
                        dummyHex.setId("default-hex-odd");
                    }
                }
                this.add(dummyHex,  x+1, y+1);
            }
        }
    }

    public HexGrid(byte[] data) {
        this.data = data;
        this.rows = data.length / 16;
        buildHexGrid();
    }

    public HexGrid() {
        this.data = new byte[256];
        this.rows = data.length / 16;
        buildHexGrid();
    }

}
