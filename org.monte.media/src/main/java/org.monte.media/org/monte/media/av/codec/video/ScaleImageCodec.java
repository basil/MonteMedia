/*
 * @(#)ScaleImageCodec.java
 * Copyright © 2023 Werner Randelshofer, Switzerland. MIT License.
 */
package org.monte.media.av.codec.video;

import org.monte.media.av.Buffer;
import org.monte.media.av.Format;
import org.monte.media.av.FormatKeys.MediaType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

import static org.monte.media.av.BufferFlag.DISCARD;
import static org.monte.media.av.FormatKeys.EncodingKey;
import static org.monte.media.av.FormatKeys.MIME_JAVA;
import static org.monte.media.av.FormatKeys.MediaTypeKey;
import static org.monte.media.av.FormatKeys.MimeTypeKey;
import static org.monte.media.av.codec.video.VideoFormatKeys.DepthKey;
import static org.monte.media.av.codec.video.VideoFormatKeys.ENCODING_BUFFERED_IMAGE;
import static org.monte.media.av.codec.video.VideoFormatKeys.HeightKey;
import static org.monte.media.av.codec.video.VideoFormatKeys.WidthKey;

/**
 * Scales a buffered image.
 *
 * @author Werner Randelshofer
 */
public class ScaleImageCodec extends AbstractVideoCodec {

    private Object interpolationRenderingHint = RenderingHints.VALUE_INTERPOLATION_BICUBIC;

    public ScaleImageCodec() {
        super(new Format[]{
                        new Format(MediaTypeKey, MediaType.VIDEO, MimeTypeKey, MIME_JAVA,
                                EncodingKey, ENCODING_BUFFERED_IMAGE), //
                },
                new Format[]{
                        new Format(MediaTypeKey, MediaType.VIDEO, MimeTypeKey, MIME_JAVA,
                                EncodingKey, ENCODING_BUFFERED_IMAGE), //
                }//
        );
        name = "Scale Image";
    }

    @Override
    public Format setOutputFormat(Format f) {
        if (!f.containsKey(WidthKey) || !f.containsKey(HeightKey)) {
            throw new IllegalArgumentException("Output format must specify width and height.");
        }
        Format fNew = super.setOutputFormat(f.prepend(DepthKey, 24));
        return fNew;
    }

    @Override
    public int process(Buffer in, Buffer out) {
        out.setMetaTo(in);
        out.format = outputFormat;

        if (in.isFlag(DISCARD)) {
            return CODEC_OK;
        }
        BufferedImage imgIn = (BufferedImage) in.data;
        if (imgIn == null) {
            out.setFlag(DISCARD);
            return CODEC_FAILED;
        }

        BufferedImage imgOut = null;
        if (out.data instanceof BufferedImage) {
            imgOut = (BufferedImage) out.data;
            if (imgOut.getWidth() != outputFormat.get(WidthKey)
                    || imgOut.getHeight() != outputFormat.get(HeightKey)//
                    || imgOut.getType() != imgIn.getType()) {
                imgOut = null;
            }
        }
        if (imgOut == null) {
            if (imgIn.getColorModel() instanceof IndexColorModel) {
                imgOut = new BufferedImage(outputFormat.get(WidthKey), outputFormat.get(HeightKey), imgIn.getType(), (IndexColorModel) imgIn.getColorModel());
            } else {
                imgOut = new BufferedImage(outputFormat.get(WidthKey), outputFormat.get(HeightKey), imgIn.getType());
            }

        }
        Graphics2D g = imgOut.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolationRenderingHint);
        g.drawImage(imgIn, 0, 0, imgOut.getWidth() - 1, imgOut.getHeight() - 1, 0, 0, imgIn.getWidth() - 1, imgIn.getHeight() - 1, null);
        g.dispose();

        out.data = imgOut;

        return CODEC_OK;
    }

    public void setInterpolationRenderingHint(Object interpolationRenderingHint) {
        this.interpolationRenderingHint = interpolationRenderingHint;
    }
}
