/* @(#)BaselineTIFFTagSet.java
 * Copyright © 2017 Werner Randelshofer, Switzerland. Licensed under the MIT License.
 */
package org.monte.media.exif;

import org.monte.media.tiff.TagSet;
import org.monte.media.tiff.EnumValueFormatter;
import org.monte.media.tiff.*;
import static org.monte.media.tiff.TIFFTag.*;

/**
 * Syntethic tags for the entry information Multi-Picture format (MPF) tags
 * as found in MPO image files generated by Fujifilm Finepix Real 3D W1
 * cameras.
 * <p>
 * Source:
 * <p>
 * Multi-Picture Format
 * (February 4, 2009). Standard of the Camera &amp; Imaging Products Association.
 * CIPA DC-007-Translation-2009.
 * <a href="http://www.cipa.jp/english/hyoujunka/kikaku/pdf/DC-007_E.pdf">
 * http://www.cipa.jp/english/hyoujunka/kikaku/pdf/DC-007_E.pdf</a>
 *
 * 
 * @author Werner Randelshofer
 * @version 1.0 2010-07-24 Created.
 */
public class MPEntryTagSet extends TagSet {

    public final static int TAG_DependentParentImageFlag = -1;
    public final static int TAG_DependentChildImageFlag = -2;
    public final static int TAG_RepresentativeImageFlag = -3;
    public final static int TAG_ImageDataFormat = -4;
    public final static int TAG_MPTypeCode = -5;
    public final static int TAG_IndividualImageSize = -6;
    public final static int TAG_IndividualImageDataOffset = -7;
    public final static int TAG_DependentImage1EntryNumber = -8;
    public final static int TAG_DependentImage2EntryNumber = -9;
    //
    /**  Individual Image Data Offset
     * This field specifies the data offset to the beginning (i.e. SOI marker)
     * of an Individual Image. The field is specified as a LONG value, and the
     * byte order depends on MP Endian. This offset is specified relative to the
     * address of the MP Endian field in the MP Header (see §5.2), unless the
     * image is a First Individual Image, in which case the value of the offset
     * shall be NULL(00000000.H).
     */
    public final static TIFFTag IndividualImageDataOffset = new TIFFTag("IndividualImageDataOffset", TAG_IndividualImageDataOffset, LONG_MASK);
    /**  Individual Image Size
     * This field specifies the size of the image data between the SOI and EOI
     * markers of an Individual Image. The field is specified as a LONG value,
     * and the byte order depends on MP Endian.
     */
    public final static TIFFTag IndividualImageSize = new TIFFTag("IndividualImageSize", TAG_IndividualImageSize, LONG_MASK);
    //
    private static MPEntryTagSet instance;

    private MPEntryTagSet(TIFFTag[] tags) {
        super("MPEntry", tags);
    }

    public static TIFFTag get(int tagNumber) {
        return getInstance().getTag(tagNumber);
    }

