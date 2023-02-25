/* @(#)ANIMWriterSpi.java
 * Copyright © 2017 Werner Randelshofer, Switzerland. MIT License.
 */
package org.monte.media.anim;

import org.monte.media.av.Format;
import org.monte.media.av.MovieWriter;
import org.monte.media.av.MovieWriterSpi;

import javax.imageio.stream.ImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ANIMWriterSpi.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ANIMWriterSpi implements MovieWriterSpi {

    private final static List<String> extensions = Collections.unmodifiableList(Arrays.asList(new String[]{"anim"}));

    @Override
    public MovieWriter create(File file) throws IOException {
        return new ANIMWriter(file);
    }

    @Override
    public MovieWriter create(ImageOutputStream out) throws IOException {
        return new ANIMWriter(out);
    }

    @Override
    public List<String> getExtensions() {
        return extensions;
    }

    @Override
    public Format getFileFormat() {
        return ANIMWriter.ANIM;
    }

}
