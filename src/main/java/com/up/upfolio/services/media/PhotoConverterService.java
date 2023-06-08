package com.up.upfolio.services.media;

import org.apache.commons.lang3.tuple.Pair;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface PhotoConverterService {
    BufferedImage loadAndCrop(InputStream input, int x, int y, int width, int height);

    BufferedImage loadAndCrop(InputStream input, int x, int y, int side);

    Pair<Long, InputStream> getPng(BufferedImage img);
}
