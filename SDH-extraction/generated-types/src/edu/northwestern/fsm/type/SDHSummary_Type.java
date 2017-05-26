
/* First created by JCasGen Fri May 26 16:27:41 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData_Type;

/** SDH summary
 * Updated by JCasGen Fri May 26 16:27:41 CDT 2017
 * @generated */
public class SDHSummary_Type extends DocumentMetaData_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SDHSummary.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.fsm.type.SDHSummary");
 
  /** @generated */
  final Feature casFeat_side;
  /** @generated */
  final int     casFeatCode_side;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSide(int addr) {
        if (featOkTst && casFeat_side == null)
      jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDHSummary");
    return ll_cas.ll_getIntValue(addr, casFeatCode_side);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSide(int addr, int v) {
        if (featOkTst && casFeat_side == null)
      jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDHSummary");
    ll_cas.ll_setIntValue(addr, casFeatCode_side, v);}
    
  
 
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
      jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDHSummary");
    return ll_cas.ll_getIntValue(addr, casFeatCode_thickness);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setThickness(int addr, int v) {
        if (featOkTst && casFeat_thickness == null)
      jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDHSummary");
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
      jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDHSummary");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_convexity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConvexity(int addr, boolean v) {
        if (featOkTst && casFeat_convexity == null)
      jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDHSummary");
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
      jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDHSummary");
    return ll_cas.ll_getIntValue(addr, casFeatCode_shift);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setShift(int addr, int v) {
        if (featOkTst && casFeat_shift == null)
      jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDHSummary");
    ll_cas.ll_setIntValue(addr, casFeatCode_shift, v);}
    
  
 
  /** @generated */
  final Feature casFeat_count;
  /** @generated */
  final int     casFeatCode_count;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCount(int addr) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "edu.northwestern.fsm.type.SDHSummary");
    return ll_cas.ll_getIntValue(addr, casFeatCode_count);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCount(int addr, int v) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "edu.northwestern.fsm.type.SDHSummary");
    ll_cas.ll_setIntValue(addr, casFeatCode_count, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SDHSummary_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_side = jcas.getRequiredFeatureDE(casType, "side", "uima.cas.Integer", featOkTst);
    casFeatCode_side  = (null == casFeat_side) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_side).getCode();

 
    casFeat_thickness = jcas.getRequiredFeatureDE(casType, "thickness", "uima.cas.Integer", featOkTst);
    casFeatCode_thickness  = (null == casFeat_thickness) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_thickness).getCode();

 
    casFeat_convexity = jcas.getRequiredFeatureDE(casType, "convexity", "uima.cas.Boolean", featOkTst);
    casFeatCode_convexity  = (null == casFeat_convexity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_convexity).getCode();

 
    casFeat_shift = jcas.getRequiredFeatureDE(casType, "shift", "uima.cas.Integer", featOkTst);
    casFeatCode_shift  = (null == casFeat_shift) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_shift).getCode();

 
    casFeat_count = jcas.getRequiredFeatureDE(casType, "count", "uima.cas.Integer", featOkTst);
    casFeatCode_count  = (null == casFeat_count) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_count).getCode();

  }
}



    