package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Refrigirator extends Gadget {

    private byte temperatureDesired;

    public Refrigirator() {
    }

    public byte getTemperatureDesired() {
        return temperatureDesired;
    }

    public void setTemperatureDesired(byte temperatureDesired) {
        this.temperatureDesired = temperatureDesired;
    }

    @Override
    public void setOn(boolean on) {
        if (on) {
            Byte choice = null;
            do {
                System.out.println("Установите желаемую температуру");
                try {
                    choice = Byte.parseByte(new BufferedReader(new InputStreamReader(System.in)).readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("Введите число");
                }
            } while (choice == null);
            setTemperatureDesired(choice);
        }
        super.setOn(on);
    }

    @Override
    public void doAction() {
        if (isOn()) {
            System.out.println("Понижение температуры до " + temperatureDesired);
        }
    }
}
