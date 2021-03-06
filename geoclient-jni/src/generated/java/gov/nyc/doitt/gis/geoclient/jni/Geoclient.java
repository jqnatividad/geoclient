/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Mon Jan 11 17:19:19 EST 2016 ----! */

package gov.nyc.doitt.gis.geoclient.jni;

import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

public interface Geoclient {


  /** Interface to C language function: <br> <code> void callgeo(char *  wa1, char *  wa2) </code> 
      @param wa1 a direct or array-backed {@link java.nio.ByteBuffer}
      @param wa2 a direct or array-backed {@link java.nio.ByteBuffer}   */
  public void callgeo(ByteBuffer wa1, ByteBuffer wa2);

  /** Interface to C language function: <br> <code> void callgeo(char *  wa1, char *  wa2) </code>    */
  public void callgeo(byte[] wa1, int wa1_offset, byte[] wa2, int wa2_offset);


} // end of class Geoclient
