

/* First created by JCasGen Thu May 25 21:49:31 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;


/** SDH annotation
 * Updated by JCasGen Thu May 25 21:49:31 CDT 2017
 * XML source: /Users/willthompson/Code/northwestern/NeuroNLP/SDH-extraction/generated-types/resources/edu/northwestern/fsm/type/dkpro-types.xml
 * @generated */
public class SDH extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SDH.class);
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
  protected SDH() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SDH(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SDH(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SDH(JCas jcas, int begin, int end) {
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
  public Side getSide() {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_side == null)
      jcasType.jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDH");
    return (Side)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SDH_Type)jcasType).casFeatCode_side)));}
    
  /** setter for side - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSide(Side v) {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_side == null)
      jcasType.jcas.throwFeatMissing("side", "edu.northwestern.fsm.type.SDH");
    jcasType.ll_cas.ll_setRefValue(addr, ((SDH_Type)jcasType).casFeatCode_side, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: thickness

  /** getter for thickness - gets 
   * @generated
   * @return value of the feature 
   */
  public int getThickness() {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_thickness == null)
      jcasType.jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDH");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDH_Type)jcasType).casFeatCode_thickness);}
    
  /** setter for thickness - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setThickness(int v) {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_thickness == null)
      jcasType.jcas.throwFeatMissing("thickness", "edu.northwestern.fsm.type.SDH");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDH_Type)jcasType).casFeatCode_thickness, v);}    
   
    
  //*--------------*
  //* Feature: convexity

  /** getter for convexity - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getConvexity() {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_convexity == null)
      jcasType.jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDH");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SDH_Type)jcasType).casFeatCode_convexity);}
    
  /** setter for convexity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setConvexity(boolean v) {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_convexity == null)
      jcasType.jcas.throwFeatMissing("convexity", "edu.northwestern.fsm.type.SDH");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SDH_Type)jcasType).casFeatCode_convexity, v);}    
   
    
  //*--------------*
  //* Feature: shift

  /** getter for shift - gets 
   * @generated
   * @return value of the feature 
   */
  public int getShift() {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_shift == null)
      jcasType.jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDH");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SDH_Type)jcasType).casFeatCode_shift);}
    
  /** setter for shift - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setShift(int v) {
    if (SDH_Type.featOkTst && ((SDH_Type)jcasType).casFeat_shift == null)
      jcasType.jcas.throwFeatMissing("shift", "edu.northwestern.fsm.type.SDH");
    jcasType.ll_cas.ll_setIntValue(addr, ((SDH_Type)jcasType).casFeatCode_shift, v);}    
  }

    