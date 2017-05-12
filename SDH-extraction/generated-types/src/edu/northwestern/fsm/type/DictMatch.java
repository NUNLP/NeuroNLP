

/* First created by JCasGen Fri May 12 08:41:03 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Dictionary match annotation
 * Updated by JCasGen Fri May 12 08:41:03 CDT 2017
 * XML source: /Users/willthompson/Code/northwestern/NeuroNLP/SDH-extraction/generated-types/resources/edu/northwestern/fsm/type/dkpro-types.xml
 * @generated */
public class DictMatch extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DictMatch.class);
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
  protected DictMatch() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DictMatch(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DictMatch(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public DictMatch(JCas jcas, int begin, int end) {
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
  //* Feature: code

  /** getter for code - gets Code
   * @generated
   * @return value of the feature 
   */
  public String getCode() {
    if (DictMatch_Type.featOkTst && ((DictMatch_Type)jcasType).casFeat_code == null)
      jcasType.jcas.throwFeatMissing("code", "edu.northwestern.fsm.type.DictMatch");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DictMatch_Type)jcasType).casFeatCode_code);}
    
  /** setter for code - sets Code 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCode(String v) {
    if (DictMatch_Type.featOkTst && ((DictMatch_Type)jcasType).casFeat_code == null)
      jcasType.jcas.throwFeatMissing("code", "edu.northwestern.fsm.type.DictMatch");
    jcasType.ll_cas.ll_setStringValue(addr, ((DictMatch_Type)jcasType).casFeatCode_code, v);}    
  }

    