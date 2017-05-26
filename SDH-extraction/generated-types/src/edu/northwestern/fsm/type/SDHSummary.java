

/* First created by JCasGen Fri May 26 16:27:41 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;


/** SDH summary
 * Updated by JCasGen Fri May 26 16:27:41 CDT 2017
 * XML source: /Users/willthompson/Code/northwestern/NeuroNLP/SDH-extraction/generated-types/resources/edu/northwestern/fsm/type/sdh-types.xml
 * @generated */
public class SDHSummary extends DocumentMetaData {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SDHSummary.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SDHSummary() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SDHSummary(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SDHSummary(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SDHSummary(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: side

  /** getter for side - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSide() {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_side == null)
      jcasType.jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDHSummary");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_side);}
    
  /** setter for side - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSide(int v) {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_side == null)
      jcasType.jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDHSummary");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_side, v);}    
   
    
  //*--------------*
  //* Feature: thickness

  /** getter for thickness - gets 
   * @generated
   * @return value of the feature 
   */
  public int getThickness() {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_thickness == null)
      jcasType.jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDHSummary");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_thickness);}
    
  /** setter for thickness - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setThickness(int v) {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_thickness == null)
      jcasType.jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDHSummary");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_thickness, v);}    
   
    
  //*--------------*
  //* Feature: convexity

  /** getter for convexity - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getConvexity() {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_convexity == null)
      jcasType.jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDHSummary");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_convexity);}
    
  /** setter for convexity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setConvexity(boolean v) {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_convexity == null)
      jcasType.jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDHSummary");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_convexity, v);}    
   
    
  //*--------------*
  //* Feature: shift

  /** getter for shift - gets 
   * @generated
   * @return value of the feature 
   */
  public int getShift() {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_shift == null)
      jcasType.jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDHSummary");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_shift);}
    
  /** setter for shift - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setShift(int v) {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_shift == null)
      jcasType.jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDHSummary");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_shift, v);}    
   
    
  //*--------------*
  //* Feature: count

  /** getter for count - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCount() {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "edu.northwestern.fsm.type.SDHSummary");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_count);}
    
  /** setter for count - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCount(int v) {
    if (SDHSummary_Type.featOkTst && ((SDHSummary_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "edu.northwestern.fsm.type.SDHSummary");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDHSummary_Type)jcasType).casFeatCode_count, v);}    
  }

    