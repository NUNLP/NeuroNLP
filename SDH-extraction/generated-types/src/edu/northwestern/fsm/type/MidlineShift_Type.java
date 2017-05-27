
/* First created by JCasGen Sat May 27 14:22:23 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity_Type;

/** Measure annotation
 * Updated by JCasGen Sat May 27 14:22:23 CDT 2017
 * @generated */
public class MidlineShift_Type extends NamedEntity_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MidlineShift.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.fsm.type.MidlineShift");
 
  /** @generated */
  final Feature casFeat_distance;
  /** @generated */
  final int     casFeatCode_distance;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDistance(int addr) {
        if (featOkTst && casFeat_distance == null)
      jcas.throwFeatMissing("distance", "edu.northwestern.fsm.type.MidlineShift");
    return ll_cas.ll_getRefValue(addr, casFeatCode_distance);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDistance(int addr, int v) {
        if (featOkTst && casFeat_distance == null)
      jcas.throwFeatMissing("distance", "edu.northwestern.fsm.type.MidlineShift");
    ll_cas.ll_setRefValue(addr, casFeatCode_distance, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MidlineShift_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_distance = jcas.getRequiredFeatureDE(casType, "distance", "edu.northwestern.fsm.type.Measure", featOkTst);
    casFeatCode_distance  = (null == casFeat_distance) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_distance).getCode();

  }
}



    