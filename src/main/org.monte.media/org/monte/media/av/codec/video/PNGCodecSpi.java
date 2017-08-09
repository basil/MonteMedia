/* @(#)PNGCodecSpi
 * Copyright (c) 2017 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package org.monte.media.av.codec.video;

import org.monte.media.av.codec.audio.*;
import org.monte.media.av.Codec;
import org.monte.media.av.CodecSpi;

/**
 * PNGCodecSpi.
 *
 * @author Werner Randelshofer
 * @version $$Id$$
 */
public class PNGCodecSpi implements CodecSpi {

    @Override
    public PNGCodec create() {
       return new PNGCodec();
    }

}