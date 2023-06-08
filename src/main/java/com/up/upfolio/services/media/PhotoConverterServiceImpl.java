package com.up.upfolio.services.media;

import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhotoConverterServiceImpl implements PhotoConverterService {
    private static final String DEST_FORMAT = "png";

    @Override
    public BufferedImage loadAndCrop(InputStream input, int x, int y, int width, int height) {
        try {
            Image image = ImageIO.read(input);

            if (image == null) throw new GenericApiErrorException(ErrorDescriptor.PHOTO_IS_INVALID);

            validateDimensions(image.getWidth(null), image.getHeight(null), x, y, width, height);

            BufferedImage bufferedImage = this.toBufferedImage(image);

            return bufferedImage.getSubimage(x, y, width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public BufferedImage loadAndCrop(InputStream input, int x, int y, int side) {
        return loadAndCrop(input, x, y, side, side);
    }

    @Override
    public Pair<Long, InputStream> getPng(BufferedImage img) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            if (!ImageIO.write(img, DEST_FORMAT, os))
                throw new GenericApiErrorException(ErrorDescriptor.INTERNAL_SERVER_ERROR);

            return new ImmutablePair<>((long) os.size(), new ByteArrayInputStream(os.toByteArray()));

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    // https://stackoverflow.com/a/13605411
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage buffImg)
            return buffImg;

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    private void validateDimensions(int imageWidth, int imageHeight, int cropX, int cropY, int cropWidth, int cropHeight) {
        if (cropWidth <= 0 || cropHeight <= 0 || imageWidth <= 0 || imageHeight <= 0)
            throw new GenericApiErrorException(ErrorDescriptor.PHOTO_DIMENSIONS_ARE_INVALID);

        if ((cropWidth + cropX) > imageWidth || (cropHeight + cropY) > imageHeight)
            throw new GenericApiErrorException(ErrorDescriptor.PHOTO_DIMENSIONS_ARE_INVALID);
    }
}
