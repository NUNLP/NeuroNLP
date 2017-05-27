

/* First created by JCasGen Sat May 27 14:22:23 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;


/** Measure annotation
 * Updated by JCasGen Sat May 27 14:22:23 CDT 2017
 * XML source: /Users/willthompson/Code/northwestern/NeuroNLP/SDH-extraction/generated-types/resources/edu/northwestern/fsm/type/sdh-types.xml
 * @generated */
public class MidlineShift extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MidlineShift.class);
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
  protected MidlineShift() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MidlineShift(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MidlineShift(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MidlineShift(JCas jcas, int begin, int end) {
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
  //* Feature: distance

  /** getter for distance - gets 
   * @generated
   * @return value of the feature 
   */
  public Measure getDistance() {
    if (MidlineShift_Type.featOkTst && ((MidlineShift_Type)jcasType).casFeat_distance == null)
      jcasType.jcas.throwFeatMissing("distance", "edu.northwestern.fsm.type.MidlineShift");
    return (Measure)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MidlineShift_Type)jcasType).casFeatCode_distance)));}
    
  /** setter for distance - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDistance(Measure v) {
    if (MidlineShift_Type.featOkTst && ((MidlineShift_Type)jcasType).casFeat_distance == null)
      jcasType.jcas.throwFeatMissing("distance", "edu.northwestern.fsm.type.MidlineShift");
    jcasType.ll_cas.ll_setRefValue(addr, ((MidlineShift_Type)jcasType).casFeatCode_distance, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    