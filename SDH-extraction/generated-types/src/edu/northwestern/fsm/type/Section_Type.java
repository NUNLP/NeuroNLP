
/* First created by JCasGen Wed May 24 21:04:28 CDT 2017 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Div_Type;

/** Section annotation
 * Updated by JCasGen Wed May 24 21:04:28 CDT 2017
 * @generated */
public class Section_Type extends Div_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Section.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.fsm.type.Section");
 
  /** @generated */
  final Feature casFeat_divType;
  /** @generated */
  final int     casFeatCode_divType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDivType(int addr) {
        if (featOkTst && casFeat_divType == null)
      jcas.throwFeatMissing("divType", "edu.northwestern.fsm.type.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_divType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDivType(int addr, String v) {
        if (featOkTst && casFeat_divType == null)
      jcas.throwFeatMissing("divType", "edu.northwestern.fsm.type.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_divType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Section_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_divType = jcas.getRequiredFeatureDE(casType, "divType", "uima.cas.String", featOkTst);
    casFeatCode_divType  = (null == casFeat_divType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_divType).getCode();

  }
}



    