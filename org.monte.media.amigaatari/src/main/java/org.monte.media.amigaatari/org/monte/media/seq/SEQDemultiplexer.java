/*
 * @(#)Main.java
 * Copyright © 2023 Werner Randelshofer, Switzerland. MIT License.
 */
package org.monte.media.seq;

import org.monte.media.av.Demultiplexer;
import org.monte.media.av.Track;

import java.io.File;
import java.io.IOException;

/**
 * {@code SEQDemultiplexer}.
 *
 * @author Werner Randelshofer
 */
public class SEQDemultiplexer extends SEQReader implements Demultiplexer {

    private Track[] tracks;

    public SEQDemultiplexer(File file) throws IOException {
        super(file);
    }

    public SEQDemultiplexer(File file, boolean variableFramerate) throws IOException {
        super(file, variableFramerate);
    }

    @Override
    public Track[] getTracks() {
        if (tracks == null) {
            tracks = new Track[]{new SEQTrack(this)};
        }
        return tracks.clone();
    }
}
