/* @(#)ASCIIValueFormatter.java
 * Copyright © 2017 Werner Randelshofer, Switzerland. MIT License.
 */
package org.monte.media.tiff;

import java.io.UnsupportedEncodingException;

/**
 * Formats byte arrays as string.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ASCIIValueFormatter implements ValueFormatter {

    /**
     * Creates a new enumeration.
     * The enumeration consists of a list of String=Integer pairs.
     */
    public ASCIIValueFormatter() {
    }

    @Override
    public Object format(Object value) {
        if (value instanceof byte[]) {
            try {
                return new String((byte[]) value, "ASCII");
            } catch (UnsupportedEncodingException ex) {
                throw new InternalError("ASCII not supported");
            }
        }
        return value;
    }

    @Override
    public Object prettyFormat(Object value) {
        return format(value);
    }

    @Override
    public String descriptionFormat(Object data) {
        return null;
    }
}
