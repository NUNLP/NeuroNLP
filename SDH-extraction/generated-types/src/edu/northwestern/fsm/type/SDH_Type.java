
/* First created by JCasGen Thu May 25 11:48:07 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity_Type;

/** Disorder annotation
 * Updated by JCasGen Thu May 25 11:48:07 CDT 2017
 * @generated */
public class SDH_Type extends NamedEntity_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SDH.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.fsm.type.SDH");
 
  /** @generated */
  final Feature casFeat_side;
  /** @generated */
  final int     casFeatCode_side;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSide(int addr) {
        if (featOkTst && casFeat_side == null)
      jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDH");
    return ll_cas.ll_getStringValue(addr, casFeatCode_side);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSide(int addr, String v) {
        if (featOkTst && casFeat_side == null)
      jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDH");
    ll_cas.ll_setStringValue(addr, casFeatCode_side, v);}
    
  
 
  /** @generated */
  final Feature casFeat_thickness;
  /** @generated */
  final int     casFeatCode_thickness;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getThickness(int addr) {
        if (featOkTst && casFeat_thickness == null)
      jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDH");
    return ll_cas.ll_getIntValue(addr, casFeatCode_thickness);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setThickness(int addr, int v) {
        if (featOkTst && casFeat_thickness == null)
      jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDH");
    ll_cas.ll_setIntValue(addr, casFeatCode_thickness, v);}
    
  
 
  /** @generated */
  final Feature casFeat_convexity;
  /** @generated */
  final int     casFeatCode_convexity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getConvexity(int addr) {
        if (featOkTst && casFeat_convexity == null)
      jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDH");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_convexity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConvexity(int addr, boolean v) {
        if (featOkTst && casFeat_convexity == null)
      jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDH");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_convexity, v);}
    
  
 
  /** @generated */
  final Feature casFeat_shift;
  /** @generated */
  final int     casFeatCode_shift;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getShift(int addr) {
        if (featOkTst && casFeat_shift == null)
      jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDH");
    return ll_cas.ll_getIntValue(addr, casFeatCode_shift);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setShift(int addr, int v) {
        if (featOkTst && casFeat_shift == null)
      jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDH");
    ll_cas.ll_setIntValue(addr, casFeatCode_shift, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SDH_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_side = jcas.getRequiredFeatureDE(casType, "side", "uima.cas.String", featOkTst);
    casFeatCode_side  = (null == casFeat_side) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_side).getCode();

 
    casFeat_thickness = jcas.getRequiredFeatureDE(casType, "thickness", "uima.cas.Integer", featOkTst);
    casFeatCode_thickness  = (null == casFeat_thickness) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_thickness).getCode();

 
    casFeat_convexity = jcas.getRequiredFeatureDE(casType, "convexity", "uima.cas.Boolean", featOkTst);
    casFeatCode_convexity  = (null == casFeat_convexity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_convexity).getCode();

 
    casFeat_shift = jcas.getRequiredFeatureDE(casType, "shift", "uima.cas.Integer", featOkTst);
    casFeatCode_shift  = (null == casFeat_shift) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_shift).getCode();

  }
}



    