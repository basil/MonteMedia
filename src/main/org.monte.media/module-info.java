/* @(#)module-info.java
 * Copyright © 2007 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

/**
 * Provides classes for processing audio, video and images.
 * Also includes a small screen-recorder application, and classes for parsing
 * EXIF meta data..
 * <p>
 * Supports the following container formats:
 * <table>
 * <tr><td>IFF</td><td>Electronic Arts Interchange File Format</td><td>Read,
 * Write</td></tr>
 * <tr><td>RIFF</td><td>Microsoft Resource Interchange File
 * Format</td><td>Read</td></tr>
 * <tr><td>AVI</td><td>Microsoft Audio Video Interchange</td><td>Read,
 * Write</td></tr>
 * <tr><td>MOV</td><td>Apple QuickTime</td><td>Write</td></tr>
 * <tr><td>JFIF</td><td>JPEG File Interchange Format</td><td>Read,
 * Write</td></tr>
 * <tr><td>MP3</td><td>MP3 Elementary Stream</td><td>Read</td></tr>
 * <tr><td>MPO</td><td>MultiPicture Object Format</td><td>Read</td></tr>
 * <tr><td>TIFF</td><td>Tagged Image File Format</td><td>Read, Write</td></tr>
 * </table>
 * <p>
 * Supports the following video encodings:
 * <table>
 * <tr><td>Op5</td><td>ANIM</td><td>Amiga Animation</td><td>Decode,
 * Encode</td></tr>
 * <tr><td>Op7 Short/Long</td><td>ANIM</td><td>Amiga
 * Animation</td><td>Decode</td></tr>
 * <tr><td>Op8 Short/Long</td><td>ANIM</td><td>Amiga
 * Animation</td><td>Decode</td></tr>
 * <tr><td>PNG</td><td>AVI, MOV</td><td>Portable Networks
 * Graphics</td><td>Decode, Encode</td></tr>
 * <tr><td>DIB</td><td>AVI</td><td>Microsoft Device Independent
 * Bitmap</td><td>Encode</td></tr>
 * <tr><td>Run Length</td><td>AVI</td><td>Run Length
 * Encoding</td><td></td>Encode</tr>
 * <tr><td>ZMBV</td><td>AVI</td><td>DosBox Capture
 * Codec</td><td></td>Decode</tr>
 * <tr><td>MJPG</td><td>AVI, MOV</td><td>Motion JPEG</td><td></td>Decode,
 * Encode</tr>
 * <tr><td>SEQ</td><td>SEQ<td></td>Atari Cyber Paint
 * Sequence</td><td>Decode</td></tr>
 * <tr><td></td><td></td><td></td><td></td></tr>
 * <tr><td></td><td></td><td></td><td></td></tr>
 * </table>
 * <p>
 * Supports the following audio encodings:
 * <table>
 * <tr><td>PCM</td><td>AVI, MOV</td><td>Pulse Code Modulation</td><td>Decode,
 * Encode</td></tr>
 * <tr><td>8SVX</td><td>8SVX, ANIM</td><td>Pulse Code
 * Modulation</td><td></td>Decode</tr>
 * </table>
 * <p>
 * Provides the following image encodings to javax.imageio:
 * <table>
 * <tr><td>ILBM</td><td>IFF</td><td>Amiga Interleaved Bitmap</td><td>Decode,
 * Encode</td></tr>
 * <tr><td>PBM</td><td>IFF</td><td>Amiga Packed Bitmap</td><td>Decode,
 * Encode</td></tr>
 * <tr><td>PGM</td><td>PGM</td><td>Netpbm grayscale
 * image</td><td>Decode</td></tr>
 * <tr><td>CMYK</td><td>JPEG</td><td>JPEG CMYK Image</td><td>Decode</td></tr>
 * <tr><td>MPO</td><td>MPO</td><td>MultiPicture Object Format</td><td>Decode</td></tr>
 * </table>
 * <p>
 * Supports the following meta-data encodings:
 * <table>
 * <tr><td>EXIF</td><td>AVI, JPEG, MPO</td><td>Exchangeable Image File
 * Format</td><td>Decode</td></tr>
 * </table>
 */
module org.monte.media {
    requires java.desktop;
    requires javafx.swing;
    requires java.prefs;

    exports org.monte.media.beans;
    exports org.monte.media.anim;
    exports org.monte.media.av;
    exports org.monte.media.av.codec.audio;
    exports org.monte.media.av.codec.time;
    exports org.monte.media.av.codec.video;
    exports org.monte.media.avi;
    exports org.monte.media.avi.codec.audio;
    exports org.monte.media.avi.codec.video;
    exports org.monte.media.binary;
    exports org.monte.media.bitmap;
    exports org.monte.media.bitmap.codec.video;
    exports org.monte.media.concurrent;
    exports org.monte.media.color;
    exports org.monte.media.eightsvx;
    exports org.monte.media.exception;
    exports org.monte.media.exif;
    exports org.monte.media.iff;
    exports org.monte.media.ilbm;
    exports org.monte.media.image;
    exports org.monte.media.imgseq;
    exports org.monte.media.interpolator;
    exports org.monte.media.io;
    exports org.monte.media.javafx;
    exports org.monte.media.jfif;
    exports org.monte.media.jpeg;
    exports org.monte.media.math;
    exports org.monte.media.mjpg;
    exports org.monte.media.mp3;
    exports org.monte.media.mpo;
    exports org.monte.media.pbm;
    exports org.monte.media.pgm;
    exports org.monte.media.player;
    exports org.monte.media.quicktime;
    exports org.monte.media.quicktime.codec.audio;
    exports org.monte.media.quicktime.codec.video;
    exports org.monte.media.riff;
    exports org.monte.media.screenrecorder;
    exports org.monte.media.seq;
    exports org.monte.media.swing;
    exports org.monte.media.swing.border;
    exports org.monte.media.swing.datatransfer;
    exports org.monte.media.swing.movie;
    exports org.monte.media.swing.plaf;
    exports org.monte.media.swing.player;
    exports org.monte.media.tree;
    exports org.monte.media.tiff;
    exports org.monte.media.util;
    exports org.monte.media.util.stream;

    uses org.monte.media.av.CodecSpi;
    uses org.monte.media.av.MovieWriterSpi;
    uses org.monte.media.av.MovieReaderSpi;

    provides org.monte.media.av.CodecSpi with
            org.monte.media.bitmap.codec.video.BitmapCodecSpi,
            org.monte.media.av.codec.video.JPEGCodecSpi,
            org.monte.media.av.codec.video.PNGCodecSpi,
            org.monte.media.av.codec.video.TechSmithCodecSpi;

    provides org.monte.media.av.MovieWriterSpi with
            org.monte.media.anim.ANIMWriterSpi,
            org.monte.media.quicktime.QuickTimeWriterSpi,
            org.monte.media.avi.AVIWriterSpi;

    provides org.monte.media.av.MovieReaderSpi with
            org.monte.media.avi.AVIReaderSpi,
            org.monte.media.quicktime.QuickTimeReaderSpi;

    provides javax.imageio.spi.ImageReaderSpi with
            org.monte.media.ilbm.ILBMImageReaderSpi,
            org.monte.media.pbm.PBMImageReaderSpi,
            org.monte.media.pgm.PGMImageReaderSpi,
            org.monte.media.jpeg.CMYKJPEGImageReaderSpi,
            org.monte.media.mpo.MPOImageReaderSpi;
}