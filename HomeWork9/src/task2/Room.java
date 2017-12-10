package task2;

import task2.exceptions.*;
import task2.helpers.WordFormatHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private int square;
    private int usedSquare;
    private int illumination;
    private int windows;
    private static final int MIN_LIGHT = 300;
    private static final int MAX_LIGHT = 7000;
    private List<Furniture> furniture;
    private List<Lightbulb> illuminations;

    public Room(String name, int square, int windows) throws WrongLightException {
        this.name = name;
        this.square = square;
        usedSquare = 0;
        illumination = 0;
        this.windows = windows;
        furniture = new ArrayList<>();
        illuminations = new ArrayList<>();
        illumination += Window.LIGHTING * windows;
        if (illumination < MIN_LIGHT) {
            throw new IlluminanceTooLittleException(MIN_LIGHT, MAX_LIGHT, illumination);
        } else if (illumination > MAX_LIGHT) {
            throw new IlluminanceTooMuchException(MIN_LIGHT, MAX_LIGHT, illumination);
        }
    }


    public void add(Lightbulb illum) throws IlluminanceTooMuchException {
        if (illum.getLighting() + illumination > MAX_LIGHT) {
            throw new IlluminanceTooMuchException(MIN_LIGHT, MAX_LIGHT, illumination + illum.getLighting());
        } else {
            illumination += illum.getLighting();
            illuminations.add(illum);
        }
    }

    public void add(Furniture furniture) throws SpaceUsageTooMuchException {
        if (furniture.getSquare() + usedSquare > ((double) square) / 100 * 70) {
            throw new SpaceUsageTooMuchException(furniture.getSquare() + usedSquare, ((double) square) / 100 * 70);
        } else {
            usedSquare += furniture.getSquare();
            this.furniture.add(furniture);
        }
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public List<Furniture> getFurniture() {
        return furniture;
    }

    public void setFurniture(List<Furniture> furniture) {
        this.furniture = furniture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void describe() {
        System.out.println("    " + name);
        Map<Integer, Integer> lightbulbsPower = new HashMap<>();
        for (Lightbulb i : illuminations) {
            if (lightbulbsPower.containsKey(i.getLighting())) {
                lightbulbsPower.put(i.getLighting(), lightbulbsPower.get(i.getLighting()) + 1);
            } else {
                lightbulbsPower.put(i.getLighting(), 1);
            }
        }
        System.out.println(String.format("      Освещенность = %dлк: %d окна по %d лк, %d %s", illumination, windows, Window.LIGHTING, illuminations.size(), WordFormatHelper.getCorrectForm(illuminations.size())));
        for (Map.Entry<Integer, Integer> e :
                lightbulbsPower.entrySet()) {
            int ammount = e.getValue();
            System.out.println(String.format("            %d %s мощностью %dлк", ammount, WordFormatHelper.getCorrectForm(ammount), e.getKey()));
        }
        System.out.println(String.format("      Площадь = %d м2. Занято %d м2.", square, usedSquare));
        if (furniture.size() == 0) {
            System.out.println("        Мебели нет");
        } else {
            System.out.println("        Мебель:");
            for (Furniture f :
                    furniture) {
                f.describe();
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
