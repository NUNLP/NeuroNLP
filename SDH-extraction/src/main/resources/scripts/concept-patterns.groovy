import clinicalnlp.pattern.AnnotationPattern
import clinicalnlp.pattern.AnnotationRegex
import edu.northwestern.fsm.type.SDH
import edu.northwestern.fsm.type.Side

import static clinicalnlp.pattern.AnnotationPattern.get$A
import static clinicalnlp.pattern.AnnotationPattern.get$N
import static edu.northwestern.fsm.domain.SDHConcept.*

//---------------------------------------------------------------------------------------------------------------------
// base patterns
//---------------------------------------------------------------------------------------------------------------------

def subdural$hemotoma = '''
(extra-?axial|subdural)(/subarachnoid)?\\s+(hematomas?|hemorrhages?|layerings?|collections?|blood|overlying)
'''

def left$side = '''
(left\\s+frontal\\s+lobe)|
(left\\s+frontotemporoparietal)|
(left)
'''

def right$side = '''
(right\\s+frontal\\s+lobe)|
(right)
'''

sdh$patterns = [
    (~"(?ixs)\\b(?:${subdural$hemotoma})\\b")   : SUBDURAL_HEMATOMA.map
]

side$patterns = [
    (~"(?ixs)\\b(?:${left$side})\\b")           : LEFT.map,
    (~"(?ixs)\\b(?:${right$side})\\b")          : RIGHT.map
]

//---------------------------------------------------------------------------------------------------------------------
// second order patterns
//---------------------------------------------------------------------------------------------------------------------

AnnotationRegex sdh$side$relation$1 = new AnnotationRegex((AnnotationPattern)
    $N('sdh', $A(SDH)) & $N('side', $A(Side))
)

AnnotationRegex sdh$side$relation$2 = new AnnotationRegex((AnnotationPattern)
    $N('side', $A(Side)) & $N('sdh', $A(SDH))
)


// ---------------------------------------------------------------------------------------------------------------------
// binding variable map
// ---------------------------------------------------------------------------------------------------------------------
[
    sdh$patterns:sdh$patterns,
    side$patterns:side$patterns,
    sdh$side$relation$1:sdh$side$relation$1,
    sdh$side$relation$2:sdh$side$relation$2
]