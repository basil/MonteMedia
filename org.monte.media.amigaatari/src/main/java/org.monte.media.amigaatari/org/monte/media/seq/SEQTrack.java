/* @(#)SEQTrack.java
 * Copyright © 2017 Werner Randelshofer, Switzerland. MIT License.
 */

package org.monte.media.seq;

import org.monte.media.amigabitmap.AmigaBitmapImage;
import org.monte.media.av.Buffer;
import org.monte.media.av.Format;
import org.monte.media.av.FormatKeys.MediaType;
import org.monte.media.av.Track;
import org.monte.media.math.Rational;

import static org.monte.media.av.BufferFlag.DISCARD;
import static org.monte.media.av.BufferFlag.KEYFRAME;
import static org.monte.media.av.FormatKeys.EncodingKey;
import static org.monte.media.av.FormatKeys.MIME_JAVA;
import static org.monte.media.av.FormatKeys.MediaTypeKey;
import static org.monte.media.av.FormatKeys.MimeTypeKey;
import static org.monte.media.av.codec.video.VideoFormatKeys.ENCODING_BUFFERED_IMAGE;

/**
 * {@code SEQTrack}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SEQTrack implements Track {
    private SEQDemultiplexer demux;
    private long position;
    private Format outputFormat = new Format(
            MediaTypeKey, MediaType.VIDEO,
            MimeTypeKey, MIME_JAVA,
            EncodingKey, ENCODING_BUFFERED_IMAGE);

    public SEQTrack(SEQDemultiplexer demux) {
        this.demux = demux;
    }

    @Override
    public long getSampleCount() {
        return demux.getFrameCount();
    }

    @Override
    public void setPosition(long pos) {
        this.position = pos;
    }

    @Override
    public long getPosition() {
        return position;
    }

    @Override
    public void read(Buffer buf) {
        if (position < demux.getFrameCount()) {
            buf.setFlagsTo(KEYFRAME);
            if (!(buf.data instanceof AmigaBitmapImage)) {
                buf.data = demux.createCompatibleBitmap();
            }
            demux.readFrame((int) position, (AmigaBitmapImage) buf.data);
            buf.sampleDuration = new Rational(demux.getDuration((int) position), demux.getJiffies());
            buf.format = outputFormat;
            position++;
        } else {
            buf.setFlagsTo(DISCARD);
        }
    }

    @Override
    public Format getFormat() {
        return outputFormat;
    }
}
