import edu.northwestern.fsm.type.SDH
import org.apache.uima.jcas.tcas.DocumentAnnotation

import static edu.northwestern.fsm.domain.SDHConcept.getSUBDURAL_HEMATOMA
import static edu.northwestern.fsm.logic.Functions.createConceptMentions

//---------------------------------------------------------------------------------------------------------------------
// create mention annotations from diagnosis patterns
//---------------------------------------------------------------------------------------------------------------------
createConceptMentions(
    patterns:patterns,
    jcas:jcas,
    searchSet:jcas.select(type:DocumentAnnotation),
    type:SDH,
    identifier:SUBDURAL_HEMATOMA.code
)