    /** Returns a shared instance of a BaselineTIFFTagSet. */
    public static MPEntryTagSet getInstance() {
        if (instance == null) {
            TIFFTag[] tags = {//
                new TIFFTag("IndividualImageUniqueIDList", 0xb003, SHORT_MASK),
                new TIFFTag("TotalNumberOfCapturedFrames", 0xb004, SHORT_MASK),
                new TIFFTag("MPIndividualImageNumber", 0xb101, LONG_MASK),
                new TIFFTag("PanOrientation", 0xb201, LONG_MASK),
                new TIFFTag("PanOverlap_H", 0xb202, RATIONAL_MASK),
                new TIFFTag("PanOverlap_V", 0xb203, RATIONAL_MASK),
                new TIFFTag("BaseViewpointNum", 0xb204, LONG_MASK),
                new TIFFTag("ConvergenceAngle", 0xb205, SRATIONAL_MASK),
                new TIFFTag("BaselineLength", 0xb206, RATIONAL_MASK),
                new TIFFTag("VerticalDivergence", 0xb207, SRATIONAL_MASK),
                new TIFFTag("AxisDistance_X", 0xb208, SRATIONAL_MASK),
                new TIFFTag("AxisDistance_Y", 0xb209, SRATIONAL_MASK),
                new TIFFTag("AxisDistance_Z", 0xb20a, SRATIONAL_MASK),
                new TIFFTag("YawAngle", 0xb20b, SRATIONAL_MASK),
                new TIFFTag("PitchAngle", 0xb20c, SRATIONAL_MASK),
                new TIFFTag("RollAngle", 0xb20d, SRATIONAL_MASK), //
                //
                // Synthetic tags

                /** Dependent Parent Image Flag:
                 * this flag shall be set to 1 if the Individual Image is the parent image
                 * of another dependent Individual Image. Otherwise it shall be set to 0.
                 */
                new TIFFTag("DependentParentImageFlag", TAG_DependentParentImageFlag, BYTE_MASK, new EnumValueFormatter(
                "notAParent", 0,//
                "isParent", 1//
                )),
                /** Dependent Child Image Flag:
                 * this flag shall be set to 1 if the
                 * Individual Image is the dependent child image of another
                 * Individual Image. Otherwise it shall be set to 0.*
                 */
                new TIFFTag("DependentChildImageFlag", TAG_DependentChildImageFlag, BYTE_MASK, new EnumValueFormatter(
                "notAChild", 0,//
                "isChild", 1//
                )),
                /** Representative Image Flag:
                 * if the Individual Image is a Representative Image, this flag is set to 1.
                 * Otherwise it is set to 0. There can be only one Individual Image in an MP
                 * File with the Representative Image Flag set to 1.*/
                new TIFFTag("RepresentativeImageFlag", TAG_RepresentativeImageFlag, BYTE_MASK, new EnumValueFormatter(
                "notRepresentative", 0,//
                "isRepresentative", 1//
                )), //
                /** Image Data Format:
                 * this code specifies the image data format of an Individual Image:
                 * 0 : JPEG,
                 * other : (Reserved).
                 */
                new TIFFTag("ImageDataFormat", TAG_ImageDataFormat, BYTE_MASK, new EnumValueFormatter(
                "JPEG", 0//
                )), //
                /** MP Type Code:
                 * this code specifies the MP Type of an Individual Image.
                 */
                new TIFFTag("MPTypeCode", TAG_MPTypeCode, LONG_MASK, new EnumValueFormatter(
                "BaselineMPPrimaryImage", 0x30000, //
                "LargeThumbnailVGA", 0x10001, //
                "LargeThumbnailFullHD", 0x10002, //
                "MultiFramePanoramaImage", 0x20001, //
                "MultiFrameDisparityImage", 0x20002, //
                "MultiFrameMultiAngleImage", 0x20003, //
                "Undefined", 0x0 //
                //
                )), //

                IndividualImageSize,
                IndividualImageDataOffset,
                /** Dependent Image x Entry Number(s)
                 * An Entry Number specifies the position of an arbitrary MP Entry relative to
                 * the first MP Entry. The field is specified as a SHORT value, and the
                 * byte order depends on MP Endian.
                 *  - The Entry Number of the first MP Entry is 1.
                 *  - If there are no Dependent Images then both the Dependent Image 1 Entry
                 *    Number and the Dependent Image 2 Entry Number fields are set to 0.
                 *  - If the Individual Image is a parent image (i.e. the Dependent Parent
                 *    Image Flag in the Individual Image Attribute field is set to 1) the
                 *    flags in this field are set depending on the number of Dependent
                 *    Images:
                 *     a.) If there is one dependent child image the Dependent Image 1 Entry
                 *         Number is set to the entry number of the dependent child image,
                 *         and the Dependent Image 2 Entry Number flag is set to 0.
                 *     b.) If there are two Dependent Images, the entry number of each
                 *         respective child image is specified in the Dependent Image 1 and
                 *         Dependent Image 2 Entry Number fields.
                 * -  An Individual Image may have up to two Dependent Images.
                 */
                new TIFFTag("DependentImage1EntryNumber", TAG_DependentImage1EntryNumber, SHORT_MASK), //
                new TIFFTag("DependentImage2EntryNumber", TAG_DependentImage2EntryNumber, SHORT_MASK), //
            //
            };
            instance = new MPEntryTagSet(tags);

        }
        return instance;
    }
}
