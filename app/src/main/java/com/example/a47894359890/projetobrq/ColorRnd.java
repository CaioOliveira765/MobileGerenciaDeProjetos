package com.example.a47894359890.projetobrq;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class ColorRnd {

    public static ColorRnd DEFAULT;

    static {
        DEFAULT = create(Arrays.asList(
                0xff558ae0,
                0xff00bc0f,
                0xfff2d500,
                0xfff10000,
                0xffff9000,
                0xff00c3ff,
                0xff6600ff,
                0xfff74fee,
                0xff4f4239
        ));
}

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorRnd create(List<Integer> colorList) {
        return new ColorRnd(colorList);
    }

    private ColorRnd(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}
