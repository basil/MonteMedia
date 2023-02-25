/* @(#)BufferFlag.java
 * Copyright © 2017 Werner Randelshofer, Switzerland. MIT License.
 */
package org.monte.media.av;

/**
 * {@code BufferFlag}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public enum BufferFlag {

    /**
     * Indicates that the data in this buffer should be ignored.
     */
    DISCARD,
    /**
     * Indicates that this Buffer holds an intra-coded picture, which can be
     * decoded independently.
     */
    KEYFRAME,
    /**
     * Indicates that the data in this buffer is at the end of the media.
     */
    END_OF_MEDIA,
    /**
     * Indicates that the data in this buffer is used for initializing the
     * decoding queue.
     * <p>
     * This flag is used when the media time of a track is set to a non-keyframe
     * sample. Thus decoding must start at a keyframe at an earlier time.
     * <p>
     * Decoders should decode the buffer. Encoders and Multiplexers should
     * discard the buffer.
     */
    PREFETCH,
    /**
     * Indicates that this buffer is known to have the same data as the previous
     * buffer. This may improve encoding performance.
     */
    SAME_DATA;
}